package pqt_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
public class Main2 {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        
        File fPrecios = new File("precios.txt");
        File fCompras = new File("compras.xml");
        
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        Manejador m = new Manejador();
        sp.parse(fCompras,m);
        
        ArrayList<Clase> listaClases = new ArrayList<Clase>();
              
        System.out.println("-------Array clases------");
        for (Clase c : listaClases) {
            System.out.println(c);
        }
        
        ArrayList<String> listaRegistros = new ArrayList<String>();
        
        FileReader fr = new FileReader(fPrecios);
        BufferedReader br = new BufferedReader(fr);
        
        String registro = br.readLine();
        while (registro != null) {
            listaRegistros.add(registro);
            br.readLine();
        }
        
        System.out.println("------Array registros--------");
        for (String s : listaRegistros) {
            System.out.println(s);
        }

        br.close();
        fr.close();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.newDocument();

        Element root = d.createElement("Facturas");
        d.appendChild(root);
        
        
        String[] valores;
        for (int i = 0; i < listaClases.size(); i++) {
            //Calcular artículo por cliente
                //Array para coger el artículo de cada cliente y que coincidan con la posición del array de registros
                //System.out.println(listaRegistros.get(listaClase.get(i).getCod_articulo()-1));
                valores = listaRegistros.get(listaClases.get(i).getCod_articulo() -1).split(" ");
                //System.out.println("Valores " + valores[0] + " " + valores[1] + " " + valores[2]);                           
                
                Element factura = d.createElement("Factura");
                root.appendChild(factura);
                Attr ref = d.createAttribute("ref");
                ref.setValue(String.valueOf(i + 1));
                factura.setAttributeNode(ref);

                Element cliente = d.createElement("cliente");
                Text valor = d.createTextNode(listaClases.get(i).getId());
                cliente.appendChild(valor);
                factura.appendChild(cliente);

                Element articulo = d.createElement("articulo");
                valor = d.createTextNode(valores[1]); 
                articulo.appendChild(valor);
                factura.appendChild(articulo);

                Element cantidad = d.createElement("cantidad");
                valor = d.createTextNode(String.valueOf(listaClases.get(i).getCantidad()));
                cantidad.appendChild(valor);
                factura.appendChild(cantidad);

                Element total = d.createElement("total");
                float precio = listaClases.get(i).getCantidad() * Float.parseFloat(valores[2]);
                valor = d.createTextNode(String.valueOf(precio));
                total.appendChild(valor);
                factura.appendChild(total);
                        
        }
        
        TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource ds  = new DOMSource(d);
            StreamResult sr  = new StreamResult(new File("facturas.xml"));
            StreamResult sr1 = new StreamResult(System.out);
            t.transform(ds, sr);
            
            System.out.println("----------Fichero XML facturas--------");
            t.transform(ds,sr1);
    }
}
