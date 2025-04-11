package com.example.idk.vistas;

import com.example.idk.componentes.Hilo;
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
    private Hilo[] arHilos;
    private ProgressBar[] arPgbCaracoles;
    private String[] names = {"Turbo", "Sombra", "Chet", "Suave", "Burn"};

    public void crearUI(){
        arHilos = new Hilo[5];
        arPgbCaracoles = new ProgressBar[5];
        arLblName = new Label[5];
        btnStart = new Button("Start");
        btnStart.setId("btnStart");
        left = new VBox();
        right = new VBox();

        for (int i = 0; i < arPgbCaracoles.length; i++) {
            arLblName[i] = new Label(names[i]);
            left.getChildren().add(arLblName[i]);
            arPgbCaracoles[i] = new ProgressBar(0);
            arHilos[i] = new Hilo(names[i], arPgbCaracoles[i]);
            right.getChildren().add(arPgbCaracoles[i]);
        }
        right.getChildren().add(btnStart);
        btnStart.setOnAction(e -> {
            for (int i = 0; i < arHilos.length; i++) {
                arPgbCaracoles[i].setProgress(0);
                
            }
            for (int i = 0; i < arHilos.length; i++) {
                arHilos[i].start();
            }
        });
        hbox = new HBox(left,right);
        scene = new Scene(hbox);
        scene.getStylesheets().add(getClass().getResource("/styles/carrera.css").toString());
    }

    public Caja() {
        crearUI();

        this.setTitle("Pista de caracoles");
        this.setScene(scene);
        this.show();
    }
}
