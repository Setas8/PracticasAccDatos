package pqt_masActividades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio3_v2_AddFichero {

    static Scanner tcd = new Scanner(System.in);
    
    public static void main(String[] args) {
        /*
        Añadir Objetos al fichero becarios2.bin
        */
    System.out.println("INTRODUZCA BECARIOS EN EL FICHERO");
        boolean salir = false;
        try {
            FileOutputStream   fos = new FileOutputStream(new File("becarios2.bin"),true);
            ClaseOutputStream oos = new ClaseOutputStream(fos);
            
            do {
                Alumno a = crearBecario();
                
                oos.writeObject(a);
            
                System.out.println("\nSEGUIR INTRODUCIENDO BECARIOS(S/N): ");
                salir = tcd.nextLine().equalsIgnoreCase("s") ? true: false;
                System.out.println();
        
            } while (salir);
            
            oos.close();
            fos.close();
            
        } catch (IOException ex) {
                System.out.println("Error al escribir fichero");    
        }        
        tcd.close();
    }
    static public Alumno crearBecario() {
          
        System.out.print("Nombre: ");
        String nombre = tcd.nextLine();
        System.out.print("Apellido: ");
        String apellido = tcd.nextLine();
        System.out.print("Sexo(H/M): ");
        char sexo = tcd.next().charAt(0);
        System.out.print("Edad(20-60): ");
        int edad = tcd.nextInt(); tcd.nextLine();
        System.out.print("Número de suspensos(0-4): ");
        int suspensos = tcd.nextInt();tcd.nextLine();
        System.out.print("Residencia Familiar(SI/NO): ");
        boolean residencia = tcd.nextLine().equalsIgnoreCase("si") ? true: false;
        System.out.print("Ingresos familiares: ");
        double ingresos = tcd.nextDouble();tcd.nextLine();
              
        return new Alumno(nombre,apellido,sexo,edad,suspensos,residencia,ingresos);      
    }
}