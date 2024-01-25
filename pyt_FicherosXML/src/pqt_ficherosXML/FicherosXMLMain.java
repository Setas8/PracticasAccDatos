package pqt_ficherosXML;

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
public class FicherosXMLMain {

    public static void main(String[] args) throws ParserConfigurationException,
                            SAXException, IOException {
        
        //File f = new File("paises.xml"); //Para el fichero paises.xml
        File f = new File("paises1.xml");  //Para el fichero paises1.xml
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        Manejador m = new Manejador();

        sp.parse(f, m);
        
        //LLamar array
        ArrayList<Localidad> listaLocalidades =  m.getListaLocalidades();

       System.out.println("PAÍS\t\t\tCONTINENTE\t\tCAPITAL\t\t\tHOMBRES\tMUJERES");
        for (Localidad l : listaLocalidades) {
            System.out.printf("%s\t%s\t%s\t%d\t%d",stringCaracteres(l.getPais()),
                    stringCaracteres(l.getContinente()),stringCaracteres(l.getCapital()),
                    l.getHabitantes().getHombres(),l.getHabitantes().getMujeres()); 
            System.out.println();
        }           
    }   
    public static String stringCaracteres(String palabra) {
        
        int tamanioCampo = 20;
        String str = "";
        int tamanioPalabra = palabra.length();
        
        if (tamanioPalabra <= tamanioCampo) {
            for (int i = tamanioPalabra;i < tamanioCampo; i++) {
                palabra += " ";
            }
            str = palabra;
        }
        else if (tamanioPalabra >= tamanioCampo) {
            str = palabra.substring(0, tamanioCampo);
        }
        else
            str = palabra;
                
        return str;
    }
}
