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
public class Ejercicio4 {

    public static void main(String[] args) {       
            /*
            Realizar un programa que, partiendo del fichero binario “datosbeca.bin”,
            calcule la cuantía de la beca (en caso de que la haya). El total de la
            beca se calcula como sigue:
            
            Base fija de 1500€
            Si los ingresos anuales de la familia son menores o iguales a la media
            (12.000€), la beca se incrementa en 500€, en caso contrario no lleva
            complementos.
            Si la edad de la persona es inferior a 23 años, 200€ de gratificación,
            si es mayor no hay gratificación.
            Si no hay suspensos en el curso anterior, hay una gratificación de
            500€, 1 suspenso 200€, si hay 2 o más suspensos no hay beca.
            Si vive de alquiler (no residencia familiar), 1000€ más de
            gratificación.
            Visualizar el nombre de cada uno de los becarios y su cuantía total
            (sólo los que tienen beca).
            */
        try {
            FileInputStream fis = new FileInputStream(new File("becarios2.bin"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println("");
            System.out.println("NOMBRE\t\tBECA");
            while (true) {
                Alumno a = (Alumno)ois.readObject();
                
                int beca = 1500;
                if (a.getIngresosFamiliares() <= 12000) 
                    beca += 500;
                if (a.getEdad() < 23) 
                    beca += 200;
                if (!a.isResidenciaFamiliar()) 
                    beca += 1000;
                switch (a.getNumSuspensos()) {
                    case 0:                               
                        beca += 500;
                        break;
                    case 1:
                        beca += 200;
                        break;
                    default:
                        beca = 0;
                }                                             
                                   
                System.out.println(a.getNombre() + "\t   --> " + beca + "€");
            }   
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        }
    }   
}
