package logica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static logica.ConexionDB.getConnection;

/**
 *
 * @author JMM
 */
public class PersonajeDAO {
    
    public void insertarPersonaje(Personaje personaje) {
        
        String query = "INSERT INTO personajes (nombre, vida, ataque, defensa, velocidad, nivel, experiencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
                
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, personaje.getNombre());
            ps.setInt(2, personaje.getVida());
            ps.setInt(3, personaje.getAtaque());
            ps.setInt(4, personaje.getDefensa());
            ps.setInt(5, personaje.getVelocidad());
            ps.setInt(6, personaje.getNivel());
            ps.setInt(7, personaje.getExp());
            
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                personaje.setId(idGenerado); // << Aquí se guarda el id en el objeto
                System.out.println("ID del personaje generado: " + idGenerado);
            }
        }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertarEnemigo(Personaje enemigo) {
        
        String query = "INSERT INTO enemigos (nombre, vida, ataque, defensa, velocidad, nivel, experiencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
                
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, enemigo.getNombre());
            ps.setInt(2, enemigo.getVida());
            ps.setInt(3, enemigo.getAtaque());
            ps.setInt(4, enemigo.getDefensa());
            ps.setInt(5, enemigo.getVelocidad());
            ps.setInt(6, enemigo.getNivel());
            ps.setInt(7, enemigo.getExp());
            
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                enemigo.setId(idGenerado); // << Aquí se guarda el id en el objeto
                System.out.println("ID del personaje generado: " + idGenerado);
            }
        }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Personaje obtenerPersonaje(int id) {
        
        String query = "SELECT * FROM personajes WHERE id = ?";
        Personaje personaje = null;
 
        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                personaje = instanciarPersonaje(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("vida"),
                        rs.getInt("ataque"),
                        rs.getInt("defensa"),
                        rs.getInt("velocidad"),
                        rs.getInt("nivel"),
                        rs.getInt("experiencia")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personaje;
    }
    
    public boolean eliminarPersonaje(int id) throws SQLException {
        
        String query = "DELETE FROM personajes WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            
            
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
                    
        } catch (SQLException e) {
            System.err.println("Error al eliminar el personaje: " + e.getMessage());
        }
        
        return false;
    }
    
    public static Personaje instanciarPersonaje(int id, String nombre, int vida, int ataque, int defensa, int velocidad, int nivel, int exp) {
        
        switch(nombre.toLowerCase()) {
            
            case "solaire de astora":
                return new Guerrero(0, "Solaire de Astora", 150, 18, 12, 6, false, 1, 0);
                
            case "chaman":
                return new Chaman(0, "Chaman", 120, 12, 10, 10, false, 1, 0);
            
            case "ninja":
                return new Ninja(0, "Ninja", 100, 15, 8, 15, false, 1, 0);
                
            default:
                return null;
        }
    }
}
