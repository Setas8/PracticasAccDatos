package pqt_actividadesTexto;

/**
 *
 * @author Sandra Esteban
 */
public class Producto {
    private int codigo;
    private String nombre;
    private int unidades;
    private double precio;

    public Producto(int codigo, String nombre, int unidades, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return codigo + "," +nombre+","+unidades+","+ precio;
    }
    
    

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }
    public void setUnidades(int unidades) {
        if (unidades<0)
            this.unidades = 0;
        else
            this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        if (precio<0)
            this.precio=0;
        else
            this.precio = precio;
    }
}

