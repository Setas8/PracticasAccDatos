package personal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonalMain {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/personal";
            String user = "root";
            String pswd = "";
            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            ResultSet rs;

            String selectEmple = "Select * from empleados";

            st.executeQuery(selectEmple);
            System.out.println("---------Empleados---------");
            mostrarEmpleados(st);
            //Incrementar el salario con la gratificación.
            //La antigüedad se calculará mediante elaño de alta.
            String grat = "update empleados set salario = ?";
            PreparedStatement psGrat = conn.prepareStatement(grat);

            rs = st.executeQuery(selectEmple);
            final int ANIO_ACTUAL = 2024;
            int alta = 0;
            int salario = 0;
            int sumaGratificaciones = 0;
            int aniosTrabajados = ANIO_ACTUAL - alta;
            int numVeces = 0;
            int gratificacion = 0;

            while (rs.next()) {
                alta = rs.getInt("alta");
                salario = rs.getInt("salario");
                aniosTrabajados = ANIO_ACTUAL - alta;
                numVeces = (aniosTrabajados * 12) / 3;
                gratificacion = (int) (numVeces * 25);
                /*System.out.println(alta + " añosTrabajados "
                + aniosTrabajados  + " veces " + numVeces
                + " salario " + salario + " gratificacion "
                + gratificacion);*/
                if ((ANIO_ACTUAL - alta) <= 10) {
                    //empelados con menos de diez años                   
                    psGrat.setInt(1, (salario + gratificacion));
                    psGrat.executeUpdate();
                } else {
                    //empleados con más de 10 años
                    gratificacion = (int) (gratificacion + (gratificacion * 0.03));
                    psGrat.setInt(1, salario + (gratificacion * aniosTrabajados));
                    psGrat.executeUpdate();
                }
                sumaGratificaciones += gratificacion;
                aniosTrabajados = 0;
                numVeces = 0;
                gratificacion = 0;
            }

            System.out.println("---------Empleados modificados según gratificaciones---------");
            mostrarEmpleados(st);

            //Dar de baja aquellos empleados que lleven menos de 2 años.            
            String delete = "delete from empleados where emp_no like(?)";
            PreparedStatement psDelete = conn.prepareStatement(delete);
            rs = st.executeQuery(selectEmple);
            String nombre = "";
            while (rs.next()) {
                alta = rs.getInt("alta");
                aniosTrabajados = ANIO_ACTUAL - alta;
                if (aniosTrabajados < 2) {
                    nombre = rs.getString("emp_no");
                    psDelete.setString(1, nombre);
                    psDelete.executeUpdate();
                }
                aniosTrabajados = 0;
            }

            System.out.println("---------Empleados eliminados con menos 2 años"
                    + " de antigúedad---------");
            mostrarEmpleados(st);
            //Modificar el presupuesto de cada departamento añadiéndole la 
            //cantidad total de dinero que se necesita para las gratificaciones.

            String depar = "select * from departamentos";
            Statement stP = conn.createStatement();

            System.out.println("---------Departamentos-------------");
            mostrarDepartamentos(stP);

            System.out.println("Suma de gratificaciones " + sumaGratificaciones);

            String presu = "update departamentos set presupuesto = presupuesto + ?";
            PreparedStatement psPresu = conn.prepareStatement(presu);
            psPresu.setInt(1, sumaGratificaciones);

            ResultSet rsP = stP.executeQuery(depar);

            psPresu.executeUpdate();

            System.out.println("---------Departamentos   modificados-------------");
            mostrarDepartamentos(stP);

            //Mostrar los datos de un empleado.
            Scanner tcd = new Scanner(System.in);
            System.out.println("Numero del empelado");
            String numEmp = tcd.nextLine();

            String emple = "select * from empleados where emp_no like(?)";
            PreparedStatement psEmple = conn.prepareStatement(emple);
            psEmple.setString(1, numEmp);
            ResultSet rsEmp = psEmple.executeQuery();

            System.out.println("-----Datos empleado " + numEmp + "-----");
            if (rsEmp.next()) {
                System.out.println(rsEmp.getString("emp_no") + "     "
                        + rsEmp.getString("nombre") + "    "
                        + rsEmp.getInt("alta") + "   "
                        + rsEmp.getInt("salario") + "   "
                        + rsEmp.getInt("dept_no"));
            } else {
                System.out.println("No existe ese empleado");
            }

            //Mostrar todos los empleados de un departamento en concreto          
            System.out.println("Numero del departamento");
            int numDep = tcd.nextInt();
            tcd.nextLine();
            String empDep = "select * from empleados where dept_no = ?";
            PreparedStatement psEmpDep = conn.prepareStatement(empDep);
            psEmpDep.setInt(1, numDep);
            ResultSet rsDep = psEmpDep.executeQuery();
            System.out.println("-----Datos empleados del departamento " + numDep + "-----");

            if (!rsDep.next()) {
                System.out.println("No existe el departamento");
            } else {
                rsDep = psEmpDep.executeQuery();
                while (rsDep.next()) {

                    System.out.println(rsDep.getString("emp_no") + "     "
                            + rsDep.getString("nombre") + "    "
                            + rsDep.getInt("alta") + "   "
                            + rsDep.getInt("salario") + "   "
                            + rsDep.getInt("dept_no"));
                }
            }
            stP.close();
            st.close();
            conn.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    private static void mostrarEmpleados(Statement st) {

        System.out.println("NUM_EMP  NOMBRE   ALTA  SALARIO  NUM_DEP");
        try {
            String selectEmple = "Select * from empleados";
            ResultSet rs = st.executeQuery(selectEmple);

            while (rs.next()) {
                System.out.println(rs.getString("emp_no") + "     "
                        + rs.getString("nombre") + "    "
                        + rs.getInt("alta") + "   "
                        + rs.getInt("salario") + "   "
                        + rs.getInt("dept_no"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private static void mostrarDepartamentos(Statement st) {

        System.out.println("NUM_DEP  NOMBRE   UBICACIÓN  PRESUPUESTO");
        try {
            String selectDepar = "Select * from departamentos";
            ResultSet rs = st.executeQuery(selectDepar);

            while (rs.next()) {
                System.out.println(rs.getInt("dept_no") + "     "
                        + rs.getString("nombre") + "    "
                        + rs.getString("ubicacion") + "   "
                        + rs.getInt("presupuesto"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
