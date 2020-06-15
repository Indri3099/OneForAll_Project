package entities.objects;

public class ObjectContainer extends StdObject{
    public ObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    private boolean locked = true;

    private boolean open = false;

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
    }
}
