package main.minesweeper.game;

import main.minesweeper.grid.Grid;
import main.minesweeper.input.InputHandler;

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
                System.out.print(grid.getTile(col, row).getIcon() + "|");
            }
            System.out.print("\n");
        }
    }
}
