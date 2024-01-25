package pqt_actividadesTexto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio11_Lorena {
public static void main(String[] args) {
      Scanner teclado = new Scanner(System.in);
      try {
            File f = new File("inventario.txt");
            File f1 = new File("movimientos.txt");
            File f2 = new File("auxiliar.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            FileReader fr1 = new FileReader(f1);
            BufferedReader br1 = new BufferedReader(fr1);
            FileWriter fw = new FileWriter(f2);
            BufferedWriter bw = new BufferedWriter(fw);
            
            String inv = br.readLine();
            String mov = br1.readLine();
            String[] invs;
            String[] movs;
            
            String nom;
            double precio;
            int unidades=0;
            
            while(inv!=null && mov!=null){
               invs=inv.split(" ");
               movs=mov.split(" ");
               if(invs[0].compareTo(movs[0])<0){
                   bw.write(inv);
                   bw.newLine();
                   inv = br.readLine();
               }else if(invs[0].compareTo(movs[0])>0){
                    if(movs[1].compareTo("C")==0){
                      System.out.print("NOMBRE DEL PRODUCTO: "); 
                      nom=teclado.nextLine();
                      System.out.print("PRECIO DEL PRODUCTO: ");
                      precio=teclado.nextDouble();
                      bw.write(movs[0]+" "+nom+" "+movs[2]+" "+precio);
                      bw.newLine();
                      teclado.nextLine();
                    }else
                      System.out.println("Error en la operacion pues el producto "+movs[0]+" no existe");
                    mov = br1.readLine();
               } else{
                   unidades=Integer.parseInt(invs[2]);
                   while(mov!=null && invs[0].compareTo(movs[0])==0){
                     if(movs[1].compareTo("V")==0){ 
                       if(unidades<Integer.parseInt(movs[2]))  
                         System.out.println("No hay suficientes unidades ");
                       else
                         unidades-=Integer.parseInt(movs[2]); 
                     }else
                        unidades+= Integer.parseInt(movs[2]);
                     mov = br1.readLine();
                     if(mov!=null)
                        movs=mov.split(" ");
                   }
                   bw.write(invs[0]+" "+invs[1]+" "+unidades+" "+invs[3]);
                   bw.newLine();
                   inv = br.readLine();
              }
            }
            
            while(inv!=null){
              bw.write(inv);
              bw.newLine(); 
              inv = br.readLine();  
            }
            
            while(mov!=null){
              movs=mov.split(" ");
              if(movs[1].compareTo("C")==0){
                System.out.print("NOMBRE DEL PRODUCTO: "); 
                nom=teclado.nextLine();
                System.out.print("PRECIO DEL PRODUCTO: ");
                precio=teclado.nextDouble();
                bw.write(movs[0]+" "+nom+" "+movs[2]+" "+precio);
                bw.newLine();
                teclado.nextLine();
              }else
                System.out.println("Error en la operacion pues el producto "+ movs[0]+" no existe");
              mov = br1.readLine();
            }
            bw.close();
            fw.close();
            br1.close();
            fr1.close();
            br.close();
            fr.close();
            
            f.delete();
            f2.renameTo(f);
            
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            inv = br.readLine();
            while(inv!=null){
              System.out.println(inv);
              inv = br.readLine();
            }
           br.close();
           fr.close(); 
            
        } catch (IOException e) {
        }
    }
   
}
