package games;

import entities.Room;
import entities.character.Character;
import entities.command.Command;
import main.userInterface.printer.Printer;
import parser.PhraseReduction;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericGame implements Serializable {

    /**
     * Punteggio massimo a cui è possibile arrivare per completare il gioco
     */
    private int pointGoal;

    private Time actualTime;

    /**
     * Tempo massimo per terminare la partita espresso in minuti (almeno 1)- Vale (-1) se non c'è limite di tempo;
     */
    private Time totalTime;

    /**
     * Attraverso questo oggetto sarà possibile visualizzare come il gioco risponde ai comandi in input
     * <br>Per maggiori informazioni guarda l'interfaccia Printer
     */
    protected transient Printer out;

    private boolean completed = false;

    private String name;

    /**
     * Messaggio di introduzione del gioco.
     */
    private String intro;

    /**
     * Messaggio visualizzato in caso di vittoria
     */
    private String win;

    /**
     * Messaggio visualizzato in caso di sconfitta
     */
    private String lose;

    private int actualPoints = 0;

    private Room currentRoom;

    /**
     * Protagonista del gioco
     */
    private Character mainCharacter;

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

    public Character getMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(Character mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
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

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public int getPointGoal() {
        return pointGoal;
    }

    public void setPointGoal(int pointGoal) {
        this.pointGoal = pointGoal;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

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

    public abstract void actionHandler(PhraseReduction action) throws Exception;

}
