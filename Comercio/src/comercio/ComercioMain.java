package comercio;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class ComercioMain {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/comercio";
            String user = "root";
            String pswd = "";

            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            System.out.println("- - - - - - -TABLA VENTAS ANTES - - - -");
            mostrarVentas(st);

            PreparedStatement ps1 = conn.prepareStatement("Seelect precio from productos where codprod = ?");

            String insert = "Insert into ventas values(?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(insert);

            String update = "update ventas set vendido = vendido+?, ganancia = ganancia+? where codvend = ?";
            PreparedStatement ps3 = conn.prepareStatement(update);

            String comprobar = "select * from ventas where codvend = ?";
            PreparedStatement ps4 = conn.prepareStatement(comprobar);

            ResultSet rs = st.executeQuery("Select * from Productos");

            File f = new File("ventas.dat");
            double[] precios = new double[9];
            while (rs.next()) {
                precios[rs.getInt(1) / 10 - 1] = rs.getDouble(2);

                FileInputStream fis = new FileInputStream(f);
                DataInputStream dis = new DataInputStream(fis);

                String cv = "";
                int cp, unidades;
                try {
                    while (true) {

                        cv = dis.readUTF();

                        cp = dis.readInt();
                        unidades = dis.readInt();
                        ps4.setString(1, cv);
                        rs = ps4.executeQuery();
                        if (rs.next()) {
                            System.out.println("El vendedor existe, se va a actualizar");
                            //ps3
                            ps3.setInt(1, unidades);
                            ps3.setDouble(2, unidades * (precios[cp / 10 - 1]));
                            ps3.setString(3, cv);
                            ps3.executeUpdate();
                        } else {
                            System.out.println("El vendedor no existe, se va a insertar");
                            //ps2
                            ps2.setString(1, cv);
                            ps2.setInt(2, cp);
                            ps2.setInt(3, unidades);
                            ps2.setDouble(4, unidades * (precios[cp / 10 - 1]));
                            ps2.executeUpdate();

                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }catch (EOFException ex) {
                    System.out.println("Fin de fichero");
                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
            System.out.println("- - - - -TABLA VENTAS DESPUÉS - - - -");
            mostrarVentas(st);

            st.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    private static void mostrarVentas(Statement st) throws SQLException {

        ResultSet rs = st.executeQuery("Select * from ventas");

        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getInt(2)
                        + " " + rs.getInt(3) + " " + rs.getDouble(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
