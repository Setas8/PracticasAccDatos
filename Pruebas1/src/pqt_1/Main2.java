package pqt_1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Diego Cuadrado
 */
public class Main2 {

    public static void main(String[] args) {

        File fProductos = new File("productos.dat");
        File fDatos = new File("datos.bin");

        try {
            RandomAccessFile raf = new RandomAccessFile(fProductos, "rw");
            FileInputStream fis = new FileInputStream(fDatos);
            DataInputStream dis = new DataInputStream(fis);

            int codigo;
            int cantidad;
            double precio;
            String operacion;
            String campo;

            raf.seek(0);
            try {
                while (true) {
                    codigo = raf.readInt();
                    cantidad = raf.readInt();
                    precio = raf.readDouble();

                    System.out.println(codigo + " " + cantidad + " " + precio);
                }
            } catch (EOFException eof) {
                System.out.println(eof);
            }

            raf.seek(0);
            try {
                while (true) {
                    operacion = dis.readUTF();
                    if (operacion.equalsIgnoreCase("Alta")) {
                        cantidad = dis.readInt();
                        precio = dis.readDouble();
                        codigo = (int) raf.length() / 16 + 1; // 4 + 4 + 8

                        raf.seek(raf.length());
                        raf.writeInt(codigo);
                        raf.writeInt(cantidad);
                        raf.writeDouble(precio);
                    } else if (operacion.equalsIgnoreCase("Baja")) {
                        codigo = dis.readInt();
                        raf.seek((codigo - 1) * 16);   //4 + 4 + 8
                        raf.readInt();  //codigo
                        cantidad = raf.readInt();

                        if (cantidad == -1) {
                            System.out.println("El producto ya está dado de baja");
                        } else {
                            raf.seek(raf.getFilePointer() - 4); //cantidad
                            raf.writeInt(-1);
                        }
                    } else {
                        codigo = dis.readInt();  //codigo
                        campo = dis.readUTF();
                        raf.seek((codigo - 1) * 16); // 4 + 4 + 8
                        raf.readInt();
                        if (raf.readInt() == -1) {
                            System.out.println("El producto está dado de baja y no se puede modificar");
                        } else {
                            if (campo.equalsIgnoreCase("Cantidad")) {
                                cantidad = dis.readInt();
                                raf.seek(raf.getFilePointer() - 4); //cantidad
                                raf.writeInt(cantidad);
                            } else {
                                precio = dis.readDouble();
                                raf.writeDouble(precio);
                            }
                        }
                    }
                }
            } catch (EOFException eof) {
                System.out.println(eof);
            }
            dis.close();
            fis.close();

            raf.seek(0);
            try {
                while (true) {
                    codigo = raf.readInt();
                    cantidad = raf.readInt();
                    precio = raf.readDouble();

                    if (cantidad != -1) {
                        System.out.println(codigo + " " + cantidad + " " + precio);
                    }

                }
            } catch (EOFException eof) {
                System.out.println(eof);
            }
            raf.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
