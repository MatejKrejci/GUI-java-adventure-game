package cz.vse.adventurakrem22.game;

import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.PredmetPozorovani;
import cz.vse.adventurakrem22.start.ZmenaHry;
import javafx.collections.FXCollections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Třída představující mapu lokací herního světa. V datovém atributu
 * {@link #currentArea} uchovává odkaz na aktuální lokaci, ve které
 * se hráč právě nachází. Z aktuální lokace je možné se prostřednictvím
 * jejích sousedů dostat ke všem přístupným lokacím ve hře.
 * <p>
 * Veškeré informace o stavu hry <i>(mapa prostorů, inventář apod.)</i> by měly být uložené
 * zde v podobě datových atributů.
 *
 * @author Matěj Krejčí
 * @author Jan Říha
 * @version LS-2023, 2023-25-06
 */
public class GameWorld implements PredmetPozorovani {
    private Area cela;
    private Area chodba;
    private Area straznice;
    private Area kantyna;
    private Area zahrada;
    private Area sprchy;
    private Area vez;
    private Area skrys;
    private Area currentArea;
    private Area unikoveOkno;
    private Backpack backpack;
    private Map<ZmenaHry,Set<Pozorovatel>> seznamPozorovatelu = new HashMap<>();

    /**
     * Konstruktor třídy, vytvoří jednotlivé lokace a propojí je pomocí východů. Vytváří také předměty a postavy a přidává je do jednotlivých lokací.
     * 
     * @param backpack batoh uživatele s inventářem
     */
    public GameWorld(Backpack backpack) {
        this.backpack = backpack;

        cela = new Area("cela", "-Tohle je moje cela. Smrdí to tu chcankama a tvoji spoluvězni jsou šmejdi.","Cela");
        chodba = new Area("chodba", "-Tohle je vězeňská chodba. Musim si držet odstup od stráže, chtějí stále někoho sejmout.","Chodba");
        straznice = new Area("straznice", "-Tady jsem nikdy nebyl, sem můžou jen bachaři." + "\n" + "-Tak si pospěš, ať mě tady nechytnou.","Strážnice");
        kantyna = new Area("kantyna", "-Klasicka vězeňská jídelna. Vaří tady fakt na hovno." + "\n" + "-Dneska bude k jídlu: rýže","Kantýna");
        zahrada = new Area("vezenska_zahrada", "-Zahrada vězení, je to asi nejlepší místo tady ve věznici." + "\n-Ricardo tady hraje pravidelně basket a možná tu je i dneska.","Vězeňská zahrada");
        sprchy = new Area("sprchy", "-Tady bych se moc nezdržoval, nechci tady vočumovat nahý týpky. Ještě dostanu do držky.","Sprchy");
        vez = new Area("vezenska_vez", "-Tak odtud na nás viděj všude. Tudy asi neuteču.","Vězeňská věž");
        skrys = new Area("skrys", "-Jak jsem o téhle skrýši doteď nevěděl, když je pod mojí postelí."+"\n" +"-To vypadá, že tím oknem bych mohl zdrhnout."
            + "\n-Ale jestli u sebe namám lano, tak to z té výšky nepřežiju.","Skrýš",true);
        unikoveOkno = new Area("unikove_okno", "-Jestli mam u sebe lano,tak se muzu slanit dolů.\n-Jestli u sebe nemám lano, tak umřu.", "Únikové okno");

        cela.addExit(chodba);
        skrys.addExit(unikoveOkno);

        chodba.addExit(cela);
        chodba.addExit(straznice);
        chodba.addExit(kantyna);

        straznice.addExit(chodba);
        straznice.addExit(vez);

        vez.addExit(straznice);

        kantyna.addExit(sprchy);
        kantyna.addExit(zahrada);
        kantyna.addExit(chodba);

        zahrada.addExit(kantyna);

        sprchy.addExit(kantyna);

        currentArea = cela;

        Item truhla = new Item("truhla", "Stará dřevěná truhla." + "\nMožná v ní bachaři mají nějaký věci.", false, false, true);
        Item postel = new Item("postel", "Moje polorozpadlá tvrdá postel.", false);
        Item stul = new Item("stul", "Malej stůl, u kterýho si každý večer čteš knihy.", false);
        Item basketballovej_mic = new Item("basketballovej_mic", "Starej odřenej baskeťák.", false);

        Item klic = new Item("klic", "K cemu asi tak muze byt tenhle klic", true);
        Item mydlo = new Item("mydlo", "Na zemi se válí mýdlo."+"\nRadši bych ho nebral, víš, co se ti stane, když ho sebereš ze země ve věznici.", true, false, true);
        Item kartacek = new Item("kartacek", "Starej zničenej kartáček na zuby.", true);
        Item ryze = new Item("ryze", "Hnusná rejže, co byla dnes k obědu.", true);
        Item maso = new Item("maso", "Plesnivý maso, co bylo dnes k obědu.", true);

        Npc straz = new Npc("straz", "Tenhle zm*d tady každej den někoho bije.",true, "Chceš dostat do držky?!");
        Npc spoluvezen = new Npc("spoluvezen", "Ten by mi možná mohl poradit jak odtud utéct.", true, "Odtud neutečeš, jsou tady fakt vysoký zdi.");
        Npc ricardo = new Npc("ricardo", "Říká se, že odtud už několikrát utekl.", true, "Já jsem vždycky k útěku použil dlouhý lano" + "\n" + "*chvilka ticha* \n" + "Možná zkus utéct ze skrýše, ale musíš ji nejdřív najít."  );
        Npc escobar = new Npc("escobar", "Od tý doby, co ho chytili toho moc nenamluví");

        cela.addItem(postel);
        cela.addItem(stul);

        straznice.addItem(klic);

        vez.addItem(truhla);

        sprchy.addItem(kartacek);
        sprchy.addItem(mydlo);

        kantyna.addItem(maso);
        kantyna.addItem(ryze);

        zahrada.addItem(basketballovej_mic);

        chodba.addNpc(straz);

        zahrada.addNpc(ricardo);

        kantyna.addNpc(spoluvezen);

        cela.addNpc(escobar);

        for (ZmenaHry zmenaHry : ZmenaHry.values()) {
            seznamPozorovatelu.put(zmenaHry, new HashSet<>());
        }
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return aktuální lokace
     */
    public Area getCurrentArea() {
        return currentArea;
    }

    /**
     * Metoda nastaví aktuální lokaci. Používají ji příkazy ActionMove.
     * ActionMove ji používá při přechodu z aktuální lokace do jiné lokace.
     * Pokud prikaz je uspesny nastavi se aktuální lokace na zadanou lokaci.
     *
     * @param currentArea lokace, která bude nastavena jako aktuální
     */
    public void setCurrentArea(Area currentArea) {

        this.currentArea = currentArea;
        upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
    }


    /**
     * Metoda vrací danou lokace podle zadaného jména.
     * Tato metoda je použita v příkazu mluv (s ricardem) k vytvoření exitu do skryté lokace.
     *
     * @param name nazev lokace
     * @return area lokace
     */
    public Area getArea(String name) {
        if (name.equalsIgnoreCase("cela")) {
            return cela;
        } else if (name.equalsIgnoreCase("chodba")) {
            return chodba;
        } else if (name.equalsIgnoreCase("straznice")) {
            return straznice;
        } else if (name.equalsIgnoreCase("kantyna")) {
            return kantyna;
        } else if (name.equalsIgnoreCase("vezenska_zahrada")) {
            return zahrada;
        } else if (name.equalsIgnoreCase("sprchy")) {
            return sprchy;
        } else if (name.equalsIgnoreCase("vezenska_vez")) {
            return vez;
        } else if (name.equalsIgnoreCase("skrys")) {
            return skrys;
        } else {
            return null;
        }
    }

    /**
     * Metoda vrací aktuální stav hry <i>(běžící hra, výhra, prohra)</i>.
     * Vyhrát hráč může tak, ze bude v místnosti unikove_okno a v batohu bude mít lano.
     * Prohrát může dvěma způsoby: 1) Hráč sebere ve sprše mýdlo ze země. (je to pouze vtip, jestli je to nevhodné, odstraním to)
     *                             2) Hráč bude v mistnosti unikove_okno a v batohu nebude mit lano.
     *
     * @return gameState aktuální stav hry
     */
    public GameState getGameState() {
        if (currentArea.getName().equals("unikove_okno") && backpack.containsItem("lano")) {
            return GameState.WON;
        }
        if (!backpack.containsItem("lano") && currentArea.getName().equals("unikove_okno")) {
            return GameState.LOST;
        }
        if (backpack.containsItem("mydlo")) {
            return GameState.LOST;
        }
        return GameState.PLAYING;
    }

    @Override
    public void registruj(ZmenaHry zmenaHry, Pozorovatel pozorovatel) {
        seznamPozorovatelu.get(zmenaHry).add(pozorovatel);
    }
    private void upozorniPozorovatele(ZmenaHry zmenaHry) {
        for (Pozorovatel pozorovatel : seznamPozorovatelu.get(zmenaHry)){
            pozorovatel.aktualizuj();
        }
    }
}
