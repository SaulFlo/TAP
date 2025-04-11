package com.example.idk;

import com.example.idk.componentes.Hilo;
import com.example.idk.modelos.Conexion;
import com.example.idk.vistas.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private VBox vBox;
    private MenuBar mnbPrincipal;

    private Menu mnCompetencia1, mnCompetencia2;

    private MenuItem mitCalculadora, mitRompecabezas, mitRestaurante, mitCarrera;

    private Scene escena;

    void crearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora()); //Creo un objeto calculadora temporal. -> expresion lambda.

        mitRompecabezas = new MenuItem("Rompecabezas");
        mitRompecabezas.setOnAction(event -> new Rompecabezas());

        mitRestaurante = new MenuItem("Restaurante");
        mitRestaurante.setOnAction(event -> new ListaClientes());

        mitCarrera = new MenuItem("Carrera de hilos");
        mitCarrera.setOnAction(event -> new Caja());

        mnCompetencia1 = new Menu("Primer competencia");
        mnCompetencia1.getItems().addAll(mitCalculadora, mitRompecabezas, mitRestaurante);

        mnCompetencia2 = new Menu("Segunda competencia");
        mnCompetencia2.getItems().addAll(mitCarrera);

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(mnCompetencia1, mnCompetencia2);
        vBox = new VBox(mnbPrincipal);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/styles/main.css").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {
        /*new Hilo("Lemus").start();
        new Hilo("Leo").start();*/

        Conexion.crearConnection();
        crearUI();
        stage.setTitle("Hello World"); // nombre de la ventana
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }

    void clickEvent(String txt){

        System.out.println(txt);
    }
}