package pqt_masActividades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        /*
        Realizar un programa que lea de teclado los siguientes datos y los
        almacene en un fichero binario llamado "datosbeca.bin":

        Nombre y apellido de un supuesto becario.
        Sexo (H-M)
        Edad (20-60)
        Número de suspensos del curso anterior (0-4)
        Residencia familiar (SI o NO)
        Ingresos anuales de la familia.
        */       
        Scanner tcd = new Scanner(System.in);
        
        try {
            FileOutputStream fos = new FileOutputStream(new File("datosbeca.bin"),true);
            DataOutputStream dos = new DataOutputStream(fos);
            
            System.out.print("Nombre: ");
            dos.writeUTF(tcd.nextLine());
            System.out.print("Sexo(H-M): ");
            dos.writeChar(tcd.next().charAt(0));
            System.out.print("Edad(20-60): ");
            dos.writeInt(tcd.nextInt());
            System.out.print("Número suspensos del curso anterior(0-4): ");
            dos.writeInt(tcd.nextInt());tcd.nextLine();
            System.out.print("Residencia familiar(SI o NO): ");
            dos.writeUTF(tcd.nextLine());
            System.out.print("Ingresos anuales de la familia: ");
            dos.writeFloat(tcd.nextFloat());
        
            dos.close();
            fos.close();
        
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en la escritura");
        }       
        leerFichero();
        tcd.close();
    }
    static public void leerFichero() {
        System.out.println("FICHERO ALUMNOS");
        try {           
            FileInputStream  fis = new FileInputStream (new File("datosbeca.bin"));
            DataInputStream  dis = new DataInputStream(fis);
            try {
                while (dis.available()> 0) {
                    System.out.println(
                            "Nombre: " + dis.readUTF()   +
                            "\nSexo: " + dis.readChar()  +
                            "\nEdad: " + dis.readInt()   +
                            "\nSuspensos año anterior: " + dis.readInt()  +
                            "\nResidencia familiar: "    + dis.readUTF()  +
                            "\nIngresos familiares: "    + dis.readFloat() + "€");   
                }
            }  catch (EOFException ex) {
                System.out.println("FINAL DE FICHERO " + ex.getMessage());
            }
            dis.close();
            fis.close();                      
        } catch (IOException ex) {
            System.out.println("Error en la lectura");
        }
    }
}
