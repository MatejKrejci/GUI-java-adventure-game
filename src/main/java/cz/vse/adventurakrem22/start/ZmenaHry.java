package cz.vse.adventurakrem22.start;

/**
 * Výčtový typ reprezentující různé typy změn ve hře.
 * Tyto změny mohou být pozorovány implementací rozhraní {@link Pozorovatel}.
 */
public enum ZmenaHry {
    /**
     * Změna související s aktuálním prostorem ve hře.
     */
    ZMENA_PROSTORU,
    /**
     * Změna související s inventářem hráče.
     */
    ZMENA_INVENTARE,
    /**
     * Změna související s se změnou lokace.
     */
    ZMENA_MISTNOSTI,
    /**
     * Označuje konec hry.
     */
    KONEC_HRY
}
