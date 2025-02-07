package com.example.idk.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage{

    private Scene scene;
    private TextField txtPanel;
    private VBox vBox, teclado;
    private HBox[] arHBox;
    private Button[] arBtn;
    private Button[][] arBtnTeclado;
    private GridPane gdpTeclado;
    private String teclas = "789*456/123+.0=-";

    public void crearUI(){
        txtPanel = new TextField("0");
        txtPanel.setEditable(false);
        txtPanel.setAlignment(Pos.BASELINE_RIGHT);

        CrearKeyBoard();


        vBox = new VBox(txtPanel, gdpTeclado);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        scene = new Scene(vBox, 200,200);

    }

    public void crearTeclado(){
        arHBox = new HBox[5];
        arBtn = new Button[20];
    }

    public void CrearKeyBoard(){
        arBtnTeclado = new Button[4][4];
        gdpTeclado = new GridPane();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                arBtnTeclado[i][j] = new Button("7");
                arBtnTeclado[i][j].setPrefSize(50,50);
                gdpTeclado.add(arBtnTeclado[i][j], i, j);
            }

        }
    }

    public Calculadora(){
        crearUI();
        this.setTitle("Calculadora");
        this.setScene(scene);
        this.show();
    }
}
