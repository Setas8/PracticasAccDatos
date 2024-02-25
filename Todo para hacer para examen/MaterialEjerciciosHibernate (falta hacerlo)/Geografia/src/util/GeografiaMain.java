package util;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Localidades;

public class GeografiaMain {

    static Scanner tcd = new Scanner(System.in);

    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.sessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();

        boolean continua = true;

        while (continua) {
            System.out.println("\nIntroduce una opcion\n"
                    + "a)Añadir una nueva localidad\n"
                    + "b)Mostrar los datos de una localidad por su código\n"
                    + "c)Actualizar el campo censo de una localidad\n"
                    + "d)Eliminar una localidad\n"
                    + "e)Mostrar todos los registros de la tabla\n"
                    + "f)Salir");
            char opcion = tcd.next().toLowerCase().charAt(0);

            switch (opcion) {
                case 'a':
                    System.out.println("----Añadir  nueva localidad-------");
                    addLocalidades(sesion);
                    break;
                case 'b':
                    System.out.println("----Mostrar datos de localidad por su código-------");
                    mostrarLocalidad(sesion);
                    break;
                case 'c':
                    System.out.println("-----Actualizar censo--------");
                    actualizarCenso(sesion);
                    break;
                case 'd':
                    System.out.println("-----Eliminar localidad---------");
                    eliminarLocalidad(sesion);
                    break;
                case 'e':
                    System.out.println("----Mostrar tabla------");
                    mostrarTabla(sesion);
                    break;
                case 'f':
                    System.out.println("Hasta pronto");
                    continua = false;
                    break;
                default:
                    System.out.println("No es una opción correcta");
                    break;
            }

        }

        //t.commit();
        //sesion.close();
        //sf.close();
        tcd.close();
        System.exit(0);
    }

    private static void addLocalidades(Session s) {

        System.out.println("Código: ");
        String codLoc = tcd.next();
        Localidades l = (Localidades) s.get(Localidades.class, codLoc);
        if (l == null) {
            System.out.println("Nombre: ");
            String nombre = tcd.next();
            System.out.println("Censo: ");
            int censo = tcd.nextInt();
            System.out.println("Habitantes: ");
            int habitantes = tcd.nextInt();
            System.out.println("Nombre provincia: ");
            String prov = tcd.next();
            l = new Localidades(codLoc, nombre, censo, habitantes, prov);
            s.save(l);
        } else {
            System.out.println("La localidad ya existe");
        }

    }

    private static void mostrarLocalidad(Session s) {

        System.out.println("Código: ");
        String codLoc = tcd.next();
        Localidades l = (Localidades) s.get(Localidades.class, codLoc);
        if (l == null) {
            System.out.println("Esa localidad no existe");
        } else {
            System.out.println(l.toString());
        }

    }

    private static void actualizarCenso(Session s) {

        System.out.println("Código de la localidad a cambiar: ");
        String codLoc = tcd.next();
        Localidades l = (Localidades) s.get(Localidades.class, codLoc);
        if (l == null) {
            System.out.println("Esa localidad no existe, no se puede cambiar el censo");
        } else {
            System.out.println("Localidad con censo antiguo");
            System.out.println(l.toString());
            System.out.println("Nuevo censo: ");
            int censo = tcd.nextInt();
            l.setCenso(censo);
            s.update(l);
            System.out.println("Localidad con censo nuevo");
            System.out.println(l.toString());
        }
    }

    private static void eliminarLocalidad(Session s) {

        System.out.println("Código de la localidad a eliminar: ");
        String codLoc = tcd.next();
        Localidades l = (Localidades) s.get(Localidades.class, codLoc);
        if (l == null) {
            System.out.println("Esa localidad no existe, no se puede eliminar");
        } else {
            s.delete(l);
        }
    }

    private static void mostrarTabla(Session s) {

        Query q = s.createQuery("From Localidades");
        List<Localidades> listaL = q.list();

        for (Localidades l : listaL) {
            System.out.println(l.toString());
        }
    }

}
