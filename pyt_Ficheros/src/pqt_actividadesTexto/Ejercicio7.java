package pqt_actividadesTexto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio7 {

    public static void main(String[] args) {
        /*
        Hacer un programa, que modifique el fichero "texto.txt", insertando 
        un * delante de cada vocal. Mostrar el fichero "texto.txt" resultante.

        Ej:           texto.txt                           texto.txt

               Voy a hacer una prueba          V*oy *a h*ac*er *un*a pr*u*eb*a
        */        
        
        File f = new File("archivoejercicio7.txt");
        
        try {
            f.createNewFile();
            FileWriter     fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            
            if (f.canWrite()) {
                bw.write("Voy a hacer una prueba");
                bw.newLine();
                bw.write("Voy a hacer una prueba2");
            }           
            
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        }
        
        //Leer el primer archivo
        System.out.println("Primer archivo:");
        try {
            FileReader fr = new FileReader(f);
            if (f.exists()) {
                int x;
                while ((x = fr.read()) != -1) {
                    System.out.print((char)x);               
                }
            }
            else
                System.out.println("No se encontró el fichero");            
            fr.close();           
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");   
        } 
        
        //Sobrescribir el archivo con asteriscos
        //Pasar el primer fichero a un auxiliar.
        //Borrar el primer fichero(datos no correctos)
        //Renombrar el segundo fichero(datos correctos) con el del primero
        char[] vocales = {'a','e','i','o','u','A','E','I','O','U'};
        try {            
            File fAux = new File("archivoejercicio7.2.txt");
            fAux.createNewFile();
            FileReader fr = new FileReader(f);
            FileWriter fw = new FileWriter(fAux);
            
            int x;                      
            while ((x = fr.read()) != -1) {              
                for (char c : vocales) {
                    if ((char)x == c) {
                        fw.write("*");                       
                    }                  
                }       
                fw.write(x);
            }        
            fw.close();
            fr.close();
            f.delete();   
            fAux.renameTo(f);                      
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        }
        
        //Leer el segundo archivo
        System.out.println("\nSegundo archivo:");
        try {           
            FileReader fr = new FileReader(f);
            if (f.exists()) {
                int x;
                while ((x = fr.read()) != -1) {
                    System.out.print((char)x);               
                }
            }
            else
                System.out.println("No se encontró el fichero");            
            fr.close();           
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");   
        } 
        System.out.println();
    }
}
