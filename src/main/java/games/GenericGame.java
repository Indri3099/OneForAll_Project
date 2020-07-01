package games;

import entities.Room;
import entities.character.MainCharacter;
import entities.command.Command;
import main.userInterface.printer.Printer;
import parser.PhraseReduction;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericGame implements Serializable {

    private int POINTGOAL;

    /**
     * Tempo massimo per terminare la partita espresso in minuti (almeno 1)- Vale (-1) se non c'è limite di tempo;
     */
    private Time actualTime;

    private Time totalTime;

    protected transient Printer out;

    private boolean completed = false;

    private String defaultPath;

    private String name;

    private String intro;

    private String win;

    private String lose;

    private int actualPoints = 0;

    private Room currentRoom;

    private MainCharacter mainCharacter;

    private List<Command> commandList = new ArrayList<>();

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }


    public int getPOINTGOAL() {
        return POINTGOAL;
    }

    public void setPOINTGOAL(int POINTGOAL) {
        this.POINTGOAL = POINTGOAL;
    }

    public Time getActualTime() {
        return actualTime;
    }

    public void setActualTime(Time actualTime) {
        this.actualTime = actualTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Printer getOut() {
        return out;
    }

    public void setOut(Printer out) {
        this.out = out;
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public abstract void actionHandler(PhraseReduction action) throws Exception;

    public Time getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getLose() {
        return lose;
    }
}
