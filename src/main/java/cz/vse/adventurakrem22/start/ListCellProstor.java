package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.Area;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellProstor extends ListCell<Area> {
    @Override
    protected void updateItem(Area prostor, boolean empty) {
        super.updateItem(prostor, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }else {
        setText(prostor.getName());

        String cesta = getClass().getResource("prostory/" + prostor.getName() + ".jpg").toExternalForm();
        /**if (cesta == null){
            System.out.println("Chyba");
        }else{*/
            ImageView iw = new ImageView(cesta);

            iw.setFitWidth(100);
            iw.setPreserveRatio(true);
            setGraphic(iw);


        }
    }
}

