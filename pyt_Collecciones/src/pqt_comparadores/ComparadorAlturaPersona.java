package pqt_comparadores;

import java.util.Comparator;

/**
 *
 * @author Diego Cuadrado
 */
public class ComparadorAlturaPersona implements Comparator<Persona>{

    @Override
    public int compare(Persona p1, Persona p2) {
        return  Double.compare(p1.getAltura(), p2.getAltura());       
    }        
}
