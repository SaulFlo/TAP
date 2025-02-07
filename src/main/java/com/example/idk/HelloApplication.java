package com.example.idk;

import javafx.application.Application;
import javafx.scene.Scene;
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

    private MenuItem mitCalculadora;

    void crearUI(){
        mitCalculadora = new MenuItem("Calculadora");

        mnCompetencia1 = new Menu("Primer Parcial");
        mnCompetencia1.getItems().addAll(mitCalculadora);

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(mnCompetencia1);
        vBox = new VBox(mnbPrincipal);
    }

    @Override
    public void start(Stage stage) throws IOException {
        crearUI();
        stage.setTitle("Hello World"); // nombre de la ventana
        stage.setScene(new Scene(vBox));
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