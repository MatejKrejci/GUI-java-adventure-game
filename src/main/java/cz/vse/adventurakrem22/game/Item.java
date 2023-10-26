package cz.vse.adventurakrem22.game;
/**
 * Třída představuje předmět ve scenáři hry. Každý předmět má název, popis, je/není pohyblivý,
 * je/není prozkoumaný, je/není zamčený. Název zde jednoznačně identifikuje konkrétní předměty.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class Item implements Comparable <Item>{
    private String name;
    private String description;
    private boolean moveable;
    private boolean jeProzkoumana;
    private boolean isLocked;

    /**
     * Konstruktor třídy předmět, vytvoří předmět s daným jménem, popiskem, je/není pohyblivý,
     * je/není prozkoumaný, je/není zamčený.
     * 
     * @param name název předmětu
     * @param description popis předmětu
     * @param moveable předmět pohyblivý
     * @param jeProzkoumana předmět prozkoumaný
     * @param isLocked předmět zamčený
     */
    public Item(String name, String description, boolean moveable, boolean jeProzkoumana, boolean isLocked) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
        this.jeProzkoumana = jeProzkoumana;
        this.isLocked = isLocked;
    }

    /**
     * Konstruktor třídy předmět, vytvoří předmět s daným jménem, popiskem, je/není pohyblivý.
     * 
     * @param name název předmětu
     * @param description popis předmětu
     * @param moveable je předmět pohyblivý
     */
    public Item(String name, String description, boolean moveable) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }

    /**
     * Konstruktor třídy předmět, vytvoří předmět s daným jménem, popiskem.
     * 
     * @param name název předmětu
     * @param description popis předmětu
     */
    public Item(String name, String description) {
        this(name, description, true);
    }

    /**
     * Metoda vrací název daného předmětu.
     * 
     * @return name název předmětu
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda vrací popis daného předmětu.
     * 
     * @return description popis předmětu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Metoda zkontroluje, jestli je daný předmět pohyblivý.
     * 
     * @return moveable je pohyblivý; {@code true} předmět je pohyblivý
     */
    public boolean isMoveable() {
        return moveable;
    }

    /**
     * Metoda nastavuje pohyblivost u daného předmětu na zadanou hodnotu v parametru.
     * 
     * @param moveable předmět je/není pohyblivý; {@code true} předmět je pohyblivý
     */
    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    /**
     * Metoda nastavuje je/není prozkoumaný u daného předmětu na zadanou hodnotu v parametru.
     * 
     * @param jeProzkoumana předmět je/není prozkoumaný
     */
    public void setProzkoumana(boolean jeProzkoumana) {
        this.jeProzkoumana = jeProzkoumana;
    }

    /**
     * Metoda vrací jeProzkoumana u daného předmětu.
     * 
     * @return jeProzkoumana předmět je/není prozkoumaný; {@code true} předmět již je prozkoumaný
     */
    public boolean getProzkoumana() {
        return jeProzkoumana;
    }

    /**
     * Metoda vrací, zda je daný předmět odemčený nebo ne.
     * 
     * @return isLocked předmět je/není zamčený; {@code true} předmět již je odemčený
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Metoda nastavuje zamčenost předmětu u daného předmětu dle zadaného parametru.
     * 
     * @param locked zamčenost předmětu
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
        /**
     * Metoda porovnává dvě lokace <i>(objekty)</i>. Lokace jsou shodné,
     * pokud mají stejný název <i>(atribut {@link #nazev})</i>. Tato metoda
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

        if (o instanceof Item) {
            Item item = (Item) o;

            return name.equals(item.getName());
        }

        return false;
    }

    @Override
    public int compareTo(Item item)
    {
        return name.compareTo(item.getName());
    }
}
