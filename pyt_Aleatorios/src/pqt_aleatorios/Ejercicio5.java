package pqt_aleatorios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio5 {

    public static void main(String[] args) {
        /*
        Crear un fichero serializable que contenga 10 alumnos, de los cuales
        se guardara NºClase, Nombre y Nota. El nº de clase coincidirá con la 
        posición dentro del fichero.
        */
        
        File f = new File("alumnos.dat");
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        crearAlumnos(listaAlumnos);
               
        try {
            FileOutputStream   fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
                       
            for (Alumno a : listaAlumnos) {
                oos.writeObject(a);
            }
      
                      
            leerFicheroAlumnos(f);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }            
    }
    private static void crearAlumnos(ArrayList<Alumno> lista) {
        
        lista.add(new Alumno(1,stringCaracteres("AAA"),1));
        lista.add(new Alumno(2,stringCaracteres("BBB"),2));
        lista.add(new Alumno(3,stringCaracteres("CCC"),3));
        lista.add(new Alumno(4,stringCaracteres("DDD"),4));
        lista.add(new Alumno(5,stringCaracteres("EEE"),5));
        lista.add(new Alumno(6,stringCaracteres("FFF"),6));
        lista.add(new Alumno(7,stringCaracteres("GGG"),7));
        lista.add(new Alumno(8,stringCaracteres("HHH"),8));
        lista.add(new Alumno(9,stringCaracteres("III"),9));
        lista.add(new Alumno(10,stringCaracteres("JJJ"),10));                       
    }  
    private static void leerFicheroAlumnos(File f) {
        System.out.println("NºALUMNO\tNOMBRE\t\tNOTA");
        try {
            FileInputStream   fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                Alumno a;
                while (true) {
                    a = (Alumno)ois.readObject();
                    System.out.println(a.getNumClase() + "\t\t" +
                                       a.getNombre() + "\t\t" +a.getNota());
                }                               
            } catch (EOFException ex) {              
            } 
            ois.close();
            fis.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }        
    }
    //Dar a los Strings (nombre) una longitud fija
    private static String stringCaracteres(String palabra) {
        
        int tamanioCampo = 5;
        String str = "";
        int tamanioPalabra = palabra.length();
        
        if (tamanioPalabra <= tamanioCampo) {
            for (int i = tamanioPalabra;i < tamanioCampo; i++) {
                palabra += " ";
            }
            str = palabra;
        }
        else if (tamanioPalabra >= tamanioCampo) {
            str = palabra.substring(0, tamanioCampo);
        }
        else
            str = palabra;
                
        return str;
    }
}
