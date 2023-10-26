package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionDropTest {
    private Game game;
    private Backpack backpack;
    private ActionDrop actionDrop;

    @BeforeEach
    public void setUp() {
        game = new Game();
        backpack = new Backpack(5);
        actionDrop = new ActionDrop(game, backpack);
    }

    @Test
    public void testExecuteDropItem() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Item item = new Item("item", "Item description", true);
        backpack.addItem(item);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "item" };
        String expected = "Vyhodil jsem predmet 'item' do aktualni lokace.";
        String result = actionDrop.execute(parameters);
        assertEquals(expected, result);
        assertFalse(backpack.containsItem("item"));
        assertTrue(currentArea.containsItem("item"));
    }

    @Test
    public void testExecuteDropNonExistentItem() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "hihidgsin" };
        String expected = "Takovou vec v batohu nemam.";
        String result = actionDrop.execute(parameters);
        assertEquals(expected, result);
        assertFalse(currentArea.containsItem("hihidgsin"));
    }

    @Test
    public void testExecuteDropNoParameter() {
        String[] parameters = {};
        String expected = "Musis mi rict, co chces vyhodit.";
        String result = actionDrop.execute(parameters);
        assertEquals(expected, result);
    }
}
