package logica;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextArea;

/**
 * Clase Logger donde creamos y registramos los eventos ocurridos en el juego en
 * el archivo "logs.txt"
 *
 * @author JMM
 */
public class Logger {

    private static final String LOG_FILE = "logs.txt";

    public static void registrarEvento(String mensaje) throws IOException {

        registrarEvento(mensaje, null);
    }

    /**
     * Método estático registrarEvento donde gestionamos la creación y el
     * formato del archivo "logs.txt", y no retorna nada
     *
     * @param mensaje
     * @param areaLog
     * @throws IOException
     */
    public static void registrarEvento(String mensaje, JTextArea areaLog) throws IOException {

        String tiempo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String log = "[" + tiempo + "] " + mensaje;

        // Escribir en archivo
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(log + "\n");
        } catch (IOException e) {
            areaLog.append("Error al escribir en log: " + e.getMessage() + "\n");
        }

        if (areaLog != null) {

            // Mostrar en el área de texto
            areaLog.append(log + "\n");
        }

    }
}
