package main.minesweeper.input;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler
{
    private final Pattern coordPattern;
    private final Scanner reader;

    private GameInput lastInput;
    private int[] lastCoord;

    public InputHandler() {
        this.reader = new Scanner(System.in);
        this.coordPattern = Pattern.compile("^[0-9]+x[0-9]+$");
        this.lastCoord = new int[2];
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
                lastInput = GameInput.FLAG;
                return GameInput.FLAG;
            }
            case "interact": {
                lastInput = GameInput.INTERACT;
                return GameInput.INTERACT;
            }
        }

        if (coordPattern.matcher(userInput).find()) {
            int indexOfX = userInput.indexOf('x');
            int x = Integer.parseInt(userInput.substring(0, indexOfX))-1;
            int y = Integer.parseInt(userInput.substring(indexOfX+1, userInput.length()))-1;

            lastCoord[0] = x;
            lastCoord[1] = y;
            lastInput = GameInput.COORD;
            return GameInput.COORD;
        }

        return GameInput.INVALID;
    }

    public void forceInput(String userInput) {
        lastInput = interpretInput(userInput);
    }

    public GameInput getLastInput() {
        return lastInput;
    }

    public int[] getLastCoord() {
        return lastCoord;
    }
}

