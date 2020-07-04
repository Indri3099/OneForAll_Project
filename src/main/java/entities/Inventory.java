package entities;

import entities.objects.StdObject;
import exceptions.FullInventoryException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Inventario associato ad ogni Character, di default la dimensione massima è impostata su 2. Ovviamente è modificabile
 */
public class Inventory implements Serializable {

    private int maxSize = 2;

    private List<StdObject> list = new ArrayList<>();

    public Inventory(){}

    public List<StdObject> getList() {
        return list;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setList(List<StdObject> list) throws FullInventoryException {
        if (list.size() >= maxSize) {
            this.list = list.subList(0, maxSize);
            throw new FullInventoryException();
        } else this.list = list;
    }

    public void add(StdObject obj) throws FullInventoryException {
        if (list.size() < maxSize) {
            list.add(obj);
        } else throw new FullInventoryException();
    }

    public void remove(StdObject o) {
        list.remove(o);
    }
}
