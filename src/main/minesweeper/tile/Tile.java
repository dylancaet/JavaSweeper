package main.minesweeper.tile;

public abstract class Tile {
    public final char icon;
    private TileState state;

    protected Tile(char icon) {
        this.icon = icon;
    }

    public TileState getState(){
        return state;
    }
}
