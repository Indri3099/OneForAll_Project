package parser;

import entities.Room;
import entities.character.Character;
import entities.command.Command;
import entities.objects.ObjectContainer;
import entities.objects.StdObject;
import exceptions.CommandNotValidException;

import java.util.List;
import java.util.function.Predicate;

public abstract class Parser {

    protected <X> X checkGeneric(Iterable<X> iterable, Predicate<X> predicate){
        for(X x : iterable){
            if(predicate.test(x)){
                return x;
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

    protected void wordCheck(List<String> splitted, Room currentRoom, Character mainCharacter, PhraseReduction result, int index) throws CommandNotValidException {
        StdObject object;
        Character character;
        object = checkForObject(splitted.get(index), currentRoom.getObjects());
        if (object != null) {
            result.setToObject(object);
        }
        else{
            object = checkForObject(splitted.get(index), mainCharacter.getInventory().getList());
            if (object != null) {
                result.setMyObject(object);
            } else {
                character = checkGeneric(currentRoom.getCharacters(),
                        character1 -> character1.getName().toLowerCase().equals(splitted.get(index)));
                if (character != null) {
                    result.setToCharacter(character);
                }else{
                    throw new CommandNotValidException();
                }
            }
        }
    }

    public abstract PhraseReduction analyze(String input, Room currentRoom, List<Command> commandList, Character mainCharacter) throws CommandNotValidException;
}
