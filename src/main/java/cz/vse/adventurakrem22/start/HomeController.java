package cz.vse.adventurakrem22.start;

import cz.vse.adventurakrem22.game.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HomeController {
    @FXML
    private ImageView hrac;
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

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    @FXML
    private void initialize(){
        vystup.appendText(hra.getPrologue() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);
        hra.getWorld().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
        });
        hra.registruj(ZmenaHry.KONEC_HRY,() -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        vlozSouradnice();
        panelVychodu.setCellFactory(param -> new ListCellProstor());
    }

    private void vlozSouradnice() {
        souradniceProstoru.put("cela", new Point2D(28,26));
        souradniceProstoru.put("chodba",new Point2D(86,24));
        souradniceProstoru.put("kantyna",new Point2D(143,100));
        souradniceProstoru.put("sprchy",new Point2D(139,186));
        souradniceProstoru.put("straznice",new Point2D(188,24));
        souradniceProstoru.put("vezenska_zahrada",new Point2D(14,115));
        souradniceProstoru.put("vezenska_vez",new Point2D(219,85));
        souradniceProstoru.put("skrys",new Point2D(27,77));
        souradniceProstoru.put("unikove_okno", new Point2D(28,186));
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getWorld().getCurrentArea().getAllExits());
    }

    private void aktualizujPolohuHrace() {
        String prostor = hra.getWorld().getCurrentArea().getName();
        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());

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
        String prikaz = "jdi " + cil.getNameTwo();
        zpracujPrikaz(prikaz);
    }
    @FXML
    private void napovedaKlik(ActionEvent actionEvent) {
        Stage napovedaStage = new Stage();
        WebView wv = new WebView();
        Scene napovedaScena = new Scene(wv);
        napovedaStage.setScene(napovedaScena);
        napovedaStage.show();
        wv.getEngine().load(getClass().getResource("napoveda.html").toExternalForm());
    }

    public void restartHryKlik(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Jsi si jistý, že chceš restartovat hru?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            hra.setGameOver(false);
            hra = new Game();
            seznamVychodu.clear();
            vystup.clear();
            vstup.clear();

            initialize();
        }
    }

}
