package pqt_empresaXML;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
public class BolsaMain {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File f1 = new File("Bolsa.xml");  
        SAXParserFactory spf1 = SAXParserFactory.newInstance();
        SAXParser sp1 = spf1.newSAXParser();
        ManejadorEmpresa mE = new ManejadorEmpresa();

        sp1.parse(f1, mE);
        
        //LLamar array
        ArrayList<Empresa> listaEmpresas =  mE.getListaEmpresas();
        
        System.out.println("-----Array empresas---------");
        leerArrayEmpresas(listaEmpresas);
        
        File f2 = new File("datos.xml");  
        SAXParserFactory spf2 = SAXParserFactory.newInstance();
        SAXParser sp2 = spf2.newSAXParser();
        ManejadorOperacion mO = new ManejadorOperacion();

        sp2.parse(f2, mO);
        
        //LLamar array
        ArrayList<Operacion> listaOperaciones =  mO.getListaoperaciones();
        
        System.out.println("-----Array Operaciones------");
        leerArrayOperaciones(listaOperaciones);
                      
        System.out.println("-----prueba empresa modificada empresa------");
        actualizarFichero(listaEmpresas, listaOperaciones);
        leerArrayEmpresas(listaEmpresas);
        
        System.out.println("-----prueba crear XML modificado empresa------");
        crearXMLmodificado(listaEmpresas);
        
    }   
    private static void leerArrayEmpresas(ArrayList<Empresa> listaEmpresas) throws ParserConfigurationException, SAXException, IOException {
                      
        for (Empresa e : listaEmpresas) {
            System.out.println(e);
        }
    }
    private static void leerArrayOperaciones(ArrayList<Operacion> listaOperaciones) throws ParserConfigurationException, SAXException, IOException {
        
        
        
        for (Operacion o : listaOperaciones) {
            System.out.println(o);
        }
    }
    private static int buscarIndiceEmpresa(String nombre, ArrayList<Empresa> listaEmpresas) {
        int indice   = -1;
       
        for (int i = 0; i < listaEmpresas.size(); i++) {
            if (nombre.equals(listaEmpresas.get(i).getNombre())) {
                indice = i;
            }
        }
        return indice;
    }
    private static void actualizarFichero(ArrayList<Empresa> listaEm,ArrayList<Operacion> listaOp) {
        
        for (Operacion o : listaOp) {
            switch (o.getId()) {
                case "A":                  
                    if (buscarIndiceEmpresa(o.getNombre(), listaEm) == -1) {
                        Empresa e = new Empresa();
                        e.setNombre(o.getNombre());
                        e.setIndice(o.getIndice());
                        e.setSimbolo(o.getSimbolo());
                        e.setPrecio(o.getPrecio());                   
                        listaEm.add(e);   
                    }
                    break;
                case "B":
                    if (buscarIndiceEmpresa(o.getNombre(), listaEm) == -1) {
                        System.out.println("La empresa " + o.getNombre() + " no está en la lista, no se puede dar de baja");
                    }
                    else {
                        listaEm.remove(buscarIndiceEmpresa(o.getNombre(), listaEm));
                    }
                    break;
                case "M":
                    if (buscarIndiceEmpresa(o.getNombre(), listaEm) == -1) {
                        System.out.println("La empresa no está en la lista, no se puede modificar");
                    }
                    else {
                        listaEm.get(buscarIndiceEmpresa(o.getNombre(), listaEm)).setPrecio(o.getPrecio());                        
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    private static void crearXMLmodificado(ArrayList<Empresa> listaEm) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        
        //Primero elemento raíz y colgarlo del documento
        Element root = doc.createElement("Bolsa");
        doc.appendChild(root);
        
        //Atributos del elemento si los tiene, colgarlo de su elemento
        Attr id = doc.createAttribute("dia");
        id.setNodeValue(String.valueOf(LocalDate.now()));
        root.setAttributeNode(id);
        
        //bucle for para crear el número de elementos de la raiz
        for (int i = 0; i < listaEm.size(); i++) {
            /*
            1.Crear elemento
            2.Colgarlo de la raiz
            3.[Crear atributo]
            4.[Darle valor al atributo]
            5.[Colgarlo de su dueño]
            
            1.Crear elemento
            2.Darle valor
            3.Colgarlo de su superior
            4.Colgarlo de la raíz
            */
            Element empresa = doc.createElement("Empresa");
            root.appendChild(empresa);
            Attr indice = doc.createAttribute("indice");
            indice.setNodeValue(listaEm.get(i).getIndice());
            empresa.setAttributeNode(indice);
            
            Element nombre = doc.createElement("Nombre");
            Text valor = doc.createTextNode(listaEm.get(i).getNombre());
            nombre.appendChild(valor);
            empresa.appendChild(nombre);
            
            Element simbolo = doc.createElement("Simbolo");
            valor = doc.createTextNode(listaEm.get(i).getSimbolo());
            simbolo.appendChild(valor);
            empresa.appendChild(simbolo);
            
            Element precio = doc.createElement("Precio");
            valor = doc.createTextNode(String.valueOf(listaEm.get(i).getPrecio()));
            precio.appendChild(valor);
            empresa.appendChild(precio);
            
            
        }
        //Transform
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource ds= new DOMSource(doc);
        StreamResult sr= new StreamResult(new File("bolsaActualizada.xml"));
        StreamResult sr1 = new StreamResult(System.out);
        transformer.transform(ds,sr);
        transformer.transform(ds,sr1);     
    }
}

