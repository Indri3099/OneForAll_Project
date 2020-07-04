package events;

import games.GenericGame;

import java.io.File;
import java.io.Serializable;

import main.userInterface.printer.Printer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class EventHandler implements Serializable {

    private Event event;

    private void soundPlay() {
        try {
            File f = new File("./src/main/resources/audio/EventCompleteSound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
        }
    }

    public EventHandler(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * L'evento parte e viene visualizzato un messaggio
     * @param out
     */
    public void startEvent(Printer out) {
        event.setStarted(true);
        out.print("\n" + event.getDescription());
    }

    /**
     * Quando l'evento è completato vengono aggiornati i punti della partita e riprodotto un simpatico audio.
     * @param game
     * @param out
     */
    protected void pointsUpdate(GenericGame game, Printer out){
        game.setActualPoints(game.getActualPoints() + event.getPointReward());
        out.print("Punti guadagnati: " + event.getPointReward());
        soundPlay();
    }

    /**
     * è necessario implementare questo metodo in base alle condizioni che consentono di completare l'evento
     * @param game è necessario passare il gioco per conoscerne lo stato e modificarlo
     * @param out Il printer indica dove verranno visualizzati eventuali messaggi a conclusione dell'evento
     */
    public abstract void completeEvent(GenericGame game, Printer out);
}
