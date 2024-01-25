package pqt_almacen;

import java.io.File;

/**
 *
 * @author Diego Cuadrado
 */
public class GestionSupermercado {
    
    static File ficheroClientes;
    static File ficheroProductos;
    static File ficheroCompras;
    static int[][] arrayProductos;
    
    public static void main(String[] args) {
        
        crearFicheros();
        
        
        
    }
    public static void crearFicheros() {
        
        ficheroClientes  = new File("CLIENTES.DAT");
        ficheroProductos = new File("PRODUCTOS.DAT");
        ficheroCompras   = new File("COMPRAS.DAT");
    }
    public static void almacenarProductos(){
        
        arrayProductos = new int[10][25];
        for (int i = 0; i < arrayProductos.length; i++) {
            
        }
    }
    public static void obtenerRelacionCompras(){
        
    }
    public static void visualizarArrayProductos(){
        
    }
    public static void informeVentasCliente() {
        
    }
}
