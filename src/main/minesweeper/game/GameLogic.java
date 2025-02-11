package main.minesweeper.game;

import main.minesweeper.tile.ExplosiveTile;
import main.minesweeper.tile.NumberTile;
import main.minesweeper.tile.Tile;
import main.minesweeper.tile.TileState;

import java.util.*;

public class GameLogic {
    public final int height;
    public final int width;
    public final int explosives;

    private long seed;
    private Tile[][] grid;
    private HashMap<int[], ExplosiveTile> explosiveLocations; // r, c
    private Random random;
    private EndReason endReason;
    private boolean gameOver;

    public GameLogic(int height, int width, int explosives) {
        this.height = height;
        this.width = width;
        this.explosives = explosives;
        this.explosiveLocations = new HashMap<int[], ExplosiveTile>();
        this.grid = new Tile[height][width];
        this.seed = System.currentTimeMillis();
        this.random = new Random(this.seed);
        this.gameOver = false;
    }

    public GameLogic(int height, int width, int explosives, long seed) {
        this(height, width, explosives);
        this.seed = seed;
        this.random = new Random(this.seed);
    }

    public void setupTiles(){
        grid = new Tile[height][width];
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
                // checks 0, 1, 2, 3, 6, 5, 7, 8
                if (c+col < 0 || r+row < 0 || c+col >= height || r+row >= width || (col == 0 && row == r))
                    continue;

                tiles.add(getTile(c+col, r+row));
            }
        }

        return tiles;
    }

    public ArrayList<Tile> getAdjacentTiles(int r, int c)
    {
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        // top(+0, +1) left(-1, +0) right(+1, +0) down(+0, -1)
        if (isWithinGrid(r, c+1)) tiles.add(grid[c+1][r]);
        if (isWithinGrid(r-1, c)) tiles.add(grid[c][r-1]);
        if (isWithinGrid(r+1, c)) tiles.add(grid[c][r+1]);
        if (isWithinGrid(r, c-1)) tiles.add(grid[c-1][r]);

        return tiles;
    }

    private boolean isWithinGrid(int r, int c)  {
        return r >= 0 && c >= 0 && r < width && c < height;
    }


    public Tile getTile(int column, int row)
    {
        return grid[column][row];
    }

    // floodfill 0 number tiles
    // reveal >0 number tiles
    // explode explosive tiles
    public void interact(int col, int row) {
        Tile tile = grid[col][row];

        // NUMBER TILE
        if (tile instanceof NumberTile) {
            floodfill(col, row);
        }

        // EXPLOSION TILE
        if (tile instanceof ExplosiveTile) {
            // gameover + reveal all explosives
            endGame(EndReason.EXPLOSION);
        }

    }

    private void endGame(EndReason reason) {
        for (ExplosiveTile t : getExplosiveLocations().values())
            t.setState(TileState.REVEALED);

        endReason = reason;
        gameOver = true;
    }

    public boolean isOver()
    {
        return gameOver;
    }

    private void floodfill(int col, int row)
    {
        Tile currentTile = grid[col][row];
        if (currentTile.getState() == TileState.REVEALED && !(currentTile instanceof NumberTile))
            return;

        currentTile.setState(TileState.REVEALED);
        if (((NumberTile) currentTile).getExplosionsNearby() > 0)
            return;

        ArrayList<Tile> surroundingTiles = getSurroundingTiles(row, col);
        for (Tile t : surroundingTiles) {
            if (!(t instanceof NumberTile))
                continue;
            if (((NumberTile) t).getExplosionsNearby() == 0 && t.getState() != TileState.REVEALED) {
                floodfill(t.col, t.row);
            } else if (((NumberTile) t).getExplosionsNearby() > 0) {
                t.setState(TileState.REVEALED);
            }
        }

    }

    public void flag(int col, int row) {
        // 0:row, 1:col
        Tile tile = grid[col][row];
        TileState state = tile.getState();

        if (state == TileState.REVEALED)
            return;

        tile.setState(state == TileState.FLAGGED ? TileState.HIDDEN : TileState.FLAGGED);
    }

    public HashMap<int[], ExplosiveTile> getExplosiveLocations() {
        return explosiveLocations;
    }
}
