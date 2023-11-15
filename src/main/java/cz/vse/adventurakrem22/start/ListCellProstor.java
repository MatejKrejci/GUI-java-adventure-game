package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.Area;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 * Třída reprezentující vlastní zobrazení prostoru v seznamu východů.
 */
public class ListCellProstor extends ListCell<Area> {

    /**
     * Metoda pro aktualizaci obsahu buňky seznamu na základě daného prostoru.
     *
     * @param prostor Položka reprezentující prostor, který má být zobrazen.
     * @param empty  True, pokud je buňka prázdná, jinak false.
     */
    @Override
    protected void updateItem(Area prostor, boolean empty) {
        super.updateItem(prostor, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }else {
        setText(prostor.getName());

        String cesta = getClass().getResource("prostory/" + prostor.getName() + ".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);

            iw.setFitWidth(100);
            iw.setPreserveRatio(true);
            setGraphic(iw);


        }
    }
}

