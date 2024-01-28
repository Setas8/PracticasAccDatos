package pck_gestionAlumnos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Diego Cuadrado
 */
public class GestionAlumnos {

    static Scanner tcd = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/matricula";
            String user = "root";
            String pswd = "";

            Connection conn = DriverManager.getConnection(url, user, pswd);

            Statement st = conn.createStatement();

            menu(conn, st);          

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        tcd.close();
    }

    private static void menu(Connection conn, Statement st) throws SQLException {

        int opcion;
        boolean continua;
        do {
            try {
                continua = false;
                System.out.println("\nIntroduce una opción del menú:\n"
                        + "1.Alta alumno\n"
                        + "2.Mostrar datos alumno\n"
                        + "3.Borrar un alumno\n"
                        + "4.Matricular alumno\n" 
                        + "5.Mostrar asignaturas matriculadas\n"
                        + "6.Mostrar asignaturas alumno\n"
                        + "0.Salir");

                opcion = tcd.nextInt();
                tcd.nextLine();
                int id; //Para localizar alumno
                String insertar = "insert into alumnos values(?,?,?,?,?)";
                PreparedStatement insertarAlumno = conn.prepareStatement(insertar);

                String buscar = "select * from alumnos where id_alumno = ?";
                PreparedStatement buscarAlumno = conn.prepareStatement(buscar);

                String buscar2 = "select * from asignaturas where id_asignatura = ?";
                PreparedStatement buscarAsignatura = conn.prepareStatement(buscar2);

                String eliminar = "delete from alumnos where id_alumno = ?";
                PreparedStatement eliminarAlumno = conn.prepareStatement(eliminar);

                String matricular = "insert into alumnos_asignaturas values(?,?,0)";
                PreparedStatement matricularAlum = conn.prepareStatement(matricular);
                
                String comprobarMat = "select * from alumnos_asignaturas where id_alumno = ? and id_asignatura = ?";
                PreparedStatement comprobarMatriculado = conn.prepareStatement(comprobarMat);

                String mostrarAsigMatricu = "select al.nombre, asi.nombre "
                        + "from alumnos_asignaturas alas "
                        + "join  alumnos al "
                        + "on al.id_alumno = alas.id_alumno "
                        + "join asignaturas asi "
                        + "on asi.id_asignatura = alas.id_asignatura "
                        + "where alas.id_alumno = ?";
                                            
                PreparedStatement buscarMatriAlum = conn.prepareStatement(mostrarAsigMatricu);

                String cadena = "select al.nombre, al.apellidos, al.curso,"
                        + " al.titulacion, asi.nombre, asi.tipo, asi.creditos,"
                        + " alas.cursada "
                        + "from alumnos al "
                        + "join alumnos_asignaturas alas"
                        + " on al.id_alumno = alas.id_alumno "
                        + "join asignaturas asi "
                        + "on asi.id_asignatura = alas.id_asignatura "
                        + "where al.id_alumno = ?";
                PreparedStatement mostrarAsignaAlumnos= conn.prepareStatement(cadena);
                
                switch (opcion) {
                    case 1:
                        System.out.println("\nAlumno nuevo............");
                        altaAlumno(insertarAlumno, buscarAlumno);
                        menu(conn, st);                       
                        break;
                    case 2:
                        System.out.println("Mostrar datos alumno......");
                        mostrarAlumnos(st);
                        System.out.println("Dime el id del alumno: ");
                        id = tcd.nextInt();
                        if (!buscaAlumno(buscarAlumno, id)) {
                            System.out.println("No se encontró el alumno");
                        }
                        menu(conn, st);
                        break;
                    case 3:
                        System.out.println("Borrar un alumno existente");
                        mostrarAlumnos(st);
                        System.out.println("\nDime el id del alumno: ");
                        id = tcd.nextInt();
                        eliminarAlumno(eliminarAlumno, id);
                        mostrarAlumnos(st);
                        menu(conn, st);
                        break;
                    case 4:
                        System.out.println("Matricular al alumno nuevo en varias asignaturas");
                        matricularAlumno(matricularAlum, buscarAlumno, buscarAsignatura, comprobarMatriculado, st);

                        menu(conn, st);
                        break;
                    case 5:
                        System.out.println("Consultar las asignaturas matriculadas");
                        mostrarAlumnos(st);
                        System.out.println("\nDime el id del alumno: ");
                        id = tcd.nextInt();
                        if (!buscaAlumno(buscarAlumno, id)) {
                            System.out.println("No se encontró el alumno");
                        }
                        mostrarAsigMatricula(buscarMatriAlum, id);
                        menu(conn, st);
                        break;
                    case 6:
                        System.out.println("Consultar las asignaturas alumno");
                        mostrarAlumnos(st);
                        System.out.println("\nDime el id del alumno: ");
                        id = tcd.nextInt();
                        if (!buscaAlumno(buscarAlumno, id)) {
                            System.out.println("No se encontró el alumno");
                        }
                        else
                            mostrarAsigAlumno(mostrarAsignaAlumnos, id);
                        menu(conn, st);
                        break;
                    case 0:
                        System.out.println("Hasta pronto");
                        break;
                    default:
                        System.out.println("\nOpción incorrecta");
                        menu(conn, st);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nNo has introducido un entero");
                tcd.next();
                continua = true;
            }
        } while (continua);

    }

    private static void altaAlumno(PreparedStatement psInsert, PreparedStatement psBusqueda) throws SQLException {

        System.out.println("Id alumno: ");
        int id = tcd.nextInt();
        tcd.nextLine();
        while (buscaAlumno(psBusqueda, id)) {
            System.out.println("El id ya existe, introduce otro id: ");
            id = tcd.nextInt();
            tcd.nextLine();
        }

        System.out.println("Apellidos alumno: ");
        String apellidos = tcd.nextLine();
        System.out.println("Nombre alumno: ");
        String nombre = tcd.nextLine();
        int curso = (int) (Math.random() * 7) + 1;
        int titulacion = (int) (Math.random() * 12) + 1;

        psInsert.setInt(1, id);
        psInsert.setString(2, apellidos);
        psInsert.setString(3, nombre);
        psInsert.setInt(4, curso);
        psInsert.setInt(5, titulacion);

        psInsert.executeUpdate();

    }

    private static void mostrarAlumnos(Statement st) throws SQLException {
        String mostrar = "select * from alumnos";
        ResultSet rs = st.executeQuery(mostrar);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }

    }

    private static void mostrarAsignaturas(Statement st) throws SQLException {
        String mostrar = "select * from asignaturas";
        ResultSet rs = st.executeQuery(mostrar);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2)
                            + " " + rs.getString(3) + " " + rs.getFloat(4));
        }

    }

    private static boolean buscaAlumno(PreparedStatement ps, int id) throws SQLException {

        System.out.println("=================");
        ResultSet rs;
        ps.setInt(1, id);
        rs = ps.executeQuery();
        boolean encontrado = false;

        if (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2)
                    + " " + rs.getString(3) + " " + rs.getInt(4)
                    + " " + rs.getInt(5));
            encontrado = true;
        }

        return encontrado;

    }

    private static boolean buscaAsignatura(PreparedStatement ps, int id) throws SQLException {
        System.out.println("=================");
        ResultSet rs;
        ps.setInt(1, id);
        rs = ps.executeQuery();
        boolean encontrado = false;

        if (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2)
                    + " " + rs.getString(3) + " " + rs.getFloat(4));
            encontrado = true;
        }

        return encontrado;

    }

    private static void eliminarAlumno(PreparedStatement ps, int id) throws SQLException {

        ResultSet rs;
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.out.println("No se ha eliminado ningún alumno");
        } else {
            System.out.println("Se ha eliminado el alumno con id " + id);
        }
    }

    private static void matricularAlumno(PreparedStatement psInsert,
                    PreparedStatement psBusAlu, PreparedStatement psBusAsi, 
                    PreparedStatement pscomprobarMatriculado,  Statement st) throws SQLException {
        
        mostrarAlumnos(st);
        System.out.println("Id alumno: ");
        int idAl = tcd.nextInt();
        tcd.nextLine();
        //Comprobar que el alumno existe
        while (!buscaAlumno(psBusAlu, idAl)) {
            System.out.println("El alumno no se encuentra en la base de datos\n"
                    + "Introduce otro id: ");
            idAl = tcd.nextInt();
            tcd.nextLine();
        }
       
        mostrarAsignaturas(st);
        System.out.println("Id asignatura: ");
        int idAs = tcd.nextInt();
        tcd.nextLine();
        while (!buscaAsignatura(psBusAsi, idAs)) {
            System.out.println("La asignautra no existe\nIntroduce otro id: ");
            idAs = tcd.nextInt();
            tcd.nextLine();
        }
        
        int alumno = idAl;
        int asignatura = idAs;
        System.out.println(idAl);
        System.out.println(idAs);
        boolean sePuedeMatrucular = false;
         
        ResultSet rs;
        pscomprobarMatriculado.setInt(1, idAl);
        pscomprobarMatriculado.setInt(2, idAs);
        rs = pscomprobarMatriculado.executeQuery();
        
        if (rs.next()) 
            System.out.println("El alumno ya está matriculado en esa asignatura");                  
        else 
            sePuedeMatrucular = true;
        
        if (sePuedeMatrucular) {
            psInsert.setInt(1, alumno);
            psInsert.setInt(2, asignatura);
        
            psInsert.executeUpdate();
        }          

    }

    private static void mostrarAsigMatricula(PreparedStatement ps, int id) throws SQLException {

        ResultSet rs;
        ps.setInt(1, id);
        rs = ps.executeQuery();       
        boolean sinRegistros = true;
        while (rs.next()) {
            System.out.println(rs.getString(1) + " - " + rs.getString(2));
            sinRegistros = false;
        }
        if (sinRegistros) {
            System.out.println("El alumno no se matriculó en ninguna asignatura");
        }

    }

    private static void mostrarAsigAlumno(PreparedStatement psmostrarAsignaAlumnos, int id) throws SQLException {
        
        ResultSet rs;
        
        psmostrarAsignaAlumnos.setInt(1, id);
        rs = psmostrarAsignaAlumnos.executeQuery();
             
        while (rs.next()) {
            System.out.println("Nombre alumno: " + rs.getString(1) + " " + rs.getString(2)
                        + "\nCurso: " + rs.getInt(3) + "\nTitulación: " + rs.getInt(4)
                        + "\nNombre asignatura: " + rs.getString(5)+ "\nTipo: " + rs.getString(6)
                        + "\nCréditos " + rs.getFloat(7));
            if (rs.getInt(8) == 0) 
                System.out.println("No cursando");
            else
                System.out.println("Cursando");
            System.out.println("- - - - - - - - -");
            
        }   
    }
}        