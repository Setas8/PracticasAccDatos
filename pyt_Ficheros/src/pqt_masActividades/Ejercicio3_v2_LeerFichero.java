/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pqt_masActividades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio3_v2_LeerFichero {

    public static void main(String[] args) {
        /*
        Leer el fichero becarios2.bin
        */
        System.out.println(String.format("%-20s%-20s%-8s%-8s%-10s%-12s%-8s", 
        "NOMBRE","APELLIDO","SEXO","EDAD","SUSPENSOS","RESIDENCIA","INGRESOS"));
        try {
            FileInputStream   fis = new FileInputStream(new File("becarios2.bin"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while (true) {
                Alumno a = (Alumno)ois.readObject();
                System.out.println(a);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("");
        } catch (IOException ex) {
               
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la clase");
        }
    }   
}
