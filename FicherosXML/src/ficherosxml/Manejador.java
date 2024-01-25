package ficherosxml;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler{
   private ArrayList<Localidad> lista=new ArrayList<Localidad>();
   private Localidad l;
   private StringBuilder sb=new StringBuilder();

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length); 
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName){
           case "Continente":l.setContinente(sb.toString());
                             break;
           case "Pais":l.setPais(sb.toString());
                        break;
           case "Capital":l.setCapital(sb.toString());
                          break;     
           case "Habitantes":l.setHabitantes(Integer.parseInt(sb.toString()));
                             break;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       switch(qName){
           case "Localidad": l=new Localidad();
                             lista.add(l);
                             l.setNumero(Integer.parseInt(attributes.getValue("numero")));
                             break;
           case "Continente":
           case "Pais": 
           case "Capital":
           case "Habitantes":sb.delete(0, sb.length());
                             break;
       }
    }

    public ArrayList<Localidad> getLista() {
        return lista;
    }
}  
   
