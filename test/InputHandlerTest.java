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
}
