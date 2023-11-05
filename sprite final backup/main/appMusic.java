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
        // Create a new instance of the Launcher
        launcher launcher = new launcher();

        // Create a new thread to play the music in the background
        Thread musicThread = new Thread(() -> {
            try {
                // Load the music file
                File musicFile = new File(
                        "sprite final backup/res/Density & Time - MAZE.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

                // Create a clip to play the music
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                // Play the music on loop
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                // Wait for the music to finish playing
                while (clip.isRunning()) {
                    Thread.sleep(1000);
                }

                // Close the clip
                clip.close();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        musicThread.start();

        // Wait for the music thread to start playing the music
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Launch the app
        launcher.startApp();

        // Wait for the music thread to finish playing the music
        try {
            musicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
