package Carpeta;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

class Tablero extends JPanel {
    private static final int ANCHO_TABLERO = 800;
    private static final int ALTO_TABLERO = 600;
    private static final int NUMERO_BOLAS = 100;
    private static final int NUMERO_CONTENEDORES = 10;

    private int[] contadores;
    private BlockingQueue<Componente> buffer;
    private List<Componente>[][] matrizBolas;
    private Object lock = new Object();
    private java.util.Map<Integer, List<Integer>> colisiones = new java.util.HashMap<>();

    @SuppressWarnings("unchecked")
    public Tablero(BlockingQueue<Componente> buffer) {
        this.buffer = buffer;
        contadores = new int[NUMERO_CONTENEDORES];
        setPreferredSize(new Dimension(ANCHO_TABLERO, ALTO_TABLERO));
        matrizBolas = new List[NUMERO_CONTENEDORES][ALTO_TABLERO];
        for (int i = 0; i < NUMERO_CONTENEDORES; i++) {
            for (int j = 0; j < ALTO_TABLERO; j++) {
                matrizBolas[i][j] = new LinkedList<>();
            }
        }
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

    public java.util.Map<Integer, List<Integer>> getColisiones() {
        return colisiones;
    }

    public void iniciarSimulacion() {
        new SimulacionBolas(this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int contenedorWidth = ANCHO_TABLERO / NUMERO_CONTENEDORES;

        synchronized (contadores) {
            for (int i = 0; i < NUMERO_CONTENEDORES; i++) {
                int contenedorHeight = (ALTO_TABLERO * contadores[i]) / NUMERO_BOLAS;
                g.fillRect(i * contenedorWidth, ALTO_TABLERO - contenedorHeight, contenedorWidth, contenedorHeight);
            }
        }
    }
}