package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.ActionTalk;
import cz.vse.adventurakrem22.game.Area;
import cz.vse.adventurakrem22.game.Game;
import cz.vse.adventurakrem22.game.Npc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionTalkTest {
    private Game game;
    private ActionTalk actionTalk;

    @BeforeEach
    public void setUp() {
        game = new Game();
        actionTalk = new ActionTalk(game);
    }

    @Test
    public void testExecuteTalkWithNPC() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Npc npc = new Npc("postava", "testova postava", true,"Chceš dostat do držky?!");
        currentArea.addNpc(npc);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "postava" };
        String expected = "Postava říká: 'Chceš dostat do držky?!'.";
        String result = actionTalk.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteTalkWithRicardo() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        Npc ricardo = new Npc("ricardo", "Říká se, že odtud už několikrát utekl.", true, "Já jsem vždycky k útěku použil dlouhý lano" + "\n" + "*chvilka ticha* \n" + "Možná zkus utéct ze skrýše, ale musíš ji nejdřív najít."  );
        currentArea.addNpc(ricardo);
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "ricardo" };
        String expected = "Ricardo říká: 'Já jsem vždycky k útěku použil dlouhý lano" + "\n" + "*chvilka ticha* \n" + "Možná zkus utéct ze skrýše, ale musíš ji nejdřív najít.'.";
        String result = actionTalk.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteTalkWithNonExistentNPC() {
        Area currentArea = new Area("testarea", "testovaci lokace", "TestArea");
        game.getWorld().setCurrentArea(currentArea);
        String[] parameters = { "neexistujici" };
        String expected = "Tato postava se v této oblasti nenachází.";
        String result = actionTalk.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteTalkWithoutParameter() {
        String[] parameters = {};
        String expected = "Musíš mi říct, s kým mám mluvit.";
        String result = actionTalk.execute(parameters);
        assertEquals(expected, result);
    }

    @Test
    public void testExecuteTalkWithMultipleParameters() {
        String[] parameters = { "postava1", "postava2" };
        String expected = "Nemůžu mluvit s více lidmi současně.";
        String result = actionTalk.execute(parameters);
        assertEquals(expected, result);
    }
}
