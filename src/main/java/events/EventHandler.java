package events;

import games.GenericGame;

import java.io.File;
import java.io.Serializable;

import main.userInterface.printer.Printer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class EventHandler implements Serializable {

    Event event;

    private void soundPlay() {
        try {
            File f = new File("./src/main/resources/audio/EventCompleteSound.wav");
            System.out.println(f.getName());
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

    public void startEvent(Printer out) {
        event.setStarted(true);
        out.print("\n" + event.getDescription());
    }

    protected void pointsUpdate(GenericGame game, Printer out){
        game.setActualPoints(game.getActualPoints() + event.getPointReward());
        out.print("Punti guadagnati: " + event.getPointReward());
        soundPlay();
    }

    public abstract void completeEvent(GenericGame game, Printer out);
}
