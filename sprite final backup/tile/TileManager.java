package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/03_StationTileset.png"));
            tile[0].collision = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/01_StationTileset.png"));
            tile[1].collision = false;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/02_StationTileset.png"));
            tile[2].collision = false;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(
                    getClass().getResourceAsStream("/res/space_background_pack/layers/parallax-space-backgound.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(
                    getClass().getResourceAsStream("/res/space_background_pack/layers/parallax-space-stars.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(
                    getClass().getResourceAsStream("/res/space_background_pack/layers/parallax-space-big-planet.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(
                    getClass().getResourceAsStream("/res/space_background_pack/layers/parallax-space-far-planets.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(
                    getClass().getResourceAsStream("/res/space_background_pack/layers/kingsprite.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read (
                    getClass().getResourceAsStream("/res/tiles/station1.png"));
            
            tile[9] = new Tile();
            tile[9].image = ImageIO.read (
                    getClass().getResourceAsStream("/res/tiles/station2.png"));
                    
            if (tile[7].collision == true) {
                JFrame frame = new JFrame();
                frame.setSize(500, 500);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.dispose();
            }    
            tile[7].collision = false;  

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenColumn && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenColumn) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenColumn) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }

    }   public boolean isCollisionWithKing(int x, int y) {
        int col = x / gp.arenaSize;
        int row = y / gp.arenaSize;
        if (col >= 0 && col < gp.maxScreenColumn && row >= 0 && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            return tileNum == 7;
        }
        return false;
    }


    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenColumn && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            // Check if tile[tileNum] is not null before accessing its image, if it is then
            // its jst a white background

            g2.drawImage(tile[tileNum].image, x, y, gp.arenaSize, gp.arenaSize, null);

            col++;
            x += gp.arenaSize;

            if (col == gp.maxScreenColumn) {
                col = 0;
                x = 0;
                row++;
                y += gp.arenaSize;
            }
        }
    }

}