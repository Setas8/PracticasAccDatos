package pqt_alumnos;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Diego Cuadrado
 */
public class Manejador extends DefaultHandler{
    
    private ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    private Alumno a;
    private StringBuilder strBu = new StringBuilder();
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "alumno":
                a = new Alumno();
                listaAlumnos.add(a);                             
                break;
            case "nombre":
            case "nota1":
            case "nota2":
            case "proyecto":
            case "practica":            
                strBu.delete(0,strBu.length());
                break;         
         }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        strBu.append(ch, start, length); //Cada vez que encuentra una cadena se lo añade a la etiqueta
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "nombre":
                a.setNombre(strBu.toString());
                break;
            case "nota1":
                a.setNota1(Integer.parseInt(strBu.toString()));
                break;
            case "nota2":
                a.setNota2(Integer.parseInt(strBu.toString()));
                break;
            case "proyecto":
                a.setProyecto(Integer.parseInt(strBu.toString()));
                break;
            case "practica":
                a.setPractica(Integer.parseInt(strBu.toString()));
                break;       
         }
    }

    public ArrayList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    } 
}
