package pqt_masActividades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio5 {

    public static void main(String[] args) {
        /*
        Hacer un programa que cree un fichero binario que contendrá el nombre,
        apellidos, edad, número de teléfono, dirección de email, ciudad de 
        residencia, nacionalidad y profesión de una persona.
        */
        Persona p1 = new Persona("AAA","AAA",16,111111111,
                "mail@mail.com","AAA","AAA","AAA");
        Persona p2 = new Persona("BBB","BBB",18,222222222,
                "mail@mail.com","BBB","BBB","BBB");
        Persona p3 = new Persona("CCC","CCC",41,333333333,
                "mail@mail.com","CCC","CCC","CCC");
        Persona p4 = new Persona("DDD","DDD",68,444444444,
                "mail@mail.com","DDD","DDD","DDD");
        Persona p5 = new Persona("EEE","EEE",15,555555555,
                "mail@mail.com","EEE","EEE","EEE");
        Persona p6 = new Persona("FFF","FFF",82,666666666,
                "mail@mail.com","FFF","FFF","FFF");
        
        try {
            FileOutputStream   fos = new FileOutputStream(new File("ficheroEjercicio5.bin"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.writeObject(p3);
            oos.writeObject(p4);
            oos.writeObject(p5);
            oos.writeObject(p6);
                       
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en la escritura");
        }       
    }   
}
