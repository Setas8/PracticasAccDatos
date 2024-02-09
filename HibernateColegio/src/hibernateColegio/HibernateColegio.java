package hibernateColegio;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Alumnos;
import pojos.Cambios;

/**
 *
 * @author Diego Cuadrado
 */
public class HibernateColegio {

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.sessionFactory();
        Session sesion = sf.openSession();

        //Para hacer los commit
        Transaction t = sesion.beginTransaction();

        //Devuelve un tipo List
        Query qAlu = sesion.createQuery("From Alumnos");

        List<Alumnos> listaAlumnos = qAlu.list();

        System.out.println("-------Lista alumnos-----------------");
        for (Alumnos lista : listaAlumnos) {
            System.out.println(lista.toString());
        }

        //Devuelve un tipo List
        Query qCam = sesion.createQuery("From Cambios");

        List<Cambios> listaCambios = qCam.list();

        Alumnos al;
        Scanner tcd = new Scanner(System.in);
        for (int i = 0; i < listaCambios.size(); i++) {

            al = (Alumnos) sesion.get(Alumnos.class, listaCambios.get(i).getMatricula());
            if (al == null) {
                System.out.println("Alumno no existe");
                System.out.println("¿Quiere darle de alta? (s/n): ");
                String resp = tcd.nextLine();
                if (resp.equalsIgnoreCase("s")) {
                    System.out.println("Nombre: ");
                    String nombre = tcd.nextLine();
                    System.out.println("Apellidos: ");
                    String ape = tcd.nextLine();
                    System.out.println("Nota: ");
                    int nota = tcd.nextInt();tcd.nextLine();

                    al = new Alumnos(listaCambios.get(i).getMatricula(), nombre, ape, nota);
                    sesion.save(al);
                }

            } else {
                switch (listaCambios.get(i).getTipo()) {

                    case "BJ":
                        sesion.delete(al);
                        System.out.println("Alumno borrado: " + al.getNombre());

                        break;
                    case "MD":
                        al.setNota(al.getNota() + listaCambios.get(i).getIncremento());
                        sesion.update(al);
                        break;
                    default:
                        System.out.println("La operación es errónea");
                        break;
                }
            }
        }

        t.commit();
        //Volver a hacer la select
        qAlu = sesion.createQuery("From Alumnos");
        listaAlumnos = qAlu.list();

        System.out.println("-------Lista alumnos-----------------");
        for (Alumnos lista : listaAlumnos) {
            System.out.println(lista.toString());
        }
        tcd.close();
        System.exit(0);
    }

}
