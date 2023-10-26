package cz.vse.adventurakrem22.ui;
import cz.vse.adventurakrem22.game.Game;

import java.util.Scanner;
/**
 * Třída představující uživatelské rozhraní aplikace. Zajišťuje načítání
 * příkazů z konzole a výpis reakcí hry.
 *
 * @author Matěj Krejčí
 * @author Jan Říha
 * @version LS-2023, 2023-25-06
 */
public class TextUI {
    private Game game;
    private Scanner scanner;

    /**
     * Konstruktor třídy, vytvoří uživatelské rozhraní pro danou hru.
     *
     * @param game hra
     */
    public TextUI(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);

    }

    /**
     * Metoda zajišťuje hraní hry. Nejprve vypíše úvodní text. Poté v cyklu
     * načítá zadané příkazy z konzole, předává je hře ke zpracování a vypisuje
     * reakce hry. To se neustále opakuje, dokud hra prostřednictvím metody
     * {@link Game#isGameOver() isGameOver} neoznámí, že skončila.
     */
    public void play() {
        System.out.println(game.getPrologue());

        while(!game.isGameOver()) {
            String line = scanner.nextLine();
            System.out.println(game.processAction(line));
            System.out.println("\n");

        }

        System.out.println("\n\n" + game.getEpilogue());
    }
}
