package com.example.idk.componentes;

import com.example.idk.modelos.ClientesDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;

import java.util.Optional;

public class ButtonCell extends TableCell<ClientesDAO,String> {

    private Button btnCelda;
    public ButtonCell(String name){

        btnCelda = new Button(name);
        btnCelda.setOnAction(event -> {
            ClientesDAO objC = this.getTableView().getItems().get(this.getIndex());
           if(name.equals("Editar")){

           }
           else{
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("Confirmacion");
               alert.setContentText("¿Estas seguro de querer eliminar el registro?");
               Optional<ButtonType> result = alert.showAndWait();
               if(result.get() == ButtonType.OK){
                   objC.DELETE();
               }
           }
           this.getTableView().setItems(objC.SELECT());
           this.getTableView().refresh();
        });
    }

    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            this.setGraphic(btnCelda);
        }
    }
}
