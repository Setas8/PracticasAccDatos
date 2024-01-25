package pqt_2;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Diego Cuadrado
 */
public class Manejador2 extends DefaultHandler{
    
    
    private ArrayList<Clase> listaClases = new ArrayList<Clase>();
    Clase c;
    StringBuilder sB = new StringBuilder();

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sB.append(sB, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch  (qName) {
            case "Cliente":
                c = new Clase();
                listaClases.add(c);
                c.setId(attributes.getValue("id"));
                break;
            case "cod_articulo":
            case "cantidad":
                sB.delete(0, sB.length());
                break;
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch  (qName) {
            case "cod_articulo":
                c.setCod_articulo(Integer.parseInt(sB.toString()));                    
                break;
            case "cantidad":
                c.setCantidad(Integer.parseInt(sB.toString()));
                break;
        }
    }  
}
