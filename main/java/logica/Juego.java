package logica;

import java.io.IOException;
import java.util.Scanner;

/**
 *Clase Juego desde la que se va a ejecutar el juego
 * @author JMM
 */
public class Juego {
    
    /**
     * Mï¿½todo estï¿½tico main donde ejecutamos el flujo del juego y no retorna nada
     * @param args
     * @throws JuegoException
     * @throws IOException 
     */
    public static void main(String[] args) throws JuegoException, IOException {
        
        Mundo mundo = new Mundo();
        PersonajeDAO personajeDAO = new PersonajeDAO();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n**************************************************************");
        System.out.println("Bienvenido a las tierras de Zaltor, descendiente de la ceniza.");
        System.out.println("**************************************************************");
        System.out.println("\nElige una de las siguientes clases para adentrarte en una oscura aventura y derrotar al Rey CaÃ­do, Morghan."
                + "\n(1)Guerrero"
                + "\n(2)Chamán"
                + "\n(3)Ninja");
        
        int option = sc.nextInt();
        sc.nextLine();
        
        Personaje jugador = null;
        
        switch(option) {
            
            case 1:
                jugador = new Guerrero(0, "Solaire de Astora", 150, 18, 12, 6, false, 1, 0);
                System.out.println("\nHas elegido la clase Guerrero.");
                Logger.registrarEvento("El jugador ha elegido la clase Guerrero.");
                personajeDAO.insertarPersonaje(jugador);
                break;
                
            case 2:
                jugador = new Chaman(0, "Chaman", 120, 12, 10, 10, false, 1, 0);
                System.out.println("\nHas elegido la clase ChamÃ¡n.");
                Logger.registrarEvento("El jugador ha elegido la clase Chamán.");
                personajeDAO.insertarPersonaje(jugador);
                break;
                
            case 3:
                jugador = new Ninja(0, "Ninja", 100, 15, 8, 15, false, 1, 0);
                System.out.println("\nHas elegido la clase Ninja.");
                Logger.registrarEvento("El jugador ha elegido la clase Ninja.");
                personajeDAO.insertarPersonaje(jugador);
                break;
                
            default:
                System.out.println("\nIntroduce una opción correcta.");
                
        }
        
        mundo.generarEnemigoAleatorio();
        mundo.explorar(jugador);
        
        sc.close();
    }
}
