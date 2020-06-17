package entities.character;

import entities.objects.StdObject;

import java.util.List;

public class MainCharacter extends Character{

    public MainCharacter(String name) {
        super(0, name);
    }

    public MainCharacter(String name, int maxInventory) {
        super(0, name, maxInventory);
    }
}
