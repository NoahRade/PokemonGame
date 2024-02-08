package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import entities.Player;
import map.TileManager;

import entities.Player;

public class GamePanel extends JPanel implements Runnable {
    final int orgTileSize = 16; // Tiles are 16 bit
    final int scale = 4; // Scale it up 4x
    public final int tileSize = orgTileSize * scale; // Tile true size is 64

    public final int maxCols = 23;
    public final int maxRows = 10;
    public final int scrnWidth = maxCols * tileSize;
    public final int scrnHeight = maxRows * tileSize;

    int FPS;

    TileManager tileM = new TileManager(this);
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(keyH, this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(scrnWidth, scrnHeight));
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
        int drawInterval = 32;
        double nextDrawTime = System.currentTimeMillis() + drawInterval;

        while (gameThread != null) {
            if (keyH.fastForward) {
                FPS = 128;
                drawInterval = 1000 / FPS;
            } else {
                FPS = 32;
                drawInterval = 1000 / FPS;
            }
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.updatePlayer();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);
        g2.setColor(Color.white);
        // g2.fillRect(player.xPos,player.yPos,tileSize,tileSize);

        g2.dispose();
    }
}
