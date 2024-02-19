package pqt_EjerAlmacen;

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
public class EjerAlmacen {

    public static void main(String[] args) {

        try {

            File f = new File("datos.dat");

            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/almacen";
            String user = "root";
            String pswd = "";
          
           
            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            String cadena = "update productos set stock = stock-? where codproducto=?";
            PreparedStatement ps = conn.prepareStatement(cadena);

            String sel = "select sum(unidadesvendidas), codproducto from ventas group by codproducto";
            ResultSet rs = st.executeQuery(sel);

            while (rs.next()) {
                ps.setInt(1, rs.getInt(1));
                ps.setString(2, rs.getString(2));
                ps.executeUpdate();
            }
            cadena = "select * from productos order by codproducto";
            PreparedStatement ps1 = conn.prepareStatement(cadena);
            mostrarProductos(ps1);
            System.out.println("- - - - - - - - - - - - - - - -");

            //Borrado
            cadena = "delete from productos where codproducto = ?";
            PreparedStatement ps2 = conn.prepareStatement(cadena);

            //Alta
            cadena = "insert into productos values(?,?,?,?,?)";
            PreparedStatement ps3 = conn.prepareStatement(cadena);

            //Actualizado
            cadena = "update productos set preciounitario = preciounitario + preciounitario * ?/100 where codproducto = ?";
            //cadena = "update productos set preciounitario = ? where codproducto = ?";
            PreparedStatement ps4 = conn.prepareStatement(cadena);

            //Saber si el producto existe para insertar
            cadena = "select * from productos where codproducto = ?";
            PreparedStatement ps5 = conn.prepareStatement(cadena);

            String codigo = "";
            String nombre = "";
            String linea = "";
            int precio;
            int stock;
            int porcentaje;
            
            mostrarProductos(ps1);
            System.out.println("- - - - - - - - - - - - - - - -");
            try {

                while (true) {
                    String operacion = dis.readUTF();
                    if (operacion.equalsIgnoreCase("A")) {
                        codigo = dis.readUTF();
                        nombre = dis.readUTF();
                        linea = dis.readUTF();
                        precio = dis.readInt();
                        stock = dis.readInt();
                        System.out.println("Operación: " + operacion + " " + codigo + " "
                                + nombre + " " + linea + " " + precio + " "
                                + stock);
                        ps5.setString(1, codigo);
                        rs = ps5.executeQuery();
                        if (rs.next()) {                         
                            System.out.println("No se puede insertar porque ya existe");
                        } else {
                            ps3.setString(1, codigo);
                            ps3.setString(2, nombre);
                            ps3.setString(3, linea);
                            ps3.setInt(4, precio);
                            ps3.setInt(5, stock);
                            ps3.executeUpdate();
                            System.out.println("Insertado producto " + codigo);
                        }

                    } else if (operacion.equalsIgnoreCase("B")) {
                        codigo = dis.readUTF();
                        System.out.println("Operación: " + operacion + " " + codigo);
                        ps2.setString(1, codigo);
                        if (ps2.executeUpdate() == 0) {
                            System.out.println("No se a borrado ningún registro");
                        } else {
                            System.out.println("Borrado el producto con código " + codigo);
                        }
                    } else {
                        codigo = dis.readUTF();
                        porcentaje = dis.readInt();
                        System.out.println("Operación: " + operacion + " " + codigo + " " + porcentaje);

                        ps4.setInt(1, porcentaje);
                        ps4.setString(2, codigo);
                        if (ps4.executeUpdate() == 0) {
                            System.out.println("No se a actualizado ningún registro");
                        } else {
                            System.out.println("Actualizado el producto con código " + codigo);
                        }
                    }

                }

            } catch (EOFException ex) {
                System.out.println("Fin de fichero");
            }
            dis.close();
            fis.close();
            System.out.println("- - - - - - - - - - - - - - - -");
            mostrarProductos(ps1);
            st.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void mostrarProductos(PreparedStatement ps) {
        ResultSet rs;
        try {
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2)
                        + " " + rs.getString(3) + " " + rs.getInt(4)
                        + " " + rs.getInt(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
