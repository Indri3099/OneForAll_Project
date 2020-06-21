package parser;

import entities.Room;
import entities.character.Character;
import entities.character.MainCharacter;
import entities.character.NPC;
import entities.command.Command;
import entities.objects.ObjectContainer;
import entities.objects.StdObject;
import exceptions.CommandNotValidException;

import java.util.List;

public abstract class Parser {

    protected int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().toLowerCase().equals(token) || (commands.get(i).getAlias() != null && commands.get(i).getAlias().contains(token))) {
                return i;
            }
        }
        return -1;
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

    protected int checkForCharacter(String token, List<Character> characters) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getName().toLowerCase().equals(token)) {
                return i;
            }
        }
        return -1;
    }


    public abstract PhraseReduction analyze(String input, Room currentRoom, List<Command> commandList, Character mainCharacter) throws CommandNotValidException;
}
