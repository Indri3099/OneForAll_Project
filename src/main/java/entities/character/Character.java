package entities.character;

import entities.objects.StdObject;
import exceptions.FullInventoryException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Character implements Serializable {

    private final int id;

    private String name;

    private List<StdObject> inventory = new ArrayList<>();

    private int maxInventory = 10;

    public Character(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Character(int id, String name, int maxInventory) {
        this.id = id;
        this.name = name;
        this.maxInventory = maxInventory;
    }

    public List<StdObject> getInventory() {
        return inventory;
    }

    public void setInventory(List<StdObject> inventory) throws FullInventoryException {
        if(inventory.size() >= maxInventory){
            this.inventory = inventory.subList(0,maxInventory);
            throw new FullInventoryException();
        }
        else this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
