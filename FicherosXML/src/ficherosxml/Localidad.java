
package ficherosxml;

public class Localidad {
    private int numero;
    private String continente;
    private String pais;
    private String capital;
    private int habitantes;

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

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    @Override
    public String toString() {
        return "Localidad{" + "numero=" + numero + ", continente=" + continente + ", pais=" + pais + ", capital=" + capital + ", habitantes=" + habitantes + '}';
    }
    
}
