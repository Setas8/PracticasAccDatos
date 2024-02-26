package POJOS;

public class Retenciones  implements java.io.Serializable {


     private String nombreEmpleado;
     private String nombreDepartamento;
     private int salarioBruto;
     private int salarioNeto;
     private int retencion;

    public Retenciones() {
    }

	
    public Retenciones(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    public Retenciones(String nombreEmpleado, String nombreDepartamento, int salarioBruto, int salarioNeto, int retencion) {
       this.nombreEmpleado = nombreEmpleado;
       this.nombreDepartamento = nombreDepartamento;
       this.salarioBruto = salarioBruto;
       this.salarioNeto = salarioNeto;
       this.retencion = retencion;
    }
   
    public String getNombreEmpleado() {
        return this.nombreEmpleado;
    }
    
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    public String getNombreDepartamento() {
        return this.nombreDepartamento;
    }
    
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    public int getSalarioBruto() {
        return this.salarioBruto;
    }
    
    public void setSalarioBruto(int salarioBruto) {
        this.salarioBruto = salarioBruto;
    }
    public int getSalarioNeto() {
        return this.salarioNeto;
    }
    
    public void setSalarioNeto(int salarioNeto) {
        this.salarioNeto = salarioNeto;
    }
    public int getRetencion() {
        return this.retencion;
    }
    
    public void setRetencion(int retencion) {
        this.retencion = retencion;
    }

    @Override
    public String toString() {
        return "Retenciones{" + "nombreEmpleado=" + nombreEmpleado + ", nombreDepartamento=" + nombreDepartamento + ", salarioBruto=" + salarioBruto + ", salarioNeto=" + salarioNeto + ", retencion=" + retencion + '}';
    }




}


