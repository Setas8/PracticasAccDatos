package pqt_notasAlumnos;

/**
 *
 * @author Diego Cuadrado
 */
public class Alumno {
    
    private String nombre;
    private int nota1;
    private int nota2;
    private int proyecto;
    private int practica;

    public Alumno() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getProyecto() {
        return proyecto;
    }

    public void setProyecto(int proyecto) {
        this.proyecto = proyecto;
    }

    public int getPractica() {
        return practica;
    }

    public void setPractica(int práctica) {
        this.practica = práctica;
    }

    @Override
    public String toString() {
        return nombre + nota1 + nota2 + proyecto + practica;
    }
    
    
}
