package main.java;

import main.java.minesweeper.game.GameManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long seed = 5555555L;
        int explosions = 10;
        int width = 9;
        int height = 9;

        String play = "yes";

        while (play.equals("yes")) {
            GameManager game = new GameManager(explosions, width, height);
            game.start();

            while(game.isAlive())
            {
                game.tick();
            }

            game.end();

            System.out.print("play again? (yes/no): ");
            Scanner reader = new Scanner(System.in);
            play = reader.next().toLowerCase();
        }

    }
}
