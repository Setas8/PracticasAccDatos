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
public class Ejercicio2 {

    public static void main(String[] args) {
        try {
            /*
            Hacer un programa que acceda a un determinado registro del fichero
            generado en el ejercicio 1 y lo escriba en pantalla. El número del
            registro se debe pedir por teclado.
            */
            File f = new File("ejercicio1.bin");            
            RandomAccessFile raf = new RandomAccessFile(f,"rw");
            Scanner tcd = new Scanner(System.in);
                                                                 
            final int BYTE   = 4;
            final int DOUBLE = 8;    
            final int BYTES_FIJOS = BYTE+DOUBLE;
                       
            leerFicheroEntero(raf);          
            System.out.println("Número de valores a buscar: ");
            int valorABuscar = tcd.nextInt();tcd.nextLine();
           
            if (valorABuscar <= 0) {
                System.out.println("No hay registros que buscar");
            }
            else {                  
                //Situo el puntero en la posición deseada
                int registro   = valorABuscar-1;               
                long puntero   = 0;   
                int incremento = 0;
                raf.seek(puntero);
                for (int i = 1; i < valorABuscar; i++) {  
                    puntero += BYTES_FIJOS+incremento;
                    incremento += DOUBLE;                   
                }
                if (puntero >= raf.length()) 
                    System.out.println("El fichero no tiene tantos registros");
                //Saco el registro y sus valores
                else {
                    raf.seek(puntero);
                    for (int i = registro; i < valorABuscar; i++) {               
                        System.out.println("Registro: " + raf.readInt());
                            for (int j = 1; j <= valorABuscar; j++) {
                                System.out.println(raf.readDouble() + " ");
                            }
                    } 
                }
                System.out.println("-------------------");
            }          
            raf.close();            
            tcd.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }//Fin del main 
    public static void leerFicheroEntero(RandomAccessFile raf) {
        System.out.println("-----FICHERO ENTERO-----");
        try {
            for (int i = 1; i <= raf.length(); i++) {               
                System.out.println("Registro: " + raf.readInt());
                for (int j = 1; j <= i; j++) {
                    System.out.println(raf.readDouble() + " ");
                }
            }                     
            
        } catch (IOException ex) {
            System.out.println("Fin de fichero");
        }
           System.out.println("-----FIN DE FICHERO-----");          
    }
}
