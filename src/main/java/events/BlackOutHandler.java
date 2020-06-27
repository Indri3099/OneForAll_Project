package events;

import entities.Room;
import entities.objects.StdObject;
import games.GenericGame;
import main.userInterface.Printer;

public class BlackOutHandler extends EventHandler {
    public BlackOutHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        boolean allPushed = true;
        for (StdObject object : event.getRequiredObjects()) {
            if(!object.isPushed()){
                allPushed = false;
            }
        }
        if(allPushed){
            game.getCurrentRoom().setVisible(false);
            turnOnAll(game.getCurrentRoom());
            out.print("\n" + event.getEndPhrase());
            pointsUpdate(game,out);
            event.setCompleted(true);
        }
    }

    private void turnOnAll(Room room){
        if(room != null && !room.isVisible()){
            room.setVisible(true);
            turnOnAll(room.getEast());
            turnOnAll(room.getWest());
            turnOnAll(room.getNorth());
            turnOnAll(room.getSouth());
        }
    }
}
