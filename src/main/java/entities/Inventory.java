package entities;

import entities.objects.StdObject;
import exceptions.FullInventoryException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Inventario associato ad ogni Character.
 * L'aggiunta di oggetti è gestita controllando che non venga superata la dimensione massima
 * */
public class Inventory implements Serializable {

    private int maxSize;

    private List<StdObject> list = new ArrayList<>();

    public Inventory(int maxSize){
        this.maxSize = maxSize;
    }

    public List<StdObject> getList() {
        return list;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
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
