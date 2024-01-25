package pqt_biblioteca;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Diego Cuadrado
 */
public class ManejadorLibro extends DefaultHandler {

    ArrayList<Libro> listaLibros = new ArrayList<>();
    private Libro l;
    private StringBuilder strb = new StringBuilder();

    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        strb.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Libro":
                l = new Libro();
                listaLibros.add(l);
                l.setId(Integer.parseInt(attributes.getValue("id")));
                l.setTematica(attributes.getValue("tematica"));
                break;
            case "Editorial":
            case "FPublicacion":
            case "Idioma":               
            case "Titulo":
            case "Autor":
                strb.delete(0, strb.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Editorial":
                l.setEditorial(strb.toString());
                break;
            case "FPublicacion":               
                l.setFPublicacion(strb.toString());
                break;
            case "Idioma":
                l.setIdioma(strb.toString());
                break;
            case "Titulo":
                l.setTitulo(strb.toString());
                break;
            case "Autor":
                l.setAutor(strb.toString());
                break;
        }
    }

}
