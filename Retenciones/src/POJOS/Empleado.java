package POJOS;

public class Empleado  implements java.io.Serializable {


     private String empNo;
     private Departamento departamento;
     private String nombre;
     private int salario;

    public Empleado() {
    }

	
    public Empleado(String empNo, Departamento departamento) {
        this.empNo = empNo;
        this.departamento = departamento;
    }
    public Empleado(String empNo, Departamento departamento, String nombre, int salario) {
       this.empNo = empNo;
       this.departamento = departamento;
       this.nombre = nombre;
       this.salario = salario;
    }
   
    public String getEmpNo() {
        return this.empNo;
    }
    
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    public Departamento getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getSalario() {
        return this.salario;
    }
    
    public void setSalario(int salario) {
        this.salario = salario;
    }




}


