package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionPickTest {
    private Game game;
    private Backpack backpack;
    private ActionPick actionPick;

    @BeforeEach
    public void setUp() {
        game = new Game();
        backpack = new Backpack(5);
        actionPick = new ActionPick(game, backpack);
    }

    @Test
    public void testExecutePickItem() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Item item = new Item("item", "Item description", true);
        currentArea.addItem(item);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "item" };
        String expected = "Sebral jsem predmet 'item' a ulozil ho do inventare.";
        String result = actionPick.execute(parameters);
        assertEquals(expected, result);
        assertTrue(backpack.containsItem("item"));
        assertFalse(currentArea.containsItem("item"));
    }

    @Test
    public void testExecutePickNonExistentItem() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "fadgdhfffsafsf" };
        String expected = "Takovej predmet tady nevidim.";
        String result = actionPick.execute(parameters);
        assertEquals(expected, result);
        assertFalse(backpack.containsItem("nonexistent"));
    }

    @Test
    public void testExecutePickUnmovableItem() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Item item = new Item("nehybny", "nehybny item", false);
        currentArea.addItem(item);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "nehybny" };
        String expected = "Tohle neunesu.";
        String result = actionPick.execute(parameters);
        assertEquals(expected, result);
        assertFalse(backpack.containsItem("nehybny"));
        assertTrue(currentArea.containsItem("nehybny"));
    }

    @Test
    public void testExecutePickFullBackpack() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Item item = new Item("item", "Item description", true);
        currentArea.addItem(item);
        for (int i = 0; i < 5; i++) {
            backpack.addItem(new Item("vypln" + i, "vypln batohu", true));
        }
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "item" };
        String expected = "Batoh je plny. Nejdrive musim neco zahodit.";
        String result = actionPick.execute(parameters);
        assertEquals(expected, result);
        assertFalse(backpack.containsItem("item"));
        assertTrue(currentArea.containsItem("item"));
    }

    @Test
    public void testExecutePickNoParameter() {
        String[] parameters = {};
        String expected = "Musis mi rict, co mam sebrat";
        String result = actionPick.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecutePickMultipleItems() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Item item1 = new Item("item1", "Item 1 description", true);
        Item item2 = new Item("item2", "Item 2 description", true);
        currentArea.addItem(item1);
        currentArea.addItem(item2);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "item1", "item2" };
        String expected = "Nemuzu sebrat vice predmetu soucasne";
        String result = actionPick.execute(parameters);
        assertEquals(expected, result);
        assertFalse(backpack.containsItem("item1"));
        assertFalse(backpack.containsItem("item2"));
        assertTrue(currentArea.containsItem("item1"));
        assertTrue(currentArea.containsItem("item2"));
    }
}
