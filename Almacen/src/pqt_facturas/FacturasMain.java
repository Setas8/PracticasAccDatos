package pqt_facturas;

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
public class FacturasMain {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File f = new File("datos.xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        ManejadorFacturas m = new ManejadorFacturas();
        sp.parse(f, m);

        ArrayList<Cliente> listaClientes = m.getListaClientes();
        ArrayList<Producto> listaProductos = m.getListaProductos();

        System.out.println("--------Array Clientes-----------");
        leerArrayClientes(listaClientes);
        System.out.println("--------Array Productos-----------");
        leerArrayProductos(listaProductos);
        System.out.println("--------XML Facturas-----------");
        crearXMLFacturas(listaClientes, listaProductos);

    }

    private static void leerArrayClientes(ArrayList<Cliente> l) {
        for (Cliente c : l) {
            System.out.println(c);
        }
    }

    private static void leerArrayProductos(ArrayList<Producto> l) {
        for (Producto p : l) {
            System.out.println(p);
        }
    }

    private static void crearXMLFacturas(ArrayList<Cliente> lClientes, ArrayList<Producto> lProductos) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("Facturas");
        doc.appendChild(root);

        for (int i = 0; i < lClientes.size(); i++) {

            for (Producto p : lProductos) {
                //Artículo coincide con el producto
                if (lClientes.get(i).getArticulo().equals(p.getId())) {
                    //Si hay suficientes cantidades del producto se añade al xml
                    if (lClientes.get(i).getUnidades() <= p.getStock()) {

                        Element factura = doc.createElement("Factura");
                        root.appendChild(factura);
                        Attr nif = doc.createAttribute("NIF");
                        nif.setNodeValue(lClientes.get(i).getNif());
                        factura.setAttributeNode(nif);

                        Element nombre = doc.createElement("Nombre");
                        Text valor = doc.createTextNode(lClientes.get(i).getNombre());
                        nombre.appendChild(valor);
                        factura.appendChild(nombre);

                        Element teclado = doc.createElement("Teclado");
                        valor = doc.createTextNode(p.getDescripcion());
                        teclado.appendChild(valor);
                        factura.appendChild(teclado);

                        Element unidades = doc.createElement("Unidades");
                        valor = doc.createTextNode(String.valueOf(lClientes.get(i).getUnidades()));
                        unidades.appendChild(valor);
                        factura.appendChild(unidades);

                        Element precio = doc.createElement("Precio");
                        valor = doc.createTextNode(String.valueOf(p.getPrecio()));
                        precio.appendChild(valor);
                        factura.appendChild(precio);

                        Element total = doc.createElement("Total");
                        valor = doc.createTextNode(String.valueOf(lClientes.get(i).getUnidades() * p.getPrecio()));
                        total.appendChild(valor);
                        factura.appendChild(total);

                        p.setStock(p.getStock() - lClientes.get(i).getUnidades());
                    } 
                    // No hay suficientes cantidades del producto se avisa
                    else { 
                        System.out.println("No se incluye al cliente "
                                + lClientes.get(i).getNombre() + ", con NIF "
                                + lClientes.get(i).getNif() + ".\nPidió "
                                + lClientes.get(i).getUnidades() + " de "
                                + p.getDescripcion() + " y sólo quedan "
                                + p.getStock());                       
                    }//if-else stock suficiente               
                }//If articulos coinciden en las dos listas
            }//For productos
        }//For clientes
        
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource ds = new DOMSource(doc);
        StreamResult sr = new StreamResult(new File("facturas.xml"));
        StreamResult sr1 = new StreamResult(System.out);
        transformer.transform(ds,sr);
        transformer.transform(ds,sr1);
        
        
    }
}
