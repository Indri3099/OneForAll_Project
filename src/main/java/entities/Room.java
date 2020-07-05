package entities;

import entities.character.Character;
import entities.objects.StdObject;
import events.EventHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Stanza del gioco.
 * La mappa è costruita indicando per ogni stanza quali sono le stanze adiacenti.
 * Ciascuna stanza contiene oggetti,personaggi e può esserle associato un gestore di evento.
 */
public class Room implements Serializable {

    private final Integer id;

    private String name;

    private String description;

    private String look;

    private Room sud = null;

    private Room nord = null;

    private Room est = null;

    private Room ovest = null;

    private boolean visible = true;

    private boolean locked = false;

    private List<StdObject> objects = new ArrayList<>();

    private List<Character> characters = new ArrayList<>();

    /**
     * Gestore di evento, se questo è presente ogni volta che si compie una azione in questa stanza,
     * si verifica se l'evento è stato completato oppure no.
     */
    private EventHandler eventHandler;

    public Room(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public Room getSud() {
        return sud;
    }

    public void setSud(Room sud) {
        this.sud = sud;
    }

    public Room getNord() {
        return nord;
    }

    public void setNord(Room nord) {
        this.nord = nord;
    }

    public Room getEst() {
        return est;
    }

    public void setEst(Room est) {
        this.est = est;
    }

    public Room getOvest() {
        return ovest;
    }

    public void setOvest(Room ovest) {
        this.ovest = ovest;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<StdObject> getObjects() {
        return objects;
    }

    public void setObjects(List<StdObject> objects) {
        this.objects = objects;
    }

    public void addObject(StdObject object){
        objects.add(object);
    }

    public void removeObject(StdObject object){
        objects.remove(object);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public void addCharacter(Character character){
        characters.add(character);
    }

    public void removeCharacter(Character character){
        characters.remove(character);
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
