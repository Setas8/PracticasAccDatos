package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Arma;
import pojos.Escudo;
import pojos.Personaje;

/**
 *
 * @author Diego Cuadrado
 */
public class JuegoMain {

    public static void main(String[] args) {

        File f = new File("jugadas.txt");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            SessionFactory sf = HibernateUtil.sessionFactory();
            Session sesion = sf.openSession();
            Transaction t = sesion.beginTransaction();

            Personaje perAta;
            Arma ar;
            Personaje perDef;
            Escudo es;
            
            
            System.out.println("-------DESPUÉS DEL COMMIT-----------");
            Query q = sesion.createQuery("From Personaje");
            List<Personaje> personajes = q.list();
            for (int i = 0; i < personajes.size(); i++)
                System.out.println(personajes.get(i).toString());
            

            q = sesion.createQuery("From Personaje");
            personajes = q.list();
            int[] vidas = new int[personajes.size()];

            for (int i = 0; i < personajes.size(); i++) {
                vidas[i] = personajes.get(i).getVida();
            }

            String reg = "";
            String[] valores;
            int cat;
            int codAr;
            int cdf;
            int codEs;

            while ((reg = br.readLine()) != null) {
                valores = reg.split(",");
                cat = Integer.parseInt(valores[0]);
                codAr = Integer.parseInt(valores[1]);
                cdf = Integer.parseInt(valores[2]);
                codEs = Integer.parseInt(valores[3]);

                //Comprobar si existen los valores en las tablas
                perAta = (Personaje) sesion.get(Personaje.class, cat);
                if (perAta == null) {
                    System.out.println("No existe el atacante");
                } else {
                    ar = (Arma) sesion.get(Arma.class, codAr);
                    if (ar == null) {
                        System.out.println("No existe el arma");
                    } else {
                        perDef = (Personaje) sesion.get(Personaje.class, cdf);
                        if (perDef == null) {
                            System.out.println("No existe el defensor");
                        } else {
                            es = (Escudo) sesion.get(Escudo.class, codEs);
                            if (es == null) {
                                System.out.println("No existe el escudo");
                            } //Existen todos los valores en las tablas
                            else {
                                System.out.println("Atancante: " + perAta.getNombre() + " Arma: " + ar.getNombre() + " " + ar.getDano());
                                System.out.println("Defensor: " + perDef.getNombre() + " Arma: " + es.getNombre() + " " + es.getDefensa());
                                if (ar.getDano() > es.getDefensa()) {
                                    perDef.setVida(perDef.getVida() - (ar.getDano() - es.getDefensa()));
                                    sesion.update(perDef);
                                } else {
                                    perAta.setVida(perAta.getVida() - (es.getDefensa() - ar.getDano()));
                                    sesion.update(perAta);
                                }
                            }
                        }
                    }
                }

            }
            q = sesion.createQuery("From Personaje");
            personajes = q.list();

            for (int i = 0; i < personajes.size(); i++) {
                if (personajes.get(i).getVida() < vidas[i] / 2) {
                    sesion.delete(personajes.get(i));
                }
            }
            
            t.commit();
            
            System.out.println("-------DESPUÉS DEL COMMIT-----------");
            q = sesion.createQuery("From Personaje");
            personajes = q.list();
            for (int i = 0; i < personajes.size(); i++)
                System.out.println(personajes.get(i).toString());
            
            
            System.exit(0);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
