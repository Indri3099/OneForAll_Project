package parser;

import entities.Room;
import entities.character.Character;
import entities.command.Command;
import entities.objects.ObjectContainer;
import entities.objects.StdObject;
import exceptions.CommandNotValidException;

import java.util.List;

public abstract class Parser {

    protected Command checkForCommand(String token, List<Command> commands) {
        for(Command command : commands){
            if (command.getName().toLowerCase().equals(token) || (command.getAlias() != null && command.getAlias().contains(token))) {
                return command;
            }
        }
        return null;
    }

    protected StdObject checkForObject(String token, List<StdObject> objects) {

        for (StdObject object : objects) {
            if (object.getName().toLowerCase().equals(token) || (object.getAlias() != null && object.getAlias().contains(token))) {
                return object;
            } else if (object instanceof ObjectContainer && ((ObjectContainer) object).isOpen()) {
                for (StdObject objectContained : ((ObjectContainer) object).getObjects()) {
                    if (objectContained.getName().toLowerCase().equals(token) || (objectContained.getAlias() != null && object.getAlias().contains(token))) {
                        return objectContained;
                    }
                }
            }
        }
        return null;
    }

    protected Character checkForCharacter(String token, List<Character> characters) {
        for (Character character : characters) {
            if (character.getName().toLowerCase().equals(token)) {
                return character;
            }
        }
        return null;
    }


    public abstract PhraseReduction analyze(String input, Room currentRoom, List<Command> commandList, Character mainCharacter) throws CommandNotValidException;
}
