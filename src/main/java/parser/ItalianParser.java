package parser;

import entities.Room;
import entities.character.Character;
import entities.command.Command;
import entities.objects.StdObject;
import exceptions.CommandNotValidException;

import java.util.*;

public class ItalianParser extends Parser {

    private final Set<String> prepositions;
    private final Set<String> articles;


    public ItalianParser() {
        prepositions = new HashSet<>(Arrays.asList(new String[]{"di", "a", "da", "in", "con", "su", "per", "tra", "fra"}));
        articles = new HashSet<>(Arrays.asList(new String[]{"il", "lo", "la", "i", "gli", "le", "un", "uno", "una", "quello", "quella", "quegli", "quelle", "delle", "del"}));
    }

    @Override
    public PhraseReduction analyze(String input, Room currentRoom, List<Command> commandList, Character mainCharacter) throws CommandNotValidException {

        Integer cmd = -1;
        input = input.toLowerCase().trim();
        PhraseReduction result = new PhraseReduction();

        List<String> splitted = Arrays.asList(input.split("\\s"));
        if (splitted.size() > 0) {

            cmd = checkForCommand(splitted.get(0), commandList);

            if (cmd > -1) {
                result.setCommand(commandList.get(cmd));
                if (splitted.size() == 1) {
                } else if (splitted.size() == 2) {
                    wordCheck(splitted, currentRoom, mainCharacter, result, 1);
                } else if (splitted.size() == 3) {
                    if (articles.contains(splitted.get(1)) || prepositions.contains(splitted.get(1))) {
                        wordCheck(splitted, currentRoom, mainCharacter, result, 2);
                    } else {
                        wordCheck(splitted,currentRoom,mainCharacter,result,1);
                        wordCheck(splitted,currentRoom,mainCharacter,result,2);
                    }
                } else if (splitted.size() == 4) {
                    if (articles.contains(splitted.get(1)) || prepositions.contains(splitted.get(1))) {
                        wordCheck(splitted, currentRoom, mainCharacter, result, 2);
                        wordCheck(splitted, currentRoom, mainCharacter, result, 3);
                    } else if (articles.contains(splitted.get(2)) || prepositions.contains(splitted.get(2))) {
                        wordCheck(splitted, currentRoom, mainCharacter, result, 1);
                        wordCheck(splitted, currentRoom, mainCharacter, result, 3);
                    }
                } else if (splitted.size() == 5) {
                    if ((articles.contains(splitted.get(1)) || prepositions.contains(splitted.get(1))) && (articles.contains(splitted.get(3)) || prepositions.contains(splitted.get(3)))) {
                        wordCheck(splitted, currentRoom, mainCharacter, result, 2);
                        wordCheck(splitted, currentRoom, mainCharacter, result, 4);
                    }
                }
                return result;
            }
        }
        throw new CommandNotValidException();
    }

    private void wordCheck(List<String> splitted, Room currentRoom, Character mainCharacter, PhraseReduction result, int index) throws CommandNotValidException {
        int obj1 = -1;
        StdObject object;

        object = checkForObject(splitted.get(index), currentRoom.getObjects());
        if (object != null) {
            result.setToObject(object);
        }
        else{
            object = checkForObject(splitted.get(index), mainCharacter.getInventory());
            if (object != null) {
                result.setMyObject(object);
            } else {
                obj1 = checkForCharacter(splitted.get(index), currentRoom.getCharacters());
                if (obj1 > -1) {
                    result.setToCharacter(currentRoom.getCharacters().get(obj1));
                }else{
                    throw new CommandNotValidException();
                }
            }
        }
    }
}
