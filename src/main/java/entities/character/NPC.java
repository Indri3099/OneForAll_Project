package entities.character;

import entities.objects.StdObject;

import java.util.*;

public class NPC extends Character{

    private String sentence;

    private List<StdObject> accepted = new ArrayList<>();

    public NPC(int id, String name) {
        super(id, name);
    }

    public NPC(int id, String name, String sentence) {
        super(id, name);
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<StdObject> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<StdObject> accepted) {
        this.accepted = accepted;
    }
}
