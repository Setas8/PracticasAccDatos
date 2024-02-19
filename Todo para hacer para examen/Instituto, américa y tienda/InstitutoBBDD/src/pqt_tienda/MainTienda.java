package pqt_tienda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diego Cuadrado
 */
public class MainTienda {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/Tienda";
            String user = "root";
            String pswd = "";
            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            String crearTabla = "create table if not exists artfab("
                    + "nombreArticulo varchar(30) primary key,"
                    + "nombreFabricante varchar(30),"
                    + "precio double(11),"
                    + "iva int(3))";

            st.execute(crearTabla);

            ResultSet rs = st.executeQuery("select a.nombre, f.nombre, a.precio"
                    + " from articulos a, fabricantes f where a.clfab = f.clfab");
            
            Statement st1 = conn.createStatement();
            
            double precioNuevo = 0;
            
            while (rs.next()) {
                
                if (rs.getInt(3) < 200) {
                    precioNuevo = rs.getInt(3) + (rs.getInt(3) * 0.1);
                    st1.executeUpdate("insert into artfab values('" + rs.getString(1) + "','" + rs.getString(2) + "',round(" + precioNuevo + ",2)," + 10);
                }
                else if (rs.getInt(3) >= 200 && rs.getInt(3) < 500) {
                    precioNuevo = rs.getInt(3) + (rs.getInt(3) * 0.08);
                    st1.executeUpdate("insert into artfab values('" + rs.getString(1) + "','" + rs.getString(2) + "',round(" + precioNuevo + ",2)," + 8);
                }
                else if (rs.getInt(3) >= 500 && rs.getInt(3) < 700) {
                    precioNuevo = rs.getInt(3) + (rs.getInt(3) * 0.06);
                    st1.executeUpdate("insert into artfab values('" + rs.getString(1) + "','" + rs.getString(2) + "',round(" + precioNuevo + ",2)," + 6);
                }
                else{
                    st1.executeUpdate("insert into artfab values('" + rs.getString(1) + "','" + rs.getString(2) + "',round(" + rs.getInt(3) + ",2)," + 0);
                }
                               
            }
            
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
