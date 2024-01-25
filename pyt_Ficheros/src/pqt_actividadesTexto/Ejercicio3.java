package pqt_actividadesTexto;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        /*
        Escribir un programa Java que le pida al usuario el nombre de un
        fichero. Si existe, muestra su longitud. Si no existe, lo crea.
        */
        Scanner tcd = new Scanner(System.in);
        
        System.out.println("Dime el nombre de un fichero: ");
        String fichero = tcd.nextLine();
        
        File f = new File(fichero + ".txt");
        
        if (!f.exists())
            try {
                f.createNewFile();
        } catch (IOException ex) {
                System.out.println("Fichero no creado");
        }
        else {
            System.out.println(f.length());
        }
    }
}
