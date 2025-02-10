import main.minesweeper.tile.NumberTile;
import main.minesweeper.tile.TileState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest
{
    @Test
    void number_tile_icon_check() {
        NumberTile t = new NumberTile(0, 0);
        t.setState(TileState.REVEALED);
        t.setExplosionsNearby(4);

        assertEquals("4", t.getIcon());
    }


}
