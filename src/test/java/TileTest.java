package test.java;

import main.java.minesweeper.tile.NumberTile;
import main.java.minesweeper.tile.TileState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileTest
{
    @Test
    void number_tile_icon_check() {
        NumberTile t = new NumberTile(0, 0);
        t.setState(TileState.REVEALED);
        t.setExplosionsNearby(4);

        assertEquals("\u001B[34m4\u001B[0m", t.getIcon());
    }


}
