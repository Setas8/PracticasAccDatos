package pojos;

public class Cambios  implements java.io.Serializable {


     private int id;
     private String tipo;
     private Integer matricula;
     private Integer incremento;

    public Cambios() {
    }

	
    public Cambios(int id) {
        this.id = id;
    }
    public Cambios(int id, String tipo, Integer matricula, Integer incremento) {
       this.id = id;
       this.tipo = tipo;
       this.matricula = matricula;
       this.incremento = incremento;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Integer getMatricula() {
        return this.matricula;
    }
    
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    public Integer getIncremento() {
        return this.incremento;
    }
    
    public void setIncremento(Integer incremento) {
        this.incremento = incremento;
    }

    @Override
    public String toString() {
        return "Cambios{" + "id=" + id + ", tipo=" + tipo + ", matricula=" + matricula + ", incremento=" + incremento + '}';
    }




}


