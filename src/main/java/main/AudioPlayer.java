package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Questa classe contiene un solo metodo statico "soundPlay" richiamato ogni volta che si vuole
 * riprodurre un file audio.
 */
public class AudioPlayer {
    public static void soundPlay(File audio) throws Exception {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(audio.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
