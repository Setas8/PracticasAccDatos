
package geografia;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import POJOS.Localidades;
import java.util.List;
import org.hibernate.Query;

public class Geografia {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        SessionFactory sf=HibernateUtil.sessionFactory();
        Session sesion=sf.openSession();
        Transaction t=sesion.beginTransaction();
        char opcion=Menu(teclado);
        
        while(opcion!='f'){
        
           switch(opcion){
               case 'a': AñadirLocalidad(teclado,sesion);
                         break;
               case 'b': MostarLocalidad(teclado,sesion);
                         break;
               case 'c': ActualizarCenso(teclado,sesion);
                         break;
               case 'd': EliminarLocalidad(teclado,sesion);
                         break;
               case 'e': Mostrar(sesion);
                         break;
               default:System.out.println("Opcion Incorrecta");
                       break;
           }
           opcion=Menu(teclado);
        }
        
        t.commit();
        sesion.close();
        sf.close();
       
    }
    
    public static char Menu(Scanner t){
        char opc;
        System.out.println("\n-----------------------\n");
        System.out.println("a. Añadir nueva localidad") ;
        System.out.println("b. Mostra una localidad") ;
        System.out.println("c. Actualizar censo") ;
        System.out.println("d. Eliminar localidad") ;
        System.out.println("e. Mostrar todas las localidades") ;
        System.out.println("f. Salir") ;
        System.out.println("Introduce una opcion:");
        opc=t.next().charAt(0);
        return opc;
    }
   public static void AñadirLocalidad(Scanner t,Session sesion){
       Localidades l=null;
       String codLoc, nombre,nomProv;
       int censo,habitantes;
       System.out.println("Introducir codigo localidad");
       codLoc=t.next();
       l=(Localidades)sesion.get(Localidades.class, codLoc);
       if(l==null){
          t.nextLine();
          System.out.println("Introducir nombre localidad");
          nombre=t.nextLine();
          System.out.println("Introducir provincia");
          nomProv=t.next();
          System.out.println("Introducir censo");
          censo=t.nextInt();
          System.out.println("Introducir habitantes");
          habitantes=t.nextInt();
          l=new Localidades(codLoc,nombre,censo,habitantes,nomProv);
          sesion.save(l);
       }else System.out.println("No se puede insertar pues ese codigo de localidad ya existe");
  }
   
   public static void MostarLocalidad(Scanner t,Session sesion){
       String codLoc;
       Localidades l;
       System.out.println("Introducir codigo de localidad a mostrar");
       codLoc=t.next();
       l=(Localidades)sesion.get(Localidades.class, codLoc);
       if(l==null)
           System.out.println("Dicha localidad no existe");
       else
           System.out.println(l.toString());
   }
    public static void ActualizarCenso(Scanner t,Session sesion){
        String codLoc;
        int censo;
        Localidades l;
        System.out.println("Introducor codigo de la localidad a modificar");
        codLoc=t.next();
        l=(Localidades)sesion.get(Localidades.class, codLoc);
        if(l==null)
            System.out.println("No se puede modifcar pues no existe");
        else{
            System.out.println("Introducir el nuevo valor del censo");
            censo=t.nextInt();
            l.setCenso(censo);
            sesion.update(l);
        }
    }
   
    public static void EliminarLocalidad(Scanner t,Session sesion){
         String codLoc;
         Localidades l;
         System.out.println("Introducor codigo de la localidad a eliminar");
         codLoc=t.next();
         l=(Localidades)sesion.get(Localidades.class, codLoc);
        if(l==null)
            System.out.println("No se puede borrar pues no existe");
        else
           sesion.delete(l);
    }
    
    public static void Mostrar(Session sesion){
        Query q=sesion.createQuery("From Localidades");
        List<Localidades> lista=q.list();
        for(int i=0;i<lista.size();i++)
            System.out.println(lista.get(i).toString());
    }
    
    
}
