package entities.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe estende StdObject , introducendo nuovi attributi, propri di un oggetto di tipo contenitore.
 */
public class ObjectContainer extends StdObject {
    public ObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    private boolean locked = true;

    private boolean open = false;

    private List<StdObject> objects = new ArrayList<>();

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
        for (StdObject obj : objects) {
            obj.setVisible(open);
        }
    }

    public List<StdObject> getObjects() {
        return objects;
    }

    public void addObject(StdObject object) {
        object.setVisible(open);
        objects.add(object);

    }

    public void setObjects(List<StdObject> objects) {
        for (StdObject obj : objects) {
            obj.setVisible(open);
        }
        this.objects = objects;
    }
}
