package events;

import games.GenericGame;
import main.userInterface.printer.Printer;

public class MomEventHandler extends EventHandler{
    public MomEventHandler(Event event) {
        super(event);
    }

    @Override
    public void completeEvent(GenericGame game, Printer out) {
        if (!event.isCompleted() && event.getCharacter().getInventory().getList().containsAll(event.getRequiredObjects())) {
            out.println("\n" + event.getCharacter() + ": \" "+ event.getEndPhrase() + "\"");
            event.getCharacter().setSentence("Con la pancia piena sicuramente studierai meglio");
            event.setCompleted(true);
            pointsUpdate(game,out);
        }
    }
}
