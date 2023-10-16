package Carpeta;

import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Lanzador {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulación de Bolas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BlockingQueue<Componente> buffer = new ArrayBlockingQueue<>(10);

            EstacionDeTrabajo estacion1 = new EstacionDeTrabajo(buffer);
            EstacionDeTrabajo estacion2 = new EstacionDeTrabajo(buffer);
            LineaDeEnsamblaje lineaDeEnsamblaje = new LineaDeEnsamblaje(buffer);

            Tablero tablero = new Tablero(buffer);
            frame.add(tablero);
            frame.pack();
            frame.setVisible(true);

            estacion1.start();
            estacion2.start();
            lineaDeEnsamblaje.start();
        });
    }
}
