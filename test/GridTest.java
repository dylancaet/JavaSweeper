import main.minesweeper.grid.Grid;
import main.minesweeper.tile.ExplosiveTile;
import main.minesweeper.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {
    @Test
    void ensure_explosive_count_map() {
        int expectedExplosions = 5;
        int foundExplosions = 0;

        Grid g = new Grid(8, 10, 5);
        g.setupTiles();

        for (Tile t : g.getExplosiveLocations().values()) {
            if (g.getTile(t.y, t.x) instanceof ExplosiveTile) // double check grid value is actually an explosive tile
                foundExplosions++;
        }

        assertEquals(expectedExplosions, foundExplosions);
    }

    @Test
    void ensure_explosive_count_manual() {
        int expectedExplosions = 5;
        int foundExplosions = 0;

        Grid g = new Grid(8, 10, expectedExplosions);
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
}
