package pqt_america;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diego Cuadrado
 */
public class MainAmerica {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/America";
            String user = "root";
            String pswd = "";
            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            String crearTabla = "create table if not exists personasPaises(\n"
                    + "id int(11) primary key,"
                    + "nombre varchar(15),"
                    + "apellido varchar(15),"
                    + "edad tinyint(4),"
                    + "nombrePais varchar(15),"
                    + "tamanio varchar(15))";
            st.execute(crearTabla);

            ResultSet rs = st.executeQuery("select p.id, p.nombre, p.apellido,"
                    + " p.edad, pa.nombre, pa.tamanio from persona p, pais pa"
                    + " where p.pais = pa.id");
            
            Statement st1 = conn.createStatement();
            
            /* while (rs.next()) {
            st1.executeUpdate("insert into personasPaises values("
            + rs.getInt(1) + ",'" + rs.getString(2) + "','"
            + rs.getString(3) + "'," + rs.getInt(4) + ",'"
            + rs.getString(5) + "','" + rs.getString(6) + "')");
            
            }*/
           
            
           Statement st2 = conn.createStatement();
                 
            
            st2.executeUpdate("update personaspaises set edad = edad + 1 where nombrepais = 'costa rica'");
           
            rs = st.executeQuery("Select * from personaspaises");
            
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2)
                           + " " + rs.getString(3) + " " + rs.getInt(4)
                           + " " + rs.getString(5) + " " + rs.getString(6));
            }
            
            st2.close();
            st1.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
