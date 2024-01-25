package pqt_masActividades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class Ejercicio9 {

    static File f    = new File("nominas.dat");
    static File faux = new File("auxiliarNominas.dat");   
    
    public static void main(String[] args) {
        /*
        En una empresa se tiene un fichero binario “nominas.dat” que almacena
        en cada registro el nombre de cada empleado (String) con el número de
        días de baja (int) y su nómina (double).

        Hacer un programa que actualice las nóminas de forma que:

        A  los que tengan 0 días de baja se le hace un aumento del 5%,
        A los que tengan entre 1 y 3 días de baja se quedan con la misma nómina,
        A los que tengan entre 4 y 10 se les baja un 10%
        A los que tengan más de 10 días de baja se les elimina del fichero.
        Finalmente, se escribirá en pantalla el fichero “nominas.dat” ya 
        actualizado, indicando a cuantos empleados se ha dado de baja.
        */
        crearFichero();
        actualizarFichero();        
    }
    static public void crearFichero() {
        Scanner tcd = new Scanner(System.in);
        System.out.println("¿Cuántos empleados quieres en el fichero?");
        int numEmp = tcd.nextInt();tcd.nextLine();
        try {
            FileOutputStream fos = new FileOutputStream(f);
            DataOutputStream dos = new DataOutputStream(fos);
            
            for (int i = 0; i < numEmp; i++) {
                System.out.print("\nNombre empleado " + (i+1) + ": ");
                dos.writeUTF(tcd.nextLine());
                System.out.print("Días de baja empleado "  + (i+1) + ": ");
                dos.writeInt(tcd.nextInt());tcd.nextLine();
                System.out.print("Nómina del empleado " + (i+1) + ": ");
                dos.writeDouble(tcd.nextDouble());tcd.nextLine();
            }
            dos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");  
        } catch (IOException ex) {
            System.out.println("Error en la escritura");
        }
        tcd.close();
    }
    static public void leerFichero() {
        try {
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            
            System.out.println("\nNOMBRE\tDÍAS_BAJA\tNÓMINA_MES");
            while (fis.available() > 0) {
                System.out.println(dis.readUTF() + "\t" + dis.readInt() + "\t\t" +
                                dis.readDouble() + "€");
            }       
            dis.close();
            fis.close();
            } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en la lectura");  
        }
    }
    static public void actualizarFichero() {
        
        leerFichero();        
        String cadena = "";//Empleados eliminados
        int cont = 0; //Contador de empleados eliminados
        
        try {
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            
            FileOutputStream fos = new FileOutputStream(faux);
            DataOutputStream dos = new DataOutputStream(fos);
            System.out.println("\nNOMBRE\tDÍAS_BAJA\tNÓMINA_MES");
            while (fis.available() > 0) {
                String nom    = dis.readUTF();
                int diasBaja  = dis.readInt();
                double nomina = dis.readDouble();
                
                double nominaActualizada = 0;
                            
                switch (diasBaja) {
                    case 0:
                        //aumento del 5%
                        dos.writeUTF(nom);
                        dos.writeInt(diasBaja);
                        nominaActualizada = nomina + (nomina*0.05);
                        dos.writeDouble(nominaActualizada);
                        System.out.println(nom + "\t" + diasBaja + "\t\t" +
                                nominaActualizada + "€");               
                        break;
                    case 1:
                    case 2:
                    case 3:
                        //misma nómina
                        dos.writeUTF(nom);
                        dos.writeInt(diasBaja);
                        dos.writeDouble(nomina);
                        System.out.println(nom + "\t" + diasBaja + "\t\t" +
                                nomina + "€");               
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        //reducció del 10%
                        dos.writeUTF(nom);
                        dos.writeInt(diasBaja);
                        nominaActualizada = nomina - (nomina*0.1);
                        dos.writeDouble(nominaActualizada);
                        System.out.println(nom + "\t" + diasBaja + "\t\t" +
                                nominaActualizada + "€");               
                        break;
                    default:
                        cadena += nom + " ";
                        cont +=1;                       
                }               
            }
            System.out.println("\nSe eliminaron " + cont + " empleado\\s:\n" +
                    cadena);
            dos.close();
            fos.close();
            dis.close();
            fis.close();
            f.delete();
            faux.renameTo(f);           
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en la lectura");  
        }               
    }
}
