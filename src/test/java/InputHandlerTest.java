package test.java;

import main.java.minesweeper.input.GameInput;
import main.java.minesweeper.input.InputHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest
{
    @Test
    void receive_flag_input() {
        InputHandler inputHandler = new InputHandler(new int[]{1, 1});
        inputHandler.forceInput("flag");

        assertEquals(GameInput.FLAG, inputHandler.getLastInput());
    }

    @Test
    void receive_interact_input() {
        InputHandler inputHandler = new InputHandler(new int[]{1, 1});
        inputHandler.forceInput("interact");

        assertEquals(GameInput.INTERACT, inputHandler.getLastInput());
    }

    @Test
    void receive_coord_input() {
        InputHandler inputHandler = new InputHandler(new int[]{4, 10});
        inputHandler.forceInput("3x10");

        assertEquals(GameInput.COORD, inputHandler.getLastInput());
        assertEquals(2, inputHandler.getLastCoord()[0]);
        assertEquals(9, inputHandler.getLastCoord()[1]);
    }
}

