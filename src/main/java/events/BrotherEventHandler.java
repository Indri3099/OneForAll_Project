package events;

import games.GenericGame;
import main.userInterface.printer.Printer;

public class BrotherEventHandler extends EventHandler {

    public BrotherEventHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        if (!getEvent().isCompleted() && getEvent().getCharacter().getInventory().getList().containsAll(getEvent().getRequiredObjects())) {
            out.println("\n" + getEvent().getCharacter() + ": \""+ getEvent().getEndPhrase() + "\"");
            getEvent().getCharacter().setSentence("Adesso con le cuffie non ti disturbo più");
            getEvent().setCompleted(true);
            pointsUpdate(game,out);
            getEvent().setCompleted(true);
        }
    }


}