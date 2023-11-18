package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionUnlockTest {
    private Game game;
    private Backpack backpack;
    private ActionUnlock actionUnlock;

    @BeforeEach
    public void setUp() {
        game = new Game(new Backpack(3));
        backpack = new Backpack(3);
        actionUnlock = new ActionUnlock(game, backpack);
    }

    @Test
    public void testExecuteUnlockChestWithKey() {
        Area currentArea = new Area("testarea", "testovaci lokace","TestArea");
        Item chest = new Item("truhla", "stara truhla", false,false, true);
        chest.setLocked(true);
        currentArea.addItem(chest);
        Item key = new Item("klic", "klasicky klic", false);
        backpack.addItem(key);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "truhla" };
        String expected = "Odemkl jsem truhlu. Nyní ji ještě musíš prozkoumat.";
        String result = actionUnlock.execute(parameters);
        assertFalse(chest.isLocked());
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteUnlockChestWithoutKey() {
        Area currentArea = new Area("testarea", "testovaci lokace","TestArea");
        Item chest = new Item("truhla", "stara truhla", false,false, true);
        chest.setLocked(true);
        currentArea.addItem(chest);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "truhla" };
        String expected = "Bez klíče tuhle truhlu neodemknu";
        String result = actionUnlock.execute(parameters);
        assertTrue(chest.isLocked());
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteUnlockUnlockedChest() {
        Area currentArea = new Area("testarea", "testovaci lokace","TestArea");
        Item chest = new Item("truhla", "stara truhla", false,false, true);
        chest.setLocked(false);
        currentArea.addItem(chest);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "truhla" };
        String expected = "Truhla je již odemčená.";
        String result = actionUnlock.execute(parameters);
        assertFalse(chest.isLocked());
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteUnlockNonExistentItem() {
        Area currentArea = new Area("testarea", "testovaci lokace","TestArea");
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "hihfasj" };
        String expected = "Předmět 'hihfasj' není v této místnosti.";
        String result = actionUnlock.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteUnlockNoParameter() {
        String[] parameters = {};
        String expected = "Musíš specifikovat, co chceš odemknout.";
        String result = actionUnlock.execute(parameters);
        assertEquals(expected, result);
    }
}
