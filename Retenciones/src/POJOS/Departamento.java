package POJOS;



import java.util.HashSet;
import java.util.Set;

public class Departamento  implements java.io.Serializable {


     private int deptNo;
     private String nombre;
     private String ubicacion;
     private Set<Empleado> empleados = new HashSet<Empleado>(0);

    public Departamento() {
    }

	
    public Departamento(int deptNo) {
        this.deptNo = deptNo;
    }
    public Departamento(int deptNo, String nombre, String ubicacion, Set<Empleado> empleados) {
       this.deptNo = deptNo;
       this.nombre = nombre;
       this.ubicacion = ubicacion;
       this.empleados = empleados;
    }
   
    public int getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }




}


