package pqt_2;

/**
 *
 * @author Diego Cuadrado Martinez
 */
public class Clase {

    private String id;
    private int cod_articulo;
    private int cantidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(int codigo) {
        this.cod_articulo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", cod_articulo=" + cod_articulo + ", cantidad=" + cantidad + '}';
    }
}
