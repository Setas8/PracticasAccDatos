package pqt_masActividades;

import java.io.Serializable;

/**
 *
 * @author Diego Cuadrado
 */
public class Coche implements Serializable {
    private String matricula;
    private String marca;
    private double tamanioDeposito;
    private String modelo;
    

    public Coche(String matricula, String marca, double tamanioDeposito, String modelo) {
        this.matricula = matricula;
        this.marca     = marca;
        this.modelo    = modelo;
        this.tamanioDeposito = tamanioDeposito;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getTamanioDeposito() {
        return tamanioDeposito;
    }

    public void setTamanioDeposito(double tamanioDeposito) {
        this.tamanioDeposito = tamanioDeposito;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        String datos = "";
        String matricula = String.format("%-20.20s",this.matricula);
        String marca     = String.format("%-20.20s",this.marca);
        String modelo    = String.format("%-20.20s",this.modelo);
        String tamanioDeposito = String.format("%4.4s",this.tamanioDeposito);
        datos = matricula + marca + modelo + tamanioDeposito;
        return datos;
    }
}