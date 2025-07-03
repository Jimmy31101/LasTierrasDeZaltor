package logica;

import java.io.IOException;

/**
 *Clase Ninja que hereda de la clase Personaje
 * @author JMM
 */
public class Ninja extends Personaje {
    
    protected int ataqueSigiloso;
    protected int danioBase;
    protected int energia;

    public Ninja(int id, String nombre, int ataqueSigiloso, int danioBase, int energia, int vida, boolean defendiendo, int ataque, int defensa) {
        super(0, "Ninja", 100, 15, 8, 15, false, 1, 0);
        this.ataqueSigiloso = ataqueSigiloso;
        this.danioBase = danioBase;
        this.energia = 60;
    }
    
    
    public void usarHabilidadEspecial() throws EnergiaInsuficienteException, IOException {
        
        if(energia < 20) {
            String mensaje = nombre + " no tiene suficiente energ�a para usar su habilidad especial.";
            Logger.registrarEvento("ERROR: " + mensaje);
            throw new EnergiaInsuficienteException(mensaje);
        }
        
        energia -= 20;
        Logger.registrarEvento(nombre + " usa su habilidad especial.");
        System.out.println(nombre + " usa una habilidad especial devastadora");
    }
    
    public void ataqueSigiloso() {
        
        ataqueSigiloso = ataque + 5;
    }

    @Override
    public void atacar(Personaje enemigo) {
        
        danioBase = ataque;
        danioBase = Math.max(0, danioBase);
        //System.out.println(nombre + " usa Tajo Sigiloso para atacar a " + enemigo.getNombre());
        try {
            enemigo.recibirDanio(danioBase);
        } catch (JuegoException | IOException ex) {
            //Logger.getLogger(Ninja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
