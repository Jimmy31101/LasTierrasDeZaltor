package logica;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase NoMuerto que hereda de la clase Enemigo
 * @author JMM
 */
public class NoMuerto extends Enemigo {
    
    protected int danioBase;

    public NoMuerto(int id, String nombre, int vida, int ataque, int defensa, int velocidad, boolean defendiendo, int nivel, int exp) {
        super(0, "No Muerto", 100, 15, 8, 10, false, 1, 40);
    }
    
    
    @Override
    public void ataqueEspecial() {
        
        vida += 10;
    }

    @Override
    public void atacar(Personaje enemigo) {
        
        danioBase = ataque;
        danioBase = Math.max(0, danioBase);
        //ataqueEspecial();
        //System.out.println(nombre + " ataca a " + enemigo.getNombre() + " usa Curar para recuperar 10 puntos de salud.");
        try {
            enemigo.recibirDanio(danioBase);
        } catch (JuegoException ex) {
            Logger.getLogger(NoMuerto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NoMuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
