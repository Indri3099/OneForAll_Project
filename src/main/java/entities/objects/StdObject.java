package entities.objects;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class StdObject implements Serializable {

    private final int id;

    private String name;

    private String description;

    private Set<String> alias = Collections.EMPTY_SET;

    private boolean takeable = true;

    private boolean pushable = true;

    private boolean pushed = false;

    private boolean visible = true;

    private File sound;

    public StdObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public File getSound() {
        return sound;
    }

    public void setSound(File sound) {
        this.sound = sound;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public boolean isTakeable() {
        return takeable;
    }

    public void setTakeable(boolean takeable) {
        this.takeable = takeable;
    }

    public boolean isPushable() {
        return pushable;
    }

    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    public boolean isPushed() {
        return pushed;
    }

    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StdObject stdObject = (StdObject) o;
        return id == stdObject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
}
