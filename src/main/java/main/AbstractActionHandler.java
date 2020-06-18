package main;

import entities.Room;
import games.GenericGame;
import parser.PhraseReduction;

public abstract class AbstractActionHandler {

    GenericGame game;

    public AbstractActionHandler(GenericGame game) {
        this.game = game;
    }

    public abstract void handle(PhraseReduction action) throws Exception;

}
