package pqt_accesoBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diego Cuadrado
 */
public class Prueba {

    public static void main(String[] args) {
        
        //Excepciones classforname, mysql
        try {
            //Clase del conector mysql ---arrancar el driver
            Class.forName("com.mysql.jdbc.Driver");
            //Conectarse a la base de datos -> url (nombre de la bbdd "jdbc:mysql://localhost/Instituto" //No haría falta use tabla, se conecta directamente con la bbdd
            String url = "jdbc:mysql://localhost/";
            String user = "root";
            String pswd = "";
            Connection con = DriverManager.getConnection(url, user, pswd);
            
            
            Statement st = con.createStatement();
            //Execute crear, borrar -> bbdd, tablas
            //st.execute("create database Prueba"); //Sólo se ejecuta una vez si no está creada
            st.execute("use prueba");
            /*            String cadena = "create table p1("
            + "mat varchar(5) primary key,"
            + "nota int);";
            st.execute(cadena); */       
            //st.execute("drop table p1");
            //ExecuteUpdate insertar, borrar, modificar datos
            /*for (int i = 0; i < 10; i++) {
            st.executeUpdate("insert into p1 values('"+i+"'," +(int)((Math.random()*10)+1)+")");
            }*/
            //ExecuteQuery consultar
            ResultSet rs = st.executeQuery("select * from p1");
            
            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getInt(2));
            }
                       
            rs = st.executeQuery("select * from p1 where nota >= 5");
            
            Statement st1 = con.createStatement();
            
            String cadena = "create table if not exists aprobados("
            + "mat varchar(5) primary key,"
            + "nota int);";
            st1.execute(cadena);
            
            /*while(rs.next()){
            st1.executeUpdate("insert into aprobados values('"+rs.getString(1) + "'," + rs.getInt(2) + ")");
            }*/
            
            System.out.println("- - - - - - - - - -");
            rs = st.executeQuery("select * from aprobados");
            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getInt(2));
            }
                       
            st.executeUpdate("delete from p1 where nota < 5");
            
                     
            st.close();
            st1.close();
            con.close();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        conectarBBDD();
        desconectarBBDD();
        
    }
    
    public static void conectarBBDD() {
        
    }
    
    public static void desconectarBBDD() {
        
    }
}
