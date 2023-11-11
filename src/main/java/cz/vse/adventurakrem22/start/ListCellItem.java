package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.Item;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellItem extends ListCell<Item> {
    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        }else
        {
            setText(item.getName());
            ImageView iw = new ImageView(getClass().getResource("Itemy/"+item.getName()+".jpg").toExternalForm());
            iw.setFitWidth(40);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }


    }
}
