package pqt_masActividades;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio8 {

    public static void main(String[] args) {
               
            /*
            Hacer un programa que lea el fichero Septemp.dat y calcule la
            temperatura máxima, la mínima y la media del día indicando cual ha sido
            la hora más fría y la más calurosa. Mostrar los resultados por pantalla
            */        
        int cont = 0;
        int[] temperaturas = new int[24];
        
        try {
            File f = new File("septemp.dat");
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            System.out.println("DIA\tHORA\tTEMPERATURA(º)");
            while (dis.available() > 0) {
                String dia      = dis.readUTF();
                String hora     = dis.readUTF();
                int temperatura = dis.readInt();
                        
                System.out.println(dia + "\t" + hora + "\t" + temperatura);
                temperaturas[cont++] = temperatura;
            }
            //Métodos para obtener max, min, media de la clase IntStream
            int    horaTemMax = IntStream.of(temperaturas).max().getAsInt();
            int    horaTemMin = IntStream.of(temperaturas).min().getAsInt();;
            double mediaTem   = IntStream.of(temperaturas).average().getAsDouble();;
            
            //Obtener el índice del array con las temperaturas max y min
            int horaMax = 0;
            int horaMin = 0;
            for (int i = 0; i < temperaturas.length; i++) {
                if (temperaturas[i] == horaTemMax) 
                    horaMax = i;                
                if (temperaturas[i] == horaTemMin) 
                    horaMin = i;                
            }
        
            System.out.println("Hora más calurosa: " + horaMax + ":00 horas, con " + horaTemMax + "º");
            System.out.println("Hora más fría: " + horaMin + ":00 horas, con " + horaTemMin + "º");
            System.out.printf("Media de temperaturas del día: %.2f%s", mediaTem, "º");
            System.out.println();
            
            dis.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.err.println("");
        }      
    }   
}
