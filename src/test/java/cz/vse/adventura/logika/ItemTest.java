package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testGetName() {
        Item item = new Item("klíč", "Rezavý klíč");
        assertEquals("klíč", item.getName());
    }

    @Test
    public void testGetDescription() {
        Item item = new Item("klíč", "Rezavý klíč");
        assertEquals("Rezavý klíč", item.getDescription());
    }

    @Test
    public void testIsMoveable() {
        Item movableItem = new Item("kniha", "Zajímavá kniha", true);
        Item immovableItem = new Item("Kámen", "Prostě kámen", false);
        assertTrue(movableItem.isMoveable());
        assertFalse(immovableItem.isMoveable());
    }

    @Test
    public void testSetMoveable() {
        Item item = new Item("kniha", "Zajímavá kniha", true);
        assertTrue(item.isMoveable());
        item.setMoveable(false);
        assertFalse(item.isMoveable());
        item.setMoveable(true);
        assertTrue(item.isMoveable());
    }

    @Test
    public void testSetProzkoumanaAndGetProzkoumana() {
        Item item = new Item("kniha", "Zajímavá kniha");
        assertFalse(item.getProzkoumana());
        item.setProzkoumana(true);
        assertTrue(item.getProzkoumana());
        item.setProzkoumana(false);
        assertFalse(item.getProzkoumana());
    }

    @Test
    public void testIsLocked() {
        Item lockedItem = new Item("truhla", "Zamklá truhla", true, false, true);
        Item unlockedItem = new Item("dveře", "Odemklé dveře", true, false, false);
        assertTrue(lockedItem.isLocked());
        assertFalse(unlockedItem.isLocked());
    }

    @Test
    public void testSetLocked() {
        Item item = new Item("chest", "Zamcena truhla", true, false, true);
        assertTrue(item.isLocked());
        item.setLocked(false);
        assertFalse(item.isLocked());
        item.setLocked(true);
        assertTrue(item.isLocked());
    }
}
