package entities;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Player {
    int speed = 8; // Pixels per frame
    public int xCord, yCord; // Coords of player relative to the map top left is (0,0)
    KeyHandler keyH;
    boolean isWalkingUp, isWalkingDown, isWalkingLeft, isWalkingRight;

    BufferedImage img;
    GamePanel gp;

    public Player(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;
        setImg("/character/south.png");
    }

    public void setImg(String str) {
        try {
            img = ImageIO.read(getClass().getResourceAsStream(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // Draws in the middle of the screen
        g2.drawImage(img, gp.scrnWidth / 2 - gp.tileSize / 2, gp.scrnHeight / 2, gp.tileSize, gp.tileSize, null);
    }

    public void updatePlayer() {
        if ((keyH.upPressed || isWalkingUp) && !isWalkingDown && !isWalkingLeft && !isWalkingRight) {
            yCord -= speed;
            setImg("/character/north.png");
            if (yCord % 64 == 0) {
                isWalkingUp = false;
            } else {
                isWalkingUp = true;
            }

        } else if ((keyH.downPressed || isWalkingDown) && !isWalkingUp && !isWalkingLeft && !isWalkingRight) {
            yCord += speed;
            setImg("/character/south.png");
            if (yCord % 64 == 0) {
                isWalkingDown = false;
            } else {
                isWalkingDown = true;
            }

        } else if ((keyH.leftPressed || isWalkingLeft) && !isWalkingDown && !isWalkingUp && !isWalkingRight) {
            xCord -= speed;
            setImg("/character/west.png");
            if (xCord % 64 == 0) {
                isWalkingLeft = false;
            } else {
                isWalkingLeft = true;
            }

        } else if ((keyH.rightPressed || isWalkingRight) && !isWalkingDown && !isWalkingLeft && !isWalkingUp) {
            xCord += speed;
            setImg("/character/east.png");
            if (xCord % 64 == 0) {
                isWalkingRight = false;
            } else {
                isWalkingRight = true;
            }
        }
    }
}
