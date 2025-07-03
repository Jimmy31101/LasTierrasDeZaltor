package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author JMM
 */
public class CombateDAO {

    public void registrarCombate(int personajeId, String enemigo, String resultado, int experienciaGanada, int vidaRestante) {
        String query = "INSERT INTO combates (personaje_id, enemigo_nombre, resultado, experiencia_ganada, vida_restante) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, personajeId);
            ps.setString(2, enemigo);
            ps.setString(3, resultado);
            ps.setInt(4, experienciaGanada);
            ps.setInt(5, vidaRestante);

            ps.executeUpdate();
            System.out.println("Combate registrado en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
