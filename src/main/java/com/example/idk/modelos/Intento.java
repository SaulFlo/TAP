package com.example.idk.modelos;

import java.io.Serializable;

public class Intento implements Serializable{
    String fecha;
    String hora;
    private int segundos;

    public Intento(String fecha, String hora, int segundos) {
        this.fecha = fecha;
        this.hora = hora;
        this.segundos = segundos;
    }

    public String toString(){
        return "Fecha: " + fecha + " // Hora: " + hora.substring(0,8) + " // Segundos de respuesta: " + segundos;
    }
}
