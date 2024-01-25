package pqt_almacen;

import java.time.LocalDate;

/**
 *
 * @author Diego Cuadrado
 */
public class Compra {
    
    private LocalDate fechaCompra;
    private String    dniCliente;
    private int       codigoProducto;
    private int       unidadesCompradas;
    private LocalDate fechaCaducidad;

    public Compra(LocalDate fechaCompra, String dniCliente, int codigoProducto, int unidadesCompradas, LocalDate fechaCaducidad) {
        this.fechaCompra = fechaCompra;
        this.dniCliente = dniCliente;
        this.codigoProducto = codigoProducto;
        this.unidadesCompradas = unidadesCompradas;
        this.fechaCaducidad = fechaCaducidad;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dni) {
        this.dniCliente = dni;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getUnidadesCompradas() {
        return unidadesCompradas;
    }

    public void setUnidadesCompradas(int unidadesCompradas) {
        this.unidadesCompradas = unidadesCompradas;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String toString() {
        return "Compra{" + "fechaCompra=" + fechaCompra + ", dniCliente=" + dniCliente + ", codigoProducto=" + codigoProducto + ", unidadesCompradas=" + unidadesCompradas + ", fechaCaducidad=" + fechaCaducidad + '}';
    }     
}
