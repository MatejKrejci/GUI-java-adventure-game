package cz.vse.adventurakrem22.game;

/**
 * Enumerace představuje možné stavy hry <i>(běžící hra, výhra, prohra apod.)</i>.
 *
 * @author Matěj Krejčí
 * @author Jan Říha
 * @version LS-2023, 2023-05-06
 */
public enum GameState {
    /** Hra stále běží. */
    PLAYING, 
    
    /** Hráč vyhrál. */
    WON,
    
    /** Hráč prohrál. */
    LOST;
}
