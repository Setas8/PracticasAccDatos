package pqt_2;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Diego Cuadrado Martinez
 */
public class Manejador extends DefaultHandler {

    ArrayList<Clase> lista = new ArrayList<>();
    Clase c;
    StringBuilder sB = new StringBuilder();

    public ArrayList<Clase> getLista() {
        return lista;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Cliente":
                c = new Clase();
                lista.add(c);
                c.setId(attributes.getValue("id"));
                break;
            case "cod_articulo":
            case "cantidad":
                sB.delete(0, sB.length());
                break;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sB.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "cod_articulo":
                c.setCod_articulo(Integer.parseInt(sB.toString()));
                break;
            case "cantidad":
                c.setCantidad(Integer.parseInt(sB.toString()));
                break;
        }
    }
}
