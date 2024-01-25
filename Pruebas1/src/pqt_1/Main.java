package pqt_1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Diego Cuadrado Martinez
 */
public class Main {

    public static void main(String[] args) {

        File fProductos = new File("productos.dat");
        File fDatos = new File("datos.bin");
        try {

            RandomAccessFile raf = new RandomAccessFile(fProductos, "rw");
            FileInputStream fis = new FileInputStream(fDatos);
            DataInputStream dis = new DataInputStream(fis);

            int codigo, cantidad;
            double precio;
            String operacicion, campo;

            System.out.println("--------Fichero original------------------");
            raf.seek(0);
            try {
                while (true) {
                    codigo = raf.readInt();
                    cantidad = raf.readInt();
                    precio = raf.readDouble();
                    System.out.println(codigo + " " + cantidad + " " + precio);
                }

            } catch (EOFException e) {
            }

            System.out.println("----------Modificando fichero -----------");
            raf.seek(0);
            try {
                while (true) {
                    operacicion = dis.readUTF();
                    if (operacicion.equalsIgnoreCase("Alta")) {
                        cantidad = dis.readInt();
                        precio = dis.readDouble();
                        codigo = ((int) raf.length() / 16) + 1;
                        raf.seek(raf.length());
                        raf.writeInt(codigo);
                        raf.writeInt(cantidad);
                        raf.writeDouble(precio);
                    } else if (operacicion.equalsIgnoreCase("Baja")) {
                        codigo = dis.readInt();
                        raf.seek((codigo - 1) * 16);
                        raf.readInt();
                        cantidad = raf.readInt();
                        if (cantidad == -1) {
                            System.out.println("El registro ya esta dado de baja");
                        } else {
                            raf.seek(raf.getFilePointer() - 4);
                            raf.writeInt(-1);
                        }
                    } else {
                        codigo = dis.readInt();
                        campo = dis.readUTF();
                        raf.seek((codigo - 1) * 16);
                        raf.readInt();
                        if (raf.readInt() == -1) {
                            System.out.println("El registro esta dado de baja y no se puede modificar");
                        } else {
                            if (campo.equalsIgnoreCase("cantidad")) {
                                cantidad = dis.readInt();
                                raf.seek(raf.getFilePointer() - 4);
                                raf.writeInt(cantidad);
                            } else {
                                precio = dis.readDouble();
                                raf.writeDouble(precio);
                            }
                        }

                    }
                }
            } catch (EOFException e) {
            }

            dis.close();
            fis.close();

            raf.seek(0);

            try {
                System.out.println("--------Fichero modificado------------------");
                while (true) {
                    codigo = raf.readInt();
                    cantidad = raf.readInt();
                    precio = raf.readDouble();

                    if (cantidad != -1) {
                        System.out.println(codigo + " " + cantidad + " " + precio);
                    }
                }
            } catch (EOFException e) {
            }
            raf.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
