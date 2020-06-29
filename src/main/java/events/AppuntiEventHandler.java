package events;

import games.GenericGame;
import main.userInterface.printer.Printer;

public class AppuntiEventHandler extends EventHandler{
    public AppuntiEventHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        if(game.getMainCharacter().getInventory().containsAll(event.getRequiredObjects())){
            out.println("\n" + event.getEndPhrase());
            pointsUpdate(game,out);
            event.setCompleted(true);
        }
    }
}
