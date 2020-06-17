package events;

import games.GenericGame;
import parser.PhraseReduction;

public interface Event {

    public void handleEvent(PhraseReduction action, GenericGame game) throws Exception;
}
