package pqt_actividadesTexto;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        /*
        Escribir un programa Java que le pida al usuario el nombre de un fichero
        y el nombre de un directorio.
        Compruebe si el fichero existe en ese directorio. Si no existe devuelve
        un mensaje por System.err y termina.
        Si existe:

        Comprueba si se puede leer y si se puede escribir en él.
        Visualiza la longitud del fichero.
        Borra el fichero
        */
        Scanner tcd = new Scanner(System.in);
        
        System.out.println("Dime el nombre de un directorio: ");
        String directorio = tcd.nextLine();
        System.out.println("Dime el nombre de un fichero: ");
        String fichero = tcd.nextLine();
        
        File dir = new File(directorio.toUpperCase());
        dir.mkdir();
        File f = new File(dir,fichero + ".txt");
        
        try {
            if (!f.createNewFile())
                System.err.println("El fichero " + fichero + " no existe");
            else{
                
                if (f.isFile())
                    System.out.println("Se puede leer " + f.canRead());
                
                if (f.isFile())
                    System.out.println("Se puede escribir " + f.canWrite());
                
                System.out.println("Longitud del fichero= " + f.length());
                          
                //f.delete();
            }
        } catch (IOException ex) {
            System.out.println("Fichero no creado");
        }
        if (dir.isDirectory()) {
            System.out.println("El directorio " + dir.getName() +
                             " existe y su longitud es de: " + dir.length());
            if (dir.isDirectory()) {
                File[] lista = dir.listFiles();
                for (File d : lista)
                    System.out.println(d.getName() + " " + d.getAbsolutePath() +
                        " " + d.isDirectory());
            }   
        }
        else {
            System.err.println("El directorio no existe");                     
        }
        tcd.close();
    }   
}
