module cz.vse.adventurakrem22 {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.adventurakrem22.start to javafx.fxml;
    exports cz.vse.adventurakrem22.start;
}