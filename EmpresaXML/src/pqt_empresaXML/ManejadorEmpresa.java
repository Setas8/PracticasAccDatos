package pqt_empresaXML;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Diego Cuadrado
 */
public class ManejadorEmpresa extends DefaultHandler{
    ArrayList<Empresa> listaEmpresa = new ArrayList<>();
    private Empresa e;
    private StringBuilder strBu = new StringBuilder();
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Empresa":
                e = new Empresa();
                listaEmpresa.add(e);  
                e.setIndice(attributes.getValue("indice"));                            
                break;
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
            case "Nombre":
                e.setNombre(strBu.toString());
                break;
            case "Simbolo":
                e.setSimbolo(strBu.toString());
                break;
            case "Precio":
                e.setPrecio(Float.parseFloat(strBu.toString()));
                break;
              
         }
    }

    public ArrayList<Empresa> getListaEmpresas() {
        return listaEmpresa;
    } 
}
