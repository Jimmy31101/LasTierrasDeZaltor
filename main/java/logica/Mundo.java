package logica;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Mundo donde gestionamos los mï¿½todos llamados desde la clase Juego
 *
 * @author JMM
 */
class Mundo {

    PersonajeDAO personajeDAO = new PersonajeDAO();
    CombateDAO combateDAO = new CombateDAO();
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    
    /**
     * Mï¿½todo generarEnemigoAleatorio donde generamos un enemigo aleatoriamente y retorna al enemigo generado
     * @return se retorna a uno de los 3 enemigos posibles
     * @throws IOException 
     */
    public Personaje generarEnemigoAleatorio() throws IOException {

        int option = rand.nextInt(3);
        Personaje enemigo = null;

        switch (option) {

            case 0:
                enemigo = new LoboSalvaje(0, "Lobo Salvaje", 80, 12, 5, 15, false, 1, 30);
                System.out.println("\nï¿½Ha aparecido un " + enemigo.getNombre() + "!");
                System.out.println("-------------------------------------------------------------------------");
                Logger.registrarEvento("Ha aparecido " + enemigo.nombre);
                personajeDAO.insertarEnemigo(enemigo);
                break;

            case 1:
                enemigo = new GuerreroOscuro(0, "Guerrero Oscuro", 130, 20, 10, 5, false, 1, 50);
                System.out.println("\nï¿½Ha aparecido un " + enemigo.getNombre() + "!");
                System.out.println("-------------------------------------------------------------------------");
                Logger.registrarEvento("Ha aparecido " + enemigo.nombre);
                personajeDAO.insertarEnemigo(enemigo);
                break;

            case 2:
                enemigo = new NoMuerto(0, "No Muerto", 100, 15, 8, 10, false, 1, 40);
                System.out.println("\nï¿½Ha aparecido un " + enemigo.getNombre() + "!");
                System.out.println("-------------------------------------------------------------------------");
                Logger.registrarEvento("Ha aparecido " + enemigo.nombre);
                personajeDAO.insertarEnemigo(enemigo);
                break;

            default:
                break;

        }
        return enemigo;
    }

    /**
     * Mï¿½todo turnoJugador desde el cual gestionamos las acciones que realizarï¿½ el jugador y no retorna nada
     * @param atacante pasamos por parï¿½metros al jugador como atacante
     * @param defensor pasamos por parï¿½metros al enemigo como defensor
     * @throws JuegoException
     * @throws IOException 
     */
    public void turnoJugador(Personaje atacante, Personaje defensor) throws JuegoException, IOException {

        System.out.println("\nTurno de " + atacante.getNombre());
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\nElige una acciï¿½n: ");
        System.out.println("(1) Ataque bï¿½sico\n(2) Ataque especial\n(3) Defender\n");

        int input = sc.nextInt();

        try {
            switch (input) {

                case 1:
                    System.out.println("\nHas realizado un ataque sobre " + defensor.getNombre());
                    defensor.recibirDanio(atacante.getAtaque());
                    Logger.registrarEvento(atacante.nombre + " ataca a " + defensor.nombre);
                    break;

                case 2:
                    System.out.println("\nHas realizado un ataque especial sobre " + defensor.getNombre());
                    atacante.atacar(defensor);
                    Logger.registrarEvento(atacante.nombre + " usa su ataque especial.");
                    break;

                case 3:
                    System.out.println("\nï¿½Te has defendido con ï¿½xito del ataque de " + defensor.getNombre() + "!");
                    atacante.defender();
                    Logger.registrarEvento(atacante.nombre + " se defiende de " + defensor.nombre);
                    break;

                default:
                    System.out.println("\nLa opciï¿½n seleccionada es incorrecta, intï¿½ntalo de nuevo.");
                    Logger.registrarEvento("ERROR: Opciï¿½n seleccionada incorrecta.");
                    break;
            }
        } catch (JuegoException e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * Mï¿½todo turnoEnemigo desde el cual gestionamos las acciones que realizarï¿½ el enemigo y no retorna nada
     * @param atacante pasamos por parï¿½metros al enemigo como atacante
     * @param defensor pasamos por parï¿½metros al jugador como defensor
     * @throws JuegoException
     * @throws IOException 
     */
    public void turnoEnemigo(Personaje atacante, Personaje defensor) throws JuegoException, IOException {

        System.out.println("\nTurno de " + atacante.getNombre());
        System.out.println("-------------------------------------------------------------------------");

        int input = rand.nextInt(3);

        try {
            switch (input) {

                case 0:
                    System.out.println("ï¿½" + atacante.getNombre() + " ha realizado un ataque sobre " + defensor.getNombre() + "!");
                    defensor.recibirDanio(atacante.getAtaque());
                    Logger.registrarEvento(atacante.nombre + " ataca a " + defensor.nombre);
                    break;

                case 1:
                    System.out.println("ï¿½" + atacante.getNombre() + " ha realizado un ataque contundente sobre " + defensor.getNombre() + "!");
                    atacante.atacar(defensor);
                    Logger.registrarEvento(atacante.nombre + " usa su ataque especial.");
                    break;

                case 2:
                    System.out.println("ï¿½" + atacante.getNombre() + " se ha defendido con ï¿½xito del ataque de " + defensor.getNombre());
                    atacante.defender();
                    Logger.registrarEvento(atacante.nombre + " se defiende de " + defensor.nombre);
                    break;

                default:
                    break;
            }
        } catch (JuegoException e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * Mï¿½todo iniciarCombate donde se gestiona el flujo de combate y se checkea la vida del jugador y el enemigo, y no retorna nada
     * @param jugador
     * @param enemigo
     * @throws JuegoException
     * @throws IOException 
     */
    public void iniciarCombate(Personaje jugador, Enemigo enemigo) throws JuegoException, IOException {

        System.out.println("ï¿½Comienza el combate entre " + jugador.getNombre() + " y " + enemigo.getNombre() + "!");
        System.out.println("-------------------------------------------------------------------------");

        try {

            while (jugador.estaVivo() && enemigo.estaVivo()) {

                if (jugador.getVelocidad() >= enemigo.getVelocidad()) {
                    turnoJugador(jugador, enemigo);
                    Logger.registrarEvento("Turno de " + jugador.nombre);

                    if (enemigo.estaVivo()) {
                        turnoEnemigo(enemigo, jugador);
                        Logger.registrarEvento("Turno de " + enemigo.nombre);
                    }

                } else {
                    turnoEnemigo(enemigo, jugador);
                    Logger.registrarEvento("Turno de " + jugador.nombre);

                    if (jugador.estaVivo()) {
                        turnoJugador(jugador, enemigo);
                        Logger.registrarEvento("Turno de " + enemigo.nombre);
                    }

                }
                if (!enemigo.estaVivo()) {
                    System.out.println("\n-------------------------------------------------------------------------");
                    System.out.println("ï¿½Has derrotado a " + enemigo.getNombre() + "!");
                    System.out.println("-------------------------------------------------------------------------\n");
                    Logger.registrarEvento(enemigo.nombre + " ha muerto.");
                    combateDAO.registrarCombate(jugador.getId(), enemigo.getNombre(), "¡Victoria!", 100, jugador.getVida());
                    break;
                }

                if (!jugador.estaVivo()) {
                    System.out.println("\n-------------------------------------------------------------------------");
                    System.out.println("ï¿½Has sido derrotado por " + enemigo.getNombre() + "!");
                    System.out.println("-------------------------------------------------------------------------\n");
                    Logger.registrarEvento(jugador.nombre + " ha muerto.");
                    combateDAO.registrarCombate(jugador.getId(), enemigo.getNombre(), "¡Has sido derrotado!", 0, 0);
                    break;
                }
            }
        } catch (JuegoException e) {
            System.out.println(e.getMessage());

        }
    }
 
    /**
     * Mï¿½todo explorar donde se gestionan las distintas zonas a las que puede acceder el jugador y no retorna nada
     * @param jugador
     * @throws JuegoException
     * @throws IOException 
     */
    public void explorar(Personaje jugador) throws JuegoException, IOException {

        Enemigo enemigo;

        try {

            while (jugador.estaVivo()) {
                System.out.println("\nï¿½Quï¿½ camino seguirï¿½s?");
                System.out.println("\n(1)Camino de los Sacrificios"
                        + "\n(2)Castillo de Lothdric"
                        + "\n(3)Carcel de Irithyl"
                        + "\n(4)Salir");
                
                int option = sc.nextInt();

                switch (option) {

                    case 1:
                        System.out.println("\nTe has adentrado en el Camino de los Sacrificios.");
                        System.out.println("-------------------------------------------------------------------------");
                        enemigo = (Enemigo) generarEnemigoAleatorio();
                        iniciarCombate(jugador, enemigo);
                        Logger.registrarEvento(jugador.nombre + " entra en el Camino de los sacrificios.");
                        break;

                    case 2:
                        System.out.println("\nHas atravesado las impenetrables puertas del Castillo de Lothdric.");
                        System.out.println("-------------------------------------------------------------------------");
                        enemigo = (Enemigo) generarEnemigoAleatorio();
                        iniciarCombate(jugador, enemigo);
                        Logger.registrarEvento(jugador.nombre + " entra en el Castillo de Lothdric.");
                        break;

                    case 3:
                        System.out.println("\nEstï¿½s adentrandote en lo mï¿½s profundo de la Carcel de Irithyl.");
                        System.out.println("-------------------------------------------------------------------------");
                        enemigo = (Enemigo) generarEnemigoAleatorio();
                        iniciarCombate(jugador, enemigo);
                        Logger.registrarEvento(jugador.nombre + " entra en la Carcel de Irithyl.");
                        break;

                    case 4:
                        System.out.println("Regresando al Enlace de Fuego...");
                        Logger.registrarEvento(jugador.nombre + " regresa al Enlace de Fuego.");
                        break;

                    default:
                        System.out.println("Opciï¿½n incorrecta, intï¿½ntalo de nuevo.");
                        Logger.registrarEvento("ERROR: Opciï¿½n seleccionada incorrecta.");
                        break;

                }
            }
        } catch (JuegoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Mï¿½todo estï¿½tico explorarZona donde manejamos las excepciones de las zonas bloqueadas y no retorna nada
     * @param zona
     * @throws ZonaBloqueadaException
     * @throws IOException 
     */
    public static void explorarZona(String zona) throws ZonaBloqueadaException, IOException {

        if (zona.equals("Castillo de Lothdric")) {
            String mensaje = ("ERROR: Intento de entrar al Castillo de Lothdric sin la llave Oscura.");
            Logger.registrarEvento(mensaje);
            throw new ZonaBloqueadaException(mensaje);
            
        }
        Logger.registrarEvento("Explorando la zona: " + zona);
        System.out.println("Explorando la zona: " + zona);
    }
}
