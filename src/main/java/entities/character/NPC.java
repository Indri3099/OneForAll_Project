package entities.character;

import java.util.List;

public class NPC {
    private List<String> sentences;
    private Integer sentenceIndex = 0;

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
}
