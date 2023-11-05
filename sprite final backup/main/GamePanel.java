package main;
//package munchalations;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int originalArenaSize = 16;
    final int scale = 3;

    public final int arenaSize = originalArenaSize * scale;
    public final int maxScreenColumn = 40;
    public final int maxScreenRow = 20;
    public final int screenWidth = arenaSize * maxScreenColumn;
    public final int screenHeight = arenaSize * maxScreenRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // Set default position of character
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;

    // Constructor for gamepanel
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // using nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            // updates the character's position
            update();

            // draw the line screen/fps
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // Pauses the game loop

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // updtes the position of the sprite
    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();

        // Collision with screen boundaries
        if (player.x < 0) {
        player.x = 0;
    } else if (player.x > screenWidth - 48) { // Subtract sprite width
        player.x = screenWidth - 48;
    }

    if (player.y < 0) {
        player.y = 0;
    } else if (player.y > screenHeight - 48) { // Subtract sprite height
        player.y = screenHeight - 48;
    }
}
}
