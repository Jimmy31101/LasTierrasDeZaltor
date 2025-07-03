package logica;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase LoboSalvaje que hereda de la clase Enemigo
 * @author JMM
 */
public class LoboSalvaje extends Enemigo{
    
    protected int danioBase;

    public LoboSalvaje(int id, String nombre, int danioBase, int vida, int ataque, int defensa, boolean defendiendo, int velocidad, int nivel) {
        super(0, "Lobo Salvaje", 80, 12, 5, 15, false, 1, 30);
    }
    
    
    @Override
    public void ataqueEspecial() {
        
        ataque += 2;
    }

    @Override
    public void atacar(Personaje enemigo) {
        
        danioBase = ataque;
        danioBase = Math.max(0, danioBase);
        //System.out.println(nombre + " usa Mordida para atacar a " + enemigo.getNombre());
        try {
            enemigo.recibirDanio(danioBase);
        } catch (JuegoException ex) {
            Logger.getLogger(LoboSalvaje.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoboSalvaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
