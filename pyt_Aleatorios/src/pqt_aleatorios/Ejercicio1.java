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
public class Ejercicio1 {
    
    public static void main(String[] args) {
        /*
        Hacer un programa que cree un fichero de acceso aleatorio una serie de
        registros formados por un número de orden (de tipo int) y una serie de
        valores doubles introducidos por teclado. El número de valores a
        introducir vendrá dado por el número de orden.              
        */
        Scanner tcd = new Scanner(System.in);
        
        System.out.println("Número de valores a introducir: ");
        int numValores = tcd.nextInt();tcd.nextLine();
        File f = new File("ejercicio1.bin");
        
        try {
            RandomAccessFile raf = new RandomAccessFile(f,"rw");
            double valor = 0;
            for (int i = 1; i <= numValores; i++) {
                System.out.println("Introduce " + i + " valor\\es");
                raf.writeInt(i);
                for (int j = 1; j <= i; j++) {                   
                    valor = tcd.nextDouble();tcd.nextLine();                    
                    raf.writeDouble(valor);
                }                              
            }                       
            raf.close();                      
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        tcd.close();
    }
}

