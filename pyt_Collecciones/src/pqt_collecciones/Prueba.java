package pqt_collecciones;

import java.util.ArrayList;

/**
 *
 * @author Diego Cuadrado
 */
public class Prueba {

    public static void main(String[] args) {
        
        Integer x = new Integer(34);
        Double y = new Double("3.58");
        int z = 61;
        Integer w = new Integer(z);
        Boolean bo = new Boolean("false");
        Character co = new Character('a');
        
        int a = x.intValue();
        double b = y.doubleValue();
        //boolean c = bo.booleanValue());
        char d = co.charValue();
        int i = Integer.parseInt("123");
        //double d = Double.parseDouble("34.89");
        //Integer x = Integer.valueOf("123");
        //Double y = Double.valueOf("34.89");
        
        //ArrayList<Integer> listaInt = ArrayList<Integer>;
        //ArrayList<Double> listaDouble = ArrayList<double>;
        //ArrayList<Char> listaChar = ArrayList<Char>;
        
        // Añadir un elemento. Creamos un objeto Integer y lo añadimos
        //Integer x = new Integer(34);
        //listaInt.add(x);
       // Leer un valor. Obtenemos un objeto Integer y lo pasamos a int.
        //x = listaInt.get(0);
        int num1 = x.intValue();
        
         // Añadir un elemento. Añadimos directamente un int y Java lo convierte en un objeto Integer
        //listaInt.add(34);       
         // Leer un valor. Obtenemos un objeto Integer yJava lo pasa a int para poderlo almacenar
        //int num2 = listaInt.get(0);
        
        
    }
    
}
