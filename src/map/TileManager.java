package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
    public Tile[] tile;
    public Tile[][] tileArray;
    GamePanel gp;
    String filePath = new File("").getAbsolutePath();
    int mapWidth = 24, mapHeight = 14;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        tileArray = new Tile[mapWidth][mapHeight];

        getTileImage();

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));

            tile[2] = new Tile();
            tile[2].img = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            tile[3] = new Tile();
            tile[3].img = ImageIO.read(getClass().getResourceAsStream("/tiles/sand1.png"));

            tile[4] = new Tile();
            tile[4].img = ImageIO.read(getClass().getResourceAsStream("/tiles/northpath.png"));

            tile[5] = new Tile();
            tile[5].img = ImageIO.read(getClass().getResourceAsStream("/tiles/southpath.png"));

            tile[6] = new Tile();
            tile[6].img = ImageIO.read(getClass().getResourceAsStream("/tiles/rock.png"));
            tile[6].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int x, y;
        Scanner scr = null;

        try {
            scr = new Scanner(new FileReader(filePath + "\\assets\\maps\\testmap1.txt"));

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        for (y = 0; y < mapHeight; ++y) {
            for (x = 0; x < mapWidth; ++x) {
                int tileNum = scr.nextInt();
                System.out.println(gp.player.xCord);
                g2.drawImage(tile[tileNum].img, x * gp.tileSize - gp.player.xCord, y * gp.tileSize - gp.player.yCord,
                        gp.tileSize, gp.tileSize, null);

            }
        }

    }
}
