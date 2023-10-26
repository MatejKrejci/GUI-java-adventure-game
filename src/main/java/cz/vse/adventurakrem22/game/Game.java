package cz.vse.adventurakrem22.game;
import java.util.*;

/**
 * Hlavní třída hry. Vytváří a uchovává odkaz na instanci třídy {@link GameWorld}
 * a instanci třídy {@Backpack}, také vytváří seznam platných příkazů a instance tříd provádějících jednotlivé
 * příkazy.
 * <p>
 * Během hry třída vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Matěj Krejčí
 * @author Jan Říha
 * @version LS-2023, 2023-05-07
 */
public class Game {
    private boolean gameOver;
    private GameWorld world;
    private Set<IAction> actions;
    private Backpack backpack;

    /**
     * Konstruktor třídy Game. Vytvoří hru a seznam platných příkazů.
     * Hra po vytvoření již běží a je připravená zpracovávat herní příkazy.
     * 
     */
    public Game() {
        gameOver = false;
        actions = new HashSet<>();
        backpack = new Backpack(3);
        world = new GameWorld(backpack);
        actions.add(new ActionHelp());
        actions.add(new ActionHelpTwo());
        actions.add(new ActionTerminate(this));
        actions.add(new ActionMove(this));
        actions.add(new ActionLookAround(this));
        actions.add(new ActionInvestigate(this));
        actions.add(new ActionPick(this, backpack));
        actions.add(new ActionDrop(this, backpack));
        actions.add(new ActionTalk(this));
        actions.add(new ActionShowBag(backpack));
        actions.add(new ActionLocationInfo(this));
        actions.add(new ActionUnlock(this, backpack));
    }

    /**
     * Vrací informaci, zda hra skončila (výhra, prohra nebo příkaz 'konec').
     * 
     * @return {@code true} pokud hra skončila; jinak {@code false}
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Metoda nastaví příznak indikující, zda hra skončila. Tuto metodu vužívá třída {@link ActionTerminate},
     * ale mohou být použity i jinými implementacemi rozhraní {@link IAction}.
     * 
     * @param gameOver příznak určující, zda hra skončila
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Vrací odkaz na mapu herního světa (plánek herního světa).
     * 
     * @return world mapa herního světa
     */
    public GameWorld getWorld() {
        return world;
    }

    /**
     * Zpracuje herní akci (řádek textu zadaný hráčem).
     * Řetězec uvedený jako parametr se rozdělí na slova. První slovo je považováno
     * za název příkazu, další slova za jeho parametry.
     * <p>
     * Poté se akční slovo převede na málá písmena a odstraní diakritiku, aby uživatel mohl lépe používat příkazy
     * a hra neřešila i malé překlepy. Např 'jdi/bez' může hráč napsat jako jdi,JdI,jdÍ,bez,BĚZ,BeŽ.....
     * <p>
     * Poté for loopuje kolekci actions. Dále v tom for loopu vytvoří nový Array executorNames, kam vloží celý název příkazu.
     * Poté executorName rozdělí podle "\" a udělá další for loop a hledá shodu mezi akčním parametrem a názvem příkazu.
     * Pokud najde shodu, tak spustí daný příkaz.., pokud shodu nenajde, vyskočí chybové hlášení.
     * 
     * @param line text, který hráč zadal na konzoli jako příkaz pro hru
     * @return result zpracování <i>(informace pro hráče, které se vypíšou na konzoli)</i>
     */
    public String processAction(String line) {
        line = line.toLowerCase();
        line = line.replaceAll("[ěé]", "e");
        line = line.replaceAll("[š]", "s");
        line = line.replaceAll("[č]", "c");
        line = line.replaceAll("[ř]", "r");
        line = line.replaceAll("[ď]", "d");
        line = line.replaceAll("[ž]", "z");
        line = line.replaceAll("[ý]", "y");
        line = line.replaceAll("[í]", "i");
        line = line.replaceAll("[ó]", "o");
        line = line.replaceAll("[á]", "a");
        line = line.replaceAll("[ň]", "n");
        line = line.replaceAll("[ůú]", "u");
        line = line.replaceAll("[ť]", "t");
        String[] words = line.split("[ \t]+");        
        String[] actionParameters = new String[words.length - 1];
        String result = "Tento prikaz neznam";
        String actionName = words[0];

        
        for (int i = 0; i < actionParameters.length; i++) {
            actionParameters[i] = words[i + 1];
        }
        for (IAction executor : actions) {
            String[] executorNames = executor.getName().split("/");
            for (String executorName : executorNames) {
                if (executorName.equals(actionName)) {
                    result = executor.execute(actionParameters);
                }
            }
        }
        if (this.getWorld().getGameState() != GameState.PLAYING) {
            this.setGameOver(true);
        }
        return result;
    }

    /**
     * Metoda vrací úvodní text pro hráče, který se vypíše na konzoli ihned po
     * zahájení hry.
     *
     * @return úvodní text
     */
    public String getPrologue() {
        return "Vítej ve hře: 'Útěk z vězení!'" 
        + "\nHra se odehrává v nechvalně známé věznici Alcatraz." 
        + "\nPrávě si se probudil a dneska cítíš, že je ten den, kdy se dostaneš ven."
        + "\nJestli nevíš, jak se hra ovládá, můžeš napsat (HELP).";
    }

    /**
     * Metoda vrací závěrečný text pro hráče, který se vypíše na konzoli po ukončení
     * hry. Metoda se zavolá pro všechna možná ukončení hry <i>(hráč vyhrál, hráč
     * prohrál, hráč ukončil hru příkazem 'konec')</i>.
     * <p>
     * Pro každý scénář prohry je zde odlišný epilog, protože hráč může dosáhnout prohry dvěma způsoby.
     * Pro scénář prohry je zde jen jeden epilog, protože hráč může vyhrát pouze jedním způsobem.
     *
     * @return závěrečný text
     */
    public String getEpilogue() {
        String epilogue = "Díky, že sis zahrál(a) hru.\nDoufám, že si ještě někdy zahraješ, strávil jsem u toho hodně času. :)";
        GameState gameState = world.getGameState();
        if (gameState == GameState.WON) {
            epilogue = "\n\nVyhral(a) jsi.\nSlanil ses únikovým oknem dolů na zem a konečně ses dostal z téhle díry na svobodu." + "\nGratuluju k vítězství!\n\n" + epilogue;
        }else if (gameState == GameState.LOST && backpack.containsItem("mydlo")) {
            epilogue = "\n\nProhral(a) jsi. \nSebral jsi mydlo ze země ve věznici, neznáš to pravidlo? Ve věznici mýdlo ze země neber.\n" + "Takže dneska asi neutečeš.\n\n" + epilogue;
        }else if (gameState == GameState.LOST && !backpack.containsItem("lano")) {
            epilogue = "\n\nProhral(a) jsi.\nVyskočil jsi únikovým oknem bez lana, takže jsi dopadl z 30 metrové výšky na zem a umřel.\n\n" + epilogue;
        }
        return epilogue;
    }
    
}