package pqt_empresaXML;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author Diego Cuadrado
 */
public class ManejadorOperacion extends DefaultHandler{
    ArrayList<Operacion> listaoperaciones = new ArrayList<>();
    private Operacion o;
    private StringBuilder strBu = new StringBuilder();
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Operacion":
                o = new Operacion();
                listaoperaciones.add(o);  
                o.setId(attributes.getValue("id"));                            
                break;
            case "Indice":
            case "Nombre":
            case "Simbolo":
            case "Precio":                     
                strBu.delete(0,strBu.length());
                break;         
         }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        strBu.append(ch, start, length); //Cada vez que encuentra una cadena se lo añade a la etiqueta
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "id":
                o.setId(strBu.toString());
                break;
            case "Indice":
                o.setIndice(strBu.toString());
                break;
            case "Nombre":
                o.setNombre(strBu.toString());
                break;
            case "Simbolo":
                o.setSimbolo(strBu.toString());
                break;
            case "Precio":
                o.setPrecio(Float.parseFloat(strBu.toString()));
                break;
              
         }
    }

    public ArrayList<Operacion> getListaoperaciones() {
        return listaoperaciones;
    } 
}
