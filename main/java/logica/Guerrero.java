package logica;

import java.io.IOException;
import java.util.logging.Level;

/**
 *Clase Guerrero que hereda de la clase Personaje
 * @author JMM
 */
public class Guerrero extends Personaje {

    protected int golpeDevastador;
    protected int danioBase;
    protected int energia;

    public Guerrero(int id, String nombre, int golpeDevastador, int danioBase, int energia, int vida, boolean defendiendo, int ataque, int defensa) {
        super(0, "Solaire de Astora", 150, 18, 12, 6, false, 1, 0);
        this.golpeDevastador = golpeDevastador;
        this.danioBase = danioBase;
        this.energia = 50;
    }
    
    public void usarHabilidadEspecial(Enemigo enemigo) throws EnergiaInsuficienteException, IOException {
        
        if(energia < 20) {
            String mensaje = nombre + " no tiene suficiente energía para usar su habilidad especial.";
            Logger.registrarEvento("ERROR: " + mensaje);
            throw new EnergiaInsuficienteException(mensaje);
        }
        energia -= 20;
        Logger.registrarEvento(nombre + " usa su habilidad especial.");
        System.out.println(nombre + " usa una habilidad especial devastadora");
    }
    
    public void golpeDevastador() {

        golpeDevastador = ataque * 2;
    }

    @Override
    public void atacar(Personaje enemigo) {
        
        danioBase = ataque;
        danioBase = Math.max(0, danioBase);
        //System.out.println(nombre + " usa Golpe Devastador para atacar a " + enemigo.getNombre());
        try {
            enemigo.recibirDanio(danioBase);
        } catch (JuegoException ex) {
            //Logger.getLogger(Guerrero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Guerrero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
