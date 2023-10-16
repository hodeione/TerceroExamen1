package Carpeta;

import java.awt.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class EstacionDeTrabajo extends Thread{
    private BlockingQueue<Componente> buffer;

    public EstacionDeTrabajo(BlockingQueue<Componente> buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        while(true){
            try{
                Componente componente = producirComponente();
                buffer.put(componente);
                sleep(500);
              //  System.out.println("Componente " + componente.getId() + " procesado");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private Component producirComponente(){
        return new Componente();
    }
}
