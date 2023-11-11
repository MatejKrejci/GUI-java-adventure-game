package cz.vse.adventurakrem22.start;
import cz.vse.adventurakrem22.game.Backpack;
import cz.vse.adventurakrem22.game.Game;
import cz.vse.adventurakrem22.ui.TextUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Spouštěcí třída aplikace.
 *
 * @author Matěj Krejčí
 * @author Jan Říha
 * @version LS-2023, 2023-25-06
 */
public class Start extends Application {
    
    /**
     * Spouštěcí metoda aplikace.
     *
     * @param args parametry aplikace z příkazové řádky <i>(aktuálně se ignorují)</i>
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("text")){
            System.out.print("\n> ");
            Game game = new Game(new Backpack(3));
            TextUI ui = new TextUI(game);

            ui.play();
            Platform.exit();

        }else{
            launch();
        }


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Adventura");
    }
}
