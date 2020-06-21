package events;

import games.GenericGame;
import games.TryToStudy;

import java.io.PrintStream;

public class BrotherEventHandler extends EventHandler {

    public BrotherEventHandler(Event event) {
        super(event);
    }
                //TODO : la prossima volta devi fare in modo che gli eventi si avviano entrando in una stanza
    @Override
    public void completeEvent(GenericGame game, PrintStream out) {
        if (!event.isCompleted() && event.getCharacter().getInventory().containsAll(event.getRequiredObjects())) {
            out.println(event.getCharacter() + ": \"E va bene, metto le cuffie e non do fastidio\"");
            if(event.getCharacter().getSentences().size() != event.getCharacter().getSentenceIndex() + 1) {
                event.getCharacter().setSentenceIndex(event.getCharacter().getSentenceIndex() + 1);
            }
            event.setCompleted(true);
            game.setActualPoints(game.getActualPoints() + event.getPointReward());
            out.println("Punti guadagnati : " + event.getPointReward());
            out.println("Punti attuali : " + game.getActualPoints());
        }
    }


}