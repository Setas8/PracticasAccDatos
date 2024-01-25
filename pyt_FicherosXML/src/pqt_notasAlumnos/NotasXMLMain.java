package pqt_notasAlumnos;

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
public class NotasXMLMain {

    public static void main(String[] args) throws ParserConfigurationException,
                            SAXException, IOException, TransformerConfigurationException, TransformerException {
        
        
        //Pasar elfichero XML a un arrayList con SAX
        File f = new File("notas.xml");  
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        Manejador m = new Manejador();

        sp.parse(f, m);
        ArrayList<Alumno> listaAlumnos =  m.getListaAlumnos();             
        
        leerArrayXML(listaAlumnos);
        leerMediaNotas(listaAlumnos);
        crearXMLconDOM(listaAlumnos);
        
             
        
    }
    private static void leerArrayXML(ArrayList<Alumno> l) {
        
        System.out.println("- - - - -ARRAYLIST DEL XML - - - - -\n");
        System.out.println("NOMBRE\t\tNOTA 1\tNOTA 2\tPRÁCTICA\tPROYECTO");
        for (Alumno a : l) {
            System.out.printf("%s\t%d\t%d\t%d\t\t%d",stringCaracteres(a.getNombre()),a.getNota1(),
                    a.getNota2(),a.getPractica(),a.getProyecto()); 
            System.out.println();
        }                       
    }
    private static float leerMediaNotas(ArrayList<Alumno> l) {
        
        System.out.println("\n- - - - -MEDIA DE NOTAS - - - - -\n");
        float sumaNotasAlumno = 0;
        float mediaNotaAlumno = 0;
        float sumaMediaTotal  = 0;
        float mediaNotaTotal  = 0;
        int contadorAlumnos   = 0;
        
        for (int i = 0; i < l.size(); i++) {
            sumaNotasAlumno = 0;
            mediaNotaAlumno = 0;
            sumaNotasAlumno += l.get(i).getNota1();
            sumaNotasAlumno += l.get(i).getNota2();
            sumaNotasAlumno += l.get(i).getPractica();
            sumaNotasAlumno += l.get(i).getProyecto();
            mediaNotaAlumno = sumaNotasAlumno/4;     
            sumaMediaTotal += mediaNotaAlumno;
            contadorAlumnos += i;
            System.out.println("Media Total alumno " + (i+1) + ": " + mediaNotaAlumno);
            sumaNotasAlumno = 0;
            mediaNotaAlumno = 0;
        }
        mediaNotaTotal = sumaMediaTotal/contadorAlumnos;
        System.out.println("Media Notas Total: " + mediaNotaTotal);
        return mediaNotaTotal;
    }
    private static float sacarMediaNotas(ArrayList<Alumno> l) {
            
        float sumaNotasAlumno = 0;
        float mediaNotaAlumno = 0;
        float sumaMediaTotal  = 0;
        float mediaNotaTotal  = 0;
        int contadorAlumnos   = 0;
        
        for (int i = 0; i < l.size(); i++) {
            sumaNotasAlumno = 0;
            mediaNotaAlumno = 0;
            sumaNotasAlumno += l.get(i).getNota1();
            sumaNotasAlumno += l.get(i).getNota2();
            sumaNotasAlumno += l.get(i).getPractica();
            sumaNotasAlumno += l.get(i).getProyecto();
            mediaNotaAlumno = sumaNotasAlumno/4;     
            sumaMediaTotal += mediaNotaAlumno;
            contadorAlumnos += i;
            sumaNotasAlumno = 0;
            mediaNotaAlumno = 0;
        }
        mediaNotaTotal = sumaMediaTotal/contadorAlumnos;
        return mediaNotaTotal;
    }
    private static void crearXMLconDOM(ArrayList<Alumno> l) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {
        
        System.out.println("\n- - - - -FICHERO mediaNotas.xml - - - - -\n");
        //Crear fichero XML 
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document d = db.newDocument();
                      
        //Sacar el elemento raiz del fichero XML
        Element root = d.createElement("Notas");
        d.appendChild(root);
        Attr mediaCurso = d.createAttribute("MediaCurso");
        mediaCurso.setNodeValue(String.valueOf(sacarMediaNotas(l)));
        root.setAttributeNode(mediaCurso);
        
        float sumaNotasAlumno = 0;
        float mediaNotaAlumno = 0;
        float sumaMediaTotal  = 0;
        float mediaNotaTotal  = 0;
        int  contadorAlumnos   = 0;
        
        //Crear los elementos de la raiz
        for (int i = 0; i < l.size(); i++) {
            sumaNotasAlumno = 0;
            mediaNotaAlumno = 0;
            sumaNotasAlumno += l.get(i).getNota1();
            sumaNotasAlumno += l.get(i).getNota2();
            sumaNotasAlumno += l.get(i).getPractica();
            sumaNotasAlumno += l.get(i).getProyecto();
            mediaNotaAlumno = sumaNotasAlumno/4;     
            sumaMediaTotal += mediaNotaAlumno;
            
            Element padre = d.createElement("alumno");
            root.appendChild(padre);
            
            Element nombre = d.createElement("nombre");
            Text valor = d.createTextNode(l.get(i).getNombre());
            nombre.appendChild(valor);
            padre.appendChild(nombre);
            
            Element nota = d.createElement("nota");
            valor = d.createTextNode(String.valueOf(mediaNotaAlumno));
            nota.appendChild(valor);
            padre.appendChild(nota);
            sumaNotasAlumno = 0;
            mediaNotaAlumno = 0;
        }
                      
        //Transform
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource ds = new DOMSource(d);
                
        File f = new File("mediaNotas.xml");
        //Generar la salida del documento
        StreamResult srFichero = new StreamResult(f);
        StreamResult srPantalla = new StreamResult(System.out);
        
        t.transform(ds, srFichero);
        t.transform(ds, srPantalla);
    } 
    public static String stringCaracteres(String palabra) {
        
        int tamanioCampo = 10;
        String str = "";
        int tamanioPalabra = palabra.length();
        
        if (tamanioPalabra <= tamanioCampo) {
            for (int i = tamanioPalabra;i < tamanioCampo; i++) {
                palabra += " ";
            }
            str = palabra;
        }
        else if (tamanioPalabra >= tamanioCampo) {
            str = palabra.substring(0, tamanioCampo);
        }
        else
            str = palabra;
                
        return str;
    }
}
