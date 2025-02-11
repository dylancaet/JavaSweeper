package main.java.minesweeper.tile;

public class ExplosiveTile extends Tile
{
    public ExplosiveTile(int x, int y) {
        super(x, y);
        setIcon("\u001B[31mX\u001B[0m");
    }
}
