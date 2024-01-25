package pqt_resumen;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Diego Cuadrado
 */
public class ManejadorArticulo extends DefaultHandler{
    
    private ArrayList<Articulo> listaArticulos = new ArrayList<>();
    private Articulo a;
    private Lote l;
    StringBuilder sB = new StringBuilder();

    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sB.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "lote":
                a.setLote(l);
                break;
            case "componente":
                a.setComponente(sB.toString());
                break;
            case "numcajas":
                l.setNumCajas(Integer.parseInt(sB.toString()));
                break;
            case "unidades":
                a.setUnidades(Integer.parseInt(sB.toString()));
                break;
            case "peso":
                l.setPeso(sB.toString());
                break;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "lote":
                l = new Lote();                           
                break;
            case "contenido":
                a = new Articulo();
                listaArticulos.add(a); 
                break;
            case "numcajas":
            case "componente":
            case "unidades":
            case "peso":
                sB.delete(0, sB.length());
                break;
        }
    }
    
    
    
    
}
