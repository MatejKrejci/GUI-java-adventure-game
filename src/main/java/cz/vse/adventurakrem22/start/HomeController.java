package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class HomeController {
    @FXML
    private ListView<Area> panelVychodu;
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
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);

        /**Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        }*/

        hra.getWorld().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> aktualizujSeznamVychodu());
        hra.registruj(ZmenaHry.KONEC_HRY,() -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getWorld().getCurrentArea().getAllExits());
    }

    @FXML
    private void OdešliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();

        zpracujPrikaz(prikaz);
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> "+ prikaz + "\n");
        String vysledek = hra.processAction(prikaz);
        vystup.appendText(vysledek + "\n\n");



    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Jseš si jistý, že chceš ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }
    }


    private void aktualizujKonecHry() {
        if (hra.isGameOver()){
            vystup.appendText(hra.getEpilogue());
        }
            vstup.setDisable(hra.isGameOver());
            tlacitkoOdesli.setDisable(hra.isGameOver());
            panelVychodu.setDisable(hra.isGameOver());
        }

    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Area cil = panelVychodu.getSelectionModel().getSelectedItem();
        if (cil == null) return;
        String prikaz = "jdi " + cil;
        zpracujPrikaz(prikaz);
    }
}
