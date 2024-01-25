package pqt_masActividades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio1 {
      
    public static void main(String[] args) {
        /*
        Crea una aplicación que pida por teclado la cantidad de números 
        aleatorios enteros positivos que se van a guardar en un fichero binario.
        El rango de los números aleatorios estará entre 0 y 100, incluyendo el
        100. Cada vez que se ejecute la aplicación se añadirán números al
        fichero sin borrar los anteriores. Mostrar en pantalla el fichero
        creado.
        */
        Scanner tcd = new Scanner(System.in);
        
        System.out.println("Introduce la cantidad de números positivos del fichero: ");
        int num = tcd.nextInt(); tcd.nextLine();
                 
        try {
            FileOutputStream fos = new FileOutputStream(new File("archivoEjercicio1.dat"), true);
            DataOutputStream dos = new DataOutputStream(fos);
            Random aleatorio = new Random();
            
            for (int i = 0; i < num; i++) {
                boolean ok = false;
                int numAleatorio;
                do {
                    numAleatorio = aleatorio.nextInt();
                    ok = (numAleatorio > 0);
                } while (!ok);                             
               System.out.println("Num " + (i+1) + ": " + numAleatorio);
               dos.writeInt(numAleatorio);
            }                       
            dos.close();
            fos.close();               
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");        
        } catch (IOException ex) {
            System.out.println("Error de escritura");
        }
        leerFichero();
        tcd.close();
    }
    static public void leerFichero() {
        System.out.println("\nFichero de números aleatorios");
        try {
            FileInputStream fis = new FileInputStream("archivoEjercicio1.dat");
            DataInputStream dis = new DataInputStream(fis);
            
            while (fis.available() > 0) {
                System.out.println(dis.readInt());                
            }       
        dis.close();
        fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");  
        } catch (IOException ex) {
            System.out.println("Error de lectura");
        }
        System.out.println("--FIN DE FICHERO--");       
    }
}
