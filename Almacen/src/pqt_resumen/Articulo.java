package pqt_resumen;

/**
 *
 * @author Diego Cuadrado
 */
public class Articulo {
    
    private String componente;
    private String numSerie;
    private int unidades;
    private Lote lote;

    public Articulo() {
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return "Articulo{" + "componente=" + componente + ", numSerie=" + numSerie + ", unidades=" + unidades + ", lote=" + lote.toString() + '}';
    }    
}
