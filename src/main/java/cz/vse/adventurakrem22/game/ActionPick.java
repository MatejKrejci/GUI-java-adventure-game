package cz.vse.adventurakrem22.game;

import cz.vse.adventurakrem22.start.HomeController.*;
import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.ZmenaHry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Třída implementující příkaz pro sbírání předmětů.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionPick implements IAction {
    private Game game;
    private Backpack backpack;
    private Map<ZmenaHry, Set<Pozorovatel>> seznamPozorovatelu = new HashMap<>();

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     * @param backpack batoh uživatele s inventářem
     */
    public ActionPick(Game game, Backpack backpack) {
        this.game = game;
        this.backpack = backpack;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz seber.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "seber/vezmi/pick/seberu/vezmu/sebrat/vzit";
    }

    /**
     * Metoda se pokusí sebrat předmět z aktuální lokace a uložit ho do hráčova invetáře. 
     * Nejprve zkontroluje počet parametrů. Pokud nebyl žádný nebo více jak jedn, tak vrátí chybové hlášení.
     * Následně proběhne úprava parametru (odstranění diakritiky a změna na malá písmena).
     * Následně probíhá kontrola, zda-li je předmět v aktuální lokace, pokud ano tak proběhne
     * kontrola přenositelnosti předmětu, poté proběhne kontrola místa v batohu.
     * Pokud se všechny tyto podmínky splní, hráč předmět seber z aktuální lokace (předmět zmizí z lokace) a
     * dá ho inventáře (přidá ho do batohu)
     * 
     * @param parameters parametry příkazu (očekává se pole s jedním prvkem)
     * @return informace pro hráče, která se vypíše na konzoli
     */
    public String execute(String[] parameters) {
        if (parameters.length > 1) {
            return "Nemuzu sebrat vice predmetu soucasne";
        }
        if (parameters.length < 1) {
            return "Musis mi rict, co mam sebrat";
        }
        String parametrName = parameters[0].toLowerCase();
        Area currentArea = game.getWorld().getCurrentArea();
        if (!currentArea.containsItem(parametrName)) {
            return "Takovej predmet tady nevidim.";
        }
        Item item = currentArea.getItem(parametrName);
        if (item.getName().equals("basketballovej_mic")) {
            return "Tyvole, já ten míč fakt nebudu brát, jinak mi daj do držky vězni i dozorčí.";
        }
        if (!item.isMoveable()) {
            return "Tohle neunesu.";
        }
        if (backpack.isFull()) {
            return "Batoh je plny. Nejdrive musim neco zahodit.";
        }
        boolean addedToBackpack = backpack.addItem(item);
        if (addedToBackpack) {
            currentArea.removeItem(parametrName);
            return "Sebral jsem predmet '" + parametrName + "' a ulozil ho do inventare.";
        } else {
            return "Batoh je plny. Nejdrive musim neco zahodit.";
        }
    }

}
