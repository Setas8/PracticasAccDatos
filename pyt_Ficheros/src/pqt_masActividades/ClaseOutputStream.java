package pqt_masActividades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Diego Cuadrado
 */
public class ClaseOutputStream extends ObjectOutputStream {
    
    ClaseOutputStream(FileOutputStream f) throws IOException{
        super(f);       
    }
    @Override
    protected void writeStreamHeader()  throws IOException{}   
}
