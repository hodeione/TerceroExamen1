package Carpeta;
import javax.swing.*;
import java.util.concurrent.BlockingQueue;
class LineaDeEnsamblaje extends Thread {
    private BlockingQueue<Componente> buffer;

    public LineaDeEnsamblaje(BlockingQueue<Componente> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Componente componente = buffer.take();
                // Aquí puedes realizar el ensamblaje o cualquier otra acción con el componente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
