package pqt_facturas;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Diego Cuadrado
 */
public class ManejadorFacturas extends DefaultHandler {

    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    Cliente c;
    Producto p;
    StringBuilder sB = new StringBuilder();

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sB.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "nombre":
                c.setNombre(sB.toString());
                break;
            case "NIF":
                c.setNif(sB.toString());
                break;
            case "articulo":
                c.setArticulo(sB.toString());
                break;
            case "unidades":
                c.setUnidades(Integer.parseInt(sB.toString()));
                break;
            case "id":
                p.setId(sB.toString());
                break;
            case "descripcion":
                p.setDescripcion(sB.toString());
                break;
            case "precio":
                p.setPrecio(Integer.parseInt(sB.toString()));
                break;
            case "stock":
                p.setStock(Integer.parseInt(sB.toString()));
                break;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "cliente":
                c = new Cliente();
                listaClientes.add(c);
                break;
            case "producto":
                p = new Producto();
                listaProductos.add(p);
                p.setId(attributes.getValue("id"));
                break;
            case "nombre":
            case "NIF":
            case "articulo":
            case "unidades":
            case "descripcion":
            case "precio":
            case "stock":
                sB.delete(0, sB.length());
                break;
        }
    }

}
