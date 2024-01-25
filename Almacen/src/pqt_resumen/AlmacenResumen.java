package pqt_resumen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Diego Cuadrado
 */
public class AlmacenResumen {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        
        File fAlmacen = new File("Almacen.xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        ManejadorArticulo m = new ManejadorArticulo();
        sp.parse(fAlmacen,m);
        
        ArrayList<Articulo> listaArticulos = m.getListaArticulos();
        System.out.println("-----Array Articulos-----");
        leerlistaArticulos(listaArticulos);
        System.out.println("-----XML resumen-----");
        File fResumen = new File("resumen.xml");
        crearXMLResumen(fResumen, listaArticulos);
    }
    private static void leerlistaArticulos(ArrayList<Articulo> l) {
        
        for (Articulo a : l) {
            System.out.println(a);
        }
    }
    private static void crearXMLResumen(File f, ArrayList<Articulo> l) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        
        
        Element root = doc.createElement("Resumen");
        doc.appendChild(root);
        
        for (int i = 0; i < l.size(); i++) {
            
            Element articulo = doc.createElement("Articulo");
            root.appendChild(articulo);
            Attr nombre = doc.createAttribute("nombre");
            nombre.setNodeValue(l.get(i).getComponente());
            articulo.setAttributeNode(nombre);
            
            Element cajas = doc.createElement("cajas");
            Text valor = doc.createTextNode(String.valueOf(l.get(i).getLote().getNumCajas()));
            cajas.appendChild(valor);
            articulo.appendChild(cajas);
            
            Element cantidad = doc.createElement("cantidad");
            valor = doc.createTextNode(String.valueOf(l.get(i).getUnidades()));
            cantidad.appendChild(valor);
            articulo.appendChild(cantidad);
            
            int valorTotal = l.get(i).getLote().getNumCajas() * l.get(i).getUnidades();
            Element total = doc.createElement("total");
            valor = doc.createTextNode(String.valueOf(valorTotal));
            total.appendChild(valor);
            articulo.appendChild(total);
            
            Element peso = doc.createElement("peso");
            valor = doc.createTextNode(l.get(i).getLote().getPeso());
            peso.appendChild(valor);
            articulo.appendChild(peso);
                       
        }
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource ds= new DOMSource(doc);
        StreamResult sr= new StreamResult(f);
        StreamResult sr1 = new StreamResult(System.out);
        transformer.transform(ds,sr);
        transformer.transform(ds,sr1);
    }
}
