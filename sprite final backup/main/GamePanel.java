package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
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

    // Constructor for game panel
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
        double drawInterval = 1000000000.0 / FPS; // Using nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // Update the character's position
            update();

            // Draw the screen and FPS
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // Pause the game loop

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Update the position of the sprite
    public void update() {
        player.update();
        
        int playerWidth = 20; // Width of the player's sprite
        int playerHeight = 25; // Height of the player's sprite
    
        for (int col = 0; col < maxScreenColumn; col++) {
            for (int row = 0; row < maxScreenRow; row++) {
                int tileNum = tileM.mapTileNum[col][row];
                if (tileNum == 3 || tileNum == 4 || tileNum == 5) { // Check for specific tile numbers
                    int tileX = col * arenaSize;
                    int tileY = row * arenaSize;
    
                    // Check for collision with the current tile
                    if (player.x + playerWidth > tileX && player.x < tileX + arenaSize &&
                        player.y + playerHeight > tileY && player.y < tileY + arenaSize) {
                        
                        // Handle collision logic here
                        // Adjust the player's position to prevent moving into the tile
                        if (player.vx > 0) {
                            // Moving right, prevent moving further right
                            player.x = tileX - playerWidth;
                        } else if (player.vx < 0) {
                            // Moving left, prevent moving further left
                            player.x = tileX + arenaSize;
                        }
                        
                        if (player.vy > 0) {
                            // Moving down, prevent moving further down
                            player.y = tileY - playerHeight;
                        } else if (player.vy < 0) {
                            // Moving up, prevent moving further up
                            player.y = tileY + arenaSize;
                        }
                    }
                }
            }
        }
    }
    
    
    

    @Override
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
