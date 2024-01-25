package pqt_comparadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Diego Cuadrado
 */
public class Principal2 {

    public static void main(String[] args) {
        
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("Diego", "Cuadrado Martínez",
                          68, 1.72, LocalDate.of(1980, 4, 17)));
        listaPersonas.add(new Persona("Ana", "Méndez Pérez",
                          54, 1.62, LocalDate.of(1990, 2, 1)));
        listaPersonas.add(new Persona("Raquel", "Cuadrado Fiol",
                          64, 1.73, LocalDate.of(1978, 1, 30)));
        listaPersonas.add(new Persona("Emilio", "Barrete Rebollo",
                          74, 1.82, LocalDate.of(1985, 11, 7)));
               
        
        System.out.println("\n----------ORDEN NATURAL(APELLIDOS)-----");
        Collections.sort(listaPersonas);
        
        for (Persona p : listaPersonas)
            System.out.println(p);
        
        System.out.println("\n----------ORDEN NOMBRE ASCENDENTE-----");
        Collections.sort(listaPersonas, (p1, p2) -> 
                         p1.getNombre().compareTo(p2.getNombre()));
        for (Persona p : listaPersonas)
            System.out.println(p);
        System.out.println("\n----------ORDEN NOMBRE DESCENDENTE-----");
        Collections.sort(listaPersonas, (p1, p2) ->
                         p2.getNombre().compareTo(p1.getNombre()));
        for (Persona p : listaPersonas)
            System.out.println(p);
        
        System.out.println("\n---------ORDEN FECHA NACIMIENTO-----");
        Collections.sort(listaPersonas, (p1, p2) ->
                         p1.getFechaNac().compareTo(p2.getFechaNac()));
        for (Persona p : listaPersonas)
            System.out.println(p);
        
        System.out.println("\n----------ORDEN PESO-----");
        Collections.sort(listaPersonas, (p1, p2) ->
                         (p1.getPeso()) - (p2.getPeso()));
        for (Persona p : listaPersonas)
            System.out.println(p);
        
        System.out.println("\n---------ORDEN ALTURA-----");
        Collections.sort(listaPersonas, (p1, p2) ->
                         Double.compare(p1.getAltura(), p2.getAltura()));
        for (Persona p : listaPersonas)
            System.out.println(p);
    }   
}