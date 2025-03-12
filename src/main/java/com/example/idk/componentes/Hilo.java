package com.example.idk.componentes;

public class Hilo extends Thread{

    public Hilo(String nombre){
        super(nombre);
    }

    @Override
    public void run() {
        super.run();
        for (int i = 1; i<= 10; i++){
            try {
                sleep((long)(Math.random() * 2000));
            } catch(InterruptedException e) {e.printStackTrace();}
            System.out.println("El hilo " + this.getName() + " esta en " + i);
        }
    }
}
