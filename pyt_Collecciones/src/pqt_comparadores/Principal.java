package pqt_comparadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Diego Cuadrado
 */
public class Principal {

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
                       
        Comparator c1 = new ComparadorNombrePersona();
        Collections.sort(listaPersonas, c1);
        for (Persona p : listaPersonas)
            System.out.println(p);

        
        System.out.println("\n----------ORDEN NOMBRE DESCENDENTE-----");
        Comparator c5 = new ComparadorNombrePersona().reversed();
        Collections.sort(listaPersonas, c5);
        for (Persona p : listaPersonas)
            System.out.println(p);
        
        System.out.println("\n---------ORDEN FECHA NACIMIENTO-----");
        Comparator c2 = new ComparadorFechaPersona();
        Collections.sort(listaPersonas, c2);
        for (Persona p : listaPersonas)
            System.out.println(p);
        /*
        Comparator<Persona> c = (p1,p2) -> p1.getFechaNac().compareTo(p2.getFechaNac());
        Collections.sort(listaPersonas, c);
        for (Persona p : listaPersonas)
            System.out.println(p);
        */
        
        System.out.println("\n----------ORDEN PESO-----");
        Comparator c3 = new ComparadorPesoPersona();
        Collections.sort(listaPersonas, c3);   
        for (Persona p : listaPersonas)
            System.out.println(p);
        
        System.out.println("\n---------ORDEN ALTURA-----");
        Comparator c4 = new ComparadorAlturaPersona();
        Collections.sort(listaPersonas, c4);
        for (Persona p : listaPersonas)
            System.out.println(p);        
    }   
}
