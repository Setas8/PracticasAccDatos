package pqt_ficherosXML;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Diego Cuadrado
 */
public class Manejador extends DefaultHandler {
    /*
    Definir ArrayList de tipo localidad
    Objeto Localidad
    StringBuilder
    3 métodos Rellenar los atributos
              Comenzar etiqueta
              Cerrar etiqueta
    */
    private ArrayList<Localidad> listaLocalidades = new ArrayList<>();   
    private Localidad l;   
    private Habitante h;  //Para el fichero paises1.xml
    private StringBuilder strBu = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Localidad":
                l = new Localidad();
                listaLocalidades.add(l);
                l.setNumero(Integer.parseInt(attributes.getValue("numero")));               
                break;
            case "Habitantes":
                h = new Habitante();
                break;
            case "Continente":
            case "Pais":
            case "Capital":
            case "mujeres":           
            case "hombres":  
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
            case "Continente":
                l.setContinente(strBu.toString());
                break;
            case "Pais":
                l.setPais(strBu.toString());
                break;
            case "Capital":
                l.setCapital(strBu.toString());
                break;
            case "mujeres":
                h.setMujeres(Integer.parseInt(strBu.toString()));
                break;
            case "hombres":
                h.setHombres(Integer.parseInt(strBu.toString()));
                break;
            case "Habitantes":
                l.setHabitantes(h); //Para el fichero paises.xml
                //l.setHabitantes(Integer.parseInt(strBu.toString())); //Para el fichero paises.xml
                break;         
         }
    }

    public ArrayList<Localidad> getListaLocalidades() {
        return listaLocalidades;
    }   
}
