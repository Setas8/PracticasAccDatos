package POJOS;

public class Arma  implements java.io.Serializable {


     private int codAr;
     private String nombre;
     private Integer dano;

    public Arma() {
    }

	
    public Arma(int codAr, String nombre) {
        this.codAr = codAr;
        this.nombre = nombre;
    }
    public Arma(int codAr, String nombre, Integer dano) {
       this.codAr = codAr;
       this.nombre = nombre;
       this.dano = dano;
    }
   
    public int getCodAr() {
        return this.codAr;
    }
    
    public void setCodAr(int codAr) {
        this.codAr = codAr;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getDano() {
        return this.dano;
    }
    
    public void setDano(Integer dano) {
        this.dano = dano;
    }

    @Override
    public String toString() {
        return "Arma{" + "codAr=" + codAr + ", nombre=" + nombre + ", dano=" + dano + '}';
    }




}


