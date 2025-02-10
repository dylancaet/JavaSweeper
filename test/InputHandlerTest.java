import main.minesweeper.input.GameInput;
import main.minesweeper.input.InputHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputHandlerTest
{
    @Test
    void receive_flag_input() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.forceInput("flag");

        assertEquals(GameInput.FLAG, inputHandler.getLastInput());
    }

    @Test
    void receive_interact_input() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.forceInput("interact");

        assertEquals(GameInput.INTERACT, inputHandler.getLastInput());
    }

    @Test
    void receive_coord_input() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.forceInput("3x10");

        assertEquals(GameInput.COORD, inputHandler.getLastInput());
        assertEquals(3, inputHandler.getLastCoord()[0]);
        assertEquals(10, inputHandler.getLastCoord()[1]);
    }
}

