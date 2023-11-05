package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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

        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/w1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/w3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/s1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/s3.png"));
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
    }
    

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;

            case "down":
                image = down1;
                break;

            case "left":
                image = left1;
                break;

            case "right":
                image = right1;
                break;

        }
        g2.drawImage(image, x, y, 48, 48, null);
    }
}