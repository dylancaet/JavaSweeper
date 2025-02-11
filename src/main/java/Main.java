package main.java;

import main.java.minesweeper.game.GameManager;

public class Main {
    public static void main(String[] args) {
        long seed = 5555555L;

        GameManager game = new GameManager(3, 10, 8);
        game.start();
//        game.debug(true);

        while(game.isAlive())
        {
            game.tick();
        }

        game.end();
    }
}
