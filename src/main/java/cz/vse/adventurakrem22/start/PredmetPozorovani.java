package cz.vse.adventurakrem22.start;

/**
 * Rozhraní pro předmět pozorování, který umožňuje registraci pozorovatelů pro sledování změn v hře.
 */
public interface PredmetPozorovani {
    /**
     * Metoda pro registraci pozorovatele na konkrétní typ změny v hře.
     *
     * @param zmenaHry      Typ změny v hře
     * @param pozorovatel   Pozorovatel sledující změny
     */
    void registruj(ZmenaHry zmenaHry, Pozorovatel pozorovatel);
}
