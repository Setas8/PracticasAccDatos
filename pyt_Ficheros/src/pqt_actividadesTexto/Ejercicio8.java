package pqt_actividadesTexto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio8 {

    public static void main(String[] args) {
        /*
        Hacer un programa que pida al usuario una palabra por teclado y
        diga cuántas veces aparece esa palabra en el fichero "parrafo.txt". 
        */
        File f = new File("parrafo.txt");
        //Crear el fichero párrafo     
        try {           
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write("Esto es un texto de prueba para, ver si existe la palabra"
                   + " introducida y cuántas veces se repite esa palabra"
                   + " en el texto. palabras ... Palabras");
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir el fichero");
        }
        
        //Pedir usuario una palabra
        //Recorrer el texto y comprobar cuántas veces se repite
        //Sólo tengo en cuenta , . ; : y los espacios
        Scanner tcd = new Scanner(System.in);
        System.out.print("Escribe una palabra: ");
        String palabra = tcd.nextLine().trim();
        
        String palabraTexto = "";
        int contPalabra = 0;
        try {
            FileReader fr = new FileReader(f);
            
            int x;
            while ((x = fr.read()) != -1) {
                if ((char)x != ' ' && (char)x != '.' && (char)x != ',' &&
                    (char)x != ':' && (char)x != ';'){
                    palabraTexto += (char)x;                   
                }
                else {                   
                    if (palabraTexto.equals(palabra)) {
                        contPalabra++;   
                    }
                    palabraTexto = "";
                }
                if (!palabraTexto.equals("")) {
                    if (palabraTexto.equals(palabra)) {
                        contPalabra++;     
                    }
                }                                                                       
            }
            fr.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir el fichero");
        }           
        System.out.println("La palabra '" + palabra + "' se repite " +
                            contPalabra + " veces.");
        tcd.close();
    }
}
