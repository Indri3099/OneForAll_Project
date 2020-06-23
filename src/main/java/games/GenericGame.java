package games;

import entities.Room;
import entities.character.MainCharacter;
import entities.command.Command;
import main.AbstractActionHandler;
import main.userInterface.Printer;
import parser.PhraseReduction;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public abstract class GenericGame implements Serializable {

    protected int POINTGOAL;

    /**
     * Tempo massimo per terminare la partita espresso in minuti (almeno 1)- Vale (-1) se non c'è limite di tempo;
     */

    private Time time;

    private String name;

    private String intro;

    private String ending;

    private int actualPoints = 0;

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

    public int getActualPoints() {
        return actualPoints;
    }

    public void setActualPoints(int actualPoints) {
        this.actualPoints = actualPoints;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }


    public int getPOINTGOAL() {
        return POINTGOAL;
    }

    public void setPOINTGOAL(int POINTGOAL) {
        this.POINTGOAL = POINTGOAL;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
