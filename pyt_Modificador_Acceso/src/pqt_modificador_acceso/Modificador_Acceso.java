package pqt_modificador_acceso;

/**
 *
 * @author Diego Cuadrado
 */
public class Modificador_Acceso {

    public static void main(String[] args) {
        /*
                   CLASE   PQT     SUBCLASE        SUBCLASE      CLASE EXTERNA
                                  (MISMO PQT)    (DISTINTO PQT)
        public      si      si        si              si             si
        protected   si      si        si              si             no
        default     si      si        si              no             no
        private     si      no        no              no             no
               
        */
         /*
                         CLASE       ATRIBUTO       MÉTODO      
 
        default           si            si            si 
        public            si            si            si     
        private                         si            si 
        protected         si            si            si       
        static                          si            si          
        final             si            si            si    
        synchronized                                  si
        native                                        si
        abstract          si                          si
        
               
        */
    }  
}
