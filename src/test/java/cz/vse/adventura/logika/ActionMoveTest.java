package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.ActionMove;
import cz.vse.adventurakrem22.game.Area;
import cz.vse.adventurakrem22.game.Game;
import cz.vse.adventurakrem22.game.GameWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionMoveTest {
    private Game game;
    private ActionMove actionMove;

    @BeforeEach
    public void setUp() {
        game = new Game();
        actionMove = new ActionMove(game);
    }

    @Test
    public void testExecuteValidMove() {
        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Area targetArea = new Area("targetarea", "Target Area Description", "Target Area");
        currentArea.addExit(targetArea);
        String result = actionMove.execute(new String[]{"targetarea"});
        String expected = "Odešel jsem do lokace 'Target Area'.\nTarget Area Description";
        assertEquals(expected, result);
        assertEquals(targetArea, world.getCurrentArea());
    }

    @Test
    public void testExecuteInvalidMove() {
        Area currentArea = game.getWorld().getCurrentArea();
        String result = actionMove.execute(new String[]{"afsfrgrhdfsdas"});
        String expected = "Tam se odtud jít neda.";
        assertEquals(expected, result);
        assertEquals(currentArea, game.getWorld().getCurrentArea());
    }

    @Test
    public void testExecuteSameLocation() {
        Area currentArea = game.getWorld().getCurrentArea();
        String result = actionMove.execute(new String[]{currentArea.getNameTwo()});
        String expected = "Už tady jsem.";
        assertEquals(expected, result);
        assertEquals(currentArea, game.getWorld().getCurrentArea());
    }
}
