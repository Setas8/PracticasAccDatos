package pqt_collecciones;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Diego Cuadrado
 */
public class ExpresionesRegulares {

    public static void main(String[] args) {
               
        Scanner tcd = new Scanner(System.in);
        
        //Comprobar que el número es un entero
        // Pide palabras hasta que el texto introducido cumpla con el patrón
        String dato = null;
        Matcher comparaFormato = null;
        // Genera la expresión regular para enteros
        Pattern formatoInt = Pattern.compile("-?[0-9]{1,9}"); //-?\\d{1,9}       
                                           //[0-9]{1,9} eneros y positivos
        do {
            System.out.println("Introduce un entero: ");
            dato = tcd.next();
            comparaFormato = formatoInt.matcher(dato);
        } while (!comparaFormato.matches());
        
        // Convierte el texto a un int
        int numero = Integer.parseInt(dato);
        System.out.println(numero + " es un entero");
        
        
        //fecha
        String regexp = "\\d{1,2}/\\d{1,2}/\\d{4}";
        // Lo siguiente devuelve true
        System.out.println(Pattern.matches(regexp, "11/12/2014"));
        System.out.println(Pattern.matches(regexp, "1/12/2014"));
        System.out.println(Pattern.matches(regexp, "11/2/2014"));
        // Los siguientes devuelven false
        System.out.println(Pattern.matches(regexp, "11/12/14"));  // El año no tiene cuatro cifras
        System.out.println(Pattern.matches(regexp, "11//2014"));  // el mes no tiene una o dos cifras
        System.out.println(Pattern.matches(regexp, "11/12/14perico"));  // Sobra "perico"
    
        //DNI
        String dniRegexp = "\\d{8}[A-HJ-NP-TV-Z]";
        // Lo siguiente devuelve true
        System.out.println(Pattern.matches(dniRegexp, "01234567C"));
        // Lo siguiente devuelve faslse
        System.out.println(Pattern.matches(dniRegexp, "01234567U")); // La U no es válida
        System.out.println(Pattern.matches(dniRegexp, "0123567X")); // No tiene 8 cifras
    
        //Mail
        String emailRegexp = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
        // Lo siguiente devuelve true
        System.out.println(Pattern.matches(emailRegexp, "a@b.com"));
        System.out.println(Pattern.matches(emailRegexp, "+++@+++.com"));
        // Lo siguiente devuelve faslse
        System.out.println(Pattern.matches(emailRegexp, "@b.com")); // Falta el nombre
        System.out.println(Pattern.matches(emailRegexp, "a@b.c")); // El dominio final debe tener al menos dos letras
    
    
    }
    
    
    
    
}
