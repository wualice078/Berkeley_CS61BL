package PlusWorld;
import org.junit.Test;
import static org.junit.Assert.*;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of plus shaped regions.
 */
public class PlusWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    private static final int PLUS_SIZE = 4;
    private static final long SEED = 1000;
    private static final Random RANDOM = new Random(SEED);


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        fillWithPlus(world);

        ter.renderFrame(world);
    }

    public static void addPlus(int s, int xshift, int yshift, TETile tile, TETile[][] world) {
        for (int x = 0; x < 3*s; x += 1) {
            for (int y = 0; y < 3*s; y += 1) {
                if(x + xshift >= 0 && x + xshift < WIDTH && y + yshift >= 0 && y + yshift < HEIGHT
                        && (x >= s && x < 2*s || y >= s && y < 2*s)) {
                    world[x + xshift][y + yshift] = tile;
                }
            }
        }
    }

    public static void fillWithPlus(TETile[][] world) {
        int shiftx = 0;
        while(shiftx == 0)
            shiftx = RANDOM.nextInt(0, PLUS_SIZE);
        int shifty = 0;
        while(shifty == 0)
            shifty = RANDOM.nextInt(0, PLUS_SIZE);
        System.out.println(shiftx + " " + shifty);

        int x1 = shiftx;
        int x2 = shiftx;
        int y1 = shifty;
        int y2 = shifty;
        for(int i = 0; i < WIDTH/shiftx; i++) {
            for(int j = 0; j < HEIGHT/shifty; j++) {
                addPlus(PLUS_SIZE, x1, y1, randomTile(), world);
                addPlus(PLUS_SIZE, x2, y2, randomTile(), world);
                x1 += PLUS_SIZE;
                x2 += PLUS_SIZE;
                y1 += 2 * PLUS_SIZE;
                y2 += 2 * PLUS_SIZE;
            }
            x1 = shiftx;
            x2 = shiftx;
            y1 = shifty;
            y2 = shifty;

            x1 -= 2 * i * PLUS_SIZE;
            x2 += 2 * i * PLUS_SIZE;
            y1 += i * PLUS_SIZE;
            y2 -= i * PLUS_SIZE;
        }

    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            case 3: return Tileset.GRASS;
            case 4: return Tileset.SAND;
            case 5: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }
}
