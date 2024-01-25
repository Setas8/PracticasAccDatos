package pqt_aleatorios;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        /*
        Hacer un programa para modificar un entero del fichero enteros.dat
        con acceso aleatorio. Para ello se pide por t eclado la posición que
        ocupa el entero a modificar dentro del fichero, después se lee y se
        muestra el valor , se pide el nuevo valor y se escribe el nuevo valor
        en la posición indicada, modificando de esta forma el valor antiguo por
        el nuevo. La posición deberá estar comprendida entre 1 y el número de 
        enteros que contiene el fichero.       
        */
        
        Scanner tcd = new Scanner(System.in);
        
        File f = new File("enteros.dat");
              
        
        System.out.println("Posición del número a pedir: ");
        int numAModificar = tcd.nextInt();tcd.nextLine();
        
        
        try {
           
            RandomAccessFile raf = new RandomAccessFile(f,"rw");
            leerFichero(raf);
            if ((numAModificar*4) >= raf.length()) {
                System.out.println("El fichero no tiene tantos registros");
            }
            else {
                int registroABuscar = numAModificar - 1;
                int posicionPuntero = registroABuscar*4;
                raf.seek(posicionPuntero);
                int valorAntiguo = raf.readInt();
                System.out.println("Contenido del registro " + numAModificar + ": "+ valorAntiguo);
                raf.seek(posicionPuntero);
                System.out.println("Nuevo valor: ");
                int nuevoValor = tcd.nextInt();tcd.nextLine();
                raf.writeInt(nuevoValor);
                raf.seek(posicionPuntero);
                System.out.println("Valor cambiado a " + raf.readInt());               
            }
            leerFichero(raf);
            raf.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    } 
    private static void leerFichero(RandomAccessFile raf) {
        System.out.println("\n- - - -FICHERO COMPLETO - - - ");
        try {
            
            try {
                raf.seek(0);
                while(true) {
                    System.out.println(raf.readInt());                 
                }
            } catch (EOFException e) {
                System.out.println("Fin del fichero");
            }                                      
        } catch (IOException ex) {
            System.out.println(ex);
        }                     
        System.out.println("- - - - - - - ");
    }
}
