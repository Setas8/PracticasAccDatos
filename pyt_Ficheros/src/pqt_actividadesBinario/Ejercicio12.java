package pqt_actividadesBinario;

import java.io.File;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio12 {

    
    public static void main(String[] args) {
        /*
        Escribir un programa Java que permita crear secuencialmente un fichero
        binario que contenga la información de varias personas. Para cada
        persona se almacenará: nombre, apellido1, apellido2 y año de nacimiento.
        El nombre y los apellidos tendrán 20 caracteres rellenando con espacios
        al final para completar la longitud.
        */
        pedirDatosPersona();     
    }
    public static void pedirDatosPersona() {
        
        Scanner tcd = new Scanner(System.in);
        String nombre, apellido1, apellido2, fechaNac = "";
        
        System.out.print("Nombre: ");
        nombre    = comprobarLongitudString(tcd.nextLine()); 
        //nombre = String.format("%-20.20s", tcd.nextLine());
        System.out.print("Primer apellido: ");
        apellido1 = comprobarLongitudString(tcd.nextLine());
        System.out.print("Segundo apellido: ");
        apellido2 = comprobarLongitudString(tcd.nextLine());
        boolean ok = true;
        do {
            try {
                System.out.print("Fecha nacimiento(aaaa-mm-dd): ");
                fechaNac = tcd.nextLine();
                stringToDate(fechaNac);
                ok = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha no válido");
                ok = false;
            }           
        } while (!ok);
        escribirFicheroBinario(nombre, apellido1, apellido2, fechaNac);
            
    }
    public static String comprobarLongitudString(String s) {
        if (s.length() < 20) 
            for (int i = s.length(); i < 20; i++) {
                s += " ";
            }             
        else 
            s = s.substring(0, 20);       
        return s;
    }
    public static LocalDate stringToDate(String f) throws Exception{                  
        return LocalDate.parse(f, DateTimeFormatter.ISO_DATE);        
    }
    public static void escribirFicheroBinario(String nom, String ape1, String ape2, String fecha) {
        File f = new File("archivoBiEjercici012.bin");
        try {
            f.createNewFile();
            FileOutputStream  fos = new FileOutputStream(f, true);
            DataOutputStream  dos = new DataOutputStream(fos);
            
            dos.writeUTF(nom);
            dos.writeUTF(ape1);
            dos.writeUTF(ape2);
            dos.writeUTF(fecha);
            
            dos.close();
            fos.close();           
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        }
    }  
}
