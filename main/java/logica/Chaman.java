package logica;

import java.io.IOException;

/**
 *Clase Cham�n que hereda de la clase Personaje
 * @author JMM
 */
public class Chaman extends Personaje {
    
    protected int curacionMistica;
    protected int danioBase;
    protected int energia;

    public Chaman(int id, String nombre, int curacionMistica, int danioBase, int energia, int vida, boolean defendiendo, int ataque, int defensa) {
        super(id, "Chaman", 120, 12, 10, 10, false, 1, 0);
        this.curacionMistica = curacionMistica;
        this.danioBase = danioBase;
        this.energia = 50;
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
    
    public void curar() {
        
        curacionMistica = vida + 20;
    }
    
    @Override
    public void atacar(Personaje enemigo) {
        
        danioBase = ataque;
        danioBase = Math.max(0, danioBase);
        //System.out.println(nombre + " usa Bastonazo para atacar a " + enemigo.getNombre());
        try {
            enemigo.recibirDanio(danioBase);
        } catch (JuegoException | IOException ex) {
            //Logger.getLogger(Chaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
