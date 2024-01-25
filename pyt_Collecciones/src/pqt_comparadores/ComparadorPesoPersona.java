package pqt_comparadores;

import java.util.Comparator;

/**
 *
 * @author Diego Cuadrado
 */
public class ComparadorPesoPersona implements Comparator<Persona>{

    @Override
    public int compare(Persona p1, Persona p2) {
        return (p1.getPeso() - p2.getPeso());
    }   
}
