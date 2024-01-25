package pqt_creacionDom;


import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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


/**
 *
 * @author Diego Cuadrado
 */
public class CreacionDom {

    public static void main(String[] args) throws ParserConfigurationException,
                 TransformerConfigurationException, TransformerException {
        
        //Documento
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.newDocument();
        
        Element root = d.createElement("Animales");
        d.appendChild(root);//Hijo del documeto
             
        //Crear los elementos de la raiz
        for (int i = 0; i < 5; i++) {
            Element p = d.createElement("NodoPadre" + i);
            root.appendChild(p);
            Attr id = d.createAttribute("id");
            id.setNodeValue(String.valueOf(i+1));
            p.setAttributeNode(id);
            
            Element h1 = d.createElement("Hijo" + i);
            Text valor = d.createTextNode(String.valueOf((int)(Math.random()*10)));
            h1.appendChild(valor);
            p.appendChild(h1);
            
            Element h2 = d.createElement("Hijo" + i);
            valor = d.createTextNode(String.valueOf((int)(Math.random()*10)));
            h2.appendChild(valor);
            p.appendChild(h2);
            
            Element h3 = d.createElement("Hijo" + i);
            valor = d.createTextNode(String.valueOf((int)(Math.random()*10)));
            h3.appendChild(valor);
            p.appendChild(h3);
            
            Element h4 = d.createElement("Hijo" + i);
            valor = d.createTextNode(String.valueOf((int)(Math.random()*10)));
            h4.appendChild(valor);
            p.appendChild(h4);
            
            Element h5 = d.createElement("Hijo" + i);
            valor = d.createTextNode(String.valueOf((int)(Math.random()*10)));
            h5.appendChild(valor);
            p.appendChild(h5);
        }
        
        
        
        
        
        //Transform
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource ds = new DOMSource(d);
        
        
        //Generar la salida del documento
        StreamResult srFichero = new StreamResult(new File("animales.xml"));
        StreamResult srPantalla = new StreamResult(System.out);
        
        t.transform(ds, srFichero);
        t.transform(ds, srPantalla);
        
        
    }    
}
