package entities;

import entities.character.MainCharacter;
import entities.command.Command;

import java.util.List;

public abstract class GenericGame {

    private Room currentRoom;

    private MainCharacter mainCharacter;

    private List<Command> commandList;

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

}
