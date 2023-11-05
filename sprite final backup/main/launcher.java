package main;

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

public class launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Beyond Us!");
        JPanel panel = new JPanel(new GridBagLayout()) {
            private static final long serialVersionUID = 1L;
            public Image backgroundImage = new ImageIcon(
                    getClass().getResource("/res/space_background_pack/layers/Space2-export.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setPreferredSize(new Dimension(800, 600)); // Increase the panel size

        // Use a pixel art font (replace "PixelFont.ttf" with the actual font file)
        Font pixelFont = loadPixelArtFont("/res/space_background_pack/layers/04B_30__.TTF");

        JLabel titleLabel = new JLabel("Welcome to Space Beyond Us!");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(50, 0, 20, 0); // Increased top and bottom insets
        panel.add(titleLabel, c);
        titleLabel.setFont(pixelFont.deriveFont(Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE); // Increased font size

        JLabel beginLabel = new JLabel("Begin?");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20, 0, 20, 0); // Increased top and bottom insets
        panel.add(beginLabel, c);
        beginLabel.setFont(pixelFont.deriveFont(20)); // Increased font size

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.main(args);
                frame.dispose();
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(50, 0, 20, 400); // Increased top and bottom insets, adjusted right inset
        panel.add(startButton, c);
        startButton.setFont(pixelFont.deriveFont(Font.BOLD, 12)); // Increased font size for buttons
        startButton.setPreferredSize(new Dimension(200, 60)); // Set preferred button size

        JButton quitButton = new JButton("I am a coward");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        c.gridx = 1;
        c.insets = new Insets(50, -200, 20, 0); // Increased top and bottom insets, adjusted left inset
        panel.add(quitButton, c);
        quitButton.setFont(pixelFont.deriveFont(Font.BOLD, 12)); // Increased font size for buttons
        quitButton.setPreferredSize(new Dimension(200, 60)); // Set preferred button size

        // Add the credits label
        JLabel creditsLabel = new JLabel("Made by: Saymonn Lagran, Huzaifa Fareed, Derek Gallagher");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2; // Span across two columns
        c.insets = new Insets(20, 0, 50, 0); // Adjusted top and bottom insets
        creditsLabel.setFont(pixelFont.deriveFont(Font.PLAIN, 16));
        // Set a larger font size for credits
        creditsLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(creditsLabel, c);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static Font loadPixelArtFont(String fontPath) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, launcher.class.getResourceAsStream(fontPath));
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 12); // Default font if the pixel art font fails to load
        }
    }
}
