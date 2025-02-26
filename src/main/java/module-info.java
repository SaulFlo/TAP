module com.example.idk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.idk to javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    exports com.example.idk;
}