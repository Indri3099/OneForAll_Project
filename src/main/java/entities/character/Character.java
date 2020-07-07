package entities.character;

import entities.Inventory;

import java.io.Serializable;

/**
 * Questa classe rappresenta un qualsiasi tipo di personaggio, nel nostro caso viene utilizzata per rappresentare
 * il personaggio principale e da questa si estende la classe NPC
 */
public class Character implements Serializable {

    private final int id;

    private String name;

    private Inventory inventory = new Inventory(2);

    public Character(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public Inventory getInventory() {
        return inventory;
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
