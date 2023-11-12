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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HomeController implements Pozorovatel {
    @FXML
    private ListView<Item> panelPredmetuVProstoru;
    @FXML
    private ListView<Item> panelInventáře;
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

    private Game hra = new Game(new Backpack(3));

    private ObservableList<Item> inventar = FXCollections.observableArrayList();

    private ObservableList<Area> seznamVychodu = FXCollections.observableArrayList();

    private ObservableList<Item> predmetyVProstoru = FXCollections.observableArrayList();

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    @FXML
    private void initialize(){
        vystup.appendText(hra.getPrologue() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());

        panelPredmetuVProstoru.setItems(predmetyVProstoru);
        panelVychodu.setItems(seznamVychodu);
        panelInventáře.setItems(inventar);
        aktualizujPredmetyVProstoru();

        hra.getWorld().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
            aktualizujInventar();
            aktualizujPredmetyVProstoru();
        });

        hra.getWorld().getBackpack().registruj(ZmenaHry.ZMENA_INVENTARE, () -> {
            aktualizujInventar();
        });

        hra.getWorld().getBackpack().registruj(ZmenaHry.ZMENA_PROSTORU, () ->{
            aktualizujPredmetyVProstoru();
        });

        hra.registruj(ZmenaHry.KONEC_HRY,() -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        vlozSouradnice();

        panelVychodu.setCellFactory(param -> new ListCellProstor());
        panelInventáře.setCellFactory(param -> new ListCellItem());
        panelPredmetuVProstoru.setCellFactory(param -> new ListCellItem());
    }

    private void vlozSouradnice() {
        souradniceProstoru.put("cela", new Point2D(121,58));
        souradniceProstoru.put("chodba",new Point2D(196,60));
        souradniceProstoru.put("kantyna",new Point2D(318,148));
        souradniceProstoru.put("sprchy",new Point2D(290,258));
        souradniceProstoru.put("straznice",new Point2D(498,56));
        souradniceProstoru.put("vezenska_zahrada",new Point2D(129,168));
        souradniceProstoru.put("vezenska_vez",new Point2D(435,129));
        souradniceProstoru.put("skrys",new Point2D(122,120));
        souradniceProstoru.put("unikove_okno", new Point2D(120,247));
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getWorld().getCurrentArea().getAllExits());
    }
    @FXML
    public void aktualizujInventar() {
        inventar = FXCollections.observableArrayList(hra.getWorld().getBackpack().getInventory());
        panelInventáře.getItems().clear();
        for (Item item : inventar){
            panelInventáře.getItems().add(item);
        }

    }
    public void aktualizujPredmetyVProstoru(){
        predmetyVProstoru = FXCollections.observableArrayList(hra.getWorld().getCurrentArea().getItems());
        panelPredmetuVProstoru.getItems().clear();
        for (Item item : predmetyVProstoru){
            panelPredmetuVProstoru.getItems().add(item);
        }

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
        panelInventáře.setDisable(hra.isGameOver());
        panelPredmetuVProstoru.setDisable(hra.isGameOver());
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
            hra = new Game(new Backpack(3));
            seznamVychodu.clear();
            vystup.clear();
            vstup.clear();
            inventar.clear();

            initialize();
        }
    }

    @Override
    public void aktualizuj() {
            aktualizujInventar();
    }

    @FXML
    private void klikInventar(MouseEvent mouseEvent) {
        Item cilovyItem = panelInventáře.getSelectionModel().getSelectedItem();
        if (cilovyItem == null) return;
        String prikaz = "poloz " + cilovyItem;
        zpracujPrikaz(prikaz);

        /**
        if (cilovyItem.getName() != "klic"){
            String prikaz = "poloz " + cilovyItem;
            zpracujPrikaz(prikaz);
        }else if (cilovyItem.getName() == "klic" && hra.getWorld().getCurrentArea().getName() == "vezenska_vez" && hra.getWorld().getArea("vezenska_vez").getItem("truhla").isLocked() == true){
            String prikaz = "odemkni truhla";
            zpracujPrikaz(prikaz);
        }else {
            String prikaz = "poloz " + cilovyItem;
            zpracujPrikaz(prikaz);
        }*/

    }

   /** public void klikPredmetVProstoru(MouseEvent mouseEvent) {
        Item cilovyItem = panelPredmetuVProstoru.getSelectionModel().getSelectedItem();
        if (cilovyItem == null) return;
        String prikaz = "seber " + cilovyItem;
        zpracujPrikaz(prikaz);
        aktualizujPredmetyVProstoru();
    }*/
    @FXML
    public void klikPredmetVProstoru(MouseEvent mouseEvent) {
        Item cilovyItem = panelPredmetuVProstoru.getSelectionModel().getSelectedItem();
        if (cilovyItem == null) return;

        if (cilovyItem.getName().equals("truhla")
                && hra.getWorld().getCurrentArea().getName().equals("vezenska_vez")
                && hra.getWorld().getArea("vezenska_vez").getItem("truhla").isLocked()) {

            String prikaz = "odemkni truhla";
            zpracujPrikaz(prikaz);
        }else if (cilovyItem.getName().equals("truhla")
                && hra.getWorld().getCurrentArea().getName().equals("vezenska_vez")
                && hra.getWorld().getArea("vezenska_vez").getItem("truhla").isLocked() == false){

            String prikaz = "prozkoumej truhla";
            zpracujPrikaz(prikaz);

        }
        else {
            String prikaz = "seber " + cilovyItem;
            zpracujPrikaz(prikaz);
            aktualizujPredmetyVProstoru();
        }
    }
}
