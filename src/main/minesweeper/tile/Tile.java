package main.minesweeper.tile;

public abstract class Tile {
    public final int x;
    public final int y;
    private TileState state;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TileState getState(){
        return state;
    }

    public abstract char getIcon();
}
