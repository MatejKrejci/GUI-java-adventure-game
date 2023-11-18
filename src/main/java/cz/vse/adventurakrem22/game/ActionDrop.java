package cz.vse.adventurakrem22.game;

import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.ZmenaHry;

/**
 * Třída implementující příkaz pro pokládání předmětů.
 * 
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionDrop implements IAction {
    private Game game;
    private Backpack backpack;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     * @param backpack batoh uživatele s inventářem
     */
    public ActionDrop(Game game, Backpack backpack) {
        this.game = game;
        this.backpack = backpack;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz poloz.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "poloz/vyhod/odhod/zahod/drop/throw_out/polozit/vyhodit/odhodit/zahodit/pokladam";
    }

    /**
     * Metoda se pokusí položit předmět do aktuální lokace a vyhodit ho z hráčova invetáře. 
     * Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný tak vrátí chybové hlášení.
     * Poté se zkontroluje, jestli hráč má daný předmět v batohu.
     * Pokud hráč předmět v batohu má, tak se předmět položí v aktuální lokaci a zmizí z inventáře.
     * 
     * @param parameters parametry příkazu (očekává se pole s jedním prvkem)
     * @return informace pro hráče, která se vypíše na konzoli
     */
    public String execute(String[] parameters) {
        if (parameters.length < 1) {
            return "Musis mi rict, co chces vyhodit.";
        }

        String parametrName = parameters[0].toLowerCase();
        Area currentArea = game.getWorld().getCurrentArea();

        if (!backpack.containsItem(parametrName)) {
            return "Takovou vec v batohu nemam.";
        }

        Item item = backpack.getItemByName(parametrName);
        //Item item = backpack.removeItem(parametrName);
        currentArea.addItem(item);
        backpack.removeItem(parametrName);


        return "Vyhodil jsem predmet '" + parametrName + "' do aktualni lokace.";
    }


}

