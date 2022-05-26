module na.searchablebluebook {
    requires javafx.controls;
    requires javafx.fxml;


    opens na.searchablebluebook to javafx.fxml;
    exports na.searchablebluebook;
}