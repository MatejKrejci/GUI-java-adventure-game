package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.Item;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 * Třída reprezentující vlastní zobrazení předmětu v seznamu předmětů.
 */
public class ListCellItem extends ListCell<Item> {
    /**
     * Metoda pro aktualizaci obsahu buňky seznamu na základě dané položky.
     *
     * @param item  Položka, která má být zobrazena.
     * @param empty True, pokud je buňka prázdná, jinak false.
     */
    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getName());
            ImageView iw = new ImageView(getClass().getResource("Itemy/"+item.getName()+".jpg").toExternalForm());
            iw.setFitWidth(70);
            iw.setPreserveRatio(true);
            setStyle("-fx-font-size: 10");
            setGraphic(iw);
        }
    }
}
