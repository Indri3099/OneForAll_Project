package events;

import entities.character.NPC;
import entities.objects.StdObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Descrive le caratteristiche di un evento
 */
public class Event implements Serializable {
    /**
     * Indica eventualmente il personaggio coinvolto
      */
    private NPC character;

    /**
     * Indica gli oggetti coinvolti per il completamento dell'evento
     */
    private List<StdObject> requiredObjects = new ArrayList<>();

    /**
     * Eventuale audio associato all'evento
     */
    private File audio;

    private boolean started = false;

    private boolean completed = false;

    private String description;

    /**
     * Frase stampata al concludersi dell'evento
     */
    private String endPhrase;

    /**
     * Punti guadagnati completando l'evento
     */
    private int pointReward;

    public Event(NPC character, List<StdObject> requiredObjects, String description, int pointReward) {
        this.character = character;
        this.requiredObjects = requiredObjects;
        this.description = description;
        this.pointReward = pointReward;
    }

    public File getAudio() {
        return audio;
    }

    public void setAudio(File audio) {
        this.audio = audio;
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

    public String getEndPhrase() {
        return endPhrase;
    }

    public void setEndPhrase(String endPhrase) {
        this.endPhrase = endPhrase;
    }
}
