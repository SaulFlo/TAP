package com.example.idk.vistas;

import com.example.idk.componentes.ButtonCell;
import com.example.idk.modelos.ClientesDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaClientes extends Stage {

    private Button btnAgregar;
    private ToolBar tlbMenu;
    private TableView<ClientesDAO> tbvClientes;
    private VBox vbox;
    private Scene scene;

    public void createTable(){
        ClientesDAO obj = new ClientesDAO();

        TableColumn<ClientesDAO, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nomCliente"));

        TableColumn<ClientesDAO, String> tbcTelefono = new TableColumn<>("Telefono");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telCliente"));

        TableColumn<ClientesDAO, String> tbcDireccion = new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));//Desde la clase


        TableColumn<ClientesDAO, String> tbcEmail = new TableColumn<>("Email");
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));

        TableColumn<ClientesDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClientesDAO, String>, TableCell<ClientesDAO, String>>() {
            @Override
            public TableCell<ClientesDAO, String> call(TableColumn<ClientesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell("Editar");
            }
        });

        TableColumn<ClientesDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<ClientesDAO, String>, TableCell<ClientesDAO, String>>() {
            @Override
            public TableCell<ClientesDAO, String> call(TableColumn<ClientesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell("Eliminar");
            }
        });

        tbvClientes.getColumns().addAll(tbcNombre, tbcTelefono, tbcDireccion, tbcEmail, tbcEditar, tbcEliminar);
        tbvClientes.getItems().setAll(obj.SELECT());

    }

    public void crearUI() {
        tbvClientes = new TableView<>();

        btnAgregar = new Button();
        btnAgregar.setOnAction(actionEvent -> new Cliente(tbvClientes));
        ImageView imvAgregar = new ImageView(getClass().getResource("/images/iconoAgregar.png").toString());
        imvAgregar.setFitHeight(20);
        imvAgregar.setFitWidth(20);
        btnAgregar.setGraphic(imvAgregar);

        tlbMenu = new ToolBar(btnAgregar);

        createTable();
        vbox = new VBox(tlbMenu, tbvClientes);
        scene = new Scene(vbox, 800,600);
    }

    public ListaClientes() {
        crearUI();
        this.setTitle("Lista de Clientes");
        this.setScene(scene);
        this.show();
    }
}
