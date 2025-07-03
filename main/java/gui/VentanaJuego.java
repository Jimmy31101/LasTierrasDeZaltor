package gui;

import logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase pública VentanaJuego que hereda de la clase JFrame
 *
 * @author JMM
 */
public class VentanaJuego extends JFrame {

    private JTextArea areaLog;
    private JButton btnAtacar, btnHabilidad;
    private JLabel lblVidaJugador, lblVidaEnemigo;
    private Guerrero jugador;
    private NoMuerto enemigo;
    //private LaunchPage selPersonaje;
    private JProgressBar barraVidaJugador, barraVidaEnemigo;

    /**
     * Constructor de la clase VentanaJuego
     */
    public VentanaJuego() {

        setTitle("Las Tierras de Zaltor");
        setResizable(false);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        // Crear los personajes
        //selPersonaje = new LaunchPage();
        jugador = new Guerrero(0, "Solaire de Astora", 150, 18, 12, 6, false, 1, 0);
        enemigo = new NoMuerto(0, "No Muerto", 100, 15, 8, 10, false, 1, 40);

        // Panel de combate
        JPanel panelCombate = new JPanel(new GridLayout(1, 2));
        //panelCombate.setPreferredSize(new Dimension(700, 100));

        // Panel del jugador
        JPanel panelJugador = new JPanel();
        panelJugador.setLayout(new BoxLayout(panelJugador, BoxLayout.Y_AXIS));
        panelJugador.setBorder(BorderFactory.createTitledBorder("Jugador"));
        
        ImageIcon imgJugador = new ImageIcon("Guerrero.png");
        JLabel lblImgJugador = new JLabel(imgJugador);
        lblImgJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblVidaJugador = new JLabel("Jugador: " + jugador.nombre + " Vida: " + jugador.vida);
        lblVidaJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        barraVidaJugador = new JProgressBar(0, jugador.vida);
        barraVidaJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraVidaJugador.setForeground(Color.red);
        barraVidaJugador.setBackground(Color.black);
        barraVidaJugador.setValue(jugador.vida);
        
        panelJugador.add(lblImgJugador);
        panelJugador.add(lblVidaJugador);
        panelJugador.add(barraVidaJugador);

        // Panel del enemigo
        JPanel panelEnemigo = new JPanel();
        panelEnemigo.setLayout(new BoxLayout(panelEnemigo, BoxLayout.Y_AXIS));
        panelEnemigo.setBorder(BorderFactory.createTitledBorder("Enemigo"));
        
        ImageIcon imgEnemigo = new ImageIcon("NoMuerto.png");
        JLabel lblImgEnemigo = new JLabel(imgEnemigo);
        lblImgEnemigo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblVidaEnemigo = new JLabel("Enemigo: " + enemigo.nombre + " Vida: " + enemigo.vida);
        lblVidaEnemigo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        barraVidaEnemigo = new JProgressBar(0, enemigo.vida);
        barraVidaEnemigo.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraVidaEnemigo.setForeground(Color.red);
        barraVidaEnemigo.setBackground(Color.black);
        barraVidaEnemigo.setValue(enemigo.vida);
        
        panelEnemigo.add(lblImgEnemigo);
        panelEnemigo.add(lblVidaEnemigo);
        panelEnemigo.add(barraVidaEnemigo);

        // Área de logs
        areaLog = new JTextArea(10, 40);
        areaLog.setBackground(Color.gray);
        areaLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaLog);

        // Botones de acción
        btnAtacar = new JButton("Atacar");
        btnHabilidad = new JButton("Habilidad Especial");

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAtacar);
        panelBotones.add(btnHabilidad);

        // Acción del botón Atacar
        btnAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnAtacar) {
                    jugador.atacar(enemigo);
                    areaLog.append(jugador.nombre + " ataca a " + enemigo.nombre + "\n");
                    lblVidaEnemigo.setText("Enemigo: " + enemigo.nombre + " Vida: " + enemigo.vida);
                    barraVidaEnemigo.setValue(enemigo.vida);
                }
                if (enemigo.vida <= 0) {
                    areaLog.append("¡" + enemigo.nombre + " ha sido derrotado!\n");
                    btnAtacar.setEnabled(false);
                    btnHabilidad.setEnabled(false);

                } else {
                    enemigo.atacar(jugador);
                    areaLog.append(enemigo.nombre + " ataca a " + jugador.nombre + "\n");
                    lblVidaJugador.setText("Jugador: " + jugador.nombre + " Vida: " + jugador.vida);
                    barraVidaJugador.setValue(jugador.vida);
                }
            }
        });

        btnHabilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnHabilidad) {
                    jugador.atacar(enemigo);
                    areaLog.append(jugador.nombre + " ataca a " + enemigo.nombre + "\n");
                    lblVidaEnemigo.setText("Enemigo: " + enemigo.nombre + " Vida: " + enemigo.vida);
                    barraVidaEnemigo.setValue(enemigo.vida);
                }
                if (enemigo.vida <= 0) {
                    areaLog.append("¡" + enemigo.nombre + " ha sido derrotado!\n");
                    btnAtacar.setEnabled(false);
                    btnHabilidad.setEnabled(false);

                } else {
                    enemigo.atacar(jugador);
                    areaLog.append(enemigo.nombre + " ataca a " + jugador.nombre + "\n");
                    lblVidaJugador.setText("Jugador: " + jugador.nombre + " Vida: " + jugador.vida);
                    barraVidaJugador.setValue(jugador.vida);
                }
            }
        });

        // Agregar componentes a la ventana
        panelCombate.add(panelJugador);
        panelCombate.add(panelEnemigo);
        add(panelCombate, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * Clase pública LaunchPage que despliega una nueva ventana donde podemos
     * elegir el personaje con 3 botones que todavía no me funciona:\
     */
    /*public class LaunchPage implements ActionListener {

        JFrame frame = new JFrame();
        JButton guerrero, chaman, ninja;

        LaunchPage() {

            frame.add(guerrero);
            frame.add(chaman);
            frame.add(ninja);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(420, 420);
            frame.setBackground(Color.gray);
            frame.setVisible(true);

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == guerrero) {
                frame.dispose();
                jugador = new Guerrero("Solaire de Astora", 150, 18, 12, 6, false);
            }

            if (e.getSource() == chaman) {
                frame.dispose();
                jugador = new Guerrero("Solaire de Astora", 150, 18, 12, 6, false);
            }

            if (e.getSource() == ninja) {
                frame.dispose();
                jugador = new Guerrero("Solaire de Astora", 150, 18, 12, 6, false);
            }
        }
    }*/

    public static void main(String[] args) {

        //LaunchPage selectorPersonaje = new LaunchPage();
        SwingUtilities.invokeLater(() -> new VentanaJuego().setVisible(true));

    }
}
