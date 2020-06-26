package events;

import games.GenericGame;
import main.userInterface.Printer;

public class GiveEventHandler extends EventHandler {

    public GiveEventHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        if (!event.isCompleted() && event.getCharacter().getInventory().containsAll(event.getRequiredObjects())) {
            out.println("\n" + event.getCharacter() + ": \" "+ event.getEndPhrase() + "\"");
            if(event.getCharacter().getSentences().size() != event.getCharacter().getSentenceIndex() + 1) {
                event.getCharacter().setSentenceIndex(event.getCharacter().getSentenceIndex() + 1);
            }
            event.setCompleted(true);
            pointsUpdate(game,out);
        }
    }


}