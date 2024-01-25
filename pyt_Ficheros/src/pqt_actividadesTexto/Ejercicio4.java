package pqt_actividadesTexto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        /*
        Hacer un programa que lea el fichero de texto, "texto.txt", y genere
        otro llamado "invertido.txt", en el que se guardarán las frases del
        primero pero invertidas. Mostrar en pantalla el nuevo fichero.

        Ej:       texto.txt                     invertido.txt

                  Esto es un ejemplo            olpmeje nu se otsE
        */
                             
        File fTexto = new File("texto.txt");
        File fInvertido = new File("invertido.txt");
        //Escribir el primer archivo
        try {            
            FileWriter fw1 = new FileWriter(fTexto);  
            fw1.write("Esto es un ejemplo");  
            fw1.close();
            
        } catch (IOException ex) {
            System.out.println("Fichero no creado");
        }
        //Leer el primer archivo y escribir el segundo invertido
        try {
            String frase = "";
            FileReader fr = new FileReader(fTexto);
            int x;
            while ((x = fr.read()) != -1)
                frase += (char)x;
                //frase = (char)x + letras
            
            System.out.println(frase);
            FileWriter fw2 = new FileWriter(fInvertido);
            
            for (int i = frase.length()-1; i >= 0; i--) {
                fw2.write(frase.charAt(i));               
            }        
            fr.close();
            fw2.close();         
        } catch (IOException ex) {
            System.out.println("Fichero no encontrado");
        }     
    }   
}
