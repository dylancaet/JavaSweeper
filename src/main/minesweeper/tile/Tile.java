package main.minesweeper.tile;

public abstract class Tile {
    public final int row;
    public final int col;
    private String icon;
    private TileState state;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
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
