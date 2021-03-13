module kalendator {
    requires javafx.controls;
    requires javafx.fxml;

    opens cz.pavlutom.kalendator to javafx.fxml;
    exports cz.pavlutom.kalendator;
}