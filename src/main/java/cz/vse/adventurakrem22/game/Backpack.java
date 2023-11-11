package cz.vse.adventurakrem22.game;
import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.PredmetPozorovani;
import cz.vse.adventurakrem22.start.ZmenaHry;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Třída představuje batoh ve scenáři hry. Ve hře jen jeden batoh a má určenou kapacitu.
 * V batohu se eviduje inventář, tzn. seberu nějaký předmět a přidá se do inventáře, opačne to funguje s polozenim predmetu.
 * Inventář je uložený v kolekci.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class Backpack implements PredmetPozorovani {
    private Map<String, Item> inventory;
    private int capacity;
    private Collection<Item> inventoryItems = new HashSet<>();
    private Map<ZmenaHry,Set<Pozorovatel>> seznamPozorovatelu = new HashMap();

    /**
     * Konstruktor třídy batoh, vytvoří batoh se zadanou kapacitou a inventář.
     * 
     * @param capacity kapacita batohu
     */
    public Backpack(int capacity) {
        this.capacity = capacity;
        this.inventory = new LinkedHashMap<>();
        for (ZmenaHry zmenaHry : ZmenaHry.values()) {
            seznamPozorovatelu.put(zmenaHry, new HashSet<>());
        }
    }
    
    /**
     * Metoda zkontroluje, zda-li je batoh plný nebo ne
     * 
     * @return {@code true} batoh je plný; {@code false} batoh není plný
     */
    public boolean isFull() {
        return inventory.size() >= capacity;
    }
    
    /**
     * Metoda zkontroluje, jestli daný předmět je v batohu. Kontroluje to pomocí názvu předmětu.
     * 
     * @param itemName název předmětu
     * @return {@code true} batoh obsahuje předmět; {@code false} batoh neobsahuje předmět
     */
    public boolean containsItem(String itemName) {

        return inventory.containsKey(itemName);
    }
    
    /**
     * Metoda přidává předmět do batohu, následně vrací hodnotu podle toho, jestli byl
     * předmět přidán do batohu úspěšně. Podmínka přidání předmětu do batohu je, aby
     * batoh nebyl plný.
     * 
     * @param item předmět
     * @return {@code true} předmět byl přidán úspěšně; {@code false} předmět nebyl přidán do batohu
     */
    public boolean addItem(Item item) {
        if (!isFull() && !containsItem(item.getName())){
            inventory.put(item.getName(), item);
            upozorniPozorovatele(ZmenaHry.ZMENA_INVENTARE);
            return true;
        }
        return false;
    }



    /**
     * Metoda odstraňuje předmět z batohu.
     * 
     * @param itemName název předmětu
     * @return {@code true} předmět byl odstraněň z batohu úspěšně; {@code false} předmět nebyl odstraněn z batohu
     */
    public Item removeItem(String itemName){
        Item removedItem = inventory.remove(itemName);
        if (removedItem != null) {
            upozorniPozorovatele(ZmenaHry.ZMENA_INVENTARE);
        }
        return removedItem;
    }
    
    /**
     * Metoda vrací seznam předmětů v inventáři. Dále je zde kosmetická úprava, aby 
     * se to hráči ukázala v hezčí podobě.
     * 
     * @return content seznam předmětů v invetáři
     */
    public String getContents() {
        if (inventory.isEmpty()) {
            return "Tvůj batoh je prázdný";
        }
        String content = "V batohu máš: \n";
        for (Item item : inventory.values()) {
            content += "- " + item.getName() + "\n";
        }
        return content;
    }
    public Collection<Item> getInventory(){
        return inventory.values();

    }
    private void upozorniPozorovatele(ZmenaHry zmenaHry) {
        for (Pozorovatel pozorovatel : seznamPozorovatelu.get(zmenaHry)){
            pozorovatel.aktualizuj();
        }
    }
    @Override
    public void registruj(ZmenaHry zmenaHry, Pozorovatel pozorovatel) {
        seznamPozorovatelu.get(zmenaHry).add(pozorovatel);
    }

}
