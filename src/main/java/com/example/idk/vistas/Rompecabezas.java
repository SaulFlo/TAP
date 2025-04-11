package com.example.idk.vistas;

import com.example.idk.utils.GlobalValues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Rompecabezas extends Stage {

    Text titulo;
    HBox hHead, hImagenes, hDificultad, hRegistros;
    VBox vbox;
    Scene scene;
    ToggleGroup grDificultad;
    Button  btnRegistros;

    public void crearUI(){

        titulo = new Text("Bienvenido");
        titulo.setId("titulo");
        hHead = new HBox(titulo);
        hHead.setAlignment(Pos.CENTER);
        hHead.setPadding(new Insets(20));

        ImageView imv1 = new ImageView(getClass().getResource("/images/paisaje1.jpg").toString());
        imv1.setFitHeight(300);
        imv1.setFitWidth(350);

        Button btnPaisaje1 = new Button();
        btnPaisaje1.getStyleClass().add("buttonImg");
        btnPaisaje1.setGraphic(imv1);
        btnPaisaje1.setOnAction(event -> {
            GlobalValues.urlImg = "/images/paisaje1.jpg";
            RadioButton rbSeleccionado = (RadioButton) grDificultad.getSelectedToggle();
            GlobalValues.tamaño = Integer.parseInt(rbSeleccionado.getId());
            new Tablero();
        });

        ImageView imv2 = new ImageView(getClass().getResource("/images/paisaje2.jpg").toString());
        imv2.setFitHeight(300);
        imv2.setFitWidth(350);

        Button btnPaisaje2 = new Button();
        btnPaisaje2.getStyleClass().add("buttonImg");
        btnPaisaje2.setGraphic(imv2);
        btnPaisaje2.setOnAction(event -> {
            GlobalValues.urlImg = "/images/paisaje2.jpg";
            RadioButton rbSeleccionado = (RadioButton) grDificultad.getSelectedToggle();
            GlobalValues.tamaño = Integer.parseInt(rbSeleccionado.getId());
            new Tablero();
        });

        ImageView imv3 = new ImageView(getClass().getResource("/images/paisaje3.jpg").toString());
        imv3.setFitHeight(300);
        imv3.setFitWidth(350);

        Button btnPaisaje3 = new Button();
        btnPaisaje3.getStyleClass().add("buttonImg");
        btnPaisaje3.setGraphic(imv3);
        btnPaisaje3.setOnAction(event -> {
            GlobalValues.urlImg = "/images/paisaje3.jpg";
            RadioButton rbSeleccionado = (RadioButton) grDificultad.getSelectedToggle();
            GlobalValues.tamaño = Integer.parseInt(rbSeleccionado.getId());
            new Tablero();
        });

        hImagenes = new HBox(btnPaisaje1, btnPaisaje2, btnPaisaje3);
        hImagenes.setAlignment(Pos.CENTER);
        hImagenes.setSpacing(15);

        grDificultad = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Facil");
        rb1.setToggleGroup(grDificultad);
        rb1.setId("3");
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Medio");
        rb2.setToggleGroup(grDificultad);
        rb2.setId("4");

        RadioButton rb3 = new RadioButton("Dificil");
        rb3.setToggleGroup(grDificultad);
        rb3.setId("5");

        hDificultad = new HBox(rb1, rb2, rb3);
        hDificultad.setAlignment(Pos.CENTER);
        hDificultad.setSpacing(15);
        hDificultad.setPadding(new Insets(20));

        btnRegistros = new Button("Ver registros");
        btnRegistros.setStyle("-fx-background-color: #d6ae83; -fx-border-color: #916a21; -fx-font-weight: bold; -fx-font-size: 20px");
        btnRegistros.setOnAction(event -> new Registros());

        hRegistros = new HBox(btnRegistros);
        hRegistros.setAlignment(Pos.CENTER_RIGHT);
        hRegistros.setPadding(new Insets(10, 220, 10, 10));

        vbox = new VBox(hHead, hImagenes, hDificultad, hRegistros);
        vbox.setAlignment(Pos.CENTER);

        scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("/styles/rompecabezas.css").toString());
    }

    public Rompecabezas(){
        crearUI();
        this.setTitle("Rompecabezas");
        this.setMinWidth(1100); // Ancho mínimo
        this.setMinHeight(400);
        this.setScene(scene);
        this.setMaximized(true);
        this.show();
    }
}
