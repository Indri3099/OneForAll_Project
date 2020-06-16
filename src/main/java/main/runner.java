package main;

import entities.GenericGame;
import entities.TryToStudy;

public class runner {
    public static void main(String[] args) {
        GenericGame game = new TryToStudy();

        game.init();

    }
}
