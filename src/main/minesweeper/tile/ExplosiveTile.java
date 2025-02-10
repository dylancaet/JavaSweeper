package main.minesweeper.tile;

public class ExplosiveTile extends Tile
{
    public ExplosiveTile(int x, int y) {
        super(x, y);
    }

    @Override
    public char getIcon() {
        return 'B';
    }
}
