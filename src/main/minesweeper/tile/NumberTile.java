package main.minesweeper.tile;

public class NumberTile extends Tile
{
    private final String[] charSet = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}; // max 8
    private int explosionsNearby;

    public NumberTile(int x, int y) {
        super(x, y);
        this.explosionsNearby = 0;
    }

    public void setExplosionsNearby(int nearby)
    {
        explosionsNearby = nearby;
        setIcon(charSet[explosionsNearby]);
    }

    public int getExplosionsNearby()
    {
        return explosionsNearby;
    }
}
