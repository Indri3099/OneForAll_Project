package parser;

import entities.Room;
import entities.character.Character;
import entities.character.MainCharacter;
import entities.character.NPC;
import entities.command.Command;
import entities.command.CommandType;
import entities.objects.StdObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


    private Set<String> prepositions;

    public void Parser(){
    }

    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().toLowerCase().equals(token) || (commands.get(i).getAlias() != null && commands.get(i).getAlias().contains(token))) {
                return i;
            }
        }
        return -1;
    }

    private int checkForObject(String token, List<StdObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getName().toLowerCase().equals(token) || (objects.get(i).getAlias() != null && objects.get(i).getAlias().contains(token))) {
                return i;
            }
        }
        return -1;
    }

    private int checkForCharacter(String token, List<Character> characters){
        for(int i = 0; i < characters.size(); i++){
            if(characters.get(i).getName().equals(token)){
                return i;
            }
        }
        return -1;
    }


    public PhraseReduction analyze(String input, Room currentRoom, List<Command> commandList, MainCharacter mainCharacter){
        input = input.toLowerCase().trim();
        PhraseReduction result;

        //frase semplice del tipo "Raccogli la mela" o "Parla con Marco"
        String simple = "(?<command>\\w+)(\\s(((?<article>\\w+)\\s))?(?<toObject>\\w+))?";

        //frase complessa del tipo "Colpisci la mela con il bastone"
        String complex = "";

        Pattern simplePattern = Pattern.compile(simple);
        Matcher simpleMatch = simplePattern.matcher(input);

        Pattern complexPattern = Pattern.compile(complex);
        Matcher complexMatch = complexPattern.matcher(input);

        Integer cmd = -1, toObj = -1;
        String strObj;
        //TODO : continua a lavorare sul parser
        if (simpleMatch.matches()) {
            cmd = checkForCommand(simpleMatch.group("command"),commandList);
            if (cmd > -1) { //il comando è effettivamente un comando possibile nel gioco
                Command command = commandList.get(cmd);

                if(simpleMatch.group("toObject") != null){
                    strObj = simpleMatch.group("toObject");
                    toObj = checkForObject(strObj,currentRoom.getObjects());
                    if(toObj > -1) {    //l'oggetto nella frase è effettivamente presente nella stanza
                        StdObject toObject = currentRoom.getObjects().get(toObj);
                        if(toObject.getArticles().contains(simpleMatch.group("article")) || simpleMatch.group("article") == null){
                            return new PhraseReduction(command,toObject);
                        }
                    }
                    toObj = checkForObject(strObj,mainCharacter.getInventory());
                    if(toObj > -1){     //l'oggetto nella frase è presente nel mio inventario
                        StdObject myObject = mainCharacter.getInventory().get(toObj);
                        if(myObject.getArticles().contains(simpleMatch.group("article")) || simpleMatch.group("article") == null){
                            return new PhraseReduction(command, myObject, (StdObject) null);
                        }
                    }
                    toObj = checkForCharacter(strObj, currentRoom.getCharacters());
                    if(toObj > -1){     //il personaggio a cui mi riferisco è nella stanza
                        NPC character = (NPC) currentRoom.getCharacters().get(toObj);
                        if(character.getPrepositions().contains(simpleMatch.group("article")) || simpleMatch.group("article") == null){
                            return new PhraseReduction(command,character);
                        }
                    }
                }
                return new PhraseReduction(command);
            }
        }
        else if(complexMatch.matches()){

        }
        return new PhraseReduction((Command) null, (Character) null);
    }

}
