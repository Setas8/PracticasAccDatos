package pqt_masActividades;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio2 {
    
        static ArrayList<Coche> listaCoches = new ArrayList<>();    
    
    public static void main(String[] args) {
        /*
        Crea una aplicación que almacene los datos básicos de varios vehículos:
        matricula (String), marca (String), tamaño de deposito (double) y modelo
        (String), en un fichero binario. Los datos se pedirán por teclado.
        Una vez creado el fichero mostrar por pantalla todos los datos de 
        cada coche.
        */
        crearCoches();
        introducirCochesFichero();  
        leerDatosCoches();
    }
    static public void crearCoches() {
        Scanner tcd = new Scanner(System.in);
        System.out.println("INTRODUZCA COCHES EN EL SISTEMA");
        boolean salir = false;
        
        do {
            System.out.print("Matrícula: ");
            String matricula    = tcd.nextLine();
            System.out.print("Marca: ");
            String marca = tcd.nextLine();
            System.out.print("Modelo: ");
            String modelo = tcd.nextLine();
            System.out.print("Tamaño del depósito: ");
            double tamanioDeposito = tcd.nextDouble();tcd.nextLine();
            System.out.println("SEGUIR INTRODUCIENDO COCHES(S/N): ");
            salir = tcd.nextLine().equalsIgnoreCase("s") ? true: false;
            System.out.println();
            
            listaCoches.add(new Coche(matricula,marca,tamanioDeposito,modelo));                  
        } while (salir);
        tcd.close();
    }
    static public void introducirCochesFichero() {
        File f = new File("archivoEjercicio2.dat");
        
        try {
            FileOutputStream   fos = new FileOutputStream(f);           
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            listaCoches.forEach((c) -> {
                try {
                    oos.writeObject(c);
                    
                } catch (IOException ex) {
                    System.out.println("Error al escribir la lista");
                }
            });                                 
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en escritura");
        }                
    }
    static public void leerDatosCoches() {
        File f = new File("archivoEjercicio2.dat");
        
        System.out.println(String.format("%-20.20s", "MATRÍCULA") + 
                           String.format("%-20.20s", "MARCA") + 
                           String.format("%-20.20s", "MODELO") + 
                           String.format("%-20.20s", "TAMAÑO DEL DEPÓSITO(L)"));
        try {
            FileInputStream   fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                while (true) {
                    Coche c = (Coche)ois.readObject();                   
                    System.out.println(c);
                }               
            } catch(EOFException e){ 
                
            } 
            ois.close();
            fis.close();                       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException | ClassCastException  ex) {
            System.out.println(ex);
        }   
    }
}
