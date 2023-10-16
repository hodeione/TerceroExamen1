package Carpeta;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Componente extends JPanel {
    private static final long serialVersionUID = 1L;
    private int id;
    private static int contador = 0;

    public Componente() {
        this.id = contador++;
    }

    public int getId() {
        return id;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}