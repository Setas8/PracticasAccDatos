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
public class Ejercicio10v1 {

    static File f = new File("texto10.txt");
    static File fEncriptado = new File("encriptado.txt");
    
    
    public static void main(String[] args) {
        /*
        Escribir un programa con la opción de encriptar el fichero de texto,
        "texto.txt". La encriptación consiste en que dado un fichero de texto
        de entrada genere otro fichero de salida donde el texto estará 
        encriptado. Esta encriptación consistirá en reemplazar cada carácter
        por el tercero siguiente (ej. a -> d). Si el carácter es la z entonces 
        se sustituirá por la c. Mostrar el fichero resultante.        
        */
        crearFichero(f);
        crearFichero(fEncriptado);
        escribirFichero(f);
        System.out.println("\nFichero texto:");
        leerFichero(f);
        encriptarFichero(f);
        System.out.println("\nFichero encriptado:");
        leerFichero(fEncriptado);
        System.out.println();
    }
    public static void crearFichero(File f) {
        try {
            f.createNewFile();
            
        } catch (IOException ex) {
            System.out.println("Error al crear el fichero");
        }
    }
    public static void escribirFichero(File f) {
        Scanner tcd = new Scanner(System.in);
        try {           
            FileWriter fw = new FileWriter(f);            
            System.out.print("Introduce un texto: ");
            String texto = tcd.nextLine();
            fw.write(texto);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir en el fichero");
        }
    }
    public static void encriptarFichero(File f) {
        
        try {            
            FileReader fr = new FileReader(f);   
            FileWriter fw = new FileWriter(fEncriptado);
            
            int x = 0;        
            int caracterEncriptado = 3;
            while ((x = fr.read()) != -1) {                
                if (x >= 'A' && x <= 'Z') {
                    if (x + caracterEncriptado > 'Z') 
                        x = 64 + (x + caracterEncriptado - 'Z');
                    else 
                        x = x + caracterEncriptado;                   
                } else if (x >= 'a' && x <= 'z'){ // a=97 ... z=122        
                    if (x + caracterEncriptado > 'z') 
                        x = 96 + (x + caracterEncriptado - 'z');
                    else 
                        x = x + caracterEncriptado;                    
                }    
                fw.write(x);
            }
            fr.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
        }
    }
    public static void leerFichero(File f) {
        try {           
            FileReader fr = new FileReader(f);   
            int x = 0;
            while ((x = fr.read()) != -1)
                System.out.print((char)x);                                 
            fr.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
        }
    }
}
