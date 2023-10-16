package Carpeta;
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
                sleep(300);
                visualizar(buffer.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ensamblarComponente(){

    }
    private void visualizar(int size){
        System.out.println("Componente ensamblado. Quedan " + size + " componentes por ensamblar");
    }
}
