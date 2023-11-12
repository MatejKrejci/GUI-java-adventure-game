package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionInvestigateTest {
    private Game game;
    private ActionInvestigate action;

    @BeforeEach
    public void setup() {
        game = new Game(new Backpack(3));
        action = new ActionInvestigate(game, new Backpack(3));
    }

    @Test
    public void testExecute_Item() {
        Area currentArea = new Area("test_area", "Test area","Test Area");
        Item item = new Item("item", "Item description", true);
        currentArea.addItem(item);
        game.getWorld().setCurrentArea(currentArea);
        String result = action.execute(new String[]{"item"});
        String expected = "Prozkoumávám předmět 'item'.\nItem description";
        assertEquals(expected, result);
    }

    @Test
    public void testExecute_Npc() {
        Area currentArea = new Area("test_area", "Test area description","Test Area");
        Npc npc = new Npc("npc", "NPC description", true, "NPC dialogue");
        currentArea.addNpc(npc);
        game.getWorld().setCurrentArea(currentArea);
        String result = action.execute(new String[]{"npc"});
        String expected = "Zjisťuješ kdo je 'npc'.\nNPC description";
        assertEquals(expected, result);
    }

    @Test
    public void testExecute_NoParameters() {
        String result = action.execute(new String[]{});
        String expected = "Musíš říct, co mám prozkoumat";
        assertEquals(expected, result);
    }

    @Test
    public void testExecute_MultipleParameters() {
        String result = action.execute(new String[]{"item1", "item2"});
        String expected = "Nemůžu prozkoumávat dva předměty současně";
        assertEquals(expected, result);
    }

    @Test
    public void testExecute_UnknownObject() {
        Area currentArea = new Area("test_area", "Test area description","Test Area");
        game.getWorld().setCurrentArea(currentArea);
        String result = action.execute(new String[]{"higdongosdng"});
        String expected = "To nemůžu prozkoumat, protože v téhle místnosti to není";
        assertEquals(expected, result);
    }
}
