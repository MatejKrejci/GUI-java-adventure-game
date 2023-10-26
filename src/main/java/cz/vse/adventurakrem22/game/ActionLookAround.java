package cz.vse.adventurakrem22.game;

import java.util.*;

/**
 * Třída představuje příkaz rozhledni_se (look around).
 * Příkaz rozhledni_se nám vypisuje všechny předměty a postavy v dané lokaci.
 * Je zde implementovaná funkce, aby uživatel mohl použít více alternativních příkazů k rozhledni_se. (např. rozhled, koukni_kolem_sebe atd...).
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionLookAround implements IAction {
    private Game game;

    /**
     * Konstruktor třídy
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionLookAround(Game game) {
        this.game = game;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz rozhledni_se.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "rozhledni_se/rozhledni/koukni_kolem_sebe/rozhlednu_se/rozhlednu/rozhlednout_se/rozhlednout/look_around";
    }

    /**
     * Metoda vrátí detailní popis aktuální lokace. Tzn. vypíše všechny postavy a předměty.
     * Dále funkce upravuje výsledný text, který se zobrazí hráči přehledněji a srozumitelněji.
     * 
     * @param parameters parametry příkazu (očekává se prázdné pole)
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    public String execute(String[] parameters) {
        if (parameters.length > 0) {
            return "Tomu nerozumím, umím se rozhlédnout jen kolem sebe, ne 'na něco'.";
        }

        Area currentArea = game.getWorld().getCurrentArea();
        Collection<Item> items = currentArea.getItems();
        Collection<Npc> npcs = currentArea.getNpcs();

        if (items.isEmpty() && npcs.isEmpty()) {
            return "Nic kolem sebe nevidím.";
        }

        String result = "Kolem sebe vidím:";
        
        if (!items.isEmpty()) {
            result += "\n-Predmety:";
            int itemCount = 0;
            for (Item item : items) {
                if (itemCount > 0) {
                    result += ",";
                }
                result += " " + item.getName();
                itemCount++;
            }
        }

        if (!npcs.isEmpty()) {
            result += "\n-Postavy:";
            int npcCount = 0;
            for (Npc npc : npcs) {
                if (npcCount > 0) {
                    result += ",";
                }
                result += " " + npc.getName();
                npcCount++;
            }
        }

        return result;
    }
}
