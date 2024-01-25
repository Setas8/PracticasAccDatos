package pqt_actividadesTexto;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        /*
        Escribir un programa Java que le pida al usuario el nombre
        de un fichero y el nombre de un directorio.
        */                   
        Scanner tcd = new Scanner(System.in);
        
        System.out.println("Dime el nombre de un directorio: ");
        String directorio = tcd.nextLine();
        System.out.println("Dime el nombre de un fichero: ");
        String fichero = tcd.nextLine();
        
        File dir = new File(directorio.toUpperCase());
        File f = new File(fichero + ".txt");
                          
        /*
        Con el fichero haz lo siguiente:
        Compruebe si el fichero existe. 
        Si no existe devuelve un mensaje de error por System.err y
        si existe, visualiza la longitud del fichero.
        Por último, pide al usuario un nombre nuevo y renómbralo.
        */
        try {                   
                         
            if (f.createNewFile() ) {
                System.out.println("El fichero " + f.getName() +
                                  " existe y su longitud es de: " + f.length());
                System.out.println("Renombra el fichero: ");
                fichero = tcd.nextLine();
                f.renameTo(new File(fichero + ".txt"));
                System.out.println("Fichero " + f.getName() + " renombrado" + 
                        f.getAbsolutePath());               
            } 
            else
                System.err.println("El fichero no existe");
            
        } catch (IOException ex) {
            System.out.println("Fichero no creado");
        }
        /*
        Con el directorio haz lo siguiente:
        Comprueba si existe. Si no existe, saca un mensaje de error por la 
        salida de error System.err. 
        En el caso de que si exista, lista su contenido
        (comprobar en la API cómo averiguar si es un directorio)
        */
        if (!dir.mkdir())
            System.err.println("El directorio no existe");
        else {
            System.out.println("El directorio " + dir.getName() +
                             " existe y su longitud es de: " + dir.length());
            if (dir.isDirectory()) {
                File[] lista = dir.listFiles();
                for (File d : lista)
                    System.out.println(d.getName() + " " + d.getAbsolutePath() +
                        " " + d.isDirectory());
            }           
        }
        tcd.close();
    }  
}
