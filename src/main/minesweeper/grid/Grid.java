package main.minesweeper.grid;

import main.minesweeper.tile.Tile;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private long seed;
    private int height;
    private int width;
    private int explosives;
    private Tile[][] grid;
    private Random random;

    public Grid(int height, int width, int explosives) {
        this.height = height;
        this.width = width;
        this.explosives = explosives;
        this.grid = new Tile[height][width];
        this.seed = System.currentTimeMillis();
        this.random = new Random(this.seed);
    }

    public Grid(int height, int width, int explosives, int seed) {
        this(height, width, explosives);
        this.seed = seed;
        this.random = new Random(this.seed);
    }

    public void setupTiles(){
        this.grid = new Tile[height][width];
        initExplosionTiles();
        initNumberTiles(); // for smaller grids, it should be fine to initialise them all at the start
    }

    private void initExplosionTiles(){

    }

    private void initNumberTiles(){

    }

    public void interact(int[] coord) {

    }

    public void flag(int[] coord) {

    }


}
