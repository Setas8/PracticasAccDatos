
package Hoja3;

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

public class AfinidadSentenciasPreparadas {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
        File f=new File("datos.bin");
        FileInputStream fis=new FileInputStream(f);
        DataInputStream dis=new DataInputStream(fis);
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost/Relaciones1";
        String user="root";
        String password="";
        
        Connection con=DriverManager.getConnection(url, user, password);
        
        PreparedStatement ps=con.prepareStatement("insert into Emparejamientos values(?,?,?)");
        PreparedStatement ps1=con.prepareStatement("select * from mujeres where codigo=?");
        PreparedStatement ps2=con.prepareStatement("select * from hombres where codigo=?");
        PreparedStatement ps3=con.prepareStatement("select * from emparejamientos");
        PreparedStatement ps4=con.prepareStatement("select * from hombres");
        PreparedStatement ps5=con.prepareStatement("select * from mujeres");
        PreparedStatement ps6=con.prepareStatement("delete from emparejamientos where grado>=? and grado<?");
        PreparedStatement ps7=con.prepareStatement("delete from emparejamientos where codigoH=? and codigoM=?");
        PreparedStatement ps8=con.prepareStatement("update hombres set v1=?,v2=?,v3=?,v4=?,v5=? where codigo=?");
        PreparedStatement ps9=con.prepareStatement("update mujeres set v1=?,v2=?,v3=?,v4=?,v5=? where codigo=?");
        PreparedStatement ps10=con.prepareStatement("delete from hombres where codigo=?");
        PreparedStatement ps11=con.prepareStatement("delete from mujeres where codigo=?");
        
          int cm,ch;
        try{
           while(true){
              cm=dis.readInt();
              ch=dis.readInt();
              ps.setInt(1,ch);
              ps.setInt(2,cm);
              double valor=CalculoAfinidad(ps1,ps2,cm,ch);
              ps.setDouble(3,valor);
              ps.executeUpdate();
           } 
        }catch(EOFException e){}
        
        dis.close();
        fis.close();
        
        System.out.println("EMPAREJAMIENTOS");
        ResultSet rs=ps3.executeQuery();
        while(rs.next()){
             System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getDouble(3));
        }
         System.out.println("--------------------------------------------------------------------------");
        
        
       System.out.println("ANTES DE ACTUALIZAR");
       rs=ps4.executeQuery();
       while(rs.next()){
             System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDouble(6)+" "+rs.getDouble(7));
       }
       System.out.println("--------------------------------------------------------------------------");
        
       rs=ps5.executeQuery();
       while(rs.next()){
             System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDouble(6)+" "+rs.getDouble(7));
        } 
      double v1=0.5,v2=0.8;
      
      ps6.setDouble(1, v1);
      ps6.setDouble(2, v2);
      ps6.executeUpdate();
      
      rs=ps3.executeQuery();
      double v3,v4,v5;
      while(rs.next()){
        if(rs.getDouble(3)>=0.8){
          ps10.setInt(1, rs.getInt(1));
          ps11.setInt(1,rs.getInt(2));
          ps10.executeUpdate();
          ps11.executeUpdate();
       }else{
          ps7.setInt(1,rs.getInt(1));
          ps7.setInt(2,rs.getInt(2));
          ps7.executeUpdate(); 
          ps1.setInt(1,rs.getInt(2));
          ResultSet rs1=ps1.executeQuery();
          rs1.next();
          v1=rs1.getDouble(3)+0.1;
          if(v1>1)
            v1=1;
          v2=rs1.getDouble(4)+0.1;
          if(v2>1)
            v2=1;
          v3=rs1.getDouble(5)+0.1;
          if(v3>1)
            v3=1;
          v4=rs1.getDouble(6)+0.1;
          if(v4>1)
            v4=1;
          v5=rs1.getDouble(7)+0.1;
          if(v5>1)
            v5=1;
          ps9.setDouble(1,v1);
          ps9.setDouble(2,v2);
          ps9.setDouble(3,v3);
          ps9.setDouble(4,v4);
          ps9.setDouble(5,v5);
          ps9.setInt(6, rs.getInt(2));
          ps9.executeUpdate();
          
          ps2.setInt(1,rs.getInt(1));
          ResultSet rs2=ps2.executeQuery();
          rs2.next();
          v1=rs2.getDouble(3)+0.1;
          if(v1>1)
            v1=1;
          v2=rs2.getDouble(4)+0.1;
          if(v2>1)
            v2=1;
          v3=rs2.getDouble(5)+0.1;
          if(v3>1)
            v3=1;
          v4=rs2.getDouble(6)+0.1;
          if(v4>1)
            v4=1;
          v5=rs2.getDouble(7)+0.1;
          if(v5>1)
            v5=1;
          
          ps8.setDouble(1,v1);
          ps8.setDouble(2,v2);
          ps8.setDouble(3,v3);
          ps8.setDouble(4,v4);
          ps8.setDouble(5,v5);
          ps8.setInt(6, rs.getInt(1));
          ps8.executeUpdate();
      }
     }
      
      System.out.println("\nDESPUES DE ACTUALIZAR");
               
      rs=ps4.executeQuery();
      while(rs.next()){
        System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDouble(6)+" "+rs.getDouble(7));
      }
      System.out.println("--------------------------------------------------------------------------");
               
      rs=ps5.executeQuery();
      while(rs.next()){
         System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDouble(6)+" "+rs.getDouble(7));
      }
               
      System.out.println("EMPAREJAMIENTOS");
      rs=ps3.executeQuery();
      while(rs.next()){
         System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getDouble(3));
      }
      
      ps.close();
      ps1.close();
      ps2.close();
      ps3.close();
      ps4.close();
      ps5.close();
      ps6.close();
      ps7.close();
      ps8.close();
      ps9.close();
      ps10.close();
      ps11.close();
    }
    
    static double CalculoAfinidad(PreparedStatement p1, PreparedStatement p2,int cm,int ch) throws SQLException{
        double afinidad=0;
        double dm=0,dh=0;
        p1.setInt(1, cm);
        p2.setInt(1, ch);
        ResultSet rs1=p1.executeQuery();
        ResultSet rs2=p2.executeQuery();
        rs1.next();
        rs2.next();
        for(int i=3;i<=7;i++){
          afinidad=afinidad+rs1.getDouble(i)*rs2.getDouble(i);
          dm=dm+Math.pow(rs1.getDouble(i),2);
          dh=dh+Math.pow(rs2.getDouble(i),2);
        }
        afinidad=afinidad/Math.sqrt(dm*dh);
        
       // p1.close();
      //  p2.close();
        return afinidad;
    }      
    
}
