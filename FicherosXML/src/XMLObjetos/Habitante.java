
package XMLObjetos;

public class Habitante {
   private int mujeres;
   private int hombres;

    public Habitante() {
    }

    public int getMujeres() {
        return mujeres;
    }

    public void setMujeres(int mujeres) {
        this.mujeres = mujeres;
    }

    public int getHombres() {
        return hombres;
    }

    public void setHombres(int hombres) {
        this.hombres = hombres;
    }

    @Override
    public String toString() {
        return "Habitante{" + "mujeres=" + mujeres + ", hombres=" + hombres + '}';
    }
   
 }
