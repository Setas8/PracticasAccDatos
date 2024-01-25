package pqt_actividadesTexto;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio10v2 {

    static File f = new File("texto10v2.txt");
    static File fEncriptado = new File("encriptadoV2.txt");
    static Scanner tcd = new Scanner(System.in);
    
    public static void main(String[] args) {
        /*
        Escribir un programa con la opción de encriptar el fichero de texto,
        "texto.txt". La encriptación consiste en que dado un fichero de texto
        de entrada genere otro fichero de salida donde el texto estará 
        encriptado. Esta encriptación consistirá en reemplazar cada carácter
        por el tercero siguiente (ej. a -> d). Si el carácter es la z entonces 
        se sustituirá por la c. Mostrar el fichero resultante.        
        */           
        menu();
        tcd.close();
    }
    public static void menu() {
               
        int opcion;
        boolean continua;
        do {
            try {
                continua = false;
                System.out.println("\nElije una opción:");
                System.out.println("1.Crear fichero"
                                 + "\n2.Escribir fichero"
                                 + "\n3.Añadir al fichero texto"
                                 + "\n4.Mostrar fichero texto"
                                 + "\n5.Encriptar fichero"
                                 + "\n6.Mostrar fichero encriptado"
                                 + "\n7.Borrar ficheros"
                                 + "\n0.Salir");
                opcion = tcd.nextInt(); tcd.nextLine();
                switch (opcion) {
                    case 0:
                        System.out.println("\nGracias!");
                        break;
                    case 1:
                        System.out.println("\nCreando fichero....");                          
                        crearFichero();
                        menu();
                        break;
                    case 2:
                        System.out.println("\nEscribe un texto:");
                        escribirFichero();
                        menu();
                        break;
                    case 3:
                        System.out.println("\nAñade un texto al archivo:");
                        sobrescribeFichero();
                        menu();
                        break;
                    case 4:
                        System.out.println("\nFichero texto:");
                        leerFichero(f);
                        menu();
                        break;
                    case 5:
                        System.out.println("\nEncriptando fichero....");
                        encriptarFichero();
                        menu();
                        break;
                    case 6:
                        System.out.println("\nFichero encriptado:");
                        leerFichero(fEncriptado);
                        menu();
                        break;
                    case 7:
                        System.out.println("\nBorrando ficheros....");
                        f.delete();
                        fEncriptado.delete();
                        menu();
                        break;
                    default:      
                        System.out.println("\nOpción incorrecta");
                        menu();                                          
                }
            } catch (InputMismatchException  ime) {           
                System.out.println("\nNo has introducido un entero");
                tcd.next();
                continua = true;
            } 
        } while(continua);  
    }
    public static void crearFichero() {
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error al crear el fichero");
        }
    }
    public static void escribirFichero() {       
        try {           
            FileWriter fw = new FileWriter(f);            
            System.out.print("Introduce un texto: ");
            String texto = tcd.nextLine().toLowerCase();
            fw.write(texto);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir en el fichero");
        }
    }
    public static void sobrescribeFichero() {       
        try {           
            if (f.exists()) {
                FileWriter fw = new FileWriter(f, true);            
                System.out.print("Introduce un texto: ");
                String texto = tcd.nextLine().toLowerCase();
                fw.write(texto);
                fw.close();
            }
            else
                System.out.println("Debes crear el fichero primero");           
        } catch (IOException ex) {
            System.out.println("Error al escribir en el fichero");
        }
    }
    public static void encriptarFichero() {
        try {       
            fEncriptado.createNewFile();
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
            while ((x = fr.read()) != -1) {
                System.out.print((char)x);                                 
            }
            fr.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
        }
        System.out.println();
    }
}