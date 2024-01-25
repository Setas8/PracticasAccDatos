package pqt_actividadesTexto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio9 {

    static File fNumeros = new File("numeros.txt");
    static File fEstadistica = new File("estadística.txt");
        
    public static void main(String[] args) {
        /*
        Hacer un programa que lea los valores del fichero de texto “numeros.txt”
        (0 al 9) y cree el fNumerosichero “estadistica.txt”,
        donde se guarde las frecuencias, la moda y la media.

        Ej:    numeros.txt                 estadistica.txt

               4 5 2 1 6 2 1 1 3 5 5 9      Numero 0 – 0 veces
                                            Numero 1 – 3 veces
                                            …………………………
                                            Numero 9 – 1 vez
                                            Moda: XXXXXX             
                                            Media:XXXXXX 
        */
        //Pedir números y concatenarlos en String
        Scanner tcd = new Scanner(System.in);
        System.out.print("Introduce números del 0 al 9 separados por espacios: ");
        String numeros = tcd.nextLine().trim();
        
        
        //Crear fichero de números
        try {
            fNumeros.createNewFile();
            FileWriter fw = new FileWriter(fNumeros);          
            fw.write(numeros);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir en el fichero");
        }
        
        //Leer archivo de números
        System.out.println("Archivo de números:");
        leerFichero(fNumeros);
        
        try {
            fEstadistica.createNewFile();
            FileReader fr = new FileReader(fNumeros);   
            FileWriter fw = new FileWriter(fEstadistica);
            int x = 0;            
            char[] listaNumeros = {'0','1','2','3','4','5','6','7','8','9'};
            int[] listaNumVecesRepetidos = new int[10];
                       
            while ((x = fr.read()) != -1) {
                for (int i = 0; i < listaNumeros.length; i++) {                             
                    if ((char)x == listaNumeros[i]) {
                        listaNumVecesRepetidos[i]++;
                    }                                                    
                }                                                                                  
            }
            //Escribir en fichero cuántas veces se repiten los números
            for (int i = 0; i < listaNumeros.length; i++) { 
                fw.write("Numero "+ i + " – " + listaNumVecesRepetidos[i] +
                         " veces\n");               
            } 
            //Escribir la media de veces que un número se repite
            int suma = 0;
            for (int i = 0; i < listaNumVecesRepetidos.length; i++) {
                suma += listaNumVecesRepetidos[i];
            }
            double media = (double)suma/listaNumVecesRepetidos.length;
            fw.write("\nMedia veces que se repite cualquier número: " + media);
                      
            //Escribir el número que más veces se repita
            ArrayList<Integer> listaNumMasRepetido = new ArrayList<>();
            //Saco el número que más se repite
            int numMasRepetido = Integer.MIN_VALUE;
            for (int i = 0; i < listaNumVecesRepetidos.length; i++) {
                for (int j = 1; j < listaNumVecesRepetidos.length - 1; j++) {
                    if (numMasRepetido <= listaNumVecesRepetidos[j]) {
                        numMasRepetido = listaNumVecesRepetidos[j];
                    }                  
                }              
            }
            //Añadir los que más se repiten por si hay varios
            for (int i = 0; i < listaNumVecesRepetidos.length; i++) {
                if (numMasRepetido == listaNumVecesRepetidos[i])
                    listaNumMasRepetido.add(i);
            }
            
            fw.write("\nModa: " + listaNumMasRepetido.toString());
            
            fr.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
        }
        
        System.out.println("\n- - - -");
        System.out.println("Archivo de estadística:");
        leerFichero(fEstadistica);
        System.out.println();
        
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
