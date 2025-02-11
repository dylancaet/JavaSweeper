package main.minesweeper.game;

import main.minesweeper.input.GameInput;
import main.minesweeper.input.InputHandler;
import main.minesweeper.tile.ExplosiveTile;
import main.minesweeper.tile.TileState;

public class GameManager
{
    private GameLogic game;
    private InputHandler inputHandler;
    private boolean alive = false;

    public GameManager(int explosionCount, int width, int height, long seed)
    {
        this.game = new GameLogic(height, width, explosionCount, seed);
        this.inputHandler = new InputHandler(new int[]{width-1, height-1});
    }

    public void start() {
        game.setupTiles();
        alive = true;
    }

    public void tick() {
        display();

        GameInput input = inputHandler.awaitInput();

        processInput(input);

        if (game.isOver())
            alive = false;
    }

    public void end() {
        display();
    }

    public void processInput(GameInput input) {
        int[] lastCoord = inputHandler.getLastCoord();
        switch (input)
        {
            case FLAG: {
                game.flag(lastCoord[1], lastCoord[0]);
                break;
            }
            case INTERACT: {
                game.interact(lastCoord[1], lastCoord[0]);
                break;
            }
        }
    }

    public void display()
    {

        System.out.print(" ");
        for (int i = 0; i < game.width; i++)
        {
            System.out.print("  "+(i+1)+" ");
        }
        System.out.print("\n");

        int[] lastCoord = inputHandler.getLastCoord();
        for (int col = 0; col < game.height; col++)
        {
            System.out.print(col+1 + "|");
            for (int row = 0; row < game.width; row++)
            {
                if (lastCoord != null)
                {
                    int coordRow = lastCoord[0];
                    int coordCol = lastCoord[1];
                    if (coordRow == row && coordCol == col) {
                        System.out.print("\033[47m "+game.getTile(col, row).getIcon() + " \033[0m|");
                        continue;
                    }
                }
                System.out.print(game.getTile(col, row).getIcon() + "  |");
            }
            System.out.print("\n");
        }
    }

    public boolean isAlive(){
        return alive;
    }

    public void debug(boolean value) {
//        for (int i = 0; i < game.height; i++)
//        {
//            for (int j = 0; j < game.width; j++)
//            {
//                game.getTile(i, j).setState(TileState.REVEALED);
//            }
//        }
//
        for (ExplosiveTile t : game.getExplosiveLocations().values())
        {
            t.setState(TileState.REVEALED);
        }
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public GameLogic getGame() {
        return game;
    }
}
