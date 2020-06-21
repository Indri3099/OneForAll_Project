package events;

import entities.character.Character;
import entities.character.NPC;
import entities.objects.StdObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Descrive le caratteristiche di un evento
 *
 */
public class Event {
    /**
     * Indica eventualmente il personaggio coinvolto
      */
    private NPC character;

    private List<StdObject> requiredObjects = new ArrayList<>();

    private boolean started = false;

    private boolean completed = false;

    private String description;

    private int pointReward;

    public Event(NPC character, List<StdObject> requiredObjects, String description, int pointReward) {
        this.character = character;
        this.requiredObjects = requiredObjects;
        this.started = started;
        this.completed = completed;
        this.description = description;
        this.pointReward = pointReward;
    }

    public Event(){

    }

    public NPC getCharacter() {
        return character;
    }

    public void setCharacter(NPC character) {
        this.character = character;
    }

    public List<StdObject> getRequiredObjects() {
        return requiredObjects;
    }

    public void setRequiredObjects(List<StdObject> requiredObjects) {
        this.requiredObjects = requiredObjects;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointReward() {
        return pointReward;
    }

    public void setPointReward(int pointReward) {
        this.pointReward = pointReward;
    }
}
