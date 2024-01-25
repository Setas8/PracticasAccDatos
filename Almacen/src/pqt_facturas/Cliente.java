package pqt_facturas;

/**
 *
 * @author Diego Cuadrado
 */
public class Cliente {
    
    private String nombre;
    private String nif;
    private String articulo;
    private int unidades;

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }
    
    
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", nif=" + nif + ", articulo=" + articulo + ", unidades=" + unidades + '}';
    }
    
    
}
