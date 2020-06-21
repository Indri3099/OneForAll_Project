package parser;

import entities.character.Character;
import entities.character.Character;
import entities.command.Command;
import entities.objects.StdObject;

public class PhraseReduction {

    private Command command = null;

    private StdObject myObject = null;

    private StdObject toObject = null;

    private Character toCharacter = null;

    public PhraseReduction(Command command, StdObject myObject, StdObject toObject) {
        this.command = command;
        this.myObject = myObject;
        this.toObject = toObject;
    }

    public PhraseReduction(Command command, Character toCharacter) {
        this.command = command;
        this.toCharacter = toCharacter;
    }

    public PhraseReduction(Command command) {
        this.command = command;
    }

    public PhraseReduction(){}

    public PhraseReduction(Command command, StdObject myObject, Character toCharacter) {
        this.command = command;
        this.myObject = myObject;
        this.toCharacter = toCharacter;
    }

    public PhraseReduction(Command command, StdObject toObject) {
        this.command = command;
        this.toObject = toObject;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public StdObject getMyObject() {
        return myObject;
    }

    public void setMyObject(StdObject myObject) {
        this.myObject = myObject;
    }

    public StdObject getToObject() {
        return toObject;
    }

    public void setToObject(StdObject toObject) {
        this.toObject = toObject;
    }

    public Character getToCharacter() {
        return toCharacter;
    }

    public void setToCharacter(Character toCharacter) {
        this.toCharacter = toCharacter;
    }

    @Override
    public String toString() {
        return "PhraseReduction{" +
                "command=" + command +
                ", myObject=" + myObject +
                ", toObject=" + toObject +
                ", toCharacter=" + toCharacter +
                '}';
    }
}
