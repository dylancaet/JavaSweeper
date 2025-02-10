package main.minesweeper.grid;

import main.minesweeper.tile.ExplosiveTile;
import main.minesweeper.tile.NumberTile;
import main.minesweeper.tile.Tile;

import java.util.*;

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

    // future: would be better to store tiles in groups, makes floodfill fast & quicker
    private void initNumberTiles(){
        for (int col = 0; col < height; col++)
        {
            for (int row = 0; row < width; row++)
            {
                if (grid[col][row] != null)
                    continue;

                NumberTile tile = new NumberTile(row, col);

                int nearbyExplosives = 0;
                ArrayList<Tile> surrounding = getSurroundingTiles(row, col);
                for (Tile t : surrounding)
                {
                    if (t instanceof ExplosiveTile)
                        nearbyExplosives++;
                }
                tile.setExplosionsNearby(nearbyExplosives);

                grid[col][row] = tile;
            }
        }
    }

    /*
        0 1 2   x=0,y=1 = 1 2
        3 4 5             4 5
        6 7 8             7 8
     */
    public ArrayList<Tile> getSurroundingTiles(int r, int c) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        for (int col = -1; col < 2; col++)
        {
            for (int row = -1; row < 2; row++)
            {
                // checks 0, 1, 2, 3, 6
                if (c+col < 0 || r+row < 0 || c+col >= height || r+row >= width || (c+col == c && r+row == r))
                    continue;

                tiles.add(getTile(c+col, r+row));
            }
        }

        return tiles;
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
