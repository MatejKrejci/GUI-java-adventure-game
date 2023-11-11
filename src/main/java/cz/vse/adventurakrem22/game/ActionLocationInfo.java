package cz.vse.adventurakrem22.game;

import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.ZmenaHry;

/**
 * Třída představuje příkaz lokace.
 * Příkaz lokace vypíše informace o aktuální lokaci.
 * 
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionLocationInfo implements IAction {
    private Game game;

    /**
     * Konstruktor třídy
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionLocationInfo(Game game) {
        this.game = game;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz lokace.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "lokace_info/info_lokace/lokace/location/info_location/location_info";
    }

    /** Metoda vypíše informace o místnosti. V parametru nesmí být žádné slovo, protože
     * příkaz vždy vypíše informace o aktuální lokaci.
     * 
     * @param parameters parametry příkazu (počet parametrů nesmí být žádný)
     * @return informace pro hráče, která se vypíše na konzoli o aktuální lokaci.
     */
    public String execute(String[] parameters) {
        if (parameters.length > 0) {
            return "Tomu nerozumím, umím zobrazit pouze informace o aktuální lokaci.";
        }

        Area currentArea = game.getWorld().getCurrentArea();
        return currentArea.getDescription();
    }


}
