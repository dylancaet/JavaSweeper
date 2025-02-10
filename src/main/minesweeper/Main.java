package main.minesweeper;

import main.minesweeper.game.GameManager;

public class Main {
    public static void main(String[] args) {
        GameManager game = new GameManager(10, 10, 8);
        game.start();
//        game.debug(true);

        while(game.isAlive())
        {
            game.tick();
        }
    }
}
