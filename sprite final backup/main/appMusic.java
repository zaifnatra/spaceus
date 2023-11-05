package main;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import main.App;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class appMusic {
    public static void main(String[] args) {
        // Get the existing app instance
        App app = App.getInstance();

        // Create a new thread to play the music in the background
        new Thread(() -> {
            try {
                // Load the music file
                File musicFile = new File("sprite final backup/res/Density & Time - MAZE.wav");
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

        // Create an instance of the appMusic class and call its main method
        appMusic musicApp = new appMusic();
        musicApp.main(args);

        // Launch the app
        app.start();
    }
}
