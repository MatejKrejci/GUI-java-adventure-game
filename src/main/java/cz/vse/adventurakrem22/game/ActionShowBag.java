package cz.vse.adventurakrem22.game;
/**
 * Třída implementující příkaz pro ukázání inventáře (všech předmětů v něm).
 * 
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionShowBag implements IAction {
    private Backpack backpack;

    /**
     * Konstruktor třídy.
     *
     * @param backpack batoh uživatele s inventářem
     */
    public ActionShowBag(Backpack backpack) {
        this.backpack = backpack;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz batoh.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "ukaz_batoh/batoh/bag/show_bag/backpack/ukazuj_batoh/ukazat_batoh/ukazovat_batoh";
    }

    /**
     * Metoda vrátí obsah inventáře. Pokud je zadán parametr, vrátí se chybové hlášení.
     * 
     * @param parameters parametry příkazu (očekává se prázdné pole)
     * @return contents informace pro hráče o obsahu inventáře, který hra vypíše na konzoli
     */
    public String execute(String[] parameters) {
        if (parameters.length > 0) {
            return "Nevím, který inventář chceš ukázat, mám pouze jeden batoh.";
        }

        String contents = backpack.getContents();
        return contents;
    }

}
