package events;

import games.GenericGame;

import java.io.PrintStream;

public class BrotherEventHandler extends EventHandler {

    public BrotherEventHandler(Event event) {
        super(event);
    }
                //TODO : la prossima volta devi fare in modo che gli eventi si avviano entrando in una stanza
    @Override
    public void completeEvent(GenericGame game, PrintStream out) {
        if (event.getCharacter().getInventory().containsAll(event.getRequiredObjects())) {
            out.println("E va bene, metto le cuffie e non do fastidio");
            event.getCharacter().setSentenceIndex(event.getCharacter().getSentenceIndex() + 1);
            event.setCompleted(true);
        }
    }


}