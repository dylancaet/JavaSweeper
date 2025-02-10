package main.minesweeper.grid;

import main.minesweeper.tile.Tile;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private long seed;
    private int height;
    private int width;
    private Tile[][] grid;
    private Random random;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Tile[height][width];
        this.seed = System.currentTimeMillis();
        this.random = new Random(this.seed);
    }

    public Grid(int height, int width, int seed) {
        this(height, width);
        this.seed = seed;
        this.random = new Random(this.seed);
    }

    public void initGrid(){

    }

    private void initExplosionTiles(){

    }


    private void initNumberTiles(){

    }

}
