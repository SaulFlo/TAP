package com.example.idk.vistas;

import com.example.idk.modelos.Intento;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Registros extends Stage {

    HBox hBody;
    VBox vbox, vFacil, vMedio, vDificil;
    Scene scene;

    List<Intento> inFacil, inMedio, inDificil;
    ListView<String> lvFacil, lvMedio, lvDificil;

    public void leerArchivos(){
        inFacil = new ArrayList<>();
        try{
            ObjectInputStream arch = new ObjectInputStream(new FileInputStream("archivos/intentosFacil.txt"));
            while (true) {
                try {
                    Intento intento = (Intento) arch.readObject();
                    inFacil.add(intento);
                } catch (java.io.EOFException eof) {
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        inMedio = new ArrayList<>();
        try{
            ObjectInputStream arch = new ObjectInputStream(new FileInputStream("archivos/intentosMedio.txt"));
            while (true) {
                try {
                    Intento intento = (Intento) arch.readObject();
                    inMedio.add(intento);
                } catch (java.io.EOFException eof) {
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        inDificil = new ArrayList<>();
        try{
            ObjectInputStream arch = new ObjectInputStream(new FileInputStream("archivos/intentosDificil.txt"));
            while (true) {
                try {
                    Intento intento = (Intento) arch.readObject();
                    inDificil.add(intento);
                } catch (java.io.EOFException eof) {
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void crearListas(){
        lvFacil = new ListView<>();
        int cont = 1;
        if(!inFacil.isEmpty()) {
            for (Intento intento : inFacil) {
                lvFacil.getItems().add(cont + ".- " + intento.toString());
                cont++;
            }
        }

        lvMedio = new ListView<>();
        cont = 1;
        if(!inMedio.isEmpty()) {
            for (Intento intento : inMedio){
                lvMedio.getItems().add(cont + ".- " + intento.toString());
                cont++;
            }
        }

        lvDificil = new ListView<>();
        cont = 1;
        if(!inDificil.isEmpty()) {
            for (Intento intento : inDificil) {
                lvDificil.getItems().add(cont + ".- " + intento.toString());
                cont++;
            }
        }

    }

    public void crearUI(){
        leerArchivos();
        crearListas();
        Text txtFacil = new Text("FACIL");
        txtFacil.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        Text txtMedio = new Text("MEDIO");
        txtMedio.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        Text txtDificil = new Text("DIFICIL");
        txtDificil.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        vFacil = new VBox(txtFacil, lvFacil);
        vFacil.setStyle("-fx-padding: 12px 0 0 0; -fx-spacing: 10px; -fx-background-color: #85a8c1; -fx-border-color: black");
        vFacil.setAlignment(Pos.CENTER);

        vMedio = new VBox(txtMedio, lvMedio);
        vMedio.setStyle("-fx-padding: 12px 0 0 0; -fx-spacing: 10px; -fx-background-color: #85a8c1; -fx-border-color: black");
        vMedio.setAlignment(Pos.CENTER);

        vDificil = new VBox(txtDificil, lvDificil);
        vDificil.setStyle("-fx-padding: 12px 0 0 0; -fx-spacing: 10px; -fx-background-color: #85a8c1; -fx-border-color: black");
        vDificil.setAlignment(Pos.CENTER);

        HBox.setHgrow(vFacil, Priority.ALWAYS);
        HBox.setHgrow(vMedio, Priority.ALWAYS);
        HBox.setHgrow(vDificil, Priority.ALWAYS);
        hBody = new HBox(vFacil, vMedio, vDificil);

        vbox = new VBox(hBody);

        scene = new Scene(vbox);
    }

    public Registros() {
        crearUI();
        this.setTitle("Registros");
        this.setMaximized(true);
        this.setScene(scene);
        this.show();
    }
}
