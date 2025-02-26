package com.example.idk.modelos;

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
                "values('"+nomCliente+"', '"+telCliente+"', '"+direccion+"', '"+emailCliente+"');";

        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void UPDATE(){}
    public void DELETE(){}
    public void SELECT(){}
}
