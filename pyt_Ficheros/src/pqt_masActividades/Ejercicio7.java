package pqt_masActividades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Diego Cuadrado
 */
public class Ejercicio7 {

    public static void main(String[] args) {
        /*
        Crear un fichero binario llamado Septemp.dat. que guarde las
        temperaturas correspondientes a un día del mes de septiembre.
        La estructura del fichero será: día, hora y temperatura. Los datos de 
        dichas temperaturas se sacarán del fichero de texto temperaturas.txt.
        */
        File fText = new File("temperaturas.txt");
        File fBin  = new File("septemp.dat");
        /*
        Fichero de temperaturas.txt(dia,hora,temperatura)
        1,00:00,09
        1,01:00,07
        ..........
        1,23:00,11
        */
        try {
            FileReader     fr = new FileReader(fText);
            BufferedReader br = new BufferedReader(fr);
            
            FileOutputStream fos = new FileOutputStream(fBin);
            DataOutputStream dos = new DataOutputStream(fos);
            
            String[] tempDiaSep;
            
            String registro = "";
            while ((registro = br.readLine()) != null) {
                tempDiaSep = registro.split(",");
                
                dos.writeUTF(tempDiaSep[0]);
                dos.writeUTF(tempDiaSep[1]);
                dos.writeInt(Integer.parseInt(tempDiaSep[2]));
            }                        
            dos.close();
            fos.close();
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error en la escritura del fichero");
        }      
    }   
}
