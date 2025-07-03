package logica;

import java.io.IOException;

/**
 *Clase EnergiaInsuficienteException que hereda de la clase de Java Exception
 * @author JMM
 */
public class EnergiaInsuficienteException extends JuegoException {
    
    public EnergiaInsuficienteException(String mensaje) throws IOException {
        super(mensaje);
        Logger.registrarEvento("EVENTO: " + mensaje);
    }
    
}
