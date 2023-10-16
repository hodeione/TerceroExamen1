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
            tablero.iniciarSimulacion();
        });
    }
   /* public static void main(String[] args) {
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
            tablero.iniciarSimulacion();
        });
    }*/
    /*public static void main(String[] args) {
        BlockingQueue<Componente> buffer = new ArrayBlockingQueue<>(10);

        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("Simulacion de la produccion y ensamblaje");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Tablero tablero = new Tablero(buffer);
            frame.add(tablero);
            frame.pack();
            frame.setVisible(true);

            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(100);
                        SwingUtilities.invokeLater(() -> {
                            synchronized (tablero) {
                                tablero.repaint();
                            }
                        });
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        EstacionDeTrabajo estacion1 = new EstacionDeTrabajo(buffer);
        EstacionDeTrabajo estacion2 = new EstacionDeTrabajo(buffer);
        LineaDeEnsamblaje lineaDeEnsamblaje = new LineaDeEnsamblaje(buffer);

        SwingUtilities.invokeLater(()->
        {
            System.out.println("Comenzando la producción");
            JFrame frame =new JFrame("Visualizacion del tamño del bufer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400,400);
            JPanel bufferPanel = new JPanel();
            frame.add(bufferPanel);

            estacion1.start();
            estacion2.start();
            lineaDeEnsamblaje.start();

            /*new Thread(() -> {
                while (true) {
                    try {
                        sleep(100);
                        SwingUtilities.invokeLater(() -> {
                            bufferPanel.removeAll();
                            for (int i = 0; i < buffer.size(); i++) {
                                bufferPanel.add(new Componente());
                            }
                            bufferPanel.revalidate();
                            bufferPanel.repaint();
                        });
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            frame.setVisible(true);
            });
        };*/

}
