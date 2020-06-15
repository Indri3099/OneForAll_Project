package entities.character;

import entities.objects.StdObject;

import java.util.List;

public class MainCharacter extends Character{

    public MainCharacter(String name) {
        super(0, name);
    }

    public MainCharacter(String name, List<StdObject> inventory) {
        super(0, name, inventory);
    }
}
