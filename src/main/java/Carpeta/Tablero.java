package Carpeta;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.BlockingQueue;



class Tablero extends JPanel {
    private static final int ANCHO_TABLERO = 800;
    private static final int ALTO_TABLERO = 600;
    private static final int NUMERO_BOLAS = 100;
    private static final int NUMERO_CONTENEDORES = 10;

    private int[] contadores;
    private BlockingQueue<Componente> buffer;

    public Tablero(BlockingQueue<Componente> buffer) {
        this.buffer = buffer;
        contadores = new int[NUMERO_CONTENEDORES];
        setPreferredSize(new Dimension(ANCHO_TABLERO, ALTO_TABLERO));

        // Iniciar la simulación
        new SimulacionBolas(this).start();
    }

    public int getAnchoTablero() {
        return ANCHO_TABLERO;
    }

    public int getAltoTablero() {
        return ALTO_TABLERO;
    }

    public int getNumeroContenedores() {
        return NUMERO_CONTENEDORES;
    }

    public int[] getContadores() {
        return contadores;
    }

    public BlockingQueue<Componente> getBuffer() {
        return buffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int contenedorWidth = ANCHO_TABLERO / NUMERO_CONTENEDORES;

        synchronized (contadores) {
            for (int i = 0; i < NUMERO_CONTENEDORES; i++) {
                int contenedorHeight = (ALTO_TABLERO * contadores[i]) / NUMERO_BOLAS;
                int x = i * contenedorWidth + (contenedorWidth / 2);
                int y = ALTO_TABLERO - contenedorHeight;
                g.setColor(Color.BLUE);
                g.fillOval(x - contenedorWidth / 4, y, contenedorWidth / 2, contenedorHeight);
            }
        }
    }
}