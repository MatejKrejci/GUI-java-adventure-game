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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controller třída pro grafické rozhraní hry.
 * Implementuje rozhraní {@link Pozorovatel} pro sledování změn ve hře.
 */
public class HomeController implements Pozorovatel {
    @FXML
    private ListView panelPostavVProstoru;
    @FXML
    private javafx.scene.image.ImageView ImageView;
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

    private ObservableList<Npc> postavyVProstoru = FXCollections.observableArrayList();

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    /**
     * Inicializační metoda, která se volá při startu grafického rozhraní.
     * Nastavuje počáteční stav a obsah grafických komponent.
     */
    @FXML
    private void initialize(){
        vystup.appendText(hra.getPrologue() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());

        panelPredmetuVProstoru.setItems(predmetyVProstoru);
        panelVychodu.setItems(seznamVychodu);
        panelInventáře.setItems(inventar);
        panelPostavVProstoru.setItems(postavyVProstoru);
        aktualizujPredmetyVProstoru();
        aktualizujPostavyVProstoru();

        hra.getWorld().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
            aktualizujInventar();
            aktualizujPredmetyVProstoru();
            aktualizujPostavyVProstoru();
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
        panelPostavVProstoru.setCellFactory(param -> new ListCellNpc());
    }

    /**
     * Metoda pro vložení počátečních souřadnic prostory do mapy souradniceProstoru.
     */
    private void vlozSouradnice() {
        souradniceProstoru.put("cela", new Point2D(140,21));
        souradniceProstoru.put("chodba",new Point2D(289,24));
        souradniceProstoru.put("kantyna",new Point2D(290,126));
        souradniceProstoru.put("sprchy",new Point2D(124,176));
        souradniceProstoru.put("straznice",new Point2D(479,23));
        souradniceProstoru.put("vezenska_zahrada",new Point2D(88,97));
        souradniceProstoru.put("vezenska_vez",new Point2D(464,134));
        souradniceProstoru.put("cela1", new Point2D(105,21));
        souradniceProstoru.put("chodba1",new Point2D(250,24));
        souradniceProstoru.put("kantyna1",new Point2D(255,115));
        souradniceProstoru.put("sprchy1",new Point2D(100,160));
        souradniceProstoru.put("straznice1",new Point2D(400,24));
        souradniceProstoru.put("vezenska_zahrada1",new Point2D(80,93));
        souradniceProstoru.put("vezenska_vez1",new Point2D(390,120));
        souradniceProstoru.put("skrys1",new Point2D(520,23));
        souradniceProstoru.put("unikove_okno1", new Point2D(523,128));
    }

    /**
     * Metoda pro aktualizaci seznamu východů v listview.
     */
    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getWorld().getCurrentArea().getAllExits());
    }

    /**
     * Metoda pro aktualizaci inventáře v listview.
     */
    @FXML
    public void aktualizujInventar() {
        inventar = FXCollections.observableArrayList(hra.getWorld().getBackpack().getInventory());
        panelInventáře.getItems().clear();
        for (Item item : inventar){
            panelInventáře.getItems().add(item);
        }

    }
    /**
     * Metoda pro aktualizaci předmětů v aktuálním prostoru v listview.
     */
    public void aktualizujPredmetyVProstoru(){
        predmetyVProstoru = FXCollections.observableArrayList(hra.getWorld().getCurrentArea().getItems());
        panelPredmetuVProstoru.getItems().clear();
        for (Item item : predmetyVProstoru){
            panelPredmetuVProstoru.getItems().add(item);
        }

    }
    public void aktualizujPostavyVProstoru(){
        postavyVProstoru = FXCollections.observableArrayList(hra.getWorld().getCurrentArea().getNpcs());
        panelPostavVProstoru.getItems().clear();

        for (Npc npc : postavyVProstoru){
            panelPostavVProstoru.getItems().add(npc);
        }

    }


    /**
     * Metoda pro aktualizaci polohy hráče v grafickém rozhraní.
     */
    private void aktualizujPolohuHrace() {
        /***String prostor = hra.getWorld().getCurrentArea().getName();
        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());*/
        String prostor = hra.getWorld().getCurrentArea().getName();
        if (hra.getWorld().getArea("vezenska_zahrada").getNpc("ricardo").getAlreadyTalked()) {
            ImageView.setImage(new Image((getClass().getResource("herniPlanSkrys.png").toExternalForm())));
            ImageView.setFitWidth(597.0);
            ImageView.setFitHeight(232.0);
            ImageView.setPickOnBounds(true);
            //fitHeight="232.0" fitWidth="597.0"
            hrac.setFitWidth(50);
            hrac.setLayoutX(souradniceProstoru.get(prostor + "1").getX());
            hrac.setLayoutY(souradniceProstoru.get(prostor + "1").getY());
        }else {
            hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
            hrac.setLayoutY(souradniceProstoru.get(prostor).getY());
        }
    }

    /**
     * Metoda pro zpracování vstupu hráče po stisknutí tlačítka Odešli.
     */
    @FXML
    private void OdešliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();

        zpracujPrikaz(prikaz);
    }

    /**
     * Metoda pro zpracování příkazu hráče a aktualizaci výstupního pole.
     * @param prikaz Příkaz zadaný hráčem.
     */
    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> "+ prikaz + "\n");
        String vysledek = hra.processAction(prikaz);
        vystup.appendText(vysledek + "\n\n");
        aktualizujPolohuHrace();



    }

    /**
     * Metoda pro ukončení hry s potvrzením.
     * @param actionEvent Událost tlačítka.
     */
    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Jseš si jistý, že chceš ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    /**
     * Metoda pro kontrolu skončení hry a případného vypnutí grafických prvků
     */
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

    /**
     * Metoda při kliknutí na panel východů přesune hráče do vybrané oblasti.
     *
     * @param mouseEvent Událost kliknutí myší.
     */
    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Area cil = panelVychodu.getSelectionModel().getSelectedItem();
        if (cil == null) return;
        String prikaz = "bezim " + cil.getNameTwo();
        zpracujPrikaz(prikaz);
    }
    /**
     * Metoda při požadavku na nápovědu zobrazí okno s webovou nápovědou.
     *
     * @param actionEvent Událost požadavku na nápovědu.
     */
    @FXML
    private void napovedaKlik(ActionEvent actionEvent) {
        Stage napovedaStage = new Stage();
        WebView wv = new WebView();
        Scene napovedaScena = new Scene(wv);
        napovedaStage.setScene(napovedaScena);
        napovedaStage.show();
        wv.getEngine().load(getClass().getResource("napoveda.html").toExternalForm());
    }

    /**
     * Metoda pro restart hry s potvrzením uživatele.
     *
     * @param actionEvent Událost požadavku na restart hry.
     */
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
            ImageView.setImage(new Image((getClass().getResource("herniPlan.png").toExternalForm())));
            aktualizujPolohuHrace();
            initialize();
        }
    }

    /**
     * Metoda pro aktualizaci UI při změně inventáře.
     */
    @Override
    public void aktualizuj() {
            aktualizujInventar();
    }

    /**
     * Metoda při kliknutí na inventářový panel provede akci položení vybraného předmětu.
     *
     * @param mouseEvent Událost kliknutí myší.
     */
    @FXML
    private void klikInventar(MouseEvent mouseEvent) {
        Item cilovyItem = panelInventáře.getSelectionModel().getSelectedItem();
        if (cilovyItem == null) return;
        String prikaz = "pokládám " + cilovyItem;
        zpracujPrikaz(prikaz);
    }

    /**
     * Metoda pro obsluhu kliknutí na předmět v aktuálním prostoru.
     * Zpracovává různé akce v závislosti na typu a stavu předmětu.
     *
     * @param mouseEvent Událost kliknutí myší.
     */
    @FXML
    public void klikPredmetVProstoru(MouseEvent mouseEvent) {
        Item cilovyItem = panelPredmetuVProstoru.getSelectionModel().getSelectedItem();
        String currentArea = hra.getWorld().getCurrentArea().getName();
        Item truhla = hra.getWorld().getArea("vezenska_vez").getItem("truhla");
        if (cilovyItem == null) return;

        if (cilovyItem.getName().equals("truhla")
                && currentArea.equals("vezenska_vez")
                && truhla.isLocked()){
            String prikaz = "odemikám truhla";
            zpracujPrikaz(prikaz);
        } else if (cilovyItem.getName().equals("truhla")
                && currentArea.equals("vezenska_vez")
                && truhla.isLocked() == false){

            String prikaz = "prozkoumávám truhla";
            zpracujPrikaz(prikaz);

        }
        else if (cilovyItem.getProzkoumana() == false){
            String prikaz = "prozkoumávám " + cilovyItem;
            zpracujPrikaz(prikaz);

        }else {
            String prikaz = "seberu " + cilovyItem;
            zpracujPrikaz(prikaz);
            aktualizujPredmetyVProstoru();
        }
    }

    public void klikNaPostavu(MouseEvent mouseEvent) {
        Object selectedItem = panelPostavVProstoru.getSelectionModel().getSelectedItem();
        Npc escobar = hra.getWorld().getArea("cela").getNpc("escobar");

        if (selectedItem == null || !(selectedItem instanceof Npc)) {
            return;
        }

        Npc cilovaPostava = (Npc) selectedItem;
        Npc npc = hra.getWorld().getCurrentArea().getNpc(cilovaPostava.getName());
        if (npc.getJeProzkoumany() == false){
            npc.setJeProzkoumany(true);
            String prikaz = "prozkoumávám " + cilovaPostava;
            zpracujPrikaz(prikaz);
        }else if (npc.getName() == escobar.getName() ){
            String prikaz = "mluvím_s " + cilovaPostava;
            zpracujPrikaz(prikaz);
            panelPostavVProstoru.getItems().clear();
        }

        else {
            String prikaz = "mluvím_s " + cilovaPostava;
            zpracujPrikaz(prikaz);
        }
    }
}
