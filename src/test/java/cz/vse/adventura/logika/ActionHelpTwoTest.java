package cz.vse.adventura.logika;

import cz.vse.adventurakrem22.game.ActionHelpTwo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionHelpTwoTest {
    private ActionHelpTwo action;

    @BeforeEach
    public void setup() {
        action = new ActionHelpTwo();
    }

    @Test
    public void testExecute() {
        String result = action.execute(new String[]{});
        String expected = "Tady je nápověda jak vyhrát nebo prohrát hru."
            + "\n-Klasická výhra a prohra:\n\n"
            + "Vyhrát můžeš pouze tak, že si popovídáš s Ricardem, ten ti odkraje skrýš (dostaneš se tam z cely) a pak půjdeš do unikove okno."
            + "\nAle bacha musíš u sebe mít lano (výhra), jinak prohraješ."
            + "\n\n-EasterEgg:"
            + "\nJeště je tu jeden způsob jak prohrát hru a to tím, že něco specifickýho sebereš ze země. Ve věznici bys to fakt neměl brát ze země.";
        assertEquals(expected, result);
    }

    @Test
    public void testGetName() {
        String name = action.getName();
        String expected = "napoveda2/pomoc2/help2/prirucka2";
        assertEquals(expected, name);
    }
}
