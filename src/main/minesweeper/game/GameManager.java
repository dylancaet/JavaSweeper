package main.minesweeper.game;

import main.minesweeper.grid.Grid;
import main.minesweeper.input.InputHandler;
import main.minesweeper.tile.TileState;

public class GameManager
{
    private Grid grid;
    private InputHandler inputHandler;
    private boolean alive = false;

    public GameManager(int explosionCount, int width, int height)
    {
        this.grid = new Grid(height, width, explosionCount);
        this.inputHandler = new InputHandler();
    }

    public void start() {
        grid.setupTiles();
        alive = true;
    }

    public void display()
    {
        for (int col = 0; col < grid.height; col++)
        {
            for (int row = 0; row < grid.width; row++)
            {
                System.out.print(grid.getTile(col, row).getIcon() + " | ");
            }
            System.out.print("\n");
        }
    }

    public void tick() {
        display();

        inputHandler.awaitInput();
    }

    public boolean isAlive(){
        return alive;
    }

    public void debug(boolean value) {
        for (int i = 0; i < grid.height; i++)
        {
            for (int j = 0; j < grid.width; j++)
            {
                grid.getTile(i, j).setState(TileState.REVEALED);
            }
        }
    }

}
