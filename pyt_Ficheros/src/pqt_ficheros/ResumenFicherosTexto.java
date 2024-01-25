package pqt_ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class ResumenFicherosTexto {

    static File d = new File("CARPETA"); //Sólo es objeto de clase archivo netbeans
    static File f;
    static Scanner tcd    = new Scanner(System.in);
    static String rojo    = "\033[31m";
    static String verde   = "\033[32m";
    static String azul    = "\033[34m";
    static String magenta = "\033[35m";
    
    public static void main(String[] args) {
            
        menu();
        tcd.close();
    }
    public static void menu() {               
        int opcion;
        boolean continua;
        do {
            try {
                continua = false;
                System.out.println("\n" + "- - - - - - - - - - - - - - - - - - - -");
                System.out.println(""
                + "1.Crear directorio"
                + "\n2.Crear archivo de texto"
                + "\n3.Crear archivo de texto en directorio"
                + "\n4.Mostrar contenido del directorio"
                + "\n5.Escribir en el archivo"
                + "\n6.Borrar archivo(archivoRutaXDefecto)"                              
                + "\n7.Añadir datos al final del archivo con FileWriter"
                + "\n8.Añadir datos al final del archivo con BufferedWriter"
                + "\n9.Añadir datos al final del archivo con PrintWriter"
                + "\n10.Leer archivo con FileReader dentro bucle"
                + "\n11.Leer archivo con FileReader fuera bucle"
                + "\n12.Leer archivo con BufferedReader dentro bucle"
                + "\n13.Leer archivo con BufferedReader fuera bucle"
                + "\n14.Leer archivo con Scanner"
                + "\n15.Escribir(el método escribe) archivo con campos(;-fin de campo $-fin de fichero)"
                + "\n16.Leer archivo con campos(;-fin de campo $-fin de fichero)"
                + "\n0.Salir");
                System.out.print(magenta);
                opcion = tcd.nextInt(); tcd.nextLine();
                switch (opcion) {
                    case 0:
                        System.out.println("\nSuerte!");
                        break;
                    case 1:                                                 
                        crearDirectorio();
                        menu();
                        break;
                    case 2:                       
                        crearFichero();
                        menu();
                        break;
                    case 3:
                        crearFicheroEnDirectorio();
                        menu();
                        break;
                    case 4:                       
                        mostrarContenidoDirectorio();
                        menu();
                        break;
                    case 5:
                        escribirFicheroTexto();                                                                 
                        menu();
                        break;
                    case 6:
                        System.out.println(azul + "Borrando archivo....");
                        f.delete();       
                        System.out.println(azul + "Archivo borrado....");
                        menu();
                        break;                                      
                    case 7:
                        aniadeAlFicheroTextoFileWriter();
                        menu();
                        break;
                    case 8:
                        aniadeAlFicheroTextoBufferedWriter();
                        menu();
                        break;
                    case 9:
                        aniadeAlFicheroTextoPrintWriter();
                        menu();
                        break;
                    case 10:
                        leerFicheroFileReaderDentroBucle();
                        menu();
                        break;
                    case 11:
                        leerFicheroFileReaderFueraBucle();
                        menu();
                        break;
                    case 12:
                        leerFicheroBufferedReaderDentroBucle();
                        menu();
                        break;
                    case 13:
                        leerFicheroBufferedReaderFueraBucle();
                        menu();
                        break;
                    case 14:
                        leerFicheroScanner();
                        menu();
                        break; 
                    case 15:
                        escribirFicheroConCampos();
                        menu();
                        break;                      
                    case 16:
                        leerFicheroConCampos();
                        menu();
                        break;
                    default:      
                        System.out.println(rojo + "Opción incorrecta");
                        menu();                                          
                }
            } catch (InputMismatchException  ime) {           
                System.out.println(rojo + "No has introducido un entero");
                tcd.next();
                continua = true;
            } 
        } while(continua);  
    }
    //Punto 1
    public static void crearDirectorio() {
        System.out.println(azul + "Creando directorio....");
        //mkdir() Sólo true la primera vez(cuando no existe el directorio)
        System.out.println(verde + "Directorio creado: "            + d.mkdir()); 
        System.out.println(verde + "Archivo es directorio: "        + d.isDirectory());
        System.out.println(verde + "Directorio se puede leer: "     + d.canRead());
        System.out.println(verde + "Directorio se puede escribir: " + d.canWrite());      
    }
    //Punto 2
    public static void crearFichero() {
        System.out.println(azul + "Creando fichero....");
        try {
            //f es un File static para poder usarlo en otros métodos 
            //sin necesidad de pasar parámetros en ellos
            f = new File("archivoRutaXDefecto.txt");
            //createNewFile() Sólo true la primera vez(cuando no existe el directorio)
            System.out.println(verde + "Archivo creado: "           + f.createNewFile());
            System.out.println(verde + "Nombre archivo: "           + f.getName());        
            System.out.println(verde + "Archivo existe: "           + f.exists());
            System.out.println(verde + "Archivo longitud: "         + f.length());
            System.out.println(verde + "Archivo se puede leer: "    + f.canRead());
            System.out.println(verde + "Archivo se puede escribir: "+ f.canWrite());
                   
        } catch (IOException ex) {
            System.out.println(rojo + "Fichero no creado");
        }
    }
    //Punto 3
    public static void crearFicheroEnDirectorio() {
        System.out.println(azul + "Creando fichero en directorio....");
        try {
            File f2 = new File(d, "archivoRutaDirectorio.txt");
            //createNewFile() Sólo true la primera vez(cuando no existe el directorio)
            System.out.println(verde + "Archivo creado: "           + f2.createNewFile());
            System.out.println(verde + "Nombre archivo: "           + f2.getName());      
            System.out.println(verde + "Archivo existe: "           + f2.exists());
            System.out.println(verde + "Archivo longitud: "         + f2.length());
            System.out.println(verde + "Archivo se puede leer: "    + f2.canRead());
            System.out.println(verde + "Archivo se puede escribir: "+ f2.canWrite());
                   
        } catch (IOException ex) {
            System.out.println(rojo + "Fichero no creado");
        }
    }
    //Punto 4
    public static void mostrarContenidoDirectorio() {
        File[] lista = d.listFiles();
        try {
            System.out.println(azul + "Contenido del directorio:");
            for (File dir : lista)
                System.out.println(dir.getName() + " " + dir.getAbsolutePath() +
                    " " + dir.isDirectory());
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null."
                                    + " Debes pasar por punto 2");
        }      
    }
    //Punto 5
    public static void escribirFicheroTexto() {       
        try {           
            File f2 = new File("textoPunto6.txt");   
            f.renameTo(f2);
            FileWriter fw = new FileWriter(f);            
            System.out.println(azul + "Introduce un texto (se machacará lo que haya en él): ");
            String texto = tcd.nextLine();
            fw.write(texto);
            fw.close(); //Importante cerrarlo
        } catch (IOException ex) {
            System.out.println(rojo + "Error al escribir en el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
    }
    //Punto 7
    public static void aniadeAlFicheroTextoFileWriter() {       
        try {           
            if (f.exists()) {
                File f2 = new File("textoPunto7,8,9.txt"); 
                f.renameTo(f2);                            
                FileWriter fw = new FileWriter(f, true);            
                System.out.println(azul + "Introduce un texto al final del fichero: ");
                String texto = tcd.nextLine();
                fw.write(texto);
                fw.close();
            }
            else
                System.out.println(rojo + "Debes crear el fichero primero. "
                                 + "Vuelve al punto 2");           
        } catch (IOException ex) {
            System.out.println(rojo + "Error al escribir en el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
    }
    //Punto 8
    public static void aniadeAlFicheroTextoBufferedWriter() {       
        try {           
            if (f.exists()) {
                File f2 = new File("textoPunto7,8,9.txt"); 
                f.renameTo(f2);                            
                FileWriter     fw = new FileWriter(f, true);  
                BufferedWriter bw = new BufferedWriter(fw);
                
                System.out.println(azul + "Introduce un texto al final del fichero: ");
                String texto = tcd.nextLine().toLowerCase();
                fw.write(texto);
                bw.close();
                fw.close();
            }
            else
                System.out.println(rojo + "Debes crear el fichero primero. "
                                 + "Vuelve al punto 2");           
        } catch (IOException ex) {
            System.out.println(rojo + "Error al escribir en el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null."
                                    + " Debes pasar por punto 2");
        }
    }
    //Punto 9
    public static void aniadeAlFicheroTextoPrintWriter() {       
        try {           
            if (f.exists()) {
                File f2 = new File("textoPunto7,8,9.txt"); 
                f.renameTo(f2);                            
                FileWriter  fw = new FileWriter(f, true);  
                PrintWriter pw = new PrintWriter(fw);
                
                System.out.println(azul + "Introduce un texto al final del fichero: ");
                String texto = tcd.nextLine().toLowerCase();
                pw.println(texto);
                pw.close();
                fw.close();
            }
            else
                System.out.println(rojo + "Debes crear el fichero primero. "
                                 + "Vuelve al punto 2");           
        } catch (IOException ex) {
            System.out.println(rojo + "Error al escribir en el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. "
                                    + "Debes pasar por punto 2");
        }
    }
    //Punto10
    public static void leerFicheroFileReaderDentroBucle() {
        System.out.println(azul + "Contenido del fichero con FileReader dentro del bucle:");
        try {           
            FileReader fr = new FileReader(f);   
            int x = 0;
            while ((x = fr.read()) != -1) {
                System.out.print((char)x);                                 
            }
            fr.close();
        } catch (IOException ex) {
            System.out.println(rojo + "Error al leer el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
        System.out.println();
    }
    //Punto11
    public static void leerFicheroFileReaderFueraBucle() {
        System.out.println(azul + "Contenido del fichero con FileReader fuera del bucle:");
        try {           
            FileReader fr = new FileReader(f);   
            int x = fr.read();
            while (x != -1) {
                System.out.print((char)x);
                x = fr.read();
            }
            fr.close();
        } catch (IOException ex) {
            System.out.println(rojo + "Error al leer el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
        System.out.println();
    }
    //Punto12
    public static void leerFicheroBufferedReaderDentroBucle() {
        System.out.println(azul + "Contenido del fichero con BufferedReader dentro del bucle:");
        String texto = "";
        try {           
            FileReader fr     = new FileReader(f);   
            BufferedReader br = new BufferedReader(fr);
            
            while ((texto = br.readLine()) != null) {
                System.out.println(texto);
            }
            //Importante cerrar los archivos
            br.close();  
            fr.close();
        } catch (IOException ex) {
            System.out.println(rojo + "Error al leer el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
        System.out.println();
    }
    //Punto13
    public static void leerFicheroBufferedReaderFueraBucle() {
        System.out.println(azul + "Contenido del fichero con BufferedReader fuera del bucle:");
        String texto = "";
        try {           
            FileReader     fr = new FileReader(f);  
            BufferedReader br = new BufferedReader(fr);
            
            texto = br.readLine();
            while (texto != null) {
                System.out.println(texto);
                texto = br.readLine();
            }
            //Importante cerrar los archivos
            br.close();
            fr.close();
        } catch (IOException ex) {
            System.out.println(rojo + "Error al leer el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
        System.out.println();
    }
    //Punto14
    public static void leerFicheroScanner() {
        System.out.println(azul + "Contenido del fichero con Scanner:");
        try {           
            Scanner   sc = new Scanner(f);  
            String texto = "";
            
            while (sc.hasNextLine()) {
                texto = sc.nextLine();
                System.out.println(texto);             
                //System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (IOException ex) {
            System.out.println(rojo + "Error al leer el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
        System.out.println();
    }
    //Punto 15
    public static void escribirFicheroConCampos() {
        System.out.println(azul + "Escribiendo fichero con campos....");
        try {
            FileWriter fw = new FileWriter(new File("archivoTextoCampos.txt"), true); 
            
            String [] arrayString = {"aa", "bb", "cc", "dd"};
            for(int i=0; i<arrayString.length; i++) {
                fw.write(arrayString[i]);
                if (i != arrayString.length-1) 
                    fw.write(";");
                else
                    fw.write("$");
            }
            fw.close();      //Importante cerrar el archivo       
        }catch(IOException e){
            System.out.println(rojo + "Error al escribir el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
        System.out.println();
    }
    //Punto 16
    public static void leerFicheroConCampos() {
        System.out.println(azul + "Contenido del fichero con campos:");
        try {
            FileReader     fr = new FileReader(new File("archivoTextoCampos.txt"));
            BufferedReader br = new BufferedReader(fr);
            
            String registros = "";
            while ((registros = br.readLine()) != null) {
                System.out.println(registros);
            }
            br.close();   //Importante cerrar el archivo          
        } catch (IOException e) {
            System.out.println(rojo + "Error al leer el fichero");
        } catch (NullPointerException ne) {
            System.out.println(rojo + "Fichero apunta a null. Debes pasar por punto 2");
        }
        System.out.println();
    }
}
