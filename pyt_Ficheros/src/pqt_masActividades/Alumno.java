package pqt_masActividades;

import java.io.Serializable;

/**
 *
 * @author Diego Cuadrado
 */
public class Alumno implements Serializable {
    
    private String nombre;
    private String apellido;
    transient private char sexo;
    private int edad;
    private int numSuspensos;
    private boolean residenciaFamiliar;
    private double ingresosFamiliares;

    public Alumno(String nombre, String apellido, char sexo, int edad, int numSuspensos, boolean residenciaFamiliar, double ingresosFamiliares) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.edad = edad;
        this.numSuspensos = numSuspensos;
        this.residenciaFamiliar = residenciaFamiliar;
        this.ingresosFamiliares = ingresosFamiliares;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumSuspensos() {
        return numSuspensos;
    }

    public void setNumSuspensos(int numSuspensos) {        
        this.numSuspensos = numSuspensos;
    }

    public boolean isResidenciaFamiliar() {
        return residenciaFamiliar;
    }

    public void setResidenciaFamiliar(boolean residenciaFamiliar) {
        this.residenciaFamiliar = residenciaFamiliar;
    }

    public double getIngresosFamiliares() {
        return ingresosFamiliares;
    }

    public void setIngresosFamiliares(double ingresosFamiliares) {
        this.ingresosFamiliares = ingresosFamiliares;
    }

    @Override
    public String toString() {
        char transie;
        if (sexo == 0) 
            transie = 'D';      
        else
            transie = sexo;
        
        String nom = String.format("%-20s", nombre);
        String ape = String.format("%-20s", apellido);
        String sex = String.format("%-8c", transie);
        String ed = String.format("%-8d", edad);
        String suspensos = String.format("%-10d", numSuspensos);
        String residencia = String.format("%-12b", residenciaFamiliar);
        String ingresos = String.format("%-8.2f", ingresosFamiliares);

        String cadena = nom + ape + sex + ed + suspensos + residencia + ingresos;
 
        return cadena;
    }
}
