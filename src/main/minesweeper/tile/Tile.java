package main.minesweeper.tile;

public abstract class Tile {
    public final int x;
    public final int y;
    private String icon;
    private TileState state;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TileState getState(){
        return state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
