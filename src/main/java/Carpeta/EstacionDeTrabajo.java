package Carpeta;

import java.util.concurrent.BlockingQueue;

class EstacionDeTrabajo extends Thread {
    private BlockingQueue<Componente> buffer;

    public EstacionDeTrabajo(BlockingQueue<Componente> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Componente componente = producirComponente();
                buffer.put(componente);
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Componente producirComponente() {
        return new Componente();
    }
}