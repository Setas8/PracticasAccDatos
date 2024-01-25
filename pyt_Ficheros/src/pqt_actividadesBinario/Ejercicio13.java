package pqt_actividadesBinario;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio13 {

    static String azul = "\033[34m";
    public static void main(String[] args) {
        /*
        Leer el fichero del ejercicio anterior y mostrar los datos por pantalla.
        */
        leerFicheroBinario();
    }
    public static void leerFicheroBinario() {
        System.out.println(azul + "NOMBRE              "
                        + "PRIMER APELLIDO     SEGUNDO APELLIDO   "
                        + "FECHA NACIMIENTO");

        try {
            File f = new File("archivoBiEjercici012.dat");
            FileInputStream  fis = new FileInputStream (f);
            DataInputStream  dis = new DataInputStream(fis);
            try {
                while (dis.available()> 0) {
                    System.out.println(dis.readUTF() + dis.readUTF() +
                                   dis.readUTF() + dis.readUTF());   
                }
            }  catch (EOFException ex) {
                System.out.println("FINAL DE FICHERO " + ex.getMessage());
            }
            dis.close();
            fis.close();               
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        }
    }
}
