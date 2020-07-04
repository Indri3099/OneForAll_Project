package entities.character;

import entities.objects.StdObject;

import java.util.*;

/**
 * Gli NPC estendono un personaggio generico, essi rappresentano i personaggi che troviamo nel corso della storia
 */
public class NPC extends Character{

    /**
     * Indica la frase che verrà detta dal personaggio quando si cercherà di parlargli
     */
    private String sentence;

    /**
     * possibili oggetti che il personaggio può accettare in dono.
     */
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
