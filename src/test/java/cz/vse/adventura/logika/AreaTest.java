package cz.vse.adventura.logika;

import static org.junit.jupiter.api.Assertions.*;

import cz.vse.adventurakrem22.game.Area;
import cz.vse.adventurakrem22.game.Item;
import cz.vse.adventurakrem22.game.Npc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setUp() {
        area = new Area("kuchyn", "Úchvatná krásná kuchyň", "Kuchyně");
    }

    @Test
    public void testGetName() {
        assertEquals("kuchyn", area.getName());
    }

    @Test
    public void testGetNameTwo() {
        assertEquals("Kuchyně", area.getNameTwo());
    }

    @Test
    public void testGetIsHidden() {
        assertFalse(area.getIsHidden());
    }

    @Test
    public void testSetIsHidden() {
        area.setIsHidden(true);
        assertTrue(area.getIsHidden());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Úchvatná krásná kuchyň", area.getDescription());
    }

    @Test
    public void testAddExit() {
        Area exit = new Area("sklep", "Hnusnej sklep", "Sklep");
        area.addExit(exit);
        assertTrue(area.hasExit("sklep"));
    }

    @Test
    public void testHasExit() {
        Area exit = new Area("sklep", "Hnusnej sklep", "Sklep");
        area.addExit(exit);
        assertTrue(area.hasExit("sklep"));
        assertFalse(area.hasExit("pokoj"));
    }

    @Test
    public void testGetExit() {
        Area exit = new Area("sklep", "Hnusnej sklep", "Sklep");
        area.addExit(exit);
        assertEquals(exit, area.getExit("sklep"));
        assertNull(area.getExit("pokoj"));
    }

    @Test
    public void testGetItems() {
        Item item = new Item("item", "Proste item");
        area.addItem(item);
        assertTrue(area.getItems().contains(item));
    }

    @Test
    public void testContainsItem() {
        Item item = new Item("item", "Proste item");
        area.addItem(item);
        assertTrue(area.containsItem("item"));
        assertFalse(area.containsItem("item2"));
    }

    @Test
    public void testGetItem() {
        Item item = new Item("item", "Proste item");
        area.addItem(item);
        assertEquals(item, area.getItem("item"));
        assertNull(area.getItem("item2"));
    }

    @Test
    public void testRemoveItem() {
        Item item = new Item("item", "Proste item");
        area.addItem(item);
        assertEquals(item, area.removeItem("item"));
        assertFalse(area.containsItem("item"));
        assertNull(area.removeItem("lzice"));
    }

    @Test
    public void testGetNpcs() {
        Npc npc = new Npc("matej", "Přátelský");
        area.addNpc(npc);
        assertTrue(area.getNpcs().contains(npc));
    }

    @Test
    public void testContainsNpc() {
        Npc npc = new Npc("matej", "Přátelský");
        area.addNpc(npc);
        assertTrue(area.containsNpc("matej"));
        assertFalse(area.containsNpc("lucka"));
    }

    @Test
    public void testGetNpc() {
        Npc npc = new Npc("matej", "Přátelský");
        area.addNpc(npc);
        assertEquals(npc, area.getNpc("matej"));
        assertNull(area.getNpc("lucka"));
    }

    @Test
    public void testRemoveNpc() {
        Npc npc = new Npc("matej", "Přátelský");
        area.addNpc(npc);
        assertEquals(npc, area.removeNpc("matej"));
        assertFalse(area.containsNpc("matej"));
        assertNull(area.removeNpc("lucka"));
    }
}
