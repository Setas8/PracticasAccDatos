package pqt_alumnos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


/**
 *
 * @author Diego Cuadrado
 */
public class alumnoXML {

    public static void main(String[] args) throws ParserConfigurationException,
                            SAXException, IOException {
        
        File f = new File("notas.xml");  //Para el fichero notas.xml
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        Manejador m = new Manejador();

        sp.parse(f, m);
        
        //LLamar array
        ArrayList<Alumno> listaAlumnos =  m.getListaAlumnos();
        
        System.out.println("NOMBRE\t\t\tNOTA 1\tNOTA 2\tPRÁCTICA\tPROYECTO");
        for (Alumno a : listaAlumnos) {
            System.out.printf("%s\t%d\t%d\t%d\t\t%d",a.getNombre(),a.getNota1(),
                    a.getNota2(),a.getPractica(),a.getProyecto()); 
            System.out.println();
        }
    }   
}
