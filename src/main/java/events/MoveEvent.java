package events;

import games.GenericGame;
import entities.Room;
import exceptions.LockedException;
import exceptions.OutOfMapException;
import parser.PhraseReduction;

public class MoveEvent implements Event {

    private Room destination;

    public MoveEvent(Room destination){
        this.destination = destination;
    }

    public void handleEvent(PhraseReduction action, GenericGame game) throws Exception {
        if(destination != null && !destination.isLocked()){
            game.setCurrentRoom(destination);
        }
        else if(destination == null){
            throw new OutOfMapException();
        }
        else if(destination.isLocked()){
            throw new LockedException();
        }
    }
}
