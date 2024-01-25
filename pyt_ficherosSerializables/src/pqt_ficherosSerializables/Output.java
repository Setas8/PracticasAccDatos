package pqt_ficherosSerializables;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Output extends ObjectOutputStream {
    
    Output(FileOutputStream f) throws IOException{
      super(f);   
    }

    @Override
    protected void writeStreamHeader() throws IOException {}
    
}