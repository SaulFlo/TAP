package com.example.idk.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Caja extends Stage {

    private Scene scene;
    private VBox left, right;
    private HBox hbox;
    private Button btnStart;
    private Label[] arLblName;
    private ProgressBar[] arPgbCaracoles;

    public void crearUI(){

    }

    public Caja() {
        crearUI();

        this.setTitle("Pista de caracoles");
        this.setScene(scene);
    }
}
