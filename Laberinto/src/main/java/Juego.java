import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Juego extends JPanel {
    private Laberinto laberinto;
    private Personaje personaje;
    private int nivelSeleccionado = 1;
    private EstadoJuego estadoJuego = EstadoJuego.MENU;
    private int opcionSeleccionada = 0;  // Variable para rastrear la opción seleccionada en el menú
    private boolean nivelCompletado = false;  // Variable para rastrear si el nivel está completado

    private enum EstadoJuego {
        MENU, JUEGO, FINALIZADO
    }

    public Juego() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (estadoJuego == EstadoJuego.MENU) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        actualizarOpcionSeleccionada(-1);
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        actualizarOpcionSeleccionada(1);
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (opcionSeleccionada == 0) {
                            estadoJuego = EstadoJuego.JUEGO;
                            iniciarJuego();
                        } else if (opcionSeleccionada == 1) {
                            System.exit(0);  // Salir del juego
                        }
                        repaint();
                    }
                } else if (estadoJuego == EstadoJuego.JUEGO) {
                    personaje.teclaPresionada(e);

                    // Verificar si el jugador ha llegado a la salida
                    if (laberinto.haLlegadoALaSalida(personaje)) {
                        nivelCompletado = true;
                        estadoJuego = EstadoJuego.FINALIZADO;
                        repaint();  // Vuelve a dibujar el panel para mostrar la ventana de finalización
                    }
                } else if (estadoJuego == EstadoJuego.FINALIZADO) {
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                        actualizarOpcionSeleccionada(1);
                        repaint();  // Vuelve a dibujar el panel para reflejar el cambio en la opción seleccionada
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Si el juego está finalizado y se presiona ENTER, realizar la acción según la opción seleccionada
                        if (opcionSeleccionada == 0) {
                            nivelSeleccionado++;
                            iniciarJuego();
                            estadoJuego = EstadoJuego.JUEGO;
                            nivelCompletado = false;
                        } else if (opcionSeleccionada == 1) {
                            System.exit(0);  // Salir del juego
                        }
                        repaint();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
    }

    private void iniciarJuego() {
        laberinto = new Laberinto(nivelSeleccionado);
        personaje = new Personaje(laberinto, nivelSeleccionado);
        repaint();
    }

    private void actualizarOpcionSeleccionada(int direccion) {
        opcionSeleccionada = (opcionSeleccionada + direccion + 2) % 2;
    }

    private void mostrarMenuInicio(Graphics g) {
        String titulo = "Laberinto Generativo";
        String comenzar = "Comenzar";
        String salir = "Salir";

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.BLACK);

        FontMetrics metrics = g.getFontMetrics(font);
        int xTitulo = (getWidth() - metrics.stringWidth(titulo)) / 2;
        int y = getHeight() / 2;

        g.drawString(titulo, xTitulo, y - 50);
        g.drawString(comenzar, xTitulo, y + 20);
        g.drawString(salir, xTitulo, y + 80);

        int xComenzar = xTitulo - 30;
        int xSalir = xTitulo - 30;

        g.drawString(opcionSeleccionada == 0 ? "->" : "  ", xComenzar, y + 20);
        g.drawString(opcionSeleccionada == 1 ? "->" : "  ", xSalir, y + 80);
    }

    private void mostrarVentanaFinalizacion(Graphics g) {
        String mensaje = nivelCompletado ? "¡Nivel completado!" : "¡Juego completado!";
        String siguienteNivel = nivelCompletado ? "Siguiente Nivel" : "Salir";
        String salir = "Salir";  // Agregado: Texto para la opción "Salir"

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.BLACK);

        FontMetrics metrics = g.getFontMetrics(font);
        int x = (getWidth() - metrics.stringWidth(mensaje)) / 2;
        int y = getHeight() / 2;

        g.drawString(mensaje, x, y - 50);
        g.drawString(siguienteNivel, x, y + 20);
        g.drawString(salir, x, y + 80);  // Agregado: Dibuja el texto para la opción "Salir"

        // Dibuja flechas indicativas junto a las opciones del menú de finalización
        int xSiguienteNivel = x - 30;
        int xSalir = x - 30;

        g.drawString(opcionSeleccionada == 0 ? "->" : "  ", xSiguienteNivel, y + 20);
        g.drawString(opcionSeleccionada == 1 ? "->" : "  ", xSalir, y + 80);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (estadoJuego == EstadoJuego.MENU) {
            mostrarMenuInicio(g);
        } else if (estadoJuego == EstadoJuego.JUEGO) {
            laberinto.paint(g);
            personaje.paint(g);
        } else if (estadoJuego == EstadoJuego.FINALIZADO) {
            mostrarVentanaFinalizacion(g);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame miniventana = new JFrame("Laberinto Generativo");
        Juego game = new Juego();
        miniventana.add(game);

        miniventana.setSize(937, 560);
        miniventana.setLocation(300, 200);
        miniventana.setVisible(true);

        miniventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, (String) null);
            }
            game.repaint();
        }
    }
}