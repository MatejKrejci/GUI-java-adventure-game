package cz.vse.adventurakrem22.game;

import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.ZmenaHry;

/**
 * Třída představuje příkaz prouzkoumej.
 * Příkaz prozkoumej vypíše popis daného předmětu.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionInvestigate implements IAction {
    private Game game;

    /**
     * Konstruktor třídy
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionInvestigate(Game game) {
        this.game = game;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz prozkoumej.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "prozkoumej/prozkoumat/zkoumat/explore/investigate/zkoumej";
    }

    /**
     * Metoda vrací detailní popis vybraného předmětu nebo postavy v aktuální lokaci.
     * Probíhá zde i vyhodnocení počtu slov v parametru.
     * Následně proběhne úprava parametru (odstranění diakritiky a změna na malá písmena).
     * Poté se vyhodnocuje prozkoumání truhly.
     * 
     * @param parameters parametry příkazu (očekává se pole s jedním prvkem)
     * @return informace pro hráče, která se vypíše na konzoli
     */
    public String execute(String[] parameters) {
        if (parameters.length < 1){
            return "Musíš říct, co mám prozkoumat";
        }
        if (parameters.length > 1) {
            return "Nemůžu prozkoumávat dva předměty současně";
        }

        String parametrName = parameters[0];
        Area currentArea = game.getWorld().getCurrentArea();

        if (currentArea.containsItem(parametrName)) {
            Item item = currentArea.getItem(parametrName);
            if (parametrName.equals("truhla")&& item.getProzkoumana() == false && !item.isLocked()) {
                item.setProzkoumana(true);
                Item lano = new Item("lano", "Fakt dlouhý lano.", true);
                currentArea.addItem(lano);
                return "Prozkoumal jsem truhlu a bylo v ní lano. \nNyní ho můžu sebrat. (seber lano)";

            }

            if (parametrName.equals("truhla") && item.getProzkoumana() == false && item.isLocked()) {
                return "Prozkoumávám předmět '" + parametrName + "'.\n" + item.getDescription() + "\nK odemknutí téhle truhly nejspíš potřebuješ nějaký klíč.";
            }

            if (parametrName.equals("truhla") && !item.getProzkoumana() == false && !item.isLocked()) {
                return "Prozkoumávám předmět '" + parametrName + "'.\n" + item.getDescription() + "\nTuhle truhlu jsem již otevřel a prozkoumal, bylo v ní lano."; 
            }

            return "Prozkoumávám předmět '" + parametrName + "'.\n" + item.getDescription();
        }

        if (currentArea.containsNpc(parametrName)) {
            Npc npc = currentArea.getNpc(parametrName);
            return "Zjisťuješ kdo je '" + parametrName + "'.\n" + npc.getDescription();
        }

        if (!currentArea.containsNpc(parametrName) && !currentArea.containsNpc(parametrName)) {
            return "To nemůžu prozkoumat, protože v téhle místnosti to není";

        }

        return null;
    }


}
