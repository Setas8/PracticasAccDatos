package pqt_masActividades;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio6 {

    public static void main(String[] args) {
        /*
        Tomando como base el fichero creado en el ejercicio anterior, hacer
        un programa que lea los datos de las personas y los copie en otros
        ficheros distintos según se traten de menores de edad, adultos o mayores
        de 65 años. Escribir en pantalla los 3 nuevos ficheros creados.
        */
         
        try {
            FileInputStream   fis = new FileInputStream(new File("ficheroEjercicio5.bin"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            FileOutputStream   fos1 = new FileOutputStream(new File("Ej5Menores.bin"));
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
            
            FileOutputStream   fos2 = new FileOutputStream(new File("Ej5Adultos.bin"));
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            
            FileOutputStream   fos3 = new FileOutputStream(new File("Ej5TerceraEdad.bin"));
            ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
            
            try {
                while (true) {
                    Persona p = (Persona) ois.readObject();

                    if (p.getEdad() < 18)
                        oos1.writeObject(p);
                    else if (p.getEdad() > 65)
                        oos3.writeObject(p);              
                    else
                        oos2.writeObject(p);         
                }             
            } catch(EOFException e) {
                
            } catch (ClassNotFoundException ex) {
                 System.out.println("Clase no encontrada");
            }                   
            oos1.close();
            oos2.close();
            oos3.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error de escritura");
        }
        System.out.println("NOMBRE\t\t   APELLIDO\t\t EDAD\t   TELÉFONO\t"
                + "MAIL\t\t   CIUDAD\t\tNACIONALIDAD\t\tPROFESIÓN");
        leerFicheroMenores();
        leerFicheroAdultos();
        leerFicheroMayores();
    }    
    static public void leerFicheroMenores() {
        System.out.println("FICHERO MENORES");
        try {
            FileInputStream   fis = new FileInputStream(new File("Ej5Menores.bin"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while (true) {
                Persona p = (Persona)ois.readObject();
                System.out.println(p);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
               System.out.println("----");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la clase");
        }
    }
    static public void leerFicheroAdultos() {
        System.out.println("FICHERO ADULTOS");
        try {
            FileInputStream   fis = new FileInputStream(new File("Ej5Adultos.bin"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while (true) {
                Persona p = (Persona)ois.readObject();
                System.out.println(p);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
               System.out.println("----");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la clase");
        }
    }
    static public void leerFicheroMayores() {
        System.out.println("FICHERO TERCERA EDAD");
        try {
            FileInputStream   fis = new FileInputStream(new File("Ej5TerceraEdad.bin"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while (true) {
                Persona p = (Persona)ois.readObject();
                System.out.println(p);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
               System.out.println("----");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la clase");
        }
    }
}
