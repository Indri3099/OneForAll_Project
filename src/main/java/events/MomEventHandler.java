package events;

import games.GenericGame;
import userInterface.printer.Printer;

public class MomEventHandler extends EventHandler{
    public MomEventHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        if (!getEvent().isCompleted() && getEvent().getCharacter().getInventory().getList().containsAll(getEvent().getRequiredObjects())) {
            out.println("\n" + getEvent().getCharacter() + ": \""+ getEvent().getEndPhrase() + "\"");
            getEvent().getCharacter().setSentence("Con la pancia piena sicuramente studierai meglio");
            getEvent().setCompleted(true);
            pointsUpdate(game,out);
        }
    }
}
