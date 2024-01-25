package pqt_almacen;

/**
 *
 * @author Diego Cuadrado
 */
public class Producto {
    
    private int     codProducto;
    private String  descripcion;
    private double  precioUnitario;
    private int     unidadesVendidas;

    public Producto(int codProducto, String descripcion, double precioUnitario, int unidadesVendidas) {
        this.codProducto = codProducto;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.unidadesVendidas = unidadesVendidas;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        
        this.codProducto = codProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    @Override
    public String toString() {
        return "Producto{" + "codProducto=" + codProducto + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario + ", unidadesVendidas=" + unidadesVendidas + '}';
    }
    
    
}
