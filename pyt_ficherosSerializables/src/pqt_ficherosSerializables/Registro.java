package pqt_ficherosSerializables;

import java.io.Serializable;

/**
 *
 * @author Diego Cuadrado
 */
public class Registro implements Serializable{
    
    private String nombre;
    private String apellidos;
    private int numTelefono;
    private String email;

    public Registro(String nombre, String apellidos, int numTelefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numTelefono = numTelefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(int numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + "\t\t" + apellidos + "\t\t" + numTelefono + "\t\t" + email;
    }
    
    
}
