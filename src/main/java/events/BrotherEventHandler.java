package events;

import games.GenericGame;
import main.userInterface.Printer;

public class BrotherEventHandler extends EventHandler {

    public BrotherEventHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        if (!event.isCompleted() && event.getCharacter().getInventory().containsAll(event.getRequiredObjects())) {
            out.println("\n" + event.getCharacter() + ": \" "+ event.getEndPhrase() + "\"");
            event.getCharacter().setSentence("Adesso con le cuffie non ti disturbo più");
            event.setCompleted(true);
            pointsUpdate(game,out);
            event.setCompleted(true);
        }
    }


}