package hibernateUniversidad;

import java.util.Iterator;
import java.util.List;
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

        Query qProf = sesion.createQuery("From Profesor");

        List<Profesor> listaProfesores = qProf.list();

        /*  System.out.println("-------Lista alumnos-----------------");
        for (Profesor lista : listaProfesores) {
        System.out.println(lista.toString());
        }*/
        Query qEspe = sesion.createQuery("From Especialidad");

        List<Especialidad> listaEspecialidades = qEspe.list();

        for (int i = 0; i < listaEspecialidades.size(); i++) {
            System.out.println(listaEspecialidades.get(i).toString());

            Iterator it = listaEspecialidades.get(i).getProfesors().iterator();

            while (it.hasNext()) 
                System.out.println(((Profesor) it.next()).mostrar());
           

        }
        System.exit(0);
        //t.commit();
    }

}
