package logica;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase GuerreroOscuro que hereda de la clase Enemigo
 * @author JMM
 */
class GuerreroOscuro extends Enemigo {
    
    protected int danioBase;

    public GuerreroOscuro(int id, String nombre, int danioBase, int vida, int ataque, int defensa, boolean defendiendo, int velocidad, int nivel) {
        super(0, "Guerrero Oscuro", 130, 20, 10, 5, false, 1, 50);
        
    }
    

    @Override
    public void ataqueEspecial() {
        
        ataque += 5;
    }

    @Override
    public void atacar(Personaje enemigo) {
        
        danioBase = ataque;
        danioBase = Math.max(0, danioBase);
        //System.out.println(nombre + " usa Espadazo para atacar a " + enemigo.getNombre());
        try {
            enemigo.recibirDanio(ataque);
        } catch (JuegoException ex) {
            Logger.getLogger(GuerreroOscuro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GuerreroOscuro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
