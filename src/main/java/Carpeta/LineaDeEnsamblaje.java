package Carpeta;
import javax.swing.*;
import java.util.concurrent.BlockingQueue;
public class LineaDeEnsamblaje extends Thread{
    private BlockingQueue<Componente> buffer;

    public LineaDeEnsamblaje(BlockingQueue<Componente> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Componente componente = buffer.take();
                ensamblarComponente(componente);
                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ensamblarComponente(Componente componente){
        visualizar(buffer.size());
        System.out.println("Ensamblado componente " + componente.getId());
    }
    private void visualizar(int size){
        SwingUtilities.invokeLater(()->
        {
            System.out.println("Componente ensamblado. Quedan " + size + " componentes por ensamblar");
        });
    }
}
