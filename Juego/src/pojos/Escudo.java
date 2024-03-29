package pojos;
// Generated 19-feb-2024 11:55:57 by Hibernate Tools 4.3.1



/**
 * Escudo generated by hbm2java
 */
public class Escudo  implements java.io.Serializable {


     private int codEs;
     private String nombre;
     private Integer defensa;

    public Escudo() {
    }

	
    public Escudo(int codEs, String nombre) {
        this.codEs = codEs;
        this.nombre = nombre;
    }
    public Escudo(int codEs, String nombre, Integer defensa) {
       this.codEs = codEs;
       this.nombre = nombre;
       this.defensa = defensa;
    }
   
    public int getCodEs() {
        return this.codEs;
    }
    
    public void setCodEs(int codEs) {
        this.codEs = codEs;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getDefensa() {
        return this.defensa;
    }
    
    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }

    @Override
    public String toString() {
        return "Escudo{" + "codEs=" + codEs + ", nombre=" + nombre + ", defensa=" + defensa + '}';
    }




}


