package pqt_ejercicios_varios;

import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class PiramideParesAsteriscos {

    public static void main(String[] args) {
        
        Scanner tcd = new Scanner(System.in);
        
        System.out.print("Introduzca un numero: ");
        int limite = tcd.nextInt(); tcd.nextLine();
        
        for(int i = 1; i <= limite; i++){
            for(int j = 1; j <= i; j++){                       
                if (j % 2==0){
                    for (int k = 1; k <= j; k++)
                        System.out.print("*");
                    }else 
                        System.out.print(j);                                                  
            }
        System.out.println(" ");
        }
        tcd.close();
    }
}