package pqt_collecciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Diego Cuadrado
 */
public class OperadoresAdicionales {
  
    public static void main(String[] args) {
        
        ArrayList <Integer> listado = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            listado.add(i);
        }
        
        //Desordenar una lista.
        //Desordena una colección, este método no está disponible para arrays.	
        Collections.shuffle(listado);
        //Rellenar una listado.	
        //Rellena una colección copiando el mismo valor en todos los elementos
        //de la colección. Útil para reiniciar una colección.	
        Collections.fill(listado, 0);
        //Dar la vuelta.	
        //Da la vuelta a una listado, poniéndola en orden inverso al que tiene.	
        Collections.reverse(listado);
        //Ordenar una listado	
        //Ordena la listado en orden ascente según el orden natural de los
        //elementos	
   //////////////////////Collections.sort(listado, elemento);
        //Búsqueda binaria.	
        //Permite realizar búsquedas rápidas en un una colección ordenada.
        //Es necesario que la colección esté ordenada, si no lo está, 
        //la búsqueda no tendrá éxito.	
     //////////////Collections.binarySearch(listado, elemento);
        //Convertir un array a listado.	
        //Permite rápidamente convertir un array a una listado de elementos,
        //extremadamente útil. No se especifica el tipo de listado retornado 
        //(no es ArrayList ni LinkedList), solo se especifica que retorna una
        //listado que implementa la interfaz java.util.List.	
        List lista = Arrays.asList(listado);
        //Si el tipo de dato almacenado en el array es conocido
        //(Integer por ejemplo), debemos especificar el tipo de objeto de 
        //la listado:

   ////////////List<Integer> lista = Arrays.asList(array);
    }
    
}
