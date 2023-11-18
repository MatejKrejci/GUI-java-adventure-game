package cz.vse.adventurakrem22.game;
/** Třída představuje postavy ve scenáři hry. Každá postava má
 * název, který ji jednoznačně identifikuje. Každá postava má svůj vlastní
 * a jedinečný popis a dialog. Dále také u postavy určujeme, jestli mluví.
 * 
 * @author Jan Říha
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class Npc implements Comparable <Npc>{
    private String name;
    private String description;
    private boolean talks;
    private String dialog;
    private boolean alreadyTalked;
    private boolean jeProzkoumany;

    /**
     * Konstruktor třídy, vytvoří postavu se zadaným názvem, popisem, dialogem a boolean hodnotou zda postava mluví.
     * 
     * @param name jméno postavy
     * @param description popisek postavy
     * @param talks mluví postava
     * @param dialog co řekne postava po použití příkazu mluv
     * @param alreadyTalked postava už mluvila
     */
    public Npc (String name, String description, boolean talks, String dialog, boolean alreadyTalked, boolean jeProzkoumany) {
        this.name = name;
        this.description = description;
        this.talks = talks;
        this.dialog = dialog;
        this.alreadyTalked = alreadyTalked;
        this.jeProzkoumany = jeProzkoumany;
    }
    public Npc (String name, String description,boolean alreadyTalked, boolean jeProzkoumany) {
        this.name = name;
        this.description = description;
        this.alreadyTalked = alreadyTalked;
        this.jeProzkoumany = jeProzkoumany;
    }
    /**
     * Konstruktor třídy, vytvoří postavu se zadaným názvem, popisem, dialogem a boolean hodnotou zda postava mluví.
     *
     * @param name jméno postavy
     * @param description popisek postavy
     * @param talks mluví postava
     * @param dialog co řekne postava po použití příkazu mluv
     */
    public Npc (String name, String description, boolean talks, String dialog) {
        this.name = name;
        this.description = description;
        this.talks = talks;
        this.dialog = dialog;
    }

    /**
     * Konstruktor třídy, vytvoří postavu se zadaným názvem, popisem, dialogem.
     * 
     * @param name jméno postavy
     * @param description popisek postavy
     */
    public Npc (String name, String description) {
        this.name = name;
        this.description = description;
        this.talks = false;
    }

    /**
     * Metoda vrací jméno postavy
     * 
     * @return name jméno postavy
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda vrací popisek postavy
     * 
     * @return description popis postavy
     */
    public String getDescription() {
        return description;
    }

    public boolean getJeProzkoumany(){ return jeProzkoumany; }

    /**
     * Metoda vrací dialog postavy
     * 
     * @return dialog proslov postavy
     */
    public String getDialog() {
        return dialog;
    }

    /**
     * Metoda zkontroluje, zda postava mluví nebo ne
     * 
     * return talks mluví {@code true} pokud postava mluví; {@code false} postava nemluví, nemá nastavený ani dialog
     */
    public boolean talks() {
        return talks;
    }

    public void setAlreadyTalked(boolean alreadyTalked){
        this.alreadyTalked = alreadyTalked;
    }
    public void setJeProzkoumany(boolean jeProzkoumany) {this.jeProzkoumany = jeProzkoumany;}
    public boolean getAlreadyTalked(){
        return alreadyTalked;
    }
     /**
     * Metoda porovnává dvě lokace <i>(objekty)</i>. Lokace jsou shodné,
     * pokud mají stejný název <i>(atribut)</i>. Tato metoda
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

        if (o instanceof Npc) {
            Npc npc = (Npc) o;

            return name.equals(npc.getName());
        }

        return false;
    }
    /**
     * Porovnává tuto postavu s danou postavou na základě jejich jména.
     *
     * @param npc Postava, s níž se má tato postava porovnat.
     * @return Kladné číslo, pokud je jméno této postavy větší než jméno postavy dané, záporné číslo,
     *         pokud je jméno této postavy menší než jméno postavy dané, 0, pokud jsou jména stejná.
     */
    @Override
    public int compareTo(Npc npc)
    {
        return name.compareTo(npc.getName());
    }
    @Override
    public String toString() {
        return getName();
    }
}
