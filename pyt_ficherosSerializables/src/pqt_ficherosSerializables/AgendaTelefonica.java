package pqt_ficherosSerializables;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class AgendaTelefonica {
        
    public static void main(String[] args) {
        
        Scanner tcd = new Scanner(System.in);
        File fregistros = new File("registros.dat");
        crearFichero(fregistros);
        menu(tcd);
        
        tcd.close();
    }
    public static void crearFichero(File f) {
        try {
            if(!f.exists()){
                FileOutputStream   fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);  
                oos.close();
                fos.close();
            }           
        } catch (Exception e) {
        }       
    }
    public static void menu(Scanner tcd){
       
        File fregistros = new File("registros.dat");
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n1.Añadir contacto"
                         + "\n2.Buscar contacto"
                         + "\n3.Modificar número o email del contacto"
                         + "\n4.Eliminar contacto"
                         + "\n5.Salir\n");
            opcion = tcd.nextInt();tcd.nextLine();
               
            switch (opcion) {
                case 1:
                    //Añadir contacto                   
                    mostrarAgenda(fregistros);
                    System.out.println("\nContacto nuevo...");
                    addContacto(fregistros);
                    mostrarAgenda(fregistros);
                    break;
                case 2:
                    //Buscar contacto
                    System.out.println("Buscar contacto...");
                    System.out.println("Nombre del contacto a buscar: ");
                    String nombre = tcd.nextLine().trim();
                    System.out.println("Apellidos del contacto a buscar: ");
                    String apellidos = tcd.nextLine().trim();
                    buscarContacto(tcd, fregistros, nombre, apellidos);
                    break;
                case 3:
                    //Modificar numero o email
                    System.out.println("Modificar registro");
                    ///Métod para String y método para int
                    break;
                case 4:
                    //Eliminar contacto
                    System.out.println("Eliminar contacto...");
                    break;
                case 5:
                    //Mostrar datos eliminados
                    System.out.println("Datos borrados...");               
                    break;           
                default:
                    System.out.println("Opción incorrecta");
            }//switch
        }//While
        
    }//menu
    public static void addContacto(File f) {
        Scanner tcd = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = tcd.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = tcd.nextLine();
        System.out.print("Número de teléfono: ");
        int numTelefono = tcd.nextInt();tcd.nextLine();
        System.out.print("Email: ");
        String email = tcd.nextLine();
        
        try {
            
            FileOutputStream   fos =new FileOutputStream(f,true);           
            Output op    = new Output(fos);
            Registro reg = new Registro(nombre,apellidos,numTelefono,email);
            op.writeObject(reg);
            
            op.close();
            fos.close();
           
        } catch (IOException e) {
        }
       
    } 
    public static void mostrarAgenda(File f) {
        
        System.out.println("***************AGENDA***************");
        System.out.println("\nNOMBRE\t\tAPELLIDOS\tTELÉFONO\tEMAIL");
        try {
            FileInputStream   fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try{
                Registro reg;
                while(true) {
                    reg =(Registro)ois.readObject();               
                    System.out.println(reg);               
                }  
             } catch (EOFException e) {
                
             }
             ois.close();
             fis.close();
        } catch(IOException | ClassNotFoundException e){
          System.out.println(e);
        }       
        System.out.println("******************************************");
    }
    ///No busca... depurar
    public static void buscarContacto(Scanner tcd, File f, String nom, String ape) {
        
        try {
            FileInputStream   fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try{
                Registro reg;
                boolean buscar = false;
                while(true && buscar) {
                    reg =(Registro)ois.readObject();  
                    if (!nom.equalsIgnoreCase(reg.getNombre()) && 
                        !ape.equalsIgnoreCase(reg.getApellidos())) {
                        buscar = true;  
                        System.out.println("No coincide ningún contacto con esa"
                                     + " descripción");                                                   
                    }
                    else                                                
                        System.out.println(reg);
                }               
                
            } catch (EOFException e) {
                
             }
             ois.close();
             fis.close();
        } catch(IOException | ClassNotFoundException e){
          System.out.println(e);
        }   
    }
                     
}
