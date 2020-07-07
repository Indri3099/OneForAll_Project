package events;

import games.GenericGame;
import userInterface.printer.Printer;

public class AppuntiEventHandler extends EventHandler{
    public AppuntiEventHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        if(game.getMainCharacter().getInventory().getList().containsAll(getEvent().getRequiredObjects())){
            out.println("\n" + getEvent().getEndPhrase());
            pointsUpdate(game,out);
            getEvent().setCompleted(true);
        }
    }
}
