package main.minesweeper.tile;

public abstract class Tile {
    public final int row;
    public final int col;
    private String icon;
    private TileState state;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.state = TileState.HIDDEN;
    }

    public TileState getState(){
        return state;
    }

    public void setState(TileState tileState)
    {
        state = tileState;
    }


    public String getIcon() {
        switch (state) {
            case HIDDEN: return " ";
            case FLAGGED: return "⚑";
            case REVEALED: return icon;
            default: return "ERR";
        }
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
