package pqt_ficherosXML;

/**
 *
 * @author Diego Cuadrado
 */
//implements serializable para crear fichero serializable
public class Localidad {
    
    private int numero;
    private String continente;
    private String pais;
    private String capital;
    //private int habitantes; //Para el fichero paises.xml
    private Habitante habitantes;  //Para el fichero paises1.xml

    //Constructor vacío
    public Localidad() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    
    //Para el fichero paises.xml
    /*public int getHabitantes() {
    return habitantes;
    }
    
    public void setHabitantes(int habitantes) {
    this.habitantes = habitantes;
    }*/
    //Para el fichero paises1.xml
    public Habitante getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Habitante habitantes) {
        this.habitantes = habitantes;
    }

    @Override
    public String toString() {
        return numero + " " + continente + " " + pais + " " + capital + " " +
               habitantes;
    }         
   
}
