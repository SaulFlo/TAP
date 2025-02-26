package com.example.idk.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurante";
    static private String USER = "adminR";
    static private String PWD = "4321";
    static private String HOST = "localhost";
    static private String PORT = "3306";
    public static Connection connection;

    public static void crearConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB, USER, PWD);
            System.out.println("Conectado con DB");
        }
        catch(Exception e) {e.printStackTrace();}
    }
}
