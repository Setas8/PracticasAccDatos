package pqt_comparadores;

import java.time.LocalDate;

/**
 *
 * @author Diego Cuadrado
 */
public class Persona implements Comparable<Persona> {
    //Atributos   
    private String    nombre, apellidos;
    private int       peso;
    private double    altura;
    private LocalDate fechaNac;
    

    //Constructor
    public Persona(String nombre, String apellidos, int peso, double altura) {
        this.nombre    = nombre;
        this.apellidos = apellidos;
        this.peso      = peso;
        this.altura    = altura;
        this.fechaNac  = LocalDate.now();

    }
    public Persona(String nombre, String apellidos, int peso, double altura, LocalDate fecha) {
        
        this.nombre    = nombre;
        this.apellidos = apellidos;
        this.peso      = peso;
        this.altura    = altura;
        this.fechaNac  = fecha;

    }
    //Getter y Setter
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    //toString
    @Override
    public String toString() {
        return nombre + "  " + apellidos + "  " + peso + "  " + altura +
                "  " + fechaNac;
    }
    //Método abstracto implementado de Comparable
    @Override
    public int compareTo(Persona p) {
        return this.getApellidos().compareTo(p.getApellidos());
    }
}