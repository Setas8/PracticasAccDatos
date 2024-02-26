package ej2ex;

import POJOS.Arma;
import POJOS.Escudo;
import POJOS.Personaje;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Ej2Ex {
  public static void main(String[] args) {
       try{
          File f=new File("jugadas.txt");
          FileReader fr=new FileReader(f);
          BufferedReader br=new BufferedReader(fr);
          SessionFactory sf=HibernateUtil.sessionFactory();
          Session s=sf.openSession();
          Transaction t=s.beginTransaction();
          Arma ar;
          Personaje pa,pd;
          Escudo es;
          Query q=s.createQuery("From Personaje");
          List<Personaje> lista=q.list();
          int[] vidas=new int[lista.size()];
           System.out.println("ANTES DE JUGAR");
          for(int i=0;i<lista.size();i++){
             vidas[i]=lista.get(i).getVida();
              System.out.println(lista.get(i).toString());
          }
          String registro;
         // String registro=br.readLine();
          String[] valores;
          int cat,car,cde,ces;
          
          while((registro=br.readLine())!=null){
              valores=registro.split(",");
              cat=Integer.parseInt(valores[0]);
              car=Integer.parseInt(valores[1]);
              cde=Integer.parseInt(valores[2]);
              ces=Integer.parseInt(valores[3]);
              
              pa=(Personaje)s.get(Personaje.class, cat);
              if(pa==null)
                  System.out.println("NO PUDES JUGAR NO EXISTE ESE PERSONAJE");
              else{
                ar=(Arma)s.get(Arma.class, car);
                if(ar==null)
                   System.out.println("NO PUDES JUGAR NO EXISTE ESA ARMA"); 
                else{
                  pd=(Personaje)s.get(Personaje.class, cde);
                  if(pd==null)
                    System.out.println("NO PUDES JUGAR NO EXISTE ESE PERSONAJE");
                  else{
                    es=(Escudo)s.get(Escudo.class, ces);
                    if(es==null)
                       System.out.println("NO PUDES JUGAR NO EXISTE ESE ESCUDO"); 
                    else{
                       System.out.println("ATACANTE "+pa.getNombre()+" ARMA "+ar.getNombre()+" DAÑO "+ar.getDano());
                       System.out.println("DEFENSOR "+pd.getNombre()+" ESCUDO "+es.getNombre()+" DEFENSA "+es.getDefensa()); 
                       if(ar.getDano()>es.getDefensa()){
                         pd.setVida(pd.getVida()-(ar.getDano()-es.getDefensa()));
                         s.update(pd);
                       }else{
                         pa.setVida(pa.getVida()-(es.getDefensa()-ar.getDano()));
                         s.update(pa);  
                       }
                    }  
                  }
                }
             }
              //registro=br.readLine(); 
          } 
                   
        q=s.createQuery("From Personaje");
          lista=q.list();
          
          for(int i=0;i<lista.size();i++){
              if(lista.get(i).getVida()<vidas[i]/2)
                 s.delete(lista.get(i));
          }
         t.commit();
         
          System.out.println("DESPUES DE JUGAR");
          q=s.createQuery("From Personaje");
          lista=q.list();
         for(int i=0;i<lista.size();i++){
            System.out.println(lista.get(i).toString());
          }
         
       }catch(IOException e){
           System.out.println(e);
       }
       
       System.exit(0);
    }
 }
