package pqt_institutoBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diego Cuadrado
 */
public class MainInstituto {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/Instituto";
            String user = "root";
            String pswd = "";
            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            String crear = "create table if not exists notasfinales ("
                    + "mat varchar(10),"
                    + "cod int,"
                    + "notamedia double,"
                    + "constraint primary key (mat,cod))";
            st.execute(crear);

            ResultSet rs = st.executeQuery("select * from notas");
            Statement st1 = conn.createStatement();
            /*
            while (rs.next()) {
            double media = (double) (rs.getInt(3) + rs.getInt(4) + rs.getInt(5)) / 3;
            st1.executeUpdate("insert into notasfinales values(" + "'" + rs.getString(1)
            + "'," + rs.getInt(2) + ",round(" + media + ",2))");
            }*/

            rs = st.executeQuery("select al.apel_nom,  asi.nombre, n.nota1,"
                    + " n.nota2, n.nota3, nf.notamedia\n"
                    + "from alumnos al\n"
                    + "join notas n\n"
                    + "on al.mat = n.mat\n"
                    + "join asignaturas asi\n"
                    + "on asi.cod = n.cod\n"
                    + "join notasfinales nf\n"
                    + "on al.mat = nf.mat and n.cod = nf.cod");
            
            System.out.println("Nombre Alumno\t\t\tNombre Asignatura\t\tNota 1"
                    + "\tNota 2\tNota 3\tNota Media");
            System.out.println("-----------------------------------------------"
                    + "--------------------------------------------------");
            while(rs.next()){           
                System.out.printf("%-31s%-26s%8d%8d%8d%12.2f\n", rs.getString(1),
                        rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getDouble(6));
                
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
