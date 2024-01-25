package pqt_aleatorios;

import java.io.Serializable;

/**
 *
 * @author Diego Cuadrado
 */
public class Alumno implements Serializable{
    
    private int numClase, nota;
    private String nombre;
    
    public Alumno(int numClase, String nombre, int nota) {
        
        this.numClase = numClase;
        this.nombre   = nombre;
        this.nota     = nota;
    }

    public int getNumClase() {
        return numClase;
    }

    public void setNumClase(int numClase) {
        this.numClase = numClase;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return numClase+nombre+nota;
    }
    
}
