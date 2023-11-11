package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.ActionTerminate;
import cz.vse.adventurakrem22.game.Backpack;
import cz.vse.adventurakrem22.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionTerminateTest {
    private Game game;
    private ActionTerminate action;

    @BeforeEach
    public void setup() {
        game = new Game(new Backpack(3));
        action = new ActionTerminate(game);
    }

    @Test
    public void testExecute() {
        String result = action.execute(new String[]{});
        String expected = "Hra byla ukoncena prikazem KONEC.";
        assertEquals(expected, result);
        assertTrue(game.isGameOver());
    }

    @Test
    public void testGetName() {
        String name = action.getName();
        String expected = "konec/vypnout/ukoncit/vypni/ukonci";
        assertEquals(expected, name);
    }
}
