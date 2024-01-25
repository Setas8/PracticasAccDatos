package pqt_masActividades;

import java.io.Serializable;

/**
 *
 * @author Diego Cuadrado
 */
public class Persona implements Serializable {
    private String nombre;
    private String apellidos;
    private int    edad;
    private int    numTelefono;
    private String mail;
    private String ciudadResi;
    private String nacionalidad;
    private String profesion; 

    public Persona(String nombre, String apellidos, int edad, int numTelefono, String mail, String ciudadResi, String nacionalidad, String profesion) {
        this.nombre       = nombre;
        this.apellidos    = apellidos;
        this.edad         = edad;
        this.numTelefono  = numTelefono;
        this.mail         = mail;
        this.ciudadResi   = ciudadResi;
        this.nacionalidad = nacionalidad;
        this.profesion    = profesion;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(int numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCiudadResi() {
        return ciudadResi;
    }

    public void setCiudadResi(String ciudadResi) {
        this.ciudadResi = ciudadResi;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    @Override
    public String toString() {
              
        String nom = String.format("%-20s", nombre);
        String ape = String.format("%-20s", apellidos);
        String ed  = String.format("%-8d", edad);
        String nuT = String.format("%-12d", numTelefono);
        String mai = String.format("%-20s", mail);
        String ciu = String.format("%-20s", ciudadResi);
        String nac = String.format("%-20s", nacionalidad);
        String pro = String.format("%-20s", profesion);

        String cadena = nom + ape + ed + nuT + mai + ciu + nac + pro;
 
        return cadena;
    }
    
    
}
