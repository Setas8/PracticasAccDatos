package pqt_empresaXML;

/**
 *
 * @author Diego Cuadrado
 */
public class Operacion {
    
    private String id;
    private String indice;
    private String nombre;
    private String simbolo;
    private float precio;

    public Operacion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + ", indice=" + indice + ", nombre=" + nombre + ", simbolo=" + simbolo + ", precio=" + precio + '}';
    }
    
    
}
