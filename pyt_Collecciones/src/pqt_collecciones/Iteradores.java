package pqt_collecciones;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Diego Cuadrado
 */
public class Iteradores {

    public static void main(String[] args) {
        
        ArrayList <Integer> lista = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }
        
        //Ejemplo indicando el tipo de objeto de iterador
        Iterator<Integer> it = lista.iterator();
        while (it.hasNext()) {
            Integer t = it.next();
            if (t % 2 == 0) {
                it.remove();
            }
        }
        /*
        Ejemplo no indicando el tipo de objeto del iterador
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            Integer t = (Integer) it.next();
            if (t % 2 == 0) {
                it.remove();
            }
        }
        */
        for (Integer i : lista) 
            System.out.println(i);
    }
    
}
