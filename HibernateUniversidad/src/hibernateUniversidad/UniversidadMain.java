package hibernateUniversidad;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Especialidad;
import pojos.Profesor;

/**
 *
 * @author Diego Cuadrado
 */
public class UniversidadMain {

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.sessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();

        /*  System.out.println("-------Lista alumnos-----------------");
        for (Profesor lista : listaProfesores) {
        System.out.println(lista.toString());
        }*/
        System.out.println("\n- - - -Especialidad con sus profesores - - - - - ");

        Query qEspe = sesion.createQuery("From Especialidad");

        List<Especialidad> listaEspecialidades = qEspe.list();

        for (int i = 0; i < listaEspecialidades.size(); i++) {

            System.out.println(listaEspecialidades.get(i).toString());

            Iterator it = listaEspecialidades.get(i).getProfesors().iterator();

            while (it.hasNext()) {
                System.out.println(((Profesor) it.next()).toString());
            }

        }
        System.out.println("\n- - - -Profesores con su especialidad - - - - - ");

        Query qProf = sesion.createQuery("From Profesor");

        List<Profesor> listaProfesores = qProf.list();

        for (int i = 0; i < listaProfesores.size(); i++) {

            System.out.println(listaProfesores.get(i).mostrar());

        }

        System.out.println("\n- - - Profesor-> Id y Nombre - - - - - ");

        Query q2 = sesion.createQuery("Select p.id,p.nombre FROM Profesor p");
        List<Object[]> listaP = q2.list();
        for (Object[] datos : listaP) {
            System.out.println(datos[0] + "--" + datos[1]);
        }
        System.out.println("- - - - - - - - - - - - - ");
        for (int i = 0; i < listaP.size(); i++) {
            System.out.println(listaP.get(i)[0] + "--" + listaP.get(i)[1]);
        }

        System.out.println("\n- - - Profesor-> Nombre ordenado - - - - - ");

        Query q3 = sesion.createQuery("SELECT p.nombre FROM Profesor p order by p.nombre");
        List<Object> listaP2 = q3.list();
        for (Object datos : listaP2) {
            System.out.println(datos);
        }
        System.out.println("- - - - - - - - - - - - - ");
        for (int i = 0; i < listaP2.size(); i++) {
            System.out.println(listaP2.get(i));
        }

        System.out.println("\n- - - - - - - - - ");

        System.out.println("ID: ");
        Scanner tcd = new Scanner(System.in);
        int id = tcd.nextInt();
        tcd.nextLine();
        Profesor pr = (Profesor) sesion.createQuery("SELECT p FROM Profesor p"
                + " WHERE p.id = " + id).uniqueResult();

        if (pr == null) {
            System.out.println("Profesor con Id " + id + " = NO EXISTE");
        } else {
            System.out.println("Profesor con Id " + id + " " + pr.getNombre());
        }

        System.out.println("\n- - - -Desde el HTML - - - - - ");

        int opcion = Integer.MAX_VALUE;
        while (opcion != 0) {

            System.out.println("\nProfesores ordenados por:"
                    + "\n1.ID"
                    + "\n2.NOMBRE"
                    + "\n3.APELLIDOS"
                    + "\n0.SALIR");
            opcion = tcd.nextInt();
            Query order;
            switch (opcion) {
                case 1:                   
                    order = sesion.getNamedQuery("id");
                    queryOrdenada(order);
                    break;
                case 2:
                    order = sesion.getNamedQuery("nombre");
                    queryOrdenada(order);
                    break;
                case 3:
                    order = sesion.getNamedQuery("apellidos");
                    queryOrdenada(order);
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("No es una opción válida");
            }

        }

        System.exit(0);
        //t.commit();
    }
    private static void queryOrdenada(Query q) {
        
        List<Profesor> listaProfesoresNamed = q.list();

        for (int i = 0; i < listaProfesoresNamed.size(); i++) {
            System.out.println(listaProfesoresNamed.get(i).mostrar());
        }
    }

}
