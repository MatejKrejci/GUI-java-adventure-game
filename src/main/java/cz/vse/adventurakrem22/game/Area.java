package cz.vse.adventurakrem22.game;
import cz.vse.adventurakrem22.start.Pozorovatel;
//import cz.vse.adventurakrem22.start.PredmetPozorovani;
import cz.vse.adventurakrem22.start.ZmenaHry;

import java.util.*;
/**
 * Třída představuje lokaci <i>(area, location, lokace)</i> ve scénáři hry.
 * Každá lokace má název, který ji jednoznačně identifikuje. Dále také má
 * druhé jméno, které je s diaktritikou a vypisuje se uživateli do konzole.
 * Může mít sousední lokace, do kterých z ní lze odejít. Odkazy na všechny
 * sousední lokace jsou uložené v kolekci. Lokace také může obsahovat předměty.
 * Odkazy na všechny předměty v lokaci jsou uložené v mapě.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class Area implements Comparable<Area>{
    private String name;
    private String description;
    private String nameTwo;
    private boolean isHidden = false;;
    private Set<Area> exits;
    private Map<String, Item> items;
    private Map<String, Npc> npcs;
    private Map<ZmenaHry,Set<Pozorovatel>> seznamPozorovatelu = new HashMap();

    /**
     * Konstruktor třídy, vytvoří lokaci se zadaným názvem a popisem.
     *
     * @param name název lokace <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * @param description podrobnější popis lokace.
     * @param nameTwo je druhý název zobrazující se v konzoli s mezerami a diakritikou.
     * @param isHidden je parametr určující, jestli je daná lokace skrytá.
     */
    public Area(String name, String description,String nameTwo, boolean isHidden) {
        this.name = name;
        this.description = description;
        this.isHidden = isHidden;
        this.exits = new TreeSet<>();
        this.items = new TreeMap<>();
        this.npcs = new TreeMap<>();
        this.nameTwo = nameTwo;
    }

    /**
     * Konstruktor třídy, vytvoří lokaci se zadaným názvem a popisem.
     *
     * @param name název lokace <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * @param description podrobnější popis lokace.
     * @param nameTwo je druhý název zobrazující se v konzoli s mezerami a diakritikou.
     */
    public Area(String name, String description,String nameTwo) {
        this.name = name;
        this.description = description;
        this.exits = new TreeSet<>();
        this.items = new TreeMap<>();
        this.npcs = new TreeMap<>();
        this.nameTwo = nameTwo;
    }

    /**
     * Metoda vrací název lokace, který byl zadán při vytváření instance jako
     * parametr konstruktoru. Jedná se o jednoznačný identifikátor lokace
     * <i>(ve hře nemůže existovat více lokací se stejným názvem)</i>. Aby
     * hra správně fungovala, název lokace nesmí obsahovat mezery, v případě
     * potřeby můžete více slov oddělit pomlčkami, použít camel-case apod.
     *
     * @return name název lokace
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda vrací druhý název lokace, který byl zadán při vytváření instance jako
     * parametr konstruktoru. Jedná se o jednoznačný identifikátor lokace
     * <i>(ve hře může existovat více lokací se stejným druhým názvem, neplní identifikační roli)</i>.
     * 
     * @return nameTwo název lokace
     */
    public String getNameTwo() {
        return nameTwo;
    }

    /**
     * Metoda vrací, jestli je daná lokace skrytá nebo ne.
     * 
     * @return isHidden informace, zda-li je lokace skrytá.
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Metoda nastavuje atribut isHidden na true/false podle zadaného parametru.
     * 
     * @param isHidden zda-li chceme skrýt lokaci nebo naopak.
     */
    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * Metoda vrací popis lokace. Je zde kosmetická úprava, aby se
     * uživateli popisek při vstupu do lokace zobrazil přehledněji.
     *
     * 
     * @return fullDescription popis lokace
     */
    public String getDescription() {
        String fullDescription = description;
        if (!exits.isEmpty()) {
            fullDescription += "\n----------------------------------------------------------------------\nExits: ";
            boolean isFirstExit = true;
            for (Area exit : exits) {
                if (exit.getIsHidden()) {
                    continue;
                }
                if (!isFirstExit) {
                    fullDescription += ", ";
                }
                fullDescription += exit.getNameTwo();
                isFirstExit = false;
            }
        }
        return fullDescription;
    }
    
    /**
     * Metoda přidává do lokace další východ. Z lokace A do lokace B může vést pouze jeden východ, proto exity jsou v HashSetu. 
     * 
     * @param exit je lokace do které nastavujme východ z dané lokace
     */
    public void addExit (Area exit) {
        exits.add(exit);
    }
    
    /**
     * Metoda vrací hodnotu true/false podle toho, jestli daná lokace má daný východ.
     * 
     * @param exitName název východu
     * @return {@code true}, pokud lokace má východ do jiné lokace s daným názvem; jinak {@code false}
     */
    public boolean hasExit(String exitName) {
        for (Area exit : exits){
            if (exit.getName().equals(exitName)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda pomocí for loopuje všechny exity dané lokace a vratí na ní odkaz jako objekt třídy Area.
     * 
     * @param exitName název lokace
     * @return exit lokace; {@code null} pokud lokace s takto pojmenovanou lokací nesousedí.
     */
    public Area getExit(String exitName) {
        for (Area exit : exits) {
            if (exit.getName().equals(exitName)) {
                return exit;
            }
        }
        return null;
    }

    public Collection<Area> getAllExits() {
        return exits;
    }


    /**
     * Metoda vrací novou kolekci pouze s názvy předmětů v dané lokaci.
     * 
     * @return kolekce sousedních lokací
     */
    public Collection<Item> getItems() {
        return new TreeSet<>(items.values());
    }
    
    /**
     * Metoda přidává předmět do lokace.
     * 
     * @param item předmět, který bude do lokace přidán.
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }
    
    /**
     * Metoda vrací hodnotu podle toho jestli daná lokace obsahuje daný předmět.
     * Identifikace probíhá podle jména předmětu (klíč).
     *
     * @param itemName název předmětu
     * @return {@code true}, pokud lokace obsahuje předmět s daným názve
     */
    public boolean containsItem(String itemName) {
        return items.containsKey(itemName);
    }
    
    /**
     * Metoda vrací odkaz na předmět v dané lokace, podle zadaného jména.
     * 
     * @param itemName název předmětu
     * @return item předmět jako instanci třídy Item.
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }
    
    /**
     * Metoda odstraňuje předmět v dané lokaci, podle zadaného jména. 
     * 
     * @param itemName název předmětu
     * @return item byl odstraněn z lokace {@code true}; {@code false} nebyl v lokaci.
     */
    public Item removeItem(String itemName) {
        return items.remove(itemName);
    }
    
    /**
     * Metoda vrací kolekci všech postav v dané lokaci.
     * 
     * @return kolekce názvy všech postav v lokaci
     */
    public Collection<Npc> getNpcs() {
        return new TreeSet<>(npcs.values());
    }
    
    /**
     * Metoda přidává postavu do dané lokace.
     * 
     * @param npc postava kterou chceme přidat
     */
    public void addNpc(Npc npc) {
        npcs.put(npc.getName(), npc);
    }
    
    /**
     * Metoda zkontroluje, zda-li daná lokace obsahuje postavu s daným názvem.
     * 
     * @param npcName název postavy
     * @return {@code true} pokud v lokaci je daná postava; {@code false} pokud v lokaci není daná postava
     */
    public boolean containsNpc(String npcName) {
        return npcs.containsKey(npcName);
    }
    
    /**
     * Metoda vrací podle jména postavy odkaz na danou postavu.
     * 
     * @param npcName název postavy
     * @return postava vrací odkaz na postavu
     */
    public Npc getNpc(String npcName) {
        return npcs.get(npcName);
    }
    
    /**
     * Metoda odstraňuje z dané lokaci postavu podle jména.
     * 
     * @param npcName název postavy
     * @return {@code true} pokud postava byla odstraněna úspěšně, {@code false} pokud nebyla odstraněna
     */
    public Npc removeNpc(String npcName) {
        return npcs.remove(npcName);
    }
        /**
     * Metoda porovnává dvě lokace <i>(objekty)</i>. Lokace jsou shodné,
     * pokud mají stejný název <i>(atribut {@link})</i>. Tato metoda
     * je důležitá pro správné fungování množiny východů do sousedních
     * lokací.
     * <p>
     * Podrobnější popis metody najdete v dokumentaci třídy {@linkplain Object}.
     *
     * @param o objekt, který bude porovnán s aktuálním
     * @return {@code true}, pokud mají obě lokace stejný název; jinak {@code false}
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(final Object o)
    {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof Area) {
            Area area = (Area) o;

            return name.equals(area.getName());
        }

        return false;
    }
    /**
     * Porovnává tuto oblast s danou oblastí na základě jejich jména.
     *
     * @param area Oblast, s níž se má tato oblast porovnat.
     * @return Kladné číslo, pokud je jméno této oblasti větší než jméno oblasti dané, záporné číslo,
     *         pokud je jméno této oblasti menší než jméno oblasti dané, 0, pokud jsou jména stejná.
     */
    @Override
    public int compareTo(Area area)
    {
        return name.compareTo(area.getName());
    }

}

