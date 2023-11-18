package cz.vse.adventurakrem22.game;

import cz.vse.adventurakrem22.start.Pozorovatel;
import cz.vse.adventurakrem22.start.ZmenaHry;

/**
 * Třída implementující příkaz mluv pro mluvení s postavami.
 * Příkaz mluv vypíše, co říka postava, pokud na ni promluvíte.
 * 
 * @author Matěj Krejčí
 * @version LS-2023, 2023-25-06
 */
public class ActionTalk implements IAction {
    private Game game;
    /**
     * Konstruktor třídy
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionTalk(Game game) {
        this.game = game;

    }
    
    /**
     * Metoda vrací všechny možné slova, kterými lze zavolat příkaz mluv.
     * Každé slovo je odděleno "/" z důvodu další práce s nimi.
     * 
     * @return název možných variant příkazů
     */
    public String getName() {
        return "mluv/promluv/mluv_s/talk/talk_with/mluvit/promluvit/mluvit_s/mluvim_s";
    }
    
    /**
     * Metoda vrací, co říka daná postava na kterou mluvíte, ale musí být ve stejně lokaci jako hráč.
     * Probíhá zde i vyhodnocení počtu slov v parametru.
     * Následně proběhne úprava parametru (odstranění diakritiky a změna na malá písmena).
     * Dále také postava musí mluvit, jinak vrací pouze text o tom, že se bavit nechce.
     * Poté se vyhodnocuje jestli postava byla ricardo, když ano nastanou další sounáležitosti.
     * 
     * @param parameters parametry příkazu (očekává se pole s jedním prvkem)
     * @return informace pro hráče, která se vypíše na konzoli
     */
    public String execute(String[] parameters) {
        if (parameters.length < 1) {
            return "Musíš mi říct, s kým mám mluvit.";
        }
        if (parameters.length > 1) {
            return "Nemůžu mluvit s více lidmi současně.";
        }

        String parametrName = parameters[0];
        Area currentArea = game.getWorld().getCurrentArea();

        if (currentArea.containsNpc(parametrName)) {
            Npc npc = currentArea.getNpc(parametrName);
            if (npc.getName().equals("ricardo")) {
                Area skrys = game.getWorld().getArea("skrys");
                Area cela = game.getWorld().getArea("cela");
                skrys.setIsHidden(false);
                cela.addExit(game.getWorld().getArea("skrys"));
                skrys.addExit(game.getWorld().getArea("cela"));
                npc.setAlreadyTalked(true);
                String dialog = npc.getDialog();
                String capitalizedParametrName = parametrName.substring(0, 1).toUpperCase() + parametrName.substring(1);
                return capitalizedParametrName + " říká: '" + dialog + "'.";
            }

            if (npc.talks()) {
                String dialog = npc.getDialog();
                String capitalizedParametrName = parametrName.substring(0, 1).toUpperCase() + parametrName.substring(1);
                return capitalizedParametrName + " říká: '" + dialog + "'.";
            } else {
                String capitalizedParametrName = parametrName.substring(0, 1).toUpperCase() + parametrName.substring(1);
                return capitalizedParametrName + " se mnou mluvit nechce.";
            }
        } else {
            return "Tato postava se v této oblasti nenachází.";
        }
    }


}