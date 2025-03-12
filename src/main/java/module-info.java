module com.example.idk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.idk to javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    exports com.example.idk;
    //requires mysql.connector.j;
    requires java.sql;


    opens com.example.idk.modelos;
}