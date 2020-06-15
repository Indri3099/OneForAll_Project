package entities.character;

import entities.objects.StdObject;

import java.util.List;

public abstract class Character {
    private final int id;
    private String name;
    private List<StdObject> inventory;

    public Character(int id, String name){
        this.id = id;
        this.name = name;
        inventory = null;
    }

    public Character(int id, String name, List<StdObject> inventory){
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    public List<StdObject> getInventory() {
        return inventory;
    }

    public void setInventory(List<StdObject> inventory) {
        this.inventory = inventory;
    }

    public void addObjToInventory(StdObject object){
        inventory.add(object);
    }
}
