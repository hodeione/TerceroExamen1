package Carpeta;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


class SimulacionBolas extends Thread {
    private Tablero tablero;

    public SimulacionBolas(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            double gauss = random.nextGaussian(); // Obtener un valor de la distribución normal estándar

            // Ajustar el valor para que esté en el rango del tablero
            int contenedor = (int) ((gauss + 3) * tablero.getNumeroContenedores() / 6);

            synchronized (tablero.getContadores()) {
                tablero.getContadores()[contenedor]++;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                tablero.removeAll();
                for (int i = 0; i < tablero.getBuffer().size(); i++) {
                    tablero.add(new Componente());
                }
                tablero.repaint();
            });
        }
    }
}
