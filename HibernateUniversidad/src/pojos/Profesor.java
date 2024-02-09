package pojos;

public class Profesor  implements java.io.Serializable {


     private int id;
     private Especialidad especialidad;
     private String nombre;
     private String apellidos;

    public Profesor() {
    }

	
    public Profesor(int id) {
        this.id = id;
    }
    public Profesor(int id, Especialidad especialidad, String nombre, String apellidos) {
       this.id = id;
       this.especialidad = especialidad;
       this.nombre = nombre;
       this.apellidos = apellidos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Especialidad getEspecialidad() {
        return this.especialidad;
    }
    
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

     public String mostrar() {
        
        return this.especialidad.toString();
    }
    @Override
    public String toString() {
        return "Profesor{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + '}';
    }

   


}


