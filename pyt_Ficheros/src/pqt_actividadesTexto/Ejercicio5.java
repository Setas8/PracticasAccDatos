package pqt_actividadesTexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio5 {

    public static void main(String[] args) {
        /*
        Realizar un programa que muestre las N primeras líneas del fichero de
        texto "texto.txt". El valor de N, se pedirá por teclado y se tendrá en
        cuenta que puede ser mayor que el número de filas del fichero. 
        */
        Scanner tcd = new Scanner(System.in);
        try {
            File       f  = new File("archivoejercicio5.txt");
            FileWriter fw = new FileWriter(f);
            fw.write("Esto es una prueba\nEsto es una prueba\n"
                   + "Esto es una prueba\nEsto es una prueba");
            fw.close();
            
            System.out.print("Número de filas a mostrar: ");
            int filas = tcd.nextInt(); tcd.nextLine();
            
            FileReader     fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String texto = br.readLine();
            boolean fin  = false;
            
            for (int i = 0; i < filas && !fin; i++) {
                if (texto != null) {
                    System.out.println(texto);
                    texto = br.readLine();  
                }
                else {
                    System.out.println("Ya no hay más filas");
                    fin = true;
                } 
            }            
        }catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero");
        }          
        tcd.close();
    }   
}
