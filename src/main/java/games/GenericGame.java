package games;

import entities.Room;
import entities.character.MainCharacter;
import entities.command.Command;
import main.AbstractActionHandler;
import parser.PhraseReduction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericGame implements Serializable {

    private AbstractActionHandler handler;

    private Room currentRoom;

    private MainCharacter mainCharacter;

    private List<Command> commandList = new ArrayList<>();

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public abstract void init();

    public abstract void actionHandle(PhraseReduction action);

    public void setHandler(AbstractActionHandler handler) {
        this.handler = handler;
    }

    public AbstractActionHandler getHandler() {
        return handler;
    }
}
