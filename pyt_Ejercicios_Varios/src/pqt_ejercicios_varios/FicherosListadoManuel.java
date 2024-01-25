package pqt_ejercicios_varios;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;



public class FicherosListadoManuel {
    
    static Scanner         poTeclado =  new Scanner(System.in);  
    static String  psFicheroPrincipal= "FchPrincipal.txt";
    static String  psFicheroAuxiliar = "FchAuxiliar";    
    static File           pof1, pof2; 
    static FileReader           pofr; 
    static FileWriter           pofw; 
    static int  piTamanioDelRegistro = 8 + 10;  //primer campo es la clave y tamaño 8, 
                                                //segundo campo es el nombre y tamaño 10

    public static void main(String[] args) throws IOException, FileNotFoundException { 
        mvFicherosDeTextoV1(); 
    }//main()
    static void     mvFicherosDeTextoV1() throws IOException, FileNotFoundException{           
        boolean wbTerminar= false;
        while (!wbTerminar){
            String ws = msPedirOpcionDeMenuV2().toLowerCase();
            File wof1 = new File(psFicheroPrincipal);
            if ( !wof1.exists() && ("afbmcl".contains(ws)) ){
                System.out.println(">> Lo siento. Aún NO EXISTE el fichero. Primero debe crearlo.");
                continue;
            }  
            switch (ws){
                case ("s"): {wbTerminar = true;   System.out.println(">> FIN del programa.");             break;}
                case ("v"): { mvCrearFicheroVacio();                                        break;}
                case ("l"): { mvListado();                                                  break;}     
                case ("c"): { mvConsulta(   msPedir("Clave del registro a consultar: "  )); break;}                                  
                case ("b"): { mvBaja(       msPedir("Clave del registro a BORRAR: "     )); break;} 
                case ("m"): { mvModificar(  msPedir("Clave del registro a MODIFICAR: "  ), 
                                            msPedir("Valor del registro a MODIFICAR: "  )); break;} 
                case ("f"): { mvNuevo(      msPedir("Clave del registro a AÑADIR: "      ), 
                                            msPedir("Valor del registro a AÑADIR: "     )); break;}
                case ("a"): { mvAlta(       msPedir("Clave del registro a INSERTAR: "    ), 
                                            msPedir("Valor del registro a INSERTAR: "   )); break;}
            }//switch
        }// while         
    }//mvFicherosDeTextoV1()   
    static String   msPedirOpcionDeMenuV1(){
        System.out.print( "\nMenú de Ficheros de Texto:"
            +"\n s) Salir"
            +"\n v) Crear un fichero Vacío"                
            +"\n l) Listado"  
            +"\n c) Consulta"                
            +"\n b) Baja"
            +"\n m) Modificación"                       
            +"\n f) Alta al Final"                
            +"\n a) Alta en su sitio"                
            +"\n");
        return poTeclado.nextLine();
    }//miPedirOpcionDeMenuV1()
    static String   msPedirOpcionDeMenuV2(){
        System.out.print( "\nMenú:"
            +"\n s   v   l   c    b    m   f       a:"+
             "\n Salir   Listado  Baja     Añadir  Alta" +
             "\n     Vacío   Consulta  Modificación"
            +"\n");
        return poTeclado.nextLine();
    }//miPedirOpcionDeMenuV2()
    static String   msPedir(String is){ System.out.print(is); return poTeclado.nextLine();}

    
    static void     mvCrearFicheroVacio() throws IOException {
        /*pof1= new File(psFicheroPrincipal);
        pof1.createNewFile();
        pof1= null;  */   
        (pof1= new File(psFicheroPrincipal)).createNewFile(); 
        FileWriter pofw = new FileWriter(pof1); 
        pofw.write("00000000NOMBRE++++"); 
        pofw.close();
        System.out.println(">> Se ha CREADO el fichero \'"+pof1.getName()+"\'.");
        pof1= null;
    }//mvCrearFicheroVacio
    static void     mvListado() throws IOException, FileNotFoundException  {
        System.out.println(">> COMIENZO-3 del listado.");
        pofr = new FileReader(new File(psFicheroPrincipal));
        
        int         wiCharLeidos = 0;
        String        wsRegistro = "";
        int                   wi;

        while ( ( wi=pofr.read()) != -1 ) {
            wsRegistro += (char) wi;
            if (++wiCharLeidos == 8)
                wsRegistro += "-->";
            
            if (wiCharLeidos == piTamanioDelRegistro){
                System.out.println("("+wsRegistro+")");
                wsRegistro="";
                wiCharLeidos= 0;
            }
        }//while   
        
        pofr.close();  
        System.out.println(">> FINAL del listado.\n");
    }//mvListado
    static void     mvConsulta( String isClave) throws IOException, FileNotFoundException {
        int wiClaveABuscar = Integer.parseInt(isClave);

        pofr = new FileReader(new File(psFicheroPrincipal));

        boolean   wbTerminar = false;
        boolean wbEncontrado = false;        
        int               wi;   
        int    wiCharsLeidos = 0;
        String wsCampoActual = "";
        int         wiCampo1 = 0;
        String      wsCampo2;

        while ( (wi=pofr.read()) != -1 ) {
            wsCampoActual += (char) wi; 
            wiCharsLeidos++; 

            if (wiCharsLeidos==8){
                wiCampo1= Integer.parseInt(wsCampoActual);
                wsCampoActual="";
            }
            if (wiCharsLeidos==18){
                wsCampo2      = wsCampoActual;   
                wsCampoActual = "";      
                wiCharsLeidos = 0;

                if (wiCampo1 == wiClaveABuscar){
                    System.out.print("\nValor de la clave buscada: "+wsCampo2);
                    wbEncontrado = true;
                    wbTerminar   = true;
                }
            }          
            if (wbTerminar) break;
        }//while   
        System.out.print("\n");
        if (!wbEncontrado) System.out.print(">> NO se ha ENCONTRADO el registro con clave " + isClave);
        pofr.close();      
    }//mvConsulta       
    static void     mvBaja( String isClave) throws IOException, FileNotFoundException {
        int wiClaveABuscar = Integer.parseInt(isClave);

        pof1 = new File(psFicheroPrincipal);        
        pofr = new FileReader(pof1);
        pof2 = new File(psFicheroAuxiliar);
        pofw = new FileWriter(pof2);

        boolean wbEncontrado = false;        
        int               wi;   
        int    wiCharsLeidos = 0;
        String wsCampoActual = "";
        int         wiCampo1= 0;
        String      wsCampo1= "";
        String      wsCampo2;

        while ( (wi=pofr.read()) != -1 ) {
            wsCampoActual += (char) wi; 
            wiCharsLeidos++; 

            if (wiCharsLeidos == 8){
                wsCampo1      = wsCampoActual;   
                wiCampo1      = Integer.parseInt(wsCampoActual);
                wsCampoActual ="";
            }
            if (wiCharsLeidos == 18){
                wsCampo2      = wsCampoActual;   
                wsCampoActual = "";      
                wiCharsLeidos = 0;

                if (wiCampo1 == wiClaveABuscar){
                    System.out.println(">> El Registro <"+ wiCampo1 +"> se ha ELIMINADO.");
                    wbEncontrado= true;
                    mvCopiarRestoDelFichero(pofr, pofw);
                }else{
                    pofw.write(wsCampo1+wsCampo2);
                }
            }          
        }//while   

        System.out.print("\n");
        if (!wbEncontrado) System.out.print(">> NO se ha ENCONTRADO el registro con clave " + isClave);
        pofr.close();  
        pofw.close(); 
        pof1.delete();
        pof2.renameTo(pof1);
        pof2 = null;     
        pof1= null;      
    }//mvBaja     
    static void     mvModificar( String isClave, String isValor) throws IOException, FileNotFoundException {
        int wiClaveABuscar = Integer.parseInt(isClave);
        String wsCampo2Nuevo = isValor + "          "; 
        wsCampo2Nuevo = wsCampo2Nuevo.substring(0,10);

        pof1 = new File(psFicheroPrincipal);        
        pofr = new FileReader(pof1);
        pof2 = new File(psFicheroAuxiliar);
        pofw = new FileWriter(pof2);

        boolean wbEncontrado = false;        
        int               wi;   
        int    wiCharsLeidos = 0;
        String wsCampoActual = "";
        int         wiCampo1 = 0;
        String      wsCampo1 = "";
        String      wsCampo2;

        while ( (wi=pofr.read()) != -1 ) {
            wsCampoActual += (char) wi; 
            wiCharsLeidos++; 

            if (wiCharsLeidos == 8){
                wsCampo1      = wsCampoActual;   
                wiCampo1      = Integer.parseInt(wsCampoActual);
                wsCampoActual ="";
            }
            if (wiCharsLeidos == 18){
                wsCampo2      = wsCampoActual;   
                wsCampoActual = "";      
                wiCharsLeidos = 0;

                if (wiCampo1 == wiClaveABuscar){
                    pofw.write(wsCampo1+wsCampo2Nuevo);
                    System.out.println(">> El Registro <"+ wiCampo1 +"> se ha MODIFICADO.");
                    wbEncontrado= true;
                    mvCopiarRestoDelFichero(pofr, pofw);
                }else{
                    pofw.write(wsCampo1+wsCampo2);
                }
            }          
        }//while   

        System.out.print("\n");
        if (!wbEncontrado) System.out.print(">> NO se ha ENCONTRADO el registro con clave " + isClave);
        pofr.close();  
        pofw.close(); 
        pof1.delete();
        pof2.renameTo(pof1);
        pof2 = null;     
        pof1= null;      
    }//mvModificar         
    static void     mvNuevo(    String isClave, String isValor) throws IOException, FileNotFoundException {
        int     wiClave = Integer.parseInt(isClave);
        String  wsClave = String.format("%08d", wiClave);
        String wsCampo2 = isValor + "          "; 
               wsCampo2 = wsCampo2.substring(0,10);

        pof1 = new File(psFicheroPrincipal);        
        pofw = new FileWriter(pof1, true);
        pofw.write(wsClave + wsCampo2); 
        pofw.close();
        pof1= null;      
    }//mvNuevo
    static void     mvAlta( String isClave, String isValor) throws IOException, FileNotFoundException {

        int     wiClaveNueva = Integer.parseInt(isClave);
        String  wsClaveNueva = String.format("%08d", wiClaveNueva);
        String wsCampo2Nuevo = isValor + "          "; 
               wsCampo2Nuevo = wsCampo2Nuevo.substring(0,10);
               
        pof1 = new File(psFicheroPrincipal);        
        pofr = new FileReader(pof1);
        pof2 = new File(psFicheroAuxiliar);
        pofw = new FileWriter(pof2);

        boolean wbEncontrado = false;        
        int               wi;   
        int    wiCharsLeidos = 0;
        String wsCampoActual = "";
        int         wiCampo1 = 0;
        String      wsCampo1 = "";
        String      wsCampo2 = "";
        
        while ( (wi=pofr.read()) != -1 ) {
            wsCampoActual += (char) wi; 
            wiCharsLeidos++; 

            if (wiCharsLeidos == 8){
                wsCampo1      = wsCampoActual;   
                wiCampo1      = Integer.parseInt(wsCampoActual);
                wsCampoActual ="";
            }
            if (wiCharsLeidos == 18){
                wsCampo2      = wsCampoActual;   
                wsCampoActual = "";      
                wiCharsLeidos = 0;
                if (wiCampo1 == wiClaveNueva) {
                    System.out.println("El registro ya existe!!!");
                    pofw.write(wsCampo1    +wsCampo2);
                    wbEncontrado= true;
                    mvCopiarRestoDelFichero(pofr, pofw);
                } 
                else {
                    if (wiCampo1 > wiClaveNueva){
                        pofw.write(wsClaveNueva+wsCampo2Nuevo);
                        pofw.write(wsCampo1    +wsCampo2);
                        System.out.println(">> El Registro <"+ wiCampo1 +"> se ha INSERTADO.");
                        wbEncontrado= true;
                        mvCopiarRestoDelFichero(pofr, pofw);
                    }else
                        pofw.write(wsCampo1+wsCampo2); 
                }
                              
            }          
        }//while   

        System.out.println("\n");
        if (!wbEncontrado){
            pofw.write(wsClaveNueva+wsCampo2Nuevo);
            System.out.print(">> el registro con clave " + isClave+ " se ha insertado al final.");
        }

        pofr.close();  
        pofw.close(); 
        pof1.delete();
        pof2.renameTo(pof1);
        pof2 = null;     
        pof1 = null; 
    }//mvAlta         
    static void     mvCopiarRestoDelFichero (FileReader iofr, FileWriter xofw) throws IOException, FileNotFoundException {
        int wi;
        String wsRegistro= "";
        int wiCharsLeidos=0;
        
        while ( (wi=iofr.read()) != -1 ) {
            char wc = (char) wi;
            wiCharsLeidos++;
            wsRegistro += wc;
            if (wiCharsLeidos == 18){
                xofw.write(wsRegistro); 
                wsRegistro="";
                wiCharsLeidos=0;
            }
        }//while    
    }//mvCopiarRestoDelFichero()

}//CMainFicheroTexto    
