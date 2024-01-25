package pqt_actividadesSerializable;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio15 {

    public static void main(String[] args) {
        /*
        Lee el fichero de datos de personas del ejercicio anterior y saca la
        información de cada persona por pantalla.
        */
        File f = new File("archivoEjercicio14y15.dat");
        
        System.out.println(String.format("%-20.20s", "NOMBRE") + 
                           String.format("%-20.20s", "APELLIDO 1") + 
                           String.format("%-20.20s", "APELLIDO 2") + 
                           String.format("%-20.20s", "AÑO NACIMIENTO"));
        try {
            FileInputStream   fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                while (true) {
                    Persona p = (Persona)ois.readObject();                   
                    System.out.println(p);
                }               
            } catch(EOFException e){ 
                
            } 
            ois.close();
            fis.close();                       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException | ClassCastException  ex) {
            System.out.println(ex);
        }      
    } 
}
