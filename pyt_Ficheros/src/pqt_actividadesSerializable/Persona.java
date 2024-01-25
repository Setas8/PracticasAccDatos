package pqt_actividadesSerializable;

import java.io.Serializable;

/**
 *
 * @author Diego Cuadrado
 */
public class Persona implements Serializable {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int anyo;

    public Persona(String nombre, String apellido1, String apellido2, int anyo) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.anyo = anyo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
    
    @Override
    public String toString() {
        String datos = "";
        String nombre = String.format("%-20.20s",this.nombre);
        String apellido1 = String.format("%-20.20s",this.apellido1);
        String apellido2 = String.format("%-20.20s",this.apellido2);
        String anyo = String.format("%4.4s",this.anyo);
        datos = nombre + apellido1 + apellido2 + anyo;
        return datos;
        //return "Persona{" + "nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", fNacim=" + anyo + '}';
        
    }
}
