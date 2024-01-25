package pqt_domNotas;

import java.io.File;
import java.util.ArrayList;
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
import pqt_alumnos.Alumno;
import pqt_alumnos.Manejador;

/**
 *
 * @author Diego Cuadrado
 */
public class DomNotasAlumnos {

    public static void main(String[] args) throws ParserConfigurationException,
                 TransformerConfigurationException, TransformerException {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.newDocument();
        
        Manejador m = new Manejador();
        ArrayList<Alumno> lista = m.getListaAlumnos();
        
        float sumaNotasAlumno = 0;
        float mediaNotaAlumno = 0;
        float SumaMediaTotal = 0;
        float MediaNotaTotal = 0;
        int contadorAlumnos = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            sumaNotasAlumno = 0;
            mediaNotaAlumno = 0;
            sumaNotasAlumno += lista.get(i).getNota1();
            sumaNotasAlumno += lista.get(i).getNota1();
            sumaNotasAlumno += lista.get(i).getNota1();
            sumaNotasAlumno += lista.get(i).getNota1();
           
            mediaNotaAlumno = sumaNotasAlumno/4;     
            SumaMediaTotal += mediaNotaAlumno;
            contadorAlumnos += i;
        }
        MediaNotaTotal = SumaMediaTotal/contadorAlumnos;
        
        Element root = d.createElement("Notas");
        d.appendChild(root);//Hijo del documeto   
        Attr mediaNotas = d.createAttribute("MediaCurso");
            mediaNotas.setNodeValue(String.valueOf(8));
            root.setAttributeNode(mediaNotas);
    
        //Crear los elementos de la raiz
        for (int i = 0; i < lista.size(); i++) {
            Element p = d.createElement("alumno");
            root.appendChild(p);
            
            
            Element h1 = d.createElement("nombre");
            Text valor = d.createTextNode(lista.get(i).getNombre());
            h1.appendChild(valor);
            p.appendChild(h1);
            
            Element h2 = d.createElement("nota");
            sumaNotasAlumno += lista.get(i).getNota1();
            sumaNotasAlumno += lista.get(i).getNota2();
            sumaNotasAlumno += lista.get(i).getPractica();
            sumaNotasAlumno += lista.get(i).getProyecto();
           
            mediaNotaAlumno = sumaNotasAlumno/4;
            valor = d.createTextNode(String.valueOf((float)mediaNotaAlumno));
            h2.appendChild(valor);
            p.appendChild(h2);
            
        }
       
      //Transform
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource ds = new DOMSource(d);
        
        
        //Generar la salida del documento
        StreamResult srFichero = new StreamResult(new File("notas2.xml"));
        StreamResult srPantalla = new StreamResult(System.out);
        
        t.transform(ds, srFichero);
        t.transform(ds, srPantalla);
    }
}
