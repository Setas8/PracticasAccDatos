package pojos;

public class Alumnos  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String apellido;
     private Integer nota;

    public Alumnos() {
    }

	
    public Alumnos(int id) {
        this.id = id;
    }
    public Alumnos(int id, String nombre, String apellido, Integer nota) {
       this.id = id;
       this.nombre = nombre;
       this.apellido = apellido;
       this.nota = nota;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Integer getNota() {
        return this.nota;
    }
    
    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumnos{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", nota=" + nota + '}';
    }




}


