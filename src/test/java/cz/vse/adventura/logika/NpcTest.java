package cz.vse.adventura.logika;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import cz.vse.adventurakrem22.game.Npc;
import org.junit.jupiter.api.Test;

public class NpcTest {

    @Test
    public void testGetName() {
        Npc npc = new Npc("Matěj", "Kamarádský");
        assertEquals("Matěj", npc.getName());
    }

    @Test
    public void testGetDescription() {
        Npc npc = new Npc("Matěj", "Kamarádský");
        assertEquals("Kamarádský", npc.getDescription());
    }

    @Test
    public void testGetDialog() {
        Npc talkingNpc = new Npc("Matěj", "Kamarádský", true, "Jak se máš?");
        Npc nonTalkingNpc = new Npc("Bobek", "Vylezl z klobouku");
        assertEquals("Jak se máš?", talkingNpc.getDialog());
        assertEquals(null, nonTalkingNpc.getDialog());
    }

    @Test
    public void testTalks() {
        Npc talkingNpc = new Npc("Matěj", "Kamarádský", true, "Jak se máš?");
        Npc nonTalkingNpc = new Npc("Bobek", "Vylezl z klobouku");
        assertTrue(talkingNpc.talks());
        assertFalse(nonTalkingNpc.talks());
    }
}
