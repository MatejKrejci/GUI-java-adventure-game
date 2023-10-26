package cz.vse.adventurakrem22.game;
/**
 * Třída implementující příkaz pro odemykání truhly.
 * 
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionUnlock implements IAction {
    private Game game;
    private Backpack backpack;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     * @param backpack batoh uživatele s inventářem
     */
    public ActionUnlock(Game game, Backpack backpack) {
        this.game = game;
        this.backpack = backpack;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz odemkni.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "odemkni/odemknout/unlock/otevrit/otevri";
    }

    /**
     * Metoda se pokusí odemknout předmět v aktuální lokaci (truhla ve věži).
     * Nejprve se zkontroluje, jestli je daný předmět v aktuální lokaci.
     * Poté jestli se parametr jmenuje truhla, tak proběhne další kontrola podmínky, že
     * hráč musí u sebe mít klíč k odemčení truhly. Pokud se splní všechny tyto podmínky truhla se odemkne.
     * Pokud se nesplní jedna z těchto podmínek vrací se chybové hlášení.
     * 
     * @param parameters parametry příkazu (očekává se pole s jedním prvkem)
     * @return informace pro hráče, která se vypíše na konzoli o odemčení truhly.
     */
    public String execute(String[] parameters) {
        if (parameters.length < 1) {
            return "Musíš specifikovat, co chceš odemknout.";
        }
        String parametrName = parameters[0];
        Area currentArea = game.getWorld().getCurrentArea();
        if (currentArea.containsItem(parametrName)) {
            Item item = currentArea.getItem(parametrName);
            if (parametrName.equals("truhla")) {
                if (item.isLocked() && backpack.containsItem("klic")) {
                    item.setLocked(false);
                    return "Odemkl jsem truhlu.";
                }
                else if (item.isLocked() && !backpack.containsItem(parametrName)){
                    return "Bez klíče tuhle truhlu neodemknu";
                }
                else {
                    return "Truhla je již odemčená.";
                }
            }
            return "Nemůžu odemknout předmět '" + parametrName + "'.";
        }
        return "Předmět '" + parametrName + "' není v této místnosti.";
    }

}
