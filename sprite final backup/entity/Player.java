package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public int vx, vy;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 0;
        y = 480;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/w1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/w3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/s1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/s3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/a1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/a3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/d1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/d3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            vy = -speed;
            vx = 0;
        } else if (keyH.downPressed) {
            direction = "down";
            vy = speed;
            vx = 0;
        } else if (keyH.leftPressed) {
            direction = "left";
            vx = -speed;
            vy = 0;
        } else if (keyH.rightPressed) {
            direction = "right";
            vx = speed;
            vy = 0;
        } else {
            // No movement input, set velocity to 0
            vx = 0;
            vy = 0;
        }

        // Update the player's position
        x += vx;
        y += vy;

        if (gp.tileM.isCollisionWithKing(x, y)) {
     // Display the message when the king is found
     JFrame frame = new JFrame();
     JLabel label = new JLabel();
     JOptionPane pane = new JOptionPane();
     pane.add(label);
     label.setText("You have closed the space between us");
     frame.setSize(500, 500);
     frame.setVisible(true);
     frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
     //frame.setDefaultCloseOperation(JOptionPane.ABORT);
     System.exit(0);
     // You can also perform other actions when the king is found
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;

            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }
        g2.drawImage(image, x, y, 48, 48, null);
    }
}