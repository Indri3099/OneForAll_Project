package parser;

import entities.Room;
import entities.character.Character;
import entities.command.Command;
import exceptions.CommandNotValidException;

import java.util.*;

/**
 * Versione italiana del parser, il metodo analyze è implementato secondo una struttura tipica delle frasi italiane
 */
public class ItalianParser extends Parser {

    private final Set<String> prepositions;
    private final Set<String> articles;


    public ItalianParser() {
        prepositions = new HashSet<>(Arrays.asList(new String[]{"di", "a", "da", "in", "con", "su", "per", "tra", "fra","al","alla","alle","col","sul","dal"}));
        articles = new HashSet<>(Arrays.asList(new String[]{"il", "lo", "la", "i", "gli", "le", "un", "uno", "una", "quello", "quella", "quegli", "quelle", "delle", "del"}));
    }

    @Override
    public PhraseReduction analyze(String input, Room currentRoom, List<Command> commandList, Character mainCharacter) throws CommandNotValidException {

        Command command;
        input = input.toLowerCase().trim().replaceAll("(\\s)+"," ");
        PhraseReduction result = new PhraseReduction();

        List<String> splitted = Arrays.asList(input.split("\\s"));
        if (splitted.size() > 0 && splitted.size() < 6) {

            command = checkGeneric(commandList,
                    cmd -> cmd.getName().toLowerCase().equals(splitted.get(0))
                    || (cmd.getAlias() != null && cmd.getAlias().contains(splitted.get(0))));

            if (command != null) {
                result.setCommand(command);
                if (splitted.size() == 1) {
                } else if (splitted.size() == 2) {
                    wordCheck(splitted.get(1), currentRoom, mainCharacter, result);
                } else if (splitted.size() == 3) {
                    if (articles.contains(splitted.get(1)) || prepositions.contains(splitted.get(1))) {
                        wordCheck(splitted.get(2), currentRoom, mainCharacter, result);
                    } else {
                        wordCheck(splitted.get(1),currentRoom,mainCharacter,result);
                        wordCheck(splitted.get(2),currentRoom,mainCharacter,result);
                    }
                } else if (splitted.size() == 4) {
                    if (articles.contains(splitted.get(1)) || prepositions.contains(splitted.get(1))) {
                        wordCheck(splitted.get(2), currentRoom, mainCharacter, result);
                        wordCheck(splitted.get(3), currentRoom, mainCharacter, result);
                    } else if (articles.contains(splitted.get(2)) || prepositions.contains(splitted.get(2))) {
                        wordCheck(splitted.get(1), currentRoom, mainCharacter, result);
                        wordCheck(splitted.get(3), currentRoom, mainCharacter, result);
                    }
                } else if (splitted.size() == 5) {
                    if ((articles.contains(splitted.get(1)) || prepositions.contains(splitted.get(1))) && (articles.contains(splitted.get(3)) || prepositions.contains(splitted.get(3)))) {
                        wordCheck(splitted.get(2), currentRoom, mainCharacter, result);
                        wordCheck(splitted.get(4), currentRoom, mainCharacter, result);
                    }
                }
                return result;
            }
        }
        throw new CommandNotValidException();
    }

}
