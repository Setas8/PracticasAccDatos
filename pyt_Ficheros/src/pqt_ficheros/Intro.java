package pqt_ficheros;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Cuadrado
 */
public class Intro {

    public static void main(String[] args) {
        
        try {
            Scanner tcd = new Scanner(System.in);
            System.out.println("Introduce el nombre del fichero. Cuidado lo voy a borrar");
            String fichero = tcd.nextLine();
            
            File f = new File(fichero);
            
            f.createNewFile();
            
            
            if (f.exists()) {
                System.out.println("La longitud del fichero es de " + f.length());
                if (f.canRead())
                    System.out.println("Se puede leer");
                if (f.canRead())
                    System.out.println("Se puede escribir");
            }
            else {
                System.out.println("El fichero no existe");
            }
            System.out.println(f.getAbsolutePath());
            System.out.println(f.getPath());
            System.out.println("");
            f.delete();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        /*
        ruta /home/developer
        
        
        */
        
    }
    
}
