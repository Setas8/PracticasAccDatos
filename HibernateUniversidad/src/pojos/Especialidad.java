package pojos;
// Generated 02-feb-2024 13:03:41 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Especialidad generated by hbm2java
 */
public class Especialidad  implements java.io.Serializable {


     private int cod;
     private String nombre;
     private Set<Profesor> profesors = new HashSet<Profesor>(0);

    public Especialidad() {
    }

	
    public Especialidad(int cod) {
        this.cod = cod;
    }
    public Especialidad(int cod, String nombre, Set<Profesor> profesors) {
       this.cod = cod;
       this.nombre = nombre;
       this.profesors = profesors;
    }
   
    public int getCod() {
        return this.cod;
    }
    
    public void setCod(int cod) {
        this.cod = cod;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Profesor> getProfesors() {
        return this.profesors;
    }
    
    public void setProfesors(Set<Profesor> profesors) {
        this.profesors = profesors;
    }

    @Override
    public String toString() {
        return "Especialidad{" + "cod=" + cod + ", nombre=" + nombre + '}';
    }


    

}


