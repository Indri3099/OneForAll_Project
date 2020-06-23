package main;

import entities.Room;
import entities.character.Character;
import entities.character.NPC;
import entities.objects.StdObject;
import exceptions.*;
import games.GenericGame;
import entities.command.CommandType;
import main.userInterface.Printer;
import parser.PhraseReduction;

import java.util.List;

public class TTSActionHandler extends AbstractActionHandler {

    private Printer out;

    public TTSActionHandler(GenericGame game, Printer out) {
        super(game);
        this.out = out;
    }

    @Override
    public void handle(PhraseReduction action) throws Exception {
        if (action.getCommand().getType() == CommandType.NORD) {
            move(game.getCurrentRoom().getNorth());
        } else if (action.getCommand().getType() == CommandType.SOUTH) {
            move(game.getCurrentRoom().getSouth());
        } else if (action.getCommand().getType() == CommandType.EAST) {
            move(game.getCurrentRoom().getEast());
        } else if (action.getCommand().getType() == CommandType.WEST) {
            move(game.getCurrentRoom().getWest());
        } else if (action.getCommand().getType() == CommandType.PICK_UP) {
            if (action.getMyObject() != null)
                throw new ObjectOwnedException();
            take(action.getToObject());
        } else if (action.getCommand().getType() == CommandType.INVENTORY) {
            inventory(game.getMainCharacter().getInventory());
        } else if (action.getCommand().getType() == CommandType.LOOK_AT) {
            if (action.getToObject() != null)
                lookAt(action.getToObject());
            else
                lookAt(action.getMyObject());
        } else if (action.getCommand().getType() == CommandType.GIVE) {
            give(action.getMyObject(), (NPC) action.getToCharacter());
        } else if (action.getCommand().getType() == CommandType.TALK_TO) {
            talkTo((NPC) action.getToCharacter());
        } else {
            throw new CommandNotValidException();
        }
    }

    public void move(Room destination) throws Exception {
        if (destination != null && !destination.isLocked()) {
            out.println(destination + " : " + destination.getDescription());
            game.setCurrentRoom(destination);
            if (destination.getEventHandler() != null && !destination.getEventHandler().getEvent().isStarted()) {
                destination.getEventHandler().startEvent(out);
            }
        } else if (destination == null) {
            throw new OutOfMapException();
        } else if (destination.isLocked()) {
            throw new LockedException();
        }
    }

    public void inventory(List<StdObject> inventory) throws Exception {
        StringBuilder strInventory = new StringBuilder("Ecco il tuo inventario: ");
        if (game.getMainCharacter().getInventory().isEmpty()) {
            throw new EmptyInvException();
        } else {
            for (StdObject object : game.getMainCharacter().getInventory()) {
                strInventory.append(" - " + object + "\n");
            }
            out.println(strInventory.toString());
        }
    }

    public void lookAt(StdObject toObject) throws Exception {
        String look;
        if (toObject == null && game.getCurrentRoom().getLook() != null) {
            look = game.getCurrentRoom() + " : " + game.getCurrentRoom().getLook();
            out.println(look);
        } else if (toObject != null && toObject.getDescription() != null) {
            look = toObject + " : " + toObject.getDescription();
            out.println(look);
        } else {
            throw new NoDescriptionException();
        }
    }

    public void take(StdObject toObject) throws Exception {
        if (toObject == null) {
            throw new ObjectNotFoundException();
        } else if (toObject.isTakeable() && toObject.isVisible()) {
            game.getMainCharacter().getInventory().add(toObject);
            game.getCurrentRoom().getObjects().remove(toObject);
            out.println("Hai raccolto " + toObject);
        } else {
            throw new UnTakeableException();
        }
    }

    public void give(StdObject myObject, NPC toCharacter) throws Exception {
        if (myObject == null || toCharacter == null)
            throw new CommandNotValidException();
        else {
            out.println(toCharacter.getAccepted().toString());
            if (toCharacter.getAccepted().contains(myObject)) {
                out.println("Hai dato " + myObject + " a " + toCharacter);
                toCharacter.getInventory().add(myObject);
                game.getMainCharacter().getInventory().remove(myObject);
            }
        }
    }

    public void talkTo(NPC toCharacter) throws Exception {
        if (toCharacter.getSentences().get(toCharacter.getSentenceIndex()) != null) {
            out.println(toCharacter + ": " + toCharacter.getSentences().get(toCharacter.getSentenceIndex()));
        } else {
            out.println(toCharacter + ": " + "non ho nulla da dirti");
        }
    }
}
