package com.example.idk.vistas;

import com.example.idk.modelos.ClientesDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {

    private TextField txtNombre, txtTelefono, txtDireccion, txtEmail;
    private Button btnGuardar;
    private VBox vbox;
    private Scene scene;
    private ClientesDAO objCl;
    private TableView<ClientesDAO> tbvClientes;

    public void crearUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del cliente");

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono del cliente");

        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion del cliente");

        txtEmail = new TextField();
        txtEmail.setPromptText("Email del cliente");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            objCl.setNomCliente(txtNombre.getText());
            objCl.setTelCliente(txtTelefono.getText());
            objCl.setDireccion(txtDireccion.getText());
            objCl.setEmailCliente(txtEmail.getText());

            objCl.INSERT();

            tbvClientes.setItems(objCl.SELECT());
            tbvClientes.refresh();

            this.close();
        });

        vbox = new VBox(txtNombre, txtTelefono, txtDireccion, txtEmail, btnGuardar);
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(10));

        scene = new Scene(vbox, 220,250);
    }

    public Cliente(TableView<ClientesDAO> tbvClientes) {
        this.tbvClientes = tbvClientes;
        objCl = new ClientesDAO();

        crearUI();
        this.setTitle("Registrar Cliente");
        this.setScene(scene);
        this.show();
    }
}
