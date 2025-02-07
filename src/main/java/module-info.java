module com.example.idk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.idk to javafx.fxml;
    exports com.example.idk;
}