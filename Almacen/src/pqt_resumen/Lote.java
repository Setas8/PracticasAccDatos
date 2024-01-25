package pqt_resumen;

/**
 *
 * @author Diego Cuadrado
 */
public class Lote {
    
    private int numCajas;
    private String peso;

    public Lote() {
    }

    public int getNumCajas() {
        return numCajas;
    }

    public void setNumCajas(int numCajas) {
        this.numCajas = numCajas;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Lote{" + "numCajas=" + numCajas + ", peso=" + peso + '}';
    }
    
    
    
}
