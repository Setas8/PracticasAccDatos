package pqt_actividadesSerializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio14 {

    static ArrayList<Persona> listaPersonas = new ArrayList<>();    
    
    public static void main(String[] args) {
        /*
        Realizar el ejercicio 12 utilizando una clase Persona que implemente
        Serializable
        */
        crearPersonas();
        introducirPersonasFichero();                   
    }
    static public void crearPersonas() {
        Scanner tcd = new Scanner(System.in);
        
        boolean salir = false;
        
        do {
            System.out.print("Nombre: ");
            String nombre    = tcd.nextLine();
            System.out.print("Primer apellido: ");
            String apellido1 = tcd.nextLine();
            System.out.print("Segundo apellido: ");
            String apellido2 = tcd.nextLine();
            System.out.print("Año de nacimiento: ");
            int anio = tcd.nextInt();tcd.nextLine();
            System.out.println("SEGUIR INTRODUCIENDO PERSONAS(S/N): ");
            salir = tcd.nextLine().equalsIgnoreCase("s") ? true: false;
            System.out.println();
            
            listaPersonas.add(new Persona(nombre,apellido1,apellido2,anio));
                       
        } while (salir);
        tcd.close();
    }
    static public void introducirPersonasFichero() {
        File f = new File("archivoEjercicio14y15.dat");
        
        try {
            FileOutputStream   fos = new FileOutputStream(f);           
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            listaPersonas.forEach((p) -> {
                try {
                    oos.writeObject(p);
                    
                } catch (IOException ex) {
                    System.out.println("Error al escribir la lista");
                }
            });           
                      
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en escritura");
        }                
    }
}
