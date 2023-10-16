package Carpeta;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class SimulacionBolas extends Thread {
    private Tablero tablero;

    public SimulacionBolas(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            int posicion = tablero.getAnchoTablero() / 2;
            for (int paso = 0; paso < tablero.getAltoTablero(); paso++) {
                int movimiento = random.nextInt(3) - 1;
                posicion += movimiento;
                posicion = Math.max(0, Math.min(posicion, tablero.getAnchoTablero() - 1));

                int contenedor = (posicion * tablero.getNumeroContenedores()) / tablero.getAnchoTablero();
                synchronized (tablero.getContadores()) {
                    tablero.getContadores()[contenedor]++;
                }

                int altura = Math.abs(tablero.getAltoTablero() - paso);

                // Manejar colisiones
                boolean colision = false;
                synchronized (tablero.getColisiones()) {
                    if (tablero.getColisiones().containsKey(contenedor)) {
                        List<Integer> alturas = tablero.getColisiones().get(contenedor);
                        if (alturas.contains(altura)) {
                            colision = true;
                        } else {
                            alturas.add(altura);
                        }
                    } else {
                        List<Integer> alturas = new LinkedList<>();
                        alturas.add(altura);
                        tablero.getColisiones().put(contenedor, alturas);
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                boolean finalColision = colision;
                SwingUtilities.invokeLater(() -> {
                    tablero.removeAll();
                    for (int i = 0; i < tablero.getBuffer().size(); i++) {
                        Componente componente = new Componente();
                        componente.setPreferredSize(new Dimension(10, finalColision ? altura / 2 : altura));
                        tablero.add(componente);
                    }
                    tablero.repaint();
                });
            }
        }
    }
}
