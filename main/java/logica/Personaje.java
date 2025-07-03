package logica;

import java.io.IOException;

/**
 * Clase abstracta Personaje donde todas las clases hijas heredar�n variables y m�todos comunes
 * @author JMM
 */
public abstract class Personaje {
    
    protected int id;
    public String nombre;
    public int vida;
    protected int ataque;
    protected int defensa;
    protected int velocidad;
    protected boolean defendiendo;
    protected int nivel;
    protected int exp;
    
    /**
     * Constructor de la clase personaje donde se inicializan todas las variables de las clases hijas
     * @param nombre (nombre del personaje elegido)
     * @param vida (vida de cada personaje y enemigo)
     * @param ataque (cantidad de da�o de ataque de cada personaje y enemigo)
     * @param defensa (cantidad de defensa de cada personaje y enemigo)
     * @param velocidad (velocidad de movimiento de cada enemigo)
     * @param defendiendo (si defiende en su turno o no)
     * @param nivel
     * @param exp
     */
    public Personaje(int id, String nombre, int vida, int ataque, int defensa, int velocidad, boolean defendiendo, int nivel, int exp) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.defendiendo = defendiendo;
        this.nivel = nivel;
        this.exp = exp;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public int getAtaque() {
        return ataque;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getVelocidad() {
        return velocidad;
    }

    public int getVida() {
        return vida;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExp() {
        return exp;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public boolean isDefendiendo() {
        return defendiendo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setDefendiendo(boolean defendiendo) {
        this.defendiendo = defendiendo;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    
    public void setVida(int vida) {
        this.vida = vida;
    }
    
    /**
     * M�todo defender para que el personaje elija defenderse de un ataque del enemigo
     */
    public void defender() {
        defendiendo = true;
    }
    
    /**
     * M�todo para que los personajes reciban da�o teniendo en cuenta su defensa
     * @param cantidad 
     * @throws logica.JuegoException
     * @throws java.io.IOException
     */
    public void recibirDanio(int cantidad) throws JuegoException, IOException {
        
        int danioFinal = cantidad - defensa;
        
        //Si est� defendiendo el da�o se reduce a la mitad
        if (defendiendo) {
            
            danioFinal /= 2;
        }
        
        //Si la defensa es mayor o igual al da�o, no recibe da�o
        if (danioFinal < 0) {
            
            danioFinal = 0;
        }
        
        vida -= danioFinal;
        
        if (vida < 0) {
            
            throw new JuegoException(nombre + " ha sido derrotado.") {};
        }
        
        //Muestra el da�o recibido y la vida restante
        System.out.println(nombre + " ha recibido " + danioFinal + " puntos de da�o.");
        Logger.registrarEvento(nombre + " ha recibido " + danioFinal + " puntos de da�o.");
        System.out.println("Vida restante de " + nombre + ": " + vida);
        Logger.registrarEvento("Vida restante de " + nombre + ": " + vida);
        estaVivo();
    }
    
    /**
     * M�todo atacar que se sobrescribe en cada clase hija por el ataque especial
     * @param enemigo 
     */
    public abstract void atacar(Personaje enemigo);
    
    /**
     * Comprueba la vida del personaje y devuelve si est� vivo o no
     * @return retorna true o false en base a la vida que tenga el personaje o enemigo
     */
    public boolean estaVivo() {
        
        return vida > 0;
    }
}
