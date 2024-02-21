package util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            File f = new File("futbol.bin");

            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            SessionFactory sf = HibernateUtil.sessionFactory();
            Session sesion = sf.openSession();
            Transaction t = sesion.beginTransaction();

            //Sacar con un count el número de equipos select from equipos
            System.out.println("\n- - - Partidos- - - - - ");
            Query qPar = sesion.createQuery("From Partidos");
            List<Partidos> listaPartidos = qPar.list();
            int numPart = 0;
            for (int i = 0; i < listaPartidos.size(); i++) {

                numPart++;

            }
            System.out.println("Número de partidos: " + numPart);
            //Array (numeroEquipos.lenght) para pg de todos los equipos posicion 0 del array equipo 1
            //Array (numeroEquipos.lenght) para pp de todos los equipos posicion 0 del array equipo 1
            //Array (numeroEquipos.lenght) para pe de todos los equipos posicion 0 del array equipo 1
            //Array (numeroEquipos.lenght) para gf de todos los equipos posicion 0 del array equipo 1
            //Array (numeroEquipos.lenght) para gc de todos los equipos posicion 0 del array equipo 1
            int[] gf = new int[10];
            int[] gc = new int[10];
            int[] pg = new int[10];
            int[] pp = new int[10];
            int[] pe = new int[10];

            int j, k;
            int golesL, golesV;
            for (int i = 0; i < listaPartidos.size(); i++) {
                j = Integer.parseInt(listaPartidos.get(i).getEquiposByCodLocal().getCodEquipo());
                k = Integer.parseInt(listaPartidos.get(i).getEquiposByCodVisitante().getCodEquipo());

                golesL = listaPartidos.get(i).getGolLocal();
                golesV = listaPartidos.get(i).getGolVisitante();

                gf[j - 1] = gf[j - 1] + golesL;
                gf[k - 1] = gf[k - 1] + golesV;
                gc[j - 1] = gf[j - 1] + golesV;
                gc[k - 1] = gf[k - 1] + golesL;

                if (golesL < golesV) {
                    pp[j - 1]++;
                    pg[k - 1]++;
                } else if (golesL > golesV) {
                    pg[j - 1]++;
                    pp[k - 1]++;
                } else {
                    pe[j - 1]++;
                    pe[k - 1]++;
                }
            }
            Estadisticas e;
            for (int i = 0; i < gf.length; i++) {
                System.out.println("Equipo " + (i + 1) + " PG-" + pg[i] + " PP-"
                        + pp[i] + " PE-" + pe[i] + " GF-" + gf[i] + " GC-" + gc[i]
                        + " Puntos- " + (pg[i] * 3 + pe[i]));
                e = (Estadisticas) sesion.get(Estadisticas.class, String.valueOf((i + 1)));
                if (e == null) {
                    e = new Estadisticas(String.valueOf((i + 1)), pg[i], pp[i], pe[i], gf[i], gc[i], (pg[i] * 3 + pe[i]));
                    sesion.update(e);
                } else {
                    System.out.println("No se puede insertar la estadística porque ya existen");
                }
            }

            System.out.println("\n- - - -Equipos - - - - - ");
            Query qEqui = sesion.createQuery("From Equipos");
            List<Equipos> listaEquipos = qEqui.list();
            /*  for (int i = 0; i < listaEquipos.size(); i++) {
        System.out.println(listaEquipos.get(i).toString());
        }*/

        /* System.out.println("\n- - - Estadísticas- - - - - ");
        Query qEsta = sesion.createQuery("From Estadisticas");
        List<Estadisticas> listaEstadistica = qEsta.list();
        
        for (int i = 0; i < listaEstadistica.size(); i++) {
        System.out.println(listaEstadistica.get(i).toString());
        }*/
        
        t.commit();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        System.exit(0);
    }

}
/*
          Equipos eqL;
        Equipos eqV;
        Partidos par;
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
 */
