package com.example.idk.componentes;

import javafx.scene.control.ProgressBar;

public class Hilo extends Thread{

    private ProgressBar pgbHilo;

    public Hilo(String nombre, ProgressBar pgbHilo){
        super(nombre);
        this.pgbHilo = pgbHilo;
    }

    @Override
    public void run(){
        super.run();
        double avance = 0;

        while(avance < 1){
            avance += Math.random() * 0.01;
            pgbHilo.setProgress(avance);
            try{
                sleep((long)(Math.random() * 100));
            }catch (InterruptedException e){e.printStackTrace();}
        }

    }

    /*@Override
    public void run() {
        super.run();
        for (int i = 1; i<= 10; i++){
            try {
                sleep((long)(Math.random() * 2000));
            } catch(InterruptedException e) {e.printStackTrace();}
            System.out.println("El hilo " + this.getName() + " esta en " + i);
        }
    }*/
}
