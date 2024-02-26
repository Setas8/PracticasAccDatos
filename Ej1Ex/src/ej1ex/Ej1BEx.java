package ej1ex;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej1BEx {

    public static void main(String[] args) {
        try {
            File f = new File("ventas.dat");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/Comercio";
            String user = "root";
            String password = "";
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("select precio from productos where codprod=?");
            String cadena = "insert into Ventas values(?,?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(cadena);
            cadena = "update Ventas set vendido=vendido+?,ganancia=ganancia+? where codvend=?";
            PreparedStatement ps2 = con.prepareStatement(cadena);
            cadena = "select * from ventas where codvend=?";
            PreparedStatement ps3 = con.prepareStatement(cadena);

            System.out.println("ANTES DE ACTUALIZAR");
            MostrarTablaVentas(st);

            ResultSet rs, rs1;

            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            String cv;
            int cp, unidades;
            double precio;
            try {
                while (true) {
                    cv = dis.readUTF();
                    cp = dis.readInt();
                    unidades = dis.readInt();
                    ps3.setString(1, cv);
                    rs = ps3.executeQuery();
                    ps.setInt(1, cp);
                    rs1 = ps.executeQuery();
                    rs1.next();
                    precio = rs1.getDouble(1);
                    if (rs.next()) {
                        System.out.println("EL VENDEDOR EXISTE, SE VA ACTUALIZAR");
                        ps2.setInt(1, unidades);
                        ps2.setDouble(2, unidades * precio);
                        ps2.setString(3, cv);
                        ps2.executeUpdate();
                    } else {
                        System.out.println("EL VENDEDOR NO EXISTE, SE VA A INSERTAR");
                        ps1.setString(1, cv);
                        ps1.setInt(2, cp);
                        ps1.setInt(3, unidades);
                        ps1.setDouble(4, unidades * precio);
                        ps1.executeUpdate();
                    }
                }
            } catch (EOFException e) {
            }

            dis.close();
            fis.close();

            System.out.println("DESPUES DE ACTUALIZAR");
            MostrarTablaVentas(st);

            st.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void MostrarTablaVentas(Statement st) {
        try {
            ResultSet rs = st.executeQuery("select * from ventas");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3)
                        + " " + rs.getDouble(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
