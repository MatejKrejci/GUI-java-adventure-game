package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionLookAroundTest {
    private Game game;
    private ActionLookAround actionLookAround;

    @BeforeEach
    public void setUp() {
        game = new Game();
        actionLookAround = new ActionLookAround(game);
    }

    @Test
    public void testExecuteEmptyArea() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = {};
        String expected = "Nic kolem sebe nevidím.";
        String result = actionLookAround.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteWithItems() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Item item1 = new Item("item1", "Item 1 description", true);
        Item item2 = new Item("item2", "Item 2 description", true);
        currentArea.addItem(item1);
        currentArea.addItem(item2);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = {};
        String expected = "Kolem sebe vidím:\n-Predmety: item1, item2";
        String result = actionLookAround.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteWithNpcs() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Npc npc1 = new Npc("npc1", "NPC 1");
        Npc npc2 = new Npc("npc2", "NPC 2");
        currentArea.addNpc(npc1);
        currentArea.addNpc(npc2);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = {};
        String expected = "Kolem sebe vidím:\n-Postavy: npc1, npc2";
        String result = actionLookAround.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteWithItemsAndNpcs() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Item item1 = new Item("item1", "Item 1 description", true);
        Item item2 = new Item("item2", "Item 2 description", true);
        Npc npc1 = new Npc("npc1", "NPC 1");
        Npc npc2 = new Npc("npc2", "NPC 2");
        currentArea.addItem(item1);
        currentArea.addItem(item2);
        currentArea.addNpc(npc1);
        currentArea.addNpc(npc2);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = {};
        String expected = "Kolem sebe vidím:\n-Predmety: item1, item2\n-Postavy: npc1, npc2";
        String result = actionLookAround.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteWithParameters() {
        String[] parameters = { "jdaojojf" };
        String expected = "Tomu nerozumím, umím se rozhlédnout jen kolem sebe, ne 'na něco'.";
        String result = actionLookAround.execute(parameters);
        assertEquals(expected, result);
    }
}
