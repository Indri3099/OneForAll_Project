package entities.character;

import entities.objects.StdObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NPC extends Character{

    private Set<String> sentences = new HashSet<>();

    private Set<String> prepositions = new HashSet<>();

    private Integer sentenceIndex = 0;

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

    public Set<String> getPrepositions() {
        return prepositions;
    }

    public void setPrepositions(String[] prepositions) {
        this.prepositions = new HashSet<>(Arrays.asList(prepositions));;
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
}
