package com.example.idk.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ClientesDAO {
    private int idCliente;
    private String nomCliente;
    private String telCliente;
    private String direccion;
    private String emailCliente;


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public void INSERT(){
        String query = "INSERT INTO clientes(nomCliente, telCliente, dirección, emailCliente)\n" +
                "values('"+nomCliente+"', '"+telCliente+"', '"+direccion+"', '"+emailCliente+"')";

        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void UPDATE(){
        String query = "UPDATE clientes SET nomCliente = '"+nomCliente+"', telCliente = '"+telCliente+"', " +
                        "dirección = '"+direccion+"',  emailCliente = '"+emailCliente+"' WHERE idCliente = "+idCliente;

        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query = "DELETE FROM clientes WHERE idCliente = "+idCliente;
        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ClientesDAO> SELECT(){
        String query = "SELECT * FROM clientes";
        ObservableList<ClientesDAO> listaC = FXCollections.observableArrayList();
        ClientesDAO objC;
        try{
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new ClientesDAO();

                objC.setIdCliente(res.getInt("idCliente"));
                objC.setNomCliente(res.getString("nomCliente"));
                objC.setTelCliente(res.getString("telCliente"));
                objC.setDireccion(res.getString("dirección"));//Desde la base de datos
                objC.setEmailCliente(res.getString("emailCliente"));

                listaC.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaC;
    }
}
