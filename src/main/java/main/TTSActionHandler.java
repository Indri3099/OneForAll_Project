package main;

import events.InventoryEvent;
import events.TakeEvent;
import exceptions.CommandNotValidException;
import games.GenericGame;
import entities.command.CommandType;
import events.Event;
import events.MoveEvent;
import parser.PhraseReduction;

import java.io.PrintStream;

public class TTSActionHandler extends AbstractActionHandler {

    private PrintStream out;

    public TTSActionHandler(GenericGame game, PrintStream out) {
        super(game);
        this.out = out;
    }

    @Override
    public void handle(PhraseReduction action) throws Exception {
        if (action.getCommand().getType() == CommandType.NORD) {
            Event move = new MoveEvent(game.getCurrentRoom().getNorth());
            move.handleEvent(action, game);
        } else if (action.getCommand().getType() == CommandType.SOUTH) {
            Event move = new MoveEvent(game.getCurrentRoom().getSouth());
            move.handleEvent(action, game);
        } else if (action.getCommand().getType() == CommandType.EAST) {
            Event move = new MoveEvent(game.getCurrentRoom().getEast());
            move.handleEvent(action, game);
        } else if (action.getCommand().getType() == CommandType.WEST) {
            Event move = new MoveEvent(game.getCurrentRoom().getWest());
            move.handleEvent(action, game);
        } else if (action.getCommand().getType() == CommandType.PICK_UP) {
            TakeEvent take = new TakeEvent();
            take.handleEvent(action, game);
            out.println("Hai raccolto : " + take.getObject());
        } else if (action.getCommand().getType() == CommandType.INVENTORY) {
            InventoryEvent inventory = new InventoryEvent();
            inventory.handleEvent(action, game);
            out.println(inventory.getInventoryList());
        } else {
            throw new CommandNotValidException();
        }
    }
}
