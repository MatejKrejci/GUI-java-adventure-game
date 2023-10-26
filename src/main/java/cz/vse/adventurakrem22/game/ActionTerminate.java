package cz.vse.adventurakrem22.game;
/**
 * Třída představuje příkaz konec.
 * Příkaz konec ukončí předčasně hru.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionTerminate implements IAction {
    private Game game;

    /**
     * Konstruktor třídy
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionTerminate(Game game) {
        this.game = game;
    }

    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz konec.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "konec/vypnout/ukoncit/vypni/ukonci";
    }

    /**
     * Metoda ukončí hru.
     * 
     * @param parameters parametry příkazu (na počtu parametrů zde nezáleží)
     * @return informace pro hráče, která se vypíše na konzoli
     */
    public String execute(String[] parameters) {
        game.setGameOver(true);
        return "Hra byla ukoncena prikazem KONEC.";

    }
}
