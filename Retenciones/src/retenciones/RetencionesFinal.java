
package retenciones;

import POJOS.Departamento;
import POJOS.Empleado;
import POJOS.Retenciones;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RetencionesFinal {
  public static void main(String[] args) {
     SessionFactory sf=HibernateUtil.sessionFactory();
     Session sesion=sf.openSession();
     Transaction t=sesion.beginTransaction();
     Query q=sesion.createQuery("From Empleado");
     Query q1=sesion.createQuery("From Retenciones");
     Departamento dpt=null;
     Retenciones ret=null;
     List<Empleado> lista=q.list();
     int retencion=0;
     int salario=0;
     for(int i=0;i<lista.size();i++){
         if(lista.get(i).getSalario()<1500){
           retencion=lista.get(i).getSalario()*10/100;
        }else if(lista.get(i).getSalario()<2500){
               retencion=lista.get(i).getSalario()*15/100;
         }else retencion=lista.get(i).getSalario()*20/100;
         
         salario=lista.get(i).getSalario()-retencion; 
         
        ret=new Retenciones(lista.get(i).getNombre(),lista.get(i).getDepartamento().getNombre(),lista.get(i).getSalario(),salario,retencion);
        sesion.save(ret);
     }
          
     List<Retenciones> listaret=q1.list();
     listaret=q1.list();
      System.out.println("TABLA RETENCIONES ANTES DE ACTUALIZARSE");
     for(int i=0;i<listaret.size();i++)
          System.out.println(listaret.get(i).toString());
     
      System.out.println("-----------------------------------------------------------");   
     
     int salarioneto=0;
     for(int i=0;i<listaret.size();i++){
        if(listaret.get(i).getSalarioNeto()<750)
           salarioneto=1000; 
        else if(listaret.get(i).getSalarioNeto()<2200) 
               salarioneto=2300;
        else if(listaret.get(i).getSalarioNeto()<3200)
              salarioneto=3300;
        listaret.get(i).setSalarioNeto(salarioneto);
        sesion.update(listaret.get(i));
     }
     
      System.out.println("TABLA RETENCIONES DESPUES DE ACTUALIZARSE");
     listaret=q1.list();
     for(int i=0;i<listaret.size();i++)
          System.out.println(listaret.get(i).toString());
     
     
     t.commit();
     sesion.close();
     sf.close();
    }
    
}
