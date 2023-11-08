package cz.vse.adventurakrem22.game;
/**
 * Třída implementující příkaz pro pohyb mezi herními lokacemi.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionMove implements IAction {
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionMove(Game game) {
        this.game = game;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz jdi.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "jdi/bez/go/run/jdu/bezim/bezet/sprintuj";
    }

    /**
     * Metoda se pokusí přesunout hráče do jiné lokace. Nejprve zkontroluje počet parametrů.
     * Pokud nebyl zadán žádný nebo bylo zadáno 3 a více parametrů vrátí se chybová hláška.
     * Následně proběhne úprava parametru (odstranění diakritiky a změna na malá písmena).
     * Když jsou v parametru slova dvě, tak prikaz nahradí mezeru za "_" a probíhá stejná kontrola (viz. výše)
     * Následně se zkontroluje, jestli aktuální lokace sousedí s lokací s daným názvem (také kontrola, jestli hráč nechce jít do aktuální lokace).
     * Pokud nějaká z podmínek neprojde vracejí se chybové hlášení. Jinak se příkaz provede a přesune hráče do zadané lokace.
     * 
     * @param parameters parametry příkazu (očekává se pole s jedním nebo dvěma prvky)
     * @return informace pro hráče, která se vypíše na konzoli
     */
    public String execute(String[] parameters) {
        String parametrName1 = parameters[0];
        if (parameters.length == 0) {
            return "Tomu nerozumím, musíš mi říct, kam mám jít.";

        }
        if (parameters.length > 2) {
            return "Tomu nerozumím, neumím jít na více míst současně";
        }
        if (parameters.length == 1) {
            Area currentArea = game.getWorld().getCurrentArea();
                        
            if (currentArea.getNameTwo().equals(parametrName1)) {
                return "Už tady jsem.";
            }
            if (!currentArea.hasExit(parametrName1)) {
                return "Tam se odtud jít neda.";
            }
            Area targetArea = currentArea.getExit(parametrName1);
            game.getWorld().setCurrentArea(targetArea);
            return "Odešel jsem do lokace '" + targetArea.getNameTwo() + "'.\n" + targetArea.getDescription();
        }
        if (parameters.length == 2) {
            String parametrName = (parameters[0] + "_" + parameters[1]);
            Area currentArea = game.getWorld().getCurrentArea();
            if (currentArea.getNameTwo().equals(parametrName)) {
                return "Už tady jsem.";
            }
            if (!currentArea.hasExit(parametrName)) {
                return "Tam se odtud jit neda.";
            }
            Area targetArea = currentArea.getExit(parametrName);
            game.getWorld().setCurrentArea(targetArea);
            return "Odešel jsem do lokace '" + targetArea.getNameTwo() + "'.\n" + targetArea.getDescription();
        }
        return null;
    }
}
