package pqt_aleatorios;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio6 {

    public static void main(String[] args) {
        /*
        Hacer un programa, que actualice un registro del fichero creado en el
        ejercicio 5 . El nº de clase se pedirá por teclado, así como los datos
        a modificar. Se debe acceder al registro directamente, sin pasar por
        todos los anteriores.
        */
        
        Scanner tcd = new Scanner(System.in);
        
        File f = new File("alumnos.dat");
        File fB = pasarFicheroSerializableBinario(f);
        
        try {
            leerFicheroSerializableConRandom(f);
            RandomAccessFile raf = new RandomAccessFile(fB,"rw");
            leerFicheroBinario(fB);
                                 
            
            System.out.println("Dime el número de registro a buscar: ");
            int numRegistro = tcd.nextInt();tcd.nextLine();
                               
            
            int tamanioRegistro = 4+7+4;
            long puntero = (numRegistro-1)* tamanioRegistro;
            if ((numRegistro*tamanioRegistro) >= raf.length()) {
                System.out.println("El fichero no tiene tantos registros");
            }
            else {
                System.out.println("Puntero" + puntero);
                //saltar el nº de clase
                puntero +=4; 
                raf.seek(puntero);
                System.out.println("Puntero" + puntero);
                System.out.println("Nuevo nombre: ");
                String nombre = tcd.nextLine();
                raf.writeUTF(stringCaracteres(nombre));
                System.out.println("Nueva nota: ");
                int nota = tcd.nextInt();tcd.nextLine();
                raf.writeInt(nota);
                
            }          
            leerFicheroBinario(fB);                     
            raf.close();
            
            //leerFicheroSerializableConRandom(f);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    private static void leerFicheroSerializableConRandom(File f) {
        
        System.out.println("-----FICHERO SERIALIZABLE--------");
        try {
            RandomAccessFile raf = new RandomAccessFile(f,"rw");                                           
            byte[] array = new byte[(int)raf.length()];
            raf.readFully(array);
            ByteArrayInputStream leer = new ByteArrayInputStream(array);
            ObjectInputStream entrada = new ObjectInputStream(leer);
            Alumno a;
            
            try {  
                while (true) {                                       
                    a=(Alumno) entrada.readObject();
                    System.out.println(a.getNumClase() + " " + 
                                       a.getNombre() + " " + 
                                       a.getNota());         
                }
            } catch (EOFException ex) {
            }
                      
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }      
    }
    private static File pasarFicheroSerializableBinario(File f) {
        
        File fB = new File("alumnos.bin");
        
        try {
            FileInputStream   fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            FileOutputStream fos = new FileOutputStream(fB);
            DataOutputStream dos = new DataOutputStream(fos);
            try{
                Alumno a;
                while(true) {
                    a =(Alumno)ois.readObject(); 
                    dos.writeInt(a.getNumClase());
                    dos.writeUTF(a.getNombre());
                    dos.writeInt(a.getNota());                                                    
                }  
             } catch (EOFException e) {
                
             }
             ois.close();
             fis.close();
        } catch(IOException | ClassNotFoundException e){
          System.out.println(e);
        }                  
        return fB;
    }
    private static void leerFicheroBinario(File fB) {
        
        System.out.println("-----FICHERO BINARIO--------");
        try {           
            FileInputStream  fis = new FileInputStream (fB);
            DataInputStream  dis = new DataInputStream(fis);
            try {
                while (dis.available()> 0) {
                System.out.println(dis.readInt() + dis.readUTF() + dis.readInt());   
                }
            }  catch (EOFException ex) {
                System.out.println("FINAL DE FICHERO " + ex.getMessage());
            }
            dis.close();
            fis.close();               
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        }
        
    }
    //Dar a los Strings (nombre) una longitud fija
    private static String stringCaracteres(String palabra) {
        
        int tamanioCampo = 5;
        String str = "";
        int tamanioPalabra = palabra.length();
        
        if (tamanioPalabra <= tamanioCampo) {
            for (int i = tamanioPalabra;i < tamanioCampo; i++) {
                palabra += " ";
            }
            str = palabra;
        }
        else if (tamanioPalabra >= tamanioCampo) {
            str = palabra.substring(0, tamanioCampo);
        }
        else
            str = palabra;
                
        return str;
    }
    private static void pasarFicheroBinarioSerializable(File fB) {
        
    }
}
