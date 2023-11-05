package main;

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
            public Image backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("res/Space2-export.png")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setPreferredSize(new Dimension(720, 1280));
        JLabel titleLabel = new JLabel("Welcome to Space Beyond Us!");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 400, 0);
        panel.add(titleLabel, c);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
    
        JLabel beginLabel = new JLabel("Begin?");
        c.gridx = 0;
        c.gridy = 1; 
        c.insets = new Insets(0, 0, 0, 0);
        panel.add(beginLabel, c);
        beginLabel.setFont(titleLabel.getFont()); // reuse titleLabel's font
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.main(args);
                frame.dispose();
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(50, 0, 0, 400);
        panel.add(startButton, c);
        
        JButton quitButton = new JButton("I am a coward");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        c.gridx = 1;
        c.insets = new Insets(50, -200, 0, 0);
        panel.add(quitButton, c);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

