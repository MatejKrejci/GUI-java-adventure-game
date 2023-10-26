package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.ActionLocationInfo;
import cz.vse.adventurakrem22.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionLocationInfoTest {
    private Game game;
    private ActionLocationInfo action;

    @BeforeEach
    public void setUp() {
        game = new Game();
        action = new ActionLocationInfo(game);
    }

    @Test
    public void testExecute() {
        String expected = "-Tohle je moje cela. Smrdí to tu chcankama a tvoji spoluvězni jsou šmejdi.\n" + "----------------------------------------------------------------------\n"
            + "Exits: Chodba";
        String result = action.execute(new String[0]);
        assertEquals(expected, result);
    }
}
