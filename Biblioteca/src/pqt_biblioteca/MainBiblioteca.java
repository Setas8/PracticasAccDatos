package pqt_biblioteca;

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
public class MainBiblioteca {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        //File fLibreria = new File("libreria.xml");
        File fEditoriales = new File("editoriales.xml");
        File fEscritores = new File("escritores.xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        ManejadorLibro mEditorial = new ManejadorLibro();
        ManejadorLibro mEscritor = new ManejadorLibro();
        sp.parse(fEditoriales, mEditorial);
        sp.parse(fEscritores, mEscritor);

        //LLamar array
        ArrayList<Libro> listaEditoriales = mEditorial.getListaLibros();
        ArrayList<Libro> listaEscritores = mEscritor.getListaLibros();

        System.out.println("\n------------LISTA EDITORIALES--------------------\n");
        leerArrayList(listaEditoriales);
        System.out.println("\n------------LISTA AUTORES--------------------\n");
        leerArrayList(listaEscritores);
        //Crear array actualizado con los dos xml
        ArrayList<Libro> listaLibreria = new ArrayList<Libro>();
        crearArrayLibreria(listaEditoriales, listaEscritores, listaLibreria);       
        System.out.println("\n------------LISTA LIBRERÍA--------------------\n");
        leerArrayList(listaLibreria);
        System.out.println("\n------------XML LIBRERÍA--------------------\n");
        crearXMLActualizado(listaLibreria);

    }

    private static void leerArrayList(ArrayList<Libro> lista) {

        for (Libro l : lista) {
            System.out.println(l);
        }
    }
    private static void crearArrayLibreria(ArrayList<Libro> listaEdi, ArrayList<Libro> listaEsc, ArrayList<Libro> listaLib) {

        int i = 0;
        int j = 0;
        int k = 0;
        while ((i < listaEdi.size() ) && (j < listaEsc.size() )) {
            System.out.println(i + "->" +listaEdi.get(i).getId() + " "+ j +"->"+  listaEsc.get(j).getId());
            if (listaEdi.get(i).getId() == listaEsc.get(j).getId()) {
                             
                listaLib.add(listaEdi.get(i));
                System.out.println(listaLib.get(k));
                listaLib.get(k).setId(listaEdi.get(i).getId());
                listaLib.get(k).setTematica(listaEdi.get(i).getTematica());
                listaLib.get(k).setEditorial(listaEdi.get(i).getEditorial());
                listaLib.get(k).setFPublicacion(listaEdi.get(i).getFPublicacion());
                listaLib.get(k).setIdioma(listaEdi.get(i).getIdioma());
                listaLib.get(k).setTitulo(listaEsc.get(j).getTitulo());
                listaLib.get(k).setAutor(listaEsc.get(j).getAutor());
                i++;
                j++;
            } else if (listaEdi.get(i).getId() < listaEsc.get(j).getId()) {
                listaLib.add(listaEdi.get(i));
                System.out.println(listaLib.get(k));
                listaLib.get(k).setId(listaEdi.get(i).getId());
                listaLib.get(k).setTematica(listaEdi.get(i).getTematica());
                listaLib.get(k).setEditorial(listaEdi.get(i).getEditorial());
                listaLib.get(k).setFPublicacion(listaEdi.get(i).getFPublicacion());
                listaLib.get(k).setIdioma(listaEdi.get(i).getIdioma());
                listaLib.get(k).setTitulo("Sin Información");
                listaLib.get(k).setAutor("Sin Información");
                i++;
            } else if (listaEdi.get(i).getId() > listaEsc.get(j).getId()) {
                listaLib.add(listaEsc.get(j));
                System.out.println(listaLib.get(k));
                listaLib.get(k).setId(listaEsc.get(j).getId());
                listaLib.get(k).setTematica("Sin información");
                listaLib.get(k).setEditorial("Sin información");
                listaLib.get(k).setFPublicacion("Sin información");
                listaLib.get(k).setIdioma("Sin información");
                listaLib.get(k).setTitulo(listaEsc.get(j).getTitulo());
                listaLib.get(k).setAutor(listaEsc.get(j).getAutor());
               
                j++;
            }
            k++;
        }//While i/j
        System.out.println(i +" "+ j);
        while (i < listaEdi.size()) {
            System.out.println("lista editoriales");
            listaLib.add(listaEdi.get(i));
            listaLib.get(k).setId(listaEdi.get(i).getId());
            listaLib.get(k).setTematica(listaEdi.get(i).getTematica());
            listaLib.get(k).setEditorial(listaEdi.get(i).getEditorial());
            listaLib.get(k).setFPublicacion(listaEdi.get(i).getFPublicacion());
            listaLib.get(k).setIdioma(listaEdi.get(i).getIdioma());
            listaLib.get(k).setTitulo("Sin Información");
            listaLib.get(k).setAutor("Sin Información");

            i++;
            k++;
        }//While i
        while (j < listaEsc.size()) {
            System.out.println("Lista autores");
            listaLib.add(listaEsc.get(j));
            listaLib.get(k).setId(listaEsc.get(j).getId());
            listaLib.get(k).setTematica("Sin información");
            listaLib.get(k).setEditorial("Sin información");
            listaLib.get(k).setFPublicacion("Sin información");
            listaLib.get(k).setIdioma("Sin información");
            listaLib.get(k).setTitulo(listaEsc.get(j).getTitulo());
            listaLib.get(k).setAutor(listaEsc.get(j).getAutor());

            j++;
            k++;
        }//While j    
    }    
    private static void crearXMLActualizado(ArrayList<Libro> lista) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("Libreria");
        doc.appendChild(root);

        for (int i = 0; i < lista.size(); i++) {

            Element libro = doc.createElement("Libro");
            root.appendChild(libro);
            Attr id = doc.createAttribute("id");
            id.setNodeValue(String.valueOf(lista.get(i).getId()));
            libro.setAttributeNode(id);
            Attr idioma = doc.createAttribute("idioma");
            idioma.setNodeValue(lista.get(i).getIdioma());
            libro.setAttributeNode(idioma);          
            
            Element titulo = doc.createElement("Titulo");
            Text valor = doc.createTextNode(lista.get(i).getTitulo());
            titulo.appendChild(valor);
            libro.appendChild(titulo);

            Element autor = doc.createElement("Autor");
            valor = doc.createTextNode(lista.get(i).getAutor());
            autor.appendChild(valor);
            libro.appendChild(autor);

            Element tematica = doc.createElement("Tematica");
            valor = doc.createTextNode(lista.get(i).getTematica());
            tematica.appendChild(valor);
            libro.appendChild(tematica);

            Element editorial = doc.createElement("Editorial");
            valor = doc.createTextNode(lista.get(i).getEditorial());
            editorial.appendChild(valor);
            libro.appendChild(editorial);

            Element fPublicacion = doc.createElement("FPublicacion");
            valor = doc.createTextNode(String.valueOf(lista.get(i).getFPublicacion()));
            fPublicacion.appendChild(valor);
            libro.appendChild(fPublicacion);
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource ds = new DOMSource(doc);
        StreamResult sr = new StreamResult(new File("libreria.xml"));
        StreamResult sr1 = new StreamResult(System.out);
        transformer.transform(ds, sr);
        transformer.transform(ds, sr1);
    }
}
/*
createElement(nombre) createAtribute(nombre) createTextNode(cadena) 
appendChild(nodo) replaceChild(nodo,referencia) getElementsByTagName()
getAttribute(nombre) setAttribute(nombre,valor) getAttributeNode(nombre)
setAttributeNode(attr) removeAttribute(nombre)

 */
