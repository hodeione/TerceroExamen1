package Carpeta;

import javax.swing.*;

public  class Componente extends JPanel {

    private static final long serialVersionUID = 1L;

    private int id;
    private static int contador = 0;

    public Componente(){
        this.id = contador++;
    }

    public int getId(){
        return id;
    }
}
