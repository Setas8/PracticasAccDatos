package pqt_ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class ResumenFicherosBinarios {

    static Scanner tcd = new Scanner(System.in);
    static File fBinario;
    static String rojo    = "\033[31m";
    static String verde   = "\033[32m";
    static String azul    = "\033[34m";
    static String magenta = "\033[35m";
    
    public static void main(String[] args) {
            
        //FileOutputStream fosBinario = new FileOutputStream(fPrincipal,true);
        //DataOutputStream dosBinario = new DataOutputStream(fosBinario);
        
        //FileInputStream fisBinario = new FileInputStream(fPrincipal);
        //DataInputStream disBinario = new DataInputStream(fisBinario);
        
        menu();
        tcd.close();
    }
    public static void menu() {               
        int opcion;
        boolean continua;
        do {
            try {
                continua = false;
                System.out.println("\n- - - - - - - - - - - - - - - - - - - -");
                System.out.println(""
                + "1.Crear fichero binario"
                + "\n2.Escribir en el archivo binario"
                + "\n3.Leer el archivo binario"
                + "\n4.Añadir datos al final del archivo binario"
                + "\n5.Borrar archivo(archivoRutaXDefecto)"                              
                + "\n0.Salir");
                opcion = tcd.nextInt(); tcd.nextLine();
                switch (opcion) {
                    case 0:
                        System.out.println("\nSuerte!");
                        break;
                    case 1:                                                 
                        crearFichero();
                        menu();
                        break;
                    case 2:                       
                        escribirFicherobinario();
                        menu();
                        break;
                    case 3:
                        leerFicheroBinario();
                        menu();
                        break;
                    case 4:                       
                        aniadeAlFicheroBinario();
                        menu();
                        break;
                    case 5:
                        System.out.println("\nBorrando archivo....");
                        fBinario.delete();       
                        System.out.println("\nArchivo borrado....");
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
    //Punto 1
    public static void crearFichero() {
        System.out.println(azul + "Creando fichero....");
        try {
            //f es un File static para poder usarlo en otros métodos 
            //sin necesidad de pasar parámetros en ellos
            fBinario = new File("ficheroBinario.dat");
            //createNewFile() Sólo true la primera vez(cuando no existe el directorio)
            System.out.println(verde + "Archivo creado: "           + fBinario.createNewFile());
            System.out.println(verde + "Nombre archivo: "           + fBinario.getName());        
            System.out.println(verde + "Archivo existe: "           + fBinario.exists());
            System.out.println(verde + "Archivo longitud: "         + fBinario.length());
            System.out.println(verde + "Archivo se puede leer: "    + fBinario.canRead());
            System.out.println(verde + "Archivo se puede escribir: "+ fBinario.canWrite());
        } catch (IOException ex) {
            System.out.println(rojo + "Fichero no creado");
        }       
    }
    //Punto2
    public static void escribirFicherobinario() {
        try {           
            File f2 = new File("ficheroBinarioPunto2.dat");              
            FileOutputStream  fos = new FileOutputStream(fBinario);
            DataOutputStream  dos = new DataOutputStream(fos);
            
            System.out.println(azul + "Introduce un String (se machacará lo que haya en él): ");
            String string = tcd.nextLine();
            dos.writeUTF(string);
            //dos.writeUTF(tcd.nextLine());
            System.out.println(azul + "Introduce un número entero (se machacará lo que haya en él): ");
            int entero = tcd.nextInt();tcd.nextLine();
            dos.writeInt(entero);
            //dos.writeInt(tcd.nextInt());
            System.out.println(azul + "Introduce un número real (se machacará lo que haya en él): ");
            double real = tcd.nextDouble();tcd.nextLine();
            dos.writeDouble(entero);
            //dos.writeInt(tcd.nextDouble());
            //dos.writeBoolean(true);
            //dos.writeByte(2);
            //dos.writeChar('a');
            //dos.writeLong(999999999999999999L);
            //dos.writeFloat(99999.999999999f);           
            
            dos.close(); //Importante cerrarlo
            fos.close();
        } catch (IOException ex) {
            System.out.println(rojo + "Error al escribir en el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
    }
    //Punto3
    public static void leerFicheroBinario() {
        System.out.println(azul + "STRING\t\tNÚMERO ENTERO\t\tNÚMERO REAL");
        try {
            
            FileInputStream  fis = new FileInputStream (fBinario);
            DataInputStream  dis = new DataInputStream(fis);
            try {
                while (dis.available()> 0) {
                System.out.println(dis.readUTF() + "\t\t" + dis.readInt() + "\t\t\t" + 
                                   dis.readDouble());   
                }
            }  catch (EOFException ex) {
                System.out.println(rojo + "FINAL DE FICHERO " + ex.getMessage());
            }
            dis.close();
            fis.close();               
        } catch (IOException e) {
            System.out.println(rojo + "Error en la lectura");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
    }
    //Punto4
    public static void aniadeAlFicheroBinario() {
        try {           
            File f2 = new File("ficheroBinarioPunto2.dat");              
            FileOutputStream  fos = new FileOutputStream(fBinario, true);
            DataOutputStream  dos = new DataOutputStream(fos);
            
            System.out.println(azul + "Introduce un String (se machacará lo que haya en él): ");
            String string = tcd.nextLine();
            dos.writeUTF(string);
            //dos.writeUTF(tcd.nextLine());
            System.out.println(azul + "Introduce un número entero (se machacará lo que haya en él): ");
            int entero = tcd.nextInt();tcd.nextLine();
            dos.writeInt(entero);
            //dos.writeInt(tcd.nextInt());
            System.out.println(azul + "Introduce un número real (se machacará lo que haya en él): ");
            double real = tcd.nextDouble();tcd.nextLine();
            dos.writeDouble(entero);
            //dos.writeInt(tcd.nextDouble());
            //dos.writeBoolean(true);
            //dos.writeByte(2);
            //dos.writeChar('a');
            //dos.writeLong(999999999999999999L);
            //dos.writeFloat(99999.999999999f);           
            
            dos.close(); //Importante cerrarlo
            fos.close();
        } catch (IOException ex) {
            System.out.println(rojo + "Error al escribir en el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
    }
}
