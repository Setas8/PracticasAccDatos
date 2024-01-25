package pqt_aleatorios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Diego Cuadrado
 */
public class Aleatorios {

    public static void main(String[] args) {
        // Hay que tener clara la estructura del fichero
        //Saltar bytes, saber en qué posición está el puntero
        //Primer registro. Puntero en la posición 0
        /*
        char 1 byte
        short 2 bytes
        int 4 bytes
        float 4 bytes
        double 8 bytes
        long 4 bytes
        String decirle cuántos bytes  (uno por caracter +2)
        */
        /*
        r   lectura
        rw  lectura y escritura
        */
        //métodos binarios
        /*
        seek()   ---- nos coloca el puntero en la posición que le indicamos
        length() ---- longitud del fichero
        getFilePointer() ---- nos da dónde está el puntero
        */
        
        try {            
            File f = new File("pruebaAleatorio.bin");           
            RandomAccessFile raf = new RandomAccessFile(f,"rw");
            
            System.out.println("Tamaño del fichero " + raf.length());
            System.out.println("Puntero al principio " + raf.getFilePointer());
            raf.writeInt(1);
            System.out.println("Puntero después del primer valor (entero) \t" + raf.getFilePointer());
            raf.writeDouble(3.5);
            System.out.println("Puntero después del segundo valor (double) \t" + raf.getFilePointer());
            raf.writeChar('C');
            System.out.println("Puntero después del tercer valor (char) \t" + raf.getFilePointer());
            raf.writeUTF("Final");
            System.out.println("Puntero después del último valor (String) " + raf.getFilePointer());
            System.out.println("Tamaño del fichero " + raf.length());
            System.out.println();
        
        
            raf.seek(0);
            
            /*try {
            while (true) {*/
                System.out.println("Puntero posición " + raf.getFilePointer()+ "\t--> Valor 1 " + raf.readInt());
                System.out.println("Posición puntero --->" + raf.getFilePointer());
                System.out.println("Puntero posición " + raf.getFilePointer()+ "\t--> Valor 2 " + raf.readDouble());
                System.out.println("Posición puntero --->" + raf.getFilePointer());
                System.out.println("Puntero posición " + raf.getFilePointer()+ "\t--> Valor 3 " + raf.readChar());
                System.out.println("Posición puntero --->" + raf.getFilePointer());
                System.out.println("Puntero " + raf.getFilePointer()+ "--> Valor 4 " + raf.readUTF());
                System.out.println("Posición puntero " + raf.getFilePointer());


            /*    }
            } catch (EOFException e) {
            System.out.println(e);
            }*/

            raf.close();
        
        } catch (IOException e) {
            System.out.println(e);
        }        
    }   
}
