package entities.character;

import entities.objects.StdObject;

import java.util.*;

public class NPC extends Character{

    private List<String> sentences = new ArrayList<>();

    private Integer sentenceIndex = 0;

    private List<StdObject> accepted = new ArrayList<>();

    public NPC(int id, String name) {
        super(id, name);
    }

    public NPC(int id, String name, int maxInventory) {
        super(id, name, maxInventory);
    }

    public NPC(int id, String name, List<String> sentences) {
        super(id, name);
        this.sentences = sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public void addSentence(String sentence){
        sentences.add(sentence);
    }

    public Integer getSentenceIndex() {
        return sentenceIndex;
    }

    public void setSentenceIndex(Integer sentenceIndex) {
        this.sentenceIndex = sentenceIndex;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public List<StdObject> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<StdObject> accepted) {
        this.accepted = accepted;
    }
}
