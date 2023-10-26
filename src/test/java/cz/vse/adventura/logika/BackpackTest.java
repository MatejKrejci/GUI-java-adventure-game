package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.Backpack;
import cz.vse.adventurakrem22.game.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackpackTest {
    private Backpack backpack;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;

    @BeforeEach
    public void setUp() {
        backpack = new Backpack(3);
        item1 = new Item("item1", "Item 1", true);
        item2 = new Item("item2", "Item 2", true);
        item3 = new Item("item3", "Item 3", true);
        item4 = new Item("item4", "Item 4", true);
    }

    @Test
    public void testIsFull() {
        assertFalse(backpack.isFull());
        backpack.addItem(item1);
        assertFalse(backpack.isFull());
        backpack.addItem(item2);
        assertFalse(backpack.isFull());
        backpack.addItem(item3);
        assertTrue(backpack.isFull());
    }

    @Test
    public void testContainsItem() {
        assertFalse(backpack.containsItem("item1"));
        assertFalse(backpack.containsItem("item2"));
        assertFalse(backpack.containsItem("item3"));
        backpack.addItem(item1);
        assertTrue(backpack.containsItem("item1"));
        assertFalse(backpack.containsItem("item2"));
        assertFalse(backpack.containsItem("item3"));
        backpack.addItem(item2);
        assertTrue(backpack.containsItem("item1"));
        assertTrue(backpack.containsItem("item2"));
        assertFalse(backpack.containsItem("item3"));
        backpack.removeItem("item1");
        assertFalse(backpack.containsItem("item1"));
        assertTrue(backpack.containsItem("item2"));
        assertFalse(backpack.containsItem("item3"));
    }

    @Test
    public void testAddItem() {
        assertTrue(backpack.addItem(item1));
        assertTrue(backpack.addItem(item2));
        assertTrue(backpack.addItem(item3));
        assertTrue(backpack.containsItem("item1"));
        assertFalse(backpack.addItem(item1));
        assertFalse(backpack.addItem(item4));
    }

    @Test
    public void testRemoveItem() {
        assertNull(backpack.removeItem("item1"));
        backpack.addItem(item1);
        assertEquals(item1, backpack.removeItem("item1"));
        assertFalse(backpack.containsItem("item1"));
        backpack.addItem(item2);
        backpack.addItem(item3);
        assertEquals(item2, backpack.removeItem("item2"));
        assertFalse(backpack.containsItem("item2"));
        assertTrue(backpack.containsItem("item3"));
    }

    @Test
    public void testGetContents() {
        assertEquals("Tvůj batoh je prázdný", backpack.getContents());
        backpack.addItem(item1);
        assertEquals("V batohu máš: \n- item1\n", backpack.getContents());
        backpack.addItem(item2);
        assertEquals("V batohu máš: \n- item1\n- item2\n", backpack.getContents());
        backpack.removeItem("item1");
        assertEquals("V batohu máš: \n- item2\n", backpack.getContents());
        backpack.removeItem("item2");
        assertEquals("Tvůj batoh je prázdný", backpack.getContents());
    }
}
