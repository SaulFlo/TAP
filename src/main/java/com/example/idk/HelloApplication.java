package com.example.idk;

import com.example.idk.componentes.Hilo;
import com.example.idk.modelos.Conexion;
import com.example.idk.vistas.Calculadora;
import com.example.idk.vistas.ListaClientes;
import com.example.idk.vistas.VentasRestaurante;
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

    private MenuItem mitCalculadora, mitRestaurante;

    private Scene escena;

    void crearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora()); //Creo un objeto calculadora temporal. -> expresion lambda.

        mitRestaurante = new MenuItem("Restaurante");
        mitRestaurante.setOnAction(event -> new ListaClientes());

        mnCompetencia1 = new Menu("Primer competencia");
        mnCompetencia1.getItems().addAll(mitCalculadora, mitRestaurante);

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(mnCompetencia1);
        vBox = new VBox(mnbPrincipal);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/styles/main.css").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {
        new Hilo("Lemus").start();
        new Hilo("Leo").start();

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