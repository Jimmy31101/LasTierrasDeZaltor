package logica;

/**
 *Clase abstracta Enemigo que hereda de la clase Personaje
 * @author JMM
 */
abstract class Enemigo extends Personaje {

    public Enemigo(int id, String nombre, int vida, int ataque, int defensa, int velocidad, boolean defendiendo, int nivel, int exp) {
        super(id, nombre, vida, ataque, defensa, velocidad, defendiendo, nivel, exp);
    }
    
    public abstract void ataqueEspecial();
    
}
