package pqt_aleatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        /*
        Hacer un programa que cree el fichero enteros.dat, que guardara 
        números enteros hasta que se pulse un 0.
        */       
        Scanner tcd = new Scanner(System.in);
        
        File f = new File("enteros.dat");
                       
        
        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            int num;
            do {
                System.out.println("Introduce un número entero hasta"
                                 + " introducir un 0: ");
                num = tcd.nextInt();tcd.nextLine();
                if (num != 0) 
                    raf.writeInt(num);               
            } while (num != 0);
            
            raf.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }                     
    }
}
