package main.minesweeper.input;

import java.util.Scanner;

public class InputHandler
{
    private final Scanner reader;
    private GameInput lastInput;

    public InputHandler() {
        this.reader = new Scanner(System.in);
    }

    public GameInput awaitInput()
    {
        GameInput input = GameInput.INVALID;

        while (input == GameInput.INVALID) {
            String userInput = reader.next();
            input = interpretInput(userInput);
        }

        return input;
    }

    private GameInput interpretInput(String userInput) {
        switch (userInput) {
            case "flag": {
                return GameInput.FLAG;
            }
        }
        return GameInput.INVALID;
    }

    public void forceInput(String userInput) {
        lastInput = interpretInput(userInput);
    }

    public GameInput getLastInput() {
        return lastInput;
    }
}

