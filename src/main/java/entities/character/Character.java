package entities.character;

import entities.Inventory;
import exceptions.FullInventoryException;

import java.io.Serializable;

/**
 * Questa classe rappresenta un qualsiasi tipo di personaggio, nel nostro caso viene utilizzata per rappresentare
 * il personaggio principale e da questa si estende la classe NPC
 */
public class Character implements Serializable {

    private final int id;

    private String name;

    private Inventory inventory = new Inventory();

    public Character(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) throws FullInventoryException {
        inventory.setList(inventory.getList());
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
