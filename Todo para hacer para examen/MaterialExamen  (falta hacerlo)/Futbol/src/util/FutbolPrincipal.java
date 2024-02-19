package util;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Equipos;
import pojos.Estadisticas;
import pojos.Partidos;

/**
 *
 * @author Diego Cuadrado
 */
public class FutbolPrincipal {

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.sessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();
        
        
        System.out.println("\n- - - -Equipos - - - - - ");
        Query qEqui = sesion.createQuery("From Equipos");
        List<Equipos> listaEquipos = qEqui.list();
        /*  for (int i = 0; i < listaEquipos.size(); i++) {
        System.out.println(listaEquipos.get(i).toString());
        }*/
        System.out.println("\n- - - Partidos- - - - - ");
        Query qPar = sesion.createQuery("From Partidos");
        List<Partidos> listaPartidos = qPar.list();

        /*   for (int i = 0; i < listaPartidos.size(); i++) {
        System.out.println(listaPartidos.get(i).mostrarPartidos());
        }*/
        System.out.println("- - - - - - - - - - - - - - -");
        Estadisticas es = new Estadisticas();
        for (int i = 0; i < listaEquipos.size(); i++) {
            es.setCodEquipo(listaEquipos.get(i).getCodEquipo());
            es.setPganados(0);
            es.setPperdidos(0);
            es.setPempatados(0);
            es.setGolFav(0);
            es.setGolCont(0);
            es.setPuntos(0);
            int pg = 0;
            int pp = 0;
            int pe = 0;
            for (int j = 0; j < listaPartidos.size(); j++) {
                if (es.getCodEquipo().equals(listaPartidos.get(j).getEquiposByCodLocal())){
                    if (listaPartidos.get(j).getGolLocal() > listaPartidos.get(j).getGolVisitante()){
                        pg++;
                    }
                    else if(listaPartidos.get(j).getGolLocal() < listaPartidos.get(j).getGolVisitante()){
                        pp++;                          
                    }
                    else{
                        pe++;
                    }
                    
                }
                else if (es.getCodEquipo().equals(listaPartidos.get(i).getEquiposByCodVisitante())){
                    if (listaPartidos.get(j).getGolLocal() > listaPartidos.get(j).getGolVisitante()){
                        pg++;
                    }
                    else if(listaPartidos.get(j).getGolLocal() < listaPartidos.get(j).getGolVisitante()){
                        pp++;                          
                    }
                    else{
                        pe++;
                    }
                }
                
            }
            
            System.out.println(es.getCodEquipo());
            System.out.println(pg);
            System.out.println(pp);
            System.out.println(pe);
            System.out.println("Fin de jornada");
            
        }
        
        
        //t.commit();
        System.exit(0);
    }

}
