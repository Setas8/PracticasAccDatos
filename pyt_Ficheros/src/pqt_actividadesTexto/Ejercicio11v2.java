package pqt_actividadesTexto;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Sandra Esteban
 */
public class Ejercicio11v2 {
    
    public static Scanner sc = new Scanner(System.in);
    public static HashMap<Integer, Producto> mapaProductos = new HashMap<>();
    
    public static void main(String[] args) {
        //Leer el fichero e insertar datos al Hash Map
        listarProductos();
        
        //Actualizar los datos con el fichero de movimientos
        actualizarDatos();
        escribirEnFichero();
    }
    public static void listarProductos(){
        String linea;
        String[] datos;
        
        try{
            BufferedReader lector = new BufferedReader(new FileReader("inventario.txt"));
            
            while((linea=lector.readLine())!=null){
                datos=linea.split(",");
                if(datos!=null){
                    Producto producto= new Producto(Integer.parseInt(datos[0].trim()),datos[1],Integer.parseInt(datos[2].trim()),Double.parseDouble(datos[3].trim()));
                    mapaProductos.put((Integer.parseInt((datos[0]).trim())), producto);
                }
            }
            
            lector.close();
            
        }catch(IOException e){
            System.err.println("No se ha podido leer el fichero.");
        }
    }
    
    public static void actualizarDatos(){
        String linea;
        String[] movimiento;
        
        try(BufferedReader lector = new BufferedReader(new FileReader("movimientos.txt"))){
            
            while((linea=lector.readLine())!=null){
                movimiento=linea.split(",");
                
                int codigo = Integer.parseInt(movimiento[0].trim());
                String operacion = movimiento[1].trim();
                int unidades = Integer.parseInt(movimiento[2].trim());
                
                if(mapaProductos.containsKey(codigo)){
                    if(operacion.equalsIgnoreCase("V")){
                        int total = mapaProductos.get(codigo).getUnidades() - unidades;
                        mapaProductos.get(codigo).setUnidades(total);  
                    }
                    else if(operacion.equalsIgnoreCase("D")||operacion.equalsIgnoreCase("C")){
                        int total = mapaProductos.get(codigo).getUnidades() + unidades;
                        mapaProductos.get(codigo).setUnidades(total);
                    }  
                }
                else{
                    if(operacion.equalsIgnoreCase("C")){
                        registrarProducto(codigo,unidades);
                    }
                }
            }
            
            lector.close();
            
        }catch(IOException e){
            System.err.println("No se han podido actualizar los datos");
        }
    }
    
    private static void registrarProducto(int codigo, int unidades){
        System.out.println("Indique los datos del producto con el codigo: " + codigo);
        System.out.print("Nombre: ");
        String nombre=sc.nextLine();
        System.out.print("Precio: ");
        double precio=sc.nextDouble();sc.nextLine();
        
        Producto nuevo = new Producto(codigo, nombre, unidades, precio);
        mapaProductos.put(codigo, nuevo);
    }
    
    private static void escribirEnFichero(){
        File inventario = new File("inventario.txt");
        File auxiliar = new File("auxiliar.txt");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(auxiliar))) {
            ArrayList<Integer> listaClaves = new ArrayList<>();
            mapaProductos.forEach((k,v) -> listaClaves.add(k));
            /* de otra manera
               for (Map.Entry e: mapaProductos.entrySet()){
                  listaClaves.add((Integer)e.getKey()); 
              }
            */
            Collections.sort(listaClaves);
            listaClaves.forEach((e) -> {
                try {
                    escritor.write(mapaProductos.get(e).toString());
                    escritor.newLine(); // agregar salto de línea después de cada registro
                } catch (IOException ex) {
                    System.err.println("No se han podido escribir los datos");
                }
            });
            //Cerrar el escritura
            escritor.close();
            //borrar el fichero inventario
            inventario.delete();
            //renombrar el fichero auxiliar a inventario
            auxiliar.renameTo(inventario);
            
        } catch (FileNotFoundException ex) {
            System.err.println("No se ha encontrado el archivo de inventario");
        } catch (IOException ex) {
            System.err.println("No se han podido escribir los datos");
        }
    }
}