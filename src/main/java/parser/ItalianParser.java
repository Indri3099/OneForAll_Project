package parser;

import entities.Room;
import entities.character.Character;
import entities.character.MainCharacter;
import entities.character.NPC;
import entities.command.Command;
import entities.objects.StdObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItalianParser extends Parser {

    private final Set<String> prepositions;
    private final Set<String> articles;


    public ItalianParser() {
        prepositions = new HashSet<>(Arrays.asList(new String[]{"di", "a", "da", "in", "con", "su", "per", "tra", "fra"}));
        articles = new HashSet<>(Arrays.asList(new String[]{"il", "lo", "la", "i", "gli", "le", "un", "uno", "una", "quello", "quella", "quegli", "quelle", "delle", "del"}));
    }

    @Override
    public PhraseReduction analyze(String input, Room currentRoom, List<Command> commandList, MainCharacter mainCharacter) {

        boolean firstPart = false;
        Integer cmd = -1, toSome = -1, toChar = -1;
        input = input.toLowerCase().trim();
        PhraseReduction result = new PhraseReduction();
        String regexp = "(?<command>\\w+)((\\s(?<artpre>\\w+))?\\s(?<toSomething>\\w+)((\\s(?<artpre2>\\w+))?\\s(?<toSomething2>\\w+))?)?";

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            cmd = checkForCommand(matcher.group("command"), commandList);
            if (cmd > -1) { //il comando è effettivamente un comando possibile nel gioco
                result.setCommand(commandList.get(cmd));

                String artPre = matcher.group("artpre");

                if (artPre == null || prepositions.contains(artPre) || articles.contains(artPre)) {
                    //l'articolo è effettivamente un articolo italiano / non c'è
                    String strToSome = matcher.group("toSomething");

                    if (strToSome != null) {

                        toSome = checkForObject(strToSome, currentRoom.getObjects());

                        if (toSome > -1) {
                            result.setToObject(currentRoom.getObjects().get(toSome));
                            firstPart = true;
                        } else {
                            toSome = checkForObject(strToSome, mainCharacter.getInventory());
                            if (toSome > -1) {
                                result.setMyObject(mainCharacter.getInventory().get(toSome));
                                firstPart = true;
                            } else {
                                toChar = checkForCharacter(strToSome, currentRoom.getCharacters());
                                if (toChar > -1) {
                                    result.setToCharacter(currentRoom.getCharacters().get(toChar));
                                    firstPart = true;
                                }
                            }
                        }
                        if (firstPart) {
                            artPre = matcher.group("artpre2");

                            if (artPre == null || prepositions.contains(artPre) || articles.contains(artPre)) {
                                strToSome = matcher.group("toSomething2");

                                if (strToSome != null) {

                                    toSome = checkForObject(strToSome, currentRoom.getObjects());

                                    if (toSome > -1) {
                                        result.setToObject(currentRoom.getObjects().get(toSome));
                                    } else {
                                        toSome = checkForObject(strToSome, mainCharacter.getInventory());
                                        if (toSome > -1) {
                                            result.setMyObject(mainCharacter.getInventory().get(toSome));
                                        } else {
                                            toChar = checkForCharacter(strToSome, currentRoom.getCharacters());
                                            if (toChar > -1) {
                                                result.setToCharacter(currentRoom.getCharacters().get(toChar));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
