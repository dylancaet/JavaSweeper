package main.minesweeper.game;

import main.minesweeper.grid.Grid;
import main.minesweeper.input.GameInput;
import main.minesweeper.input.InputHandler;
import main.minesweeper.tile.Tile;
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

    public void tick() {
        display();

        GameInput input = inputHandler.awaitInput();

        processInput(input);
    }

    public void processInput(GameInput input) {
        switch (input)
        {
            case FLAG: {
                grid.flag(inputHandler.getLastCoord());
                break;
            }
            case INTERACT: {
                break;
            }
        }
    }

    public void display()
    {
        int[] lastCoord = inputHandler.getLastCoord();
        for (int col = 0; col < grid.height; col++)
        {
            for (int row = 0; row < grid.width; row++)
            {
                if (lastCoord != null)
                {
                    int coordRow = lastCoord[0];
                    int coordCol = lastCoord[1];
                    if (coordRow == row && coordCol == col) {
                        System.out.print("\033[51m"+grid.getTile(col, row).getIcon() + " \033[0m| ");
                        continue;
                    }
                }
                System.out.print(grid.getTile(col, row).getIcon() + " | ");
            }
            System.out.print("\n");
        }
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
