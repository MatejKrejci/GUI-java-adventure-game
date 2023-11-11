package cz.vse.adventurakrem22.game;

import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.ZmenaHry;

/**
 * Třída představuje příkaz pomoc dva.
 * Příkaz pomoc2 vypíše příručku jak vyhrát hru.
 * 
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionHelpTwo implements IAction {
    
    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz pomoc2.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */    
    public String getName() {
        return "napoveda2/pomoc2/help2/prirucka2";
    }
    
    /**
     * Metoda vypíše informace, jak vyhrát hru.
     * 
     * @param parameters parametry příkazu (na počtu parametrů zde nezáleží)
     * @return informace pro hráče, která se vypíše na konzoli o tom jak vyhrát hru.
     */
    public String execute(String[] parameters) {
        return "Tady je nápověda jak vyhrát nebo prohrát hru." 
        + "\n-Klasická výhra a prohra:\n\n"
        + "Vyhrát můžeš pouze tak, že si popovídáš s Ricardem, ten ti odkraje skrýš (dostaneš se tam z cely) a pak půjdeš do unikove okno."
        + "\nAle bacha musíš u sebe mít lano (výhra), jinak prohraješ."
        +"\n\n-EasterEgg:"
        +"\nJeště je tu jeden způsob jak prohrát hru a to tím, že něco specifickýho sebereš ze země. Ve věznici bys to fakt neměl brát ze země.";

    }

}
