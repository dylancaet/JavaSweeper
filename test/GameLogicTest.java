import main.minesweeper.game.GameLogic;
import main.minesweeper.tile.ExplosiveTile;
import main.minesweeper.tile.Tile;
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
}
