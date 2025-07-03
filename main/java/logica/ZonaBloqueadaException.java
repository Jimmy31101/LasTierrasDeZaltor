package logica;


import java.io.IOException;

/**
 *Clase ZonaBloqueadaException que hereda de la clase de Java Exception
 * @author JMM
 */
public class ZonaBloqueadaException extends JuegoException{
    
    public ZonaBloqueadaException(String mensaje) throws IOException {
        super(mensaje);
        Logger.registrarEvento("EVENTO: " + mensaje);
    }
    
}
