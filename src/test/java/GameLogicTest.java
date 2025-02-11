package test.java;

import main.java.minesweeper.game.GameLogic;
import main.java.minesweeper.game.GameManager;
import main.java.minesweeper.input.GameInput;
import main.java.minesweeper.tile.ExplosiveTile;
import main.java.minesweeper.tile.Tile;
import main.java.minesweeper.tile.TileState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogicTest {
    @Test
    void ensure_explosive_count_map() {
        int expectedExplosions = 5;
        int foundExplosions = 0;

        GameLogic g = new GameLogic(8, 10, 5);
        g.setupTiles();

        for (Tile t : g.getExplosiveLocations().values()) {
            if (g.getTile(t.col, t.row) instanceof ExplosiveTile) // double check grid value is actually an explosive tile
                foundExplosions++;
        }

        assertEquals(expectedExplosions, foundExplosions);
    }

    @Test
    void ensure_explosive_count_manual() {
        int expectedExplosions = 5;
        int foundExplosions = 0;

        GameLogic g = new GameLogic(8, 10, expectedExplosions);
        g.setupTiles();

        for (int c = 0; c < 8; c++) {
            for (int r = 0; r < 10; r++)
            {
                if (g.getTile(c, r) instanceof ExplosiveTile)
                    foundExplosions++;
            }
        }

        assertEquals(expectedExplosions, foundExplosions);
    }

    @Test
    void get_surrounding_tiles_most_left() {
        GameLogic g = new GameLogic(8, 10, 5);
        g.setupTiles();

        HashSet<Tile> confirmed_tiles = new HashSet<Tile>();
        confirmed_tiles.add(g.getTile(0, 0));
        confirmed_tiles.add(g.getTile(0, 1));
        confirmed_tiles.add(g.getTile(2, 0));
        confirmed_tiles.add(g.getTile(1, 1));
        confirmed_tiles.add(g.getTile(2, 1));

        ArrayList<Tile> tiles = g.getSurroundingTiles(0, 1);

        for(Tile t: tiles) {
            confirmed_tiles.remove(t);
        }

        assertEquals(0, confirmed_tiles.size());
    }

    @Test
    void get_surrounding_tiles_most_right() {
        GameLogic g = new GameLogic(8, 4, 5);
        g.setupTiles();

        HashSet<Tile> confirmed_tiles = new HashSet<Tile>();
        confirmed_tiles.add(g.getTile(6, 2));
        confirmed_tiles.add(g.getTile(6, 3));
        confirmed_tiles.add(g.getTile(7, 2));

        ArrayList<Tile> tiles = g.getSurroundingTiles(3, 7);

        for(Tile t: tiles) {
            confirmed_tiles.remove(t);
        }

        assertEquals(0, confirmed_tiles.size());
    }

    @Test
    void floodfill_test() {
        GameManager g = new GameManager(10, 10, 8, 5555555);
        g.start();

        g.getInputHandler().forceInput("1x1");
        g.processInput(GameInput.INTERACT);
//        g.display();

        HashSet<Tile> confirmed_tiles = new HashSet<Tile>();
        confirmed_tiles.add(g.getGame().getTile(0, 0));
        confirmed_tiles.add(g.getGame().getTile(0, 1));
        confirmed_tiles.add(g.getGame().getTile(0, 2));
        confirmed_tiles.add(g.getGame().getTile(0, 3));
        confirmed_tiles.add(g.getGame().getTile(1, 0));
        confirmed_tiles.add(g.getGame().getTile(1, 1));
        confirmed_tiles.add(g.getGame().getTile(1, 2));
        confirmed_tiles.add(g.getGame().getTile(1, 3));
        confirmed_tiles.add(g.getGame().getTile(2, 0));
        confirmed_tiles.add(g.getGame().getTile(2, 1));
        confirmed_tiles.add(g.getGame().getTile(2, 2));
        confirmed_tiles.add(g.getGame().getTile(2, 3));

        confirmed_tiles.removeIf(t -> t.getState() == TileState.REVEALED);

        assertEquals(0, confirmed_tiles.size());
    }
}
