package games;

import entities.Room;
import entities.character.MainCharacter;
import entities.character.NPC;
import entities.command.Command;
import entities.command.CommandType;
import entities.objects.StdObject;
import exceptions.*;
import main.userInterface.Printer;
import parser.PhraseReduction;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericGame implements Serializable {

    protected int POINTGOAL;

    /**
     * Tempo massimo per terminare la partita espresso in minuti (almeno 1)- Vale (-1) se non c'è limite di tempo;
     */
    private Printer out;

    private Time time;

    private String name;

    private String intro;

    private String ending;

    private int actualPoints = 0;

//    private AbstractActionHandler handler;

    private Room currentRoom;

    private MainCharacter mainCharacter;

    private List<Command> commandList = new ArrayList<>();

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public abstract void init();

//    public void setHandler(AbstractActionHandler handler) {
//        this.handler = handler;
//    }
//
//    public AbstractActionHandler getHandler() {
//        return handler;
//    }

    public int getActualPoints() {
        return actualPoints;
    }

    public void setActualPoints(int actualPoints) {
        this.actualPoints = actualPoints;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }


    public int getPOINTGOAL() {
        return POINTGOAL;
    }

    public void setPOINTGOAL(int POINTGOAL) {
        this.POINTGOAL = POINTGOAL;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Printer getOut() {
        return out;
    }

    public void setOut(Printer out) {
        this.out = out;
    }

    public void actionHandler(PhraseReduction action) throws Exception {
        if (action.getCommand().getType() == CommandType.NORD) {
            move(getCurrentRoom().getNorth());
        } else if (action.getCommand().getType() == CommandType.SOUTH) {
            move(getCurrentRoom().getSouth());
        } else if (action.getCommand().getType() == CommandType.EAST) {
            move(getCurrentRoom().getEast());
        } else if (action.getCommand().getType() == CommandType.WEST) {
            move(getCurrentRoom().getWest());
        } else if (action.getCommand().getType() == CommandType.PICK_UP) {
            if (action.getMyObject() != null)
                throw new ObjectOwnedException();
            take(action.getToObject());
        } else if (action.getCommand().getType() == CommandType.INVENTORY) {
            inventory();
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
            out.print(destination + " : " + destination.getDescription());
            setCurrentRoom(destination);
            if (destination.getEventHandler() != null && !destination.getEventHandler().getEvent().isStarted()) {
                destination.getEventHandler().startEvent(out);
            }
        } else if (destination == null) {
            throw new OutOfMapException();
        } else if (destination.isLocked()) {
            throw new LockedException();
        }
    }

    public void inventory() throws Exception {
        StringBuilder strInventory = new StringBuilder("Ecco il tuo inventario: ");
        if (getMainCharacter().getInventory().isEmpty()) {
            throw new EmptyInvException();
        } else {
            for (StdObject object : getMainCharacter().getInventory()) {
                strInventory.append(" - " + object + "\n");
            }
            out.print(strInventory.toString());
        }
    }

    public void lookAt(StdObject toObject) throws Exception {
        String look;
        if (toObject == null && getCurrentRoom().getLook() != null) {
            look = getCurrentRoom() + " : " + getCurrentRoom().getLook();
            out.print(look);
        } else if (toObject != null && toObject.getDescription() != null) {
            look = toObject + " : " + toObject.getDescription();
            out.print(look);
        } else {
            throw new NoDescriptionException();
        }
    }

    public void take(StdObject toObject) throws Exception {
        if (toObject == null) {
            throw new ObjectNotFoundException();
        } else if (toObject.isTakeable() && toObject.isVisible()) {
            getMainCharacter().getInventory().add(toObject);
            getCurrentRoom().getObjects().remove(toObject);
            out.print("Hai raccolto " + toObject);
        } else {
            throw new UnTakeableException();
        }
    }

    public void give(StdObject myObject, NPC toCharacter) throws Exception {
        if (myObject == null || toCharacter == null)
            throw new CommandNotValidException();
        else {
            if (toCharacter.getAccepted().contains(myObject)) {
                out.print("Hai dato " + myObject + " a " + toCharacter);
                toCharacter.getInventory().add(myObject);
                getMainCharacter().getInventory().remove(myObject);
            }
        }
    }

    public void talkTo(NPC toCharacter) throws Exception {
        if (toCharacter.getSentences().get(toCharacter.getSentenceIndex()) != null) {
            out.print(toCharacter + ": " + toCharacter.getSentences().get(toCharacter.getSentenceIndex()));
        } else {
            out.print(toCharacter + ": " + "non ho nulla da dirti");
        }
    }
}
