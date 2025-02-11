package main.java.minesweeper.tile;

public class NumberTile extends Tile
{
    private final String[] charSet = new String[]
            {"0", "\033[94m1\033[0m", "\033[92m2\033[0m",
             "\033[91m3\033[0m", "\033[34m4\033[0m", "\033[31m5\033[0m",
             "\033[96m6\033[0m", "\033[30m7\033[0m", "\033[37m8\033[0m", "9"}; // max 8

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
