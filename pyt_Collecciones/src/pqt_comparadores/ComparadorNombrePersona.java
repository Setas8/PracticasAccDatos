package pqt_comparadores;

import java.util.Comparator;

/**
 *
 * @author Diego Cuadrado
 */
public class ComparadorNombrePersona implements Comparator<Persona>{

    @Override
    public int compare(Persona p1, Persona p2) {
        return p1.getNombre().compareTo(p2.getNombre());
    }   
}