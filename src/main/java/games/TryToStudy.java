package games;

import entities.Room;
import entities.character.NPC;
import entities.command.CommandType;
import entities.objects.ObjectContainer;
import entities.objects.StdObject;
import exceptions.*;
import main.AudioPlayer;
import parser.PhraseReduction;

public class TryToStudy extends GenericGame {

    /**
     * Costanti utilizzate per i nomi in fase di creazione del gioco, difatti adesso inutilizzate ma possono servire
     * quando bisogna effettuare controlli su particolari oggetti attraverso il nome
     */
    private class NameConstants {
        public static final String
                COMPUTER = "computer",
                CHITARRA = "chitarra",
                ZAINO = "zaino",
                CUFFIE = "cuffie",
                PENNA = "penna",
                DISPENSA = "dispensa",
                PASTA = "pasta",
                POMODORO = "pomodoro",
                NUTELLA = "nutella",
                CHIAVE = "chiave",
                MAC = "mac",
                APPUNTI = "appunti",
                CAMERETTA = "cameretta",
                CUCINA = "cucina",
                STANZETTA = "stanzetta",
                CAMERALETTO = "camera da letto",
                BAGNO = "bagno",
                SALONE = "salone",
                CORRIDOIO = "corridoio",
                MAMMA = "mamma",
                FRATELLO = "fratello",
                ME = "Enrico",
                BUTTON = "pulsante",
                PANNELLO = "pannello";
    }

    @Override
    public void actionHandler(PhraseReduction action) throws Exception {
        if (action.getCommand().getType() == CommandType.NORD) {
            move(getCurrentRoom().getNord());
        } else if (action.getCommand().getType() == CommandType.SUD) {
            move(getCurrentRoom().getSud());
        } else if (action.getCommand().getType() == CommandType.EST) {
            move(getCurrentRoom().getEst());
        } else if (action.getCommand().getType() == CommandType.OVEST) {
            move(getCurrentRoom().getOvest());
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
        } else if (action.getCommand().getType() == CommandType.OPEN) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    open(action.getMyObject());
                } else if (action.getToObject() != null) {
                    open(action.getToObject());
                } else {
                    throw new CommandNotValidException();
                }
            } else {
                throw new CommandNotValidException();
            }
        } else if (action.getCommand().getType() == CommandType.CLOSE) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    close(action.getMyObject());
                } else if (action.getToObject() != null) {
                    close(action.getToObject());
                } else {
                    throw new CommandNotValidException();
                }
            } else {
                throw new CommandNotValidException();
            }
        } else if (action.getCommand().getType() == CommandType.DROP) {
            drop(action.getMyObject());
        } else if (action.getCommand().getType() == CommandType.TALK_TO) {
            if(action.getToCharacter() != null)
                talkTo((NPC) action.getToCharacter());
            else
                throw new CommandNotValidException();
        } else if (action.getCommand().getType() == CommandType.PUSH) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    push(action.getMyObject());
                } else if (action.getToObject() != null) {
                    push(action.getToObject());
                } else {
                    throw new CommandNotValidException();
                }
            } else {
                throw new CommandNotValidException();
            }
        } else if (action.getCommand().getType() == CommandType.PLAY) {
            if (!(action.getMyObject() != null && action.getToObject() != null)) {
                if (action.getMyObject() != null) {
                    play(action.getMyObject());
                } else if (action.getToObject() != null) {
                    play(action.getToObject());
                } else {
                    throw new CommandNotValidException();
                }
            } else {
                throw new CommandNotValidException();
            }
        } else {
            throw new CommandNotValidException();
        }
    }

    private void move(Room destination) throws Exception {
        if (destination != null && !destination.isLocked()) {
            out.print(destination + " : " + destination.getDescription());
            setCurrentRoom(destination);
            //solo nella stanzetta non viene avviato l'evento entrando poiché si avvia solo quando si riesce ad aprire una porta del gioco.
            if (destination.getEventHandler() != null && !destination.getEventHandler().getEvent().isStarted() && !destination.getName().toLowerCase().equals(NameConstants.STANZETTA)) {
                destination.getEventHandler().startEvent(out);
            }
        } else if (destination == null) {
            throw new OutOfMapException();
        } else if (destination.isLocked()) {
            throw new LockedException();
        }
    }

    private void inventory() throws Exception {
        StringBuilder strInventory = new StringBuilder("Ecco il tuo inventario: ");
        if (getMainCharacter().getInventory().getList().isEmpty()) {
            throw new EmptyInvException();
        } else {
            for (StdObject object : getMainCharacter().getInventory().getList()) {
                strInventory.append("\n - " + object);
            }
            out.print(strInventory.toString());
        }
    }

    private void lookAt(StdObject object) throws Exception {
        String look;
        if (!getCurrentRoom().isVisible()) {
            throw new NoLightException();
        } else {
            if (object == null && getCurrentRoom().getLook() != null) {
                look = getCurrentRoom() + " : " + getCurrentRoom().getLook();
                out.print(look);
            } else if (object != null && object.getDescription() != null) {
                look = object + " : " + object.getDescription();
                out.print(look);
            } else {
                throw new NoDescriptionException();
            }
        }
    }

    private void take(StdObject object) throws Exception {
        if (object == null) {
            throw new ObjectNotFoundException();
        } else if (object.isTakeable() && object.isVisible()) {
            getMainCharacter().getInventory().add(object);
            for (StdObject object1 : getCurrentRoom().getObjects()) {
                if (object1 instanceof ObjectContainer) {
                    ((ObjectContainer) object1).getObjects().remove(object);
                }
            }
            getCurrentRoom().getObjects().remove(object);
            out.print("Hai raccolto " + object);
        } else {
            throw new UnTakeableException();
        }
    }

    private void give(StdObject myObject, NPC toCharacter) throws Exception {
        if (myObject == null || toCharacter == null)
            throw new CommandNotValidException();
        else {
            if (toCharacter.getAccepted().contains(myObject)) {
                out.print("Hai dato " + myObject + " a " + toCharacter);
                toCharacter.getInventory().add(myObject);
                getMainCharacter().getInventory().remove(myObject);
            } else{
                out.print(toCharacter + ": \"No grazie, non mi serve\"");
            }
        }
    }

    private void talkTo(NPC toCharacter) {
        if (toCharacter.getSentence() != null) {
            out.print(toCharacter + ": \"" + toCharacter.getSentence() + "\"");
        } else {
            out.print(toCharacter + ": " + "\"non ho nulla da dirti\"");
        }
    }

    private void open(StdObject object) throws Exception {
        if (object instanceof ObjectContainer) {
            if (!((ObjectContainer) object).isOpen() && !((ObjectContainer) object).isLocked()) {
                out.println("Hai aperto : " + object);
                ((ObjectContainer) object).setOpen(true);
                out.print(object + " contiene:");
                for (StdObject obj : ((ObjectContainer) object).getObjects()) {
                    out.print(" " + obj);
                    if (obj != ((ObjectContainer) object).getObjects().get(((ObjectContainer) object).getObjects().size() - 1)) {
                        out.print(",");
                    }
                }
            } else if (((ObjectContainer) object).isLocked()) {
                throw new LockedException();
            } else if (((ObjectContainer) object).isOpen()) {
                out.print(object + " è già aperto");
            }
        } else {
            throw new NotAContainerException();
        }
    }

    private void close(StdObject object) throws Exception {
        if (object instanceof ObjectContainer) {
            if (((ObjectContainer) object).isOpen() && !((ObjectContainer) object).isLocked()) {
                out.print("Hai chiuso : " + object);
                ((ObjectContainer) object).setOpen(false);
            } else if (((ObjectContainer) object).isLocked()) {
                throw new LockedException();
            } else if (!((ObjectContainer) object).isOpen()) {
                out.print(object + " è già chiuso");
            }
        } else {
            throw new NotAContainerException();
        }
    }

    private void drop(StdObject myObject) throws Exception {
        if (myObject != null) {
            getMainCharacter().getInventory().remove(myObject);
            getCurrentRoom().addObject(myObject);
            out.print("Hai lasciato : " + myObject);
        } else {
            throw new CommandNotValidException();
        }
    }

    private void play(StdObject object) throws NoSoundException {
        try {
            AudioPlayer.soundPlay(object.getSound());
            out.print("Stai suonando : " + object);
        } catch (Exception e) {
            throw new NoSoundException();
        }
    }

    private void push(StdObject object) throws NotPushableException {
        if (object.isPushable() && !object.isPushed()) {
            object.setPushed(true);
            out.print("Hai premuto: " + object);
        } else {
            throw new NotPushableException();
        }
    }
}
