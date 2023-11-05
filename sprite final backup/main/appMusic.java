package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class appMusic extends App {
    public static void main(String[] args) {
        // Get the existing app instance
        App app = App.getInstance();

        // Create a new thread to play the music in the background
        new Thread(() -> {
            try {
                // Load the music file
                File musicFile = new File(
                        "/Users/huzaifafareed/Documents/GitHub/spaceus/sprite final backup/res/Density & Time - MAZE.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

                // Create a clip to play the music
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                // Play the music on loop
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();

        // Launch the app
        app.start();
    }
}
