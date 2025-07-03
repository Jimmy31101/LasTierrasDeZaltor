package logica;

import java.io.IOException;

/**
 *Clase JuegoException que hereda de la clase de Java Exception
 * @author JMM
 */
public abstract class JuegoException extends Exception {

    public JuegoException(String mensaje) throws IOException {
        super(mensaje);
        Logger.registrarEvento("EVENTO: " + mensaje);
    }
    
}
