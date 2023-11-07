package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.Area;
import cz.vse.adventurakrem22.game.Game;
import cz.vse.adventurakrem22.game.GameWorld;
import cz.vse.adventurakrem22.game.IAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class HomeController implements Pozorovatel {
    @FXML
    private ListView panelVychodu;
    @FXML
    private Button tlacitkoOdesli;
    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private Game hra = new Game();

    private ObservableList<Area> seznamVychodu = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        vystup.appendText(hra.getPrologue() + "\n\n");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        });
        panelVychodu.setItems(seznamVychodu);
        hra.getWorld().registruj(this);
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getWorld().getCurrentArea().getAllExits());
    }

    @FXML
    private void OdešliVstup(ActionEvent actionEvent) {

        String prikaz = vstup.getText();
        vystup.appendText("> "+prikaz + "\n");
        String vysledek = hra.processAction(prikaz);
        vystup.appendText(vysledek + "\n\n");
        vstup.clear();

        if (hra.isGameOver()){
            vystup.appendText(hra.getEpilogue());
            vstup.setEditable(false);
            tlacitkoOdesli.setDisable(true);
        }
    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Jseš si jistý, že chceš ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    @Override
    public void aktualizuj() {
        System.out.println("Aktualizuj");
        aktualizujSeznamVychodu();
    }
}
