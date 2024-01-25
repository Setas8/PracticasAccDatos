package pqt_actividadesTexto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio6 {

    public static void main(String[] args) {
        /*
        Realizar un programa que cuente el número de líneas, caracteres y
        palabras que tiene un fichero que se pasará por teclado.
        Se debe comprobar que exista. 
        */
        
        File f = new File("archivoejercicio6.txt");
        
        //Crear y escribir el archivo
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            
            if (f.canWrite()) {
                fw.write("Esto es\nun texto\nen varias\nfilas");
            }           //12345678 901234567 8901234567 89012
            
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        }
        //Inicio las lineas y las palabras a 1 porque la última no la contabiliza
        int contLineas     = 1; 
        int contPalabras   = 1; 
        int contCaracteres = 0;
        try {
            //Leer el archivo y contabilizar caracteres,palabras y líneas
            FileReader fr = new FileReader(f);
            if (f.exists()) {
                int x;
                while ((x = fr.read()) != -1) {
                    if ((char)x  != '\n')
                        contCaracteres ++; //No contabilizo los saltos de línea
                    if ((char)x  == '\n')
                        contLineas++;
                    if (((char)x == ' ') || ((char)x == '\n')) {
                        contPalabras++;
                    }                    
                }
            }
            else
                System.out.println("No se encontró el fichero");            
            fr.close();           
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");   
        } 
        System.out.println("Caracteres del texto: " + contCaracteres);
        System.out.println("Palabras del texto:   " + contPalabras);
        System.out.println("Líneas del texto:     " + contLineas);          
    }
}
