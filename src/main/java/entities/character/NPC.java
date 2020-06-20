package entities.character;

import entities.objects.StdObject;

import java.util.*;

public class NPC extends Character{

    private Set<String> sentences = new HashSet<>();

    private Integer sentenceIndex = 0;

    private List<StdObject> accepted = new ArrayList<>();

    public NPC(int id, String name) {
        super(id, name);
    }

    public NPC(int id, String name, int maxInventory) {
        super(id, name, maxInventory);
    }

    public NPC(int id, String name, String[] sentences) {
        super(id, name);
        this.sentences = new HashSet<>(Arrays.asList(sentences));
    }

    public void setSentences(String[] sentences) {
        this.sentences = new HashSet<>(Arrays.asList(sentences));
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

    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }

    public List<StdObject> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<StdObject> accepted) {
        this.accepted = accepted;
    }
}
