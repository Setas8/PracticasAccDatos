package pqt_EjerPersonal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diego Cuadrado
 */
public class EjerPersonal {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/Personal";
            String user = "root";
            String pswd = "";
            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            String cadena = "create table if not exists oficinaEmpleados("
                    + "id_emp  varchar(4) primary key,"
                    + "nom_emp varchar(25),"
                    + "nom_dep varchar(15),"
                    + "salario int(12),"
                    + "comision double(7,2))";
            
            st.execute(cadena);
            
            String consulta = "select e.emp_no, e.nombre, d.nombre, e.salario\n"
                    + "from empleado e, departamento d\n"
                    + "where e.dept_no = d.dept_no";
          
            ResultSet rs = st.executeQuery(consulta);
            
            cadena = "insert into oficinaEmpleados values(?,?,?,?,?)";       
            PreparedStatement ps = conn.prepareStatement(cadena);
            
            while(rs.next()){
                ps.setString(1, rs.getString(1));
                ps.setString(2, rs.getString(2));
                ps.setString(3, rs.getString(3));
                ps.setInt(4, rs.getInt(4));
                
                if (rs.getString(3).equalsIgnoreCase("contabilidad"))
                    ps.setDouble(5, rs.getInt(4)*0.1);//10
                else if (rs.getString(3).equalsIgnoreCase("investigacion"))
                    ps.setDouble(5, rs.getInt(4)*0.2);//20
                else if (rs.getString(3).equalsIgnoreCase("ventas"))
                    ps.setDouble(5, rs.getInt(4)*0.05);//5
                else if (rs.getString(3).equalsIgnoreCase("produccion"))
                    ps.setDouble(5, rs.getInt(4)*0.15);//15
                ps.executeUpdate();
                
            }
            String empleados = "select * from empleado";
            ResultSet rs2 = st.executeQuery(empleados);
            System.out.println("======TABLA EMPLEADOS=========");
            verTablaEmpleado(rs2);
            
            
            String oficnaempleados = "select * from oficinaempleados";           
            rs = st.executeQuery(oficnaempleados);
            String actualizar = "update empleado set salario = ? where emp_no = ?";
            PreparedStatement psActualizar = conn.prepareStatement(actualizar);
            
            while(rs.next()){
                //10%
                if (rs.getDouble(5) < 300) {
                    psActualizar.setInt(1, rs.getInt(4) + (int)(rs.getInt(4) * 0.1));
                    psActualizar.setString(2, rs.getString(1));                  
                }
                //5%
                else if ((rs.getDouble(5) >= 400) && (rs.getDouble(5) <= 600) ) {
                    psActualizar.setInt(1, rs.getInt(4) + (int)(rs.getInt(4) * 0.05));
                    psActualizar.setString(2, rs.getString(1));
                }
                //Se queda igual
                else {
                    psActualizar.setInt(1, rs.getInt(4));
                    psActualizar.setString(2, rs.getString(1));
                }
                psActualizar.executeUpdate();
            }
            
            
            System.out.println("======TABLA EMPLEADOS=========");
            rs2 = st.executeQuery(empleados);
            verTablaEmpleado(rs2);
            
            st.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void verTablaEmpleado(ResultSet rs) {
        
        try {
            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2)
                           + " " + rs.getInt(3) + " " + rs.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
