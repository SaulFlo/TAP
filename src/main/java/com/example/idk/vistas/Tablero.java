package com.example.idk.vistas;

import com.example.idk.modelos.AppendableObjectOutputStream;
import com.example.idk.modelos.Intento;
import com.example.idk.utils.GlobalValues;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tablero extends Stage {

    Timeline timer;
    MenuItem miReiniciar, miRegistros, miSalir, facil, medio, dificil;
    Menu mnOpciones, mnDificultad;
    MenuBar menuBar;
    GridPane grdTablero;
    HBox hbox;
    VBox vbox, vBody;
    Scene scene;

    ImageView seleccionada = null;
    ImageView[][] arrImv;
    AtomicInteger segundos;
    boolean status = true;

    public void crearTablero(){
        Image img = new Image(getClass().getResource(GlobalValues.urlImg).toString());
        int n = GlobalValues.tamaño;

        grdTablero = new GridPane();
        grdTablero.setAlignment(Pos.CENTER);
        arrImv = new ImageView[n][n];

        double tWidth = img.getWidth() / n;
        double tHeight = img.getHeight() / n;

        List<int[]> posiciones = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                posiciones.add(new int[]{i, j});
            }
        }

        Collections.shuffle(posiciones);

        int index = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ImageView pieza = new ImageView(img);
                pieza.setViewport(new javafx.geometry.Rectangle2D(
                        j * tWidth,
                        i * tHeight,
                        tWidth,
                        tHeight
                ));

                pieza.setFitWidth(800/n);
                pieza.setFitHeight(600/n);
                pieza.setId("" + index);
                int[] pos = posiciones.get(index-1);
                index++;
                grdTablero.add(pieza, pos[1], pos[0]);

                arrImv[pos[0]][pos[1]] = pieza;


                pieza.setOnMouseClicked(e -> { if(status)intercambiar(pieza);});
            }
        }
    }

    public void intercambiar(ImageView pieza){
        if(pieza != seleccionada) {
            if (seleccionada == null) {
                seleccionada = pieza;
                pieza.setStyle("-fx-effect: innershadow(gaussian, red, 10, 0.5, 0, 0);");
            } else {
                ImageView cambio = pieza;

                int cSelec = GridPane.getColumnIndex(seleccionada);
                int rSelec = GridPane.getRowIndex(seleccionada);
                int cCamb = GridPane.getColumnIndex(cambio);
                int rCamb = GridPane.getRowIndex(cambio);

                grdTablero.getChildren().removeAll(seleccionada, cambio);
                grdTablero.add(seleccionada, cCamb, rCamb);
                grdTablero.add(cambio, cSelec, rSelec);

                arrImv[rCamb][cCamb] = seleccionada;
                arrImv[rSelec][cSelec] = cambio;

                seleccionada.setStyle("");
                seleccionada = null;
                validar();
            }
        }else{
            pieza.setStyle("");
            seleccionada = null;
        }
    }

    public void validar(){
        int n = 0;
        int nId;
        for(int i = 0; i < arrImv.length; i++){
            for(int j = 0; j < arrImv.length; j++){
                nId = Integer.parseInt(arrImv[i][j].getId());
                if(n > nId)
                    return;
                n = nId;
            }
        }
        timer.stop();
        status = false;

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("¡Felicidades!");
        alerta.setHeaderText("Juego terminado");
        alerta.setContentText("¡Has completado el rompecabezas!");
        alerta.showAndWait();

        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        Intento intento = new Intento(fecha.toString(), hora.toString(), segundos.get());

        try {
            String dif;
            if(GlobalValues.tamaño == 3)
                dif = "Facil";
            else if (GlobalValues.tamaño == 4)
                dif = "Medio";
            else
                dif = "Dificil";

            String ruta = "archivos/intentos" + dif + ".txt";
            File archivo = new File(ruta);
            ObjectOutputStream arch;

            if (archivo.exists() && archivo.length() > 0) {
                arch = new AppendableObjectOutputStream(new FileOutputStream(archivo, true));
            } else {
                arch = new ObjectOutputStream(new FileOutputStream(archivo));
            }

            arch.writeObject(intento);
            arch.close();

            System.out.println("Juego registrado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearMenu(){
        facil = new MenuItem("Facil");
        facil.setOnAction(e -> {
            this.close();
            GlobalValues.tamaño = 3;
            new Tablero();
        });
        medio = new MenuItem("Medio");
        medio.setOnAction(e -> {
            this.close();
            GlobalValues.tamaño = 4;
            new Tablero();
        });
        dificil = new MenuItem("Dificil");
        dificil.setOnAction(e -> {
            this.close();
            GlobalValues.tamaño = 5;
            new Tablero();
        });

        mnDificultad = new Menu("Dificultad");
        mnDificultad.getItems().addAll(facil, medio, dificil);

        miReiniciar = new MenuItem("Reiniciar");
        miReiniciar.setOnAction(e -> {
            this.close();
            new Tablero();
        });

        miRegistros = new MenuItem("Registros");
        miRegistros.setOnAction(e -> {
            this.close();
            new Registros();
        });

        miSalir = new MenuItem("Salir");
        miSalir.setOnAction(e -> this.close());

        mnOpciones = new Menu("Opciones");
        mnOpciones.getItems().addAll(miReiniciar, miRegistros, miSalir);

        menuBar = new MenuBar(mnOpciones, mnDificultad);
    }

    public void crearUI(){
        crearMenu();
        crearTablero();

        Text txtTimer = new Text("Tiempo: 0s");
        txtTimer.setId("timer");
        segundos = new AtomicInteger(0);
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            segundos.getAndIncrement();
            txtTimer.setText("Tiempo: "+segundos.get() + "s");
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        ImageView imvSolu = new ImageView(getClass().getResource(GlobalValues.urlImg).toString());
        imvSolu.setFitWidth(80);
        imvSolu.setFitHeight(60);
        hbox = new HBox(txtTimer, imvSolu);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(500);
        hbox.setPadding(new Insets(15, 10, 0, 10));

        vBody = new VBox(menuBar, grdTablero, hbox);
        vBody.setPadding(new Insets(15));
        vBody.setAlignment(Pos.CENTER);

        vbox = new VBox(menuBar, vBody);

        scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("/styles/tablero.css").toString());
    }

    public Tablero(){
        crearUI();
        this.setTitle("Tablero");
        this.setScene(scene);
        this.show();
    }
}
