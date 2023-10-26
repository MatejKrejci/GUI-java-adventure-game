package cz.vse.adventurakrem22.game;
/**
 * Třída představuje příkaz pomoc.
 * Příkaz pomoc vypíše příručku jak hrát hru (tzn. info o hře, všechny možné příkazy).
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionHelp implements IAction {
    
    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz pomoc.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "napoveda/pomoc/help/prirucka";
    }
    
    /**
     * Metoda vypíše informace, jak hrát hru.
     * 
     * @param parameters parametry příkazu (na počtu parametrů zde nezáleží)
     * @return informace pro hráče, která se vypíše na konzoli o tom jak hrát hru.
     */
    public String execute(String[] parameters) {
        return "Vítej ve hře: 'Útěk z vězení!'" 
        + "\nHra se odehrává v nechvalně známé věznici Alcatraz." 
        + "\nPrávě si se probudil a dneska cítíš, že je ten den, kdy se dostaneš ven."
        + "\n\nJe zde celkem 11 prikazu (nemusíš řešit diakritiku ani velká/malá písmena)"
        + "\nTo znamená, že můžeš napsat jdi/JDI/JdÍ/gO/bEz/BEZ/běž atd...(platí to u všech příkazů)"
        + "\n\nPrikazy:"
        + "\n\n-KONEC - Tímhle vypneš hru kdykoliv. Tak doufám, že to dohraješ poctivě."
        + "\n-HELP2 - Vypíše ti krátký návod, jak dohrát hru."
        + "\n-JDI (někam) - Tímhle příkazem chodis skrz lokace. Teď můžeš napsat 'JDI chodba' a vždy když někam vejdeš se vypíšou další exity, kam můžeš jít."
        + "\n-LOKACE - Napíše ti info o lokaci. To stejné jako když přijdeš do lokace (popis lokace, možné východy (EXITS)."
        + "\n-ROZHLEDNI_SE - Vypíše všechny předměty a postavy v dané lokaci, u commandů seber/poloz/mluv musíš používat ten název, co ti tenhle příkaz vypíše."
        + "\n-PROZKOUMEJ (něco) - Vypíše ti to bližší info o postavě nebo předmětu. Vždy to píšeš jako 'PROZKOUMEJ truhla'."
        + "\n-BAG - Vypíše ti všechny předměty, co máš v batohu."
        + "\n-SEBER (něco) - Sebereš předmět ze země, ale musí být ve stejné lokaci jako ty. Vždy to píšeš jako 'SEBER klic'."
        + "\n-POLOZ (něco) - Předmět ti uvolní místo batohu a objeví se v dané lokaci, kde ho položíš, takže neboj, nezmizí."
        + "\n-ODEMKNI (něco) - Jestli máš potřebnej předmět (např. páčidlo/klic) v batohu, tak můžeš odemknout něco (např. dveře/truhla). Vždy to píšeš jako ODEMKNI truhla."
        + "\n-MLUV (escobar) - Popovídáš si s osobou, ale musí být ve stejné lokaci jako ty.";

    }
}
