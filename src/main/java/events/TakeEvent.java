package events;

import exceptions.ObjectNotFoundException;
import games.GenericGame;
import entities.objects.StdObject;
import exceptions.UnTakeableException;
import parser.PhraseReduction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TakeEvent implements Event {

    private StdObject object;

    @Override
    public void handleEvent(PhraseReduction action, GenericGame game) throws Exception {
        object = action.getToObject();
        if (object == null) {
            throw new ObjectNotFoundException();
        } else if (object.isTakeable()) {
            game.getMainCharacter().getInventory().add(object);
            game.getCurrentRoom().getObjects().remove(object);
        } else {
            throw new UnTakeableException();
        }
    }

    public StdObject getObject() {
        return object;
    }
}
