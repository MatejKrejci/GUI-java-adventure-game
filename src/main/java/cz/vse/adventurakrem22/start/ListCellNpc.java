package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.Npc;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellNpc extends ListCell<Npc> {
    @Override
    protected void updateItem(Npc npc, boolean empty) {
        super.updateItem(npc, empty);
        if (empty || npc == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(npc.getName());
            setStyle("-fx-font-size: 35");
            String imagePath = "Npc/" + npc.getName() + ".jpg";
            ImageView iw = new ImageView(getClass().getResource(imagePath).toExternalForm());
            iw.setFitWidth(175);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}

