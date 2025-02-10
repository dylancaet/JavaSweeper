package main.minesweeper.grid;

import main.minesweeper.tile.ExplosiveTile;
import main.minesweeper.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Grid {
    private long seed;
    private int height;
    private int width;
    private int explosives;
    private Tile[][] grid;
    private HashMap<int[], Tile> explosiveLocations;
    private Random random;

    public Grid(int height, int width, int explosives) {
        this.height = height;
        this.width = width;
        this.explosives = explosives;
        this.explosiveLocations = new HashMap<int[], Tile>();
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
        while (explosiveLocations.size() < explosives) {
            int[] coord = new int[2];
            coord[0] = random.nextInt(0, width);
            coord[1] = random.nextInt(0, height);

            if (explosiveLocations.containsKey(coord))
                continue;

            ExplosiveTile tile = new ExplosiveTile(coord[0], coord[1]);
            explosiveLocations.put(coord, tile);
            grid[coord[1]][coord[0]] =  tile;
        }
    }

    private void initNumberTiles(){

    }

    public Tile getTile(int column, int row)
    {
        return grid[column][row];
    }

    public void interact(int[] coord) {

    }

    public void flag(int[] coord) {

    }

    public HashMap<int[], Tile> getExplosiveLocations() {
        return explosiveLocations;
    }
}
