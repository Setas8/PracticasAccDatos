package pqt_comparadores;

import java.util.Comparator;

/**
 *
 * @author Diego Cuadrado
 */
public class ComparadorFechaPersona implements Comparator<Persona>{

    @Override
    public int compare(Persona p1, Persona p2) {
        return p1.getFechaNac().compareTo(p2.getFechaNac());
    }    
}
