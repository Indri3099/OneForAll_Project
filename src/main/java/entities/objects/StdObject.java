package entities.objects;

import java.util.Objects;
import java.util.Set;

public class StdObject {

    private final int id;

    private String name;

    private String description;

    private Set<String> alias;

    private Set<String> articles;

    private Set<String> prepositions;

    private boolean takeable = true;

    private boolean pushable = true;

    private boolean pushed = false;

    public StdObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public Set<String> getArticles() {
        return articles;
    }

    public void setArticles(Set<String> articles) {
        this.articles = articles;
    }

    public Set<String> getPrepositions() {
        return prepositions;
    }

    public void setPrepositions(Set<String> prepositions) {
        this.prepositions = prepositions;
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
}
