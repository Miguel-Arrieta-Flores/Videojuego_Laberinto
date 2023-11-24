import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Juego extends JPanel {
    private Laberinto laberinto;
    private Personaje personaje;
    private int nivelSeleccionado = 1;
    private EstadoJuego estadoJuego;
    private int opcionSeleccionada;
    private boolean nivelCompletado;
    private int puntaje;

    public Juego() {
        this.estadoJuego = Juego.EstadoJuego.MENU;
        this.opcionSeleccionada = 0;
        this.nivelCompletado = false;
        this.puntaje=0;
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (Juego.this.estadoJuego == Juego.EstadoJuego.MENU) {
                    if (e.getKeyCode() == 38) {
                        Juego.this.actualizarOpcionSeleccionada(-1);
                    } else if (e.getKeyCode() == 40) {
                        Juego.this.actualizarOpcionSeleccionada(1);
                    } else if (e.getKeyCode() == 10) {
                        if (Juego.this.opcionSeleccionada == 0) {
                            Juego.this.estadoJuego = Juego.EstadoJuego.JUEGO;
                            Juego.this.iniciarJuego();
                        } else if (Juego.this.opcionSeleccionada == 1) {
                            System.exit(0);
                        }

                        Juego.this.repaint();
                    }
                } else if (Juego.this.estadoJuego == Juego.EstadoJuego.JUEGO) {
                    Juego.this.personaje.teclaPresionada(e);
                    if (Juego.this.laberinto.haLlegadoALaSalida(Juego.this.personaje)) {
                        Juego.this.nivelCompletado = true;
                        Juego.this.estadoJuego = Juego.EstadoJuego.FINALIZADO;
                        Juego.this.repaint();
                    }
                } else if (Juego.this.estadoJuego == Juego.EstadoJuego.FINALIZADO) {
                    if (e.getKeyCode() != 38 && e.getKeyCode() != 40) {
                        if (e.getKeyCode() == 10) {
                            if (Juego.this.opcionSeleccionada == 0) {
                                ++Juego.this.nivelSeleccionado;
                                Juego.this.iniciarJuego();
                                Juego.this.estadoJuego = Juego.EstadoJuego.JUEGO;
                                Juego.this.nivelCompletado = false;
                            } else if (Juego.this.opcionSeleccionada == 1) {
                                System.exit(0);
                            }

                            Juego.this.repaint();
                        }
                    } else {
                        Juego.this.actualizarOpcionSeleccionada(1);
                        Juego.this.repaint();
                    }
                }

            }

            public void keyReleased(KeyEvent e) {
            }
        });
        this.setFocusable(true);
    }

    private void iniciarJuego() {
        this.laberinto = new Laberinto(this.nivelSeleccionado);
        this.personaje = new Personaje(this.laberinto, this.nivelSeleccionado);
        this.nivelCompletado = false;
        this.puntaje += 100;
        this.repaint();
    }
    private void actualizarOpcionSeleccionada(int direccion) {
        this.opcionSeleccionada = (this.opcionSeleccionada + direccion + 2) % 2;
    }

    private void mostrarMenuInicio(Graphics g) {
        ImageIcon fondo = new ImageIcon("nuevo3.jpg");
        g.drawImage(fondo.getImage(), 0, 0, 930, 650, this);
        String titulo = "Laberinto Generativo";
        String comenzar = "Comenzar";
        String salir = "Salir";
        Font font = new Font("Arial", 1, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(font);
        int xTitulo = (this.getWidth() - metrics.stringWidth(titulo)) / 2;
        int y = this.getHeight() / 2;
        g.drawString(titulo, xTitulo, y - 50);
        g.drawString(comenzar, xTitulo, y + 20);
        g.drawString(salir, xTitulo, y + 80);
        int xComenzar = xTitulo - 30;
        int xSalir = xTitulo - 20;
        g.drawString(this.opcionSeleccionada == 0 ? "->" : "  ", xComenzar, y + 20);
        g.drawString(this.opcionSeleccionada == 1 ? "->" : "  ", xSalir, y + 80);
    }

    private void mostrarVentanaFinalizacion(Graphics g) {
        ImageIcon fondo = new ImageIcon("reintentar.jpg");
        g.drawImage(fondo.getImage(), 0, 0, 920, 525, this);
        String mensaje = this.nivelCompletado ? "¡Nivel completado!" : "¡Juego completado!";
        String siguienteNivel = this.nivelCompletado ? "Siguiente Nivel" : "Salir";
        String salir = "Salir";
        Font font = new Font("Arial", 1, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (this.getWidth() - metrics.stringWidth(mensaje)) / 2;
        int y = this.getHeight() / 2;
        g.drawString(mensaje, x, y - 50);
        g.drawString(siguienteNivel, x, y + 20);
        g.drawString(salir, x, y + 80);
        int xSiguienteNivel = x - 30;
        int xSalir = x - 30;
        g.drawString(this.opcionSeleccionada == 0 ? "->" : "  ", xSiguienteNivel, y + 20);
        g.drawString(this.opcionSeleccionada == 1 ? "->" : "  ", xSalir, y + 80);
        g.drawString("Puntaje: " + this.puntaje, x, y + 120);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.estadoJuego == Juego.EstadoJuego.MENU) {
            this.mostrarMenuInicio(g);
        } else if (this.estadoJuego == Juego.EstadoJuego.JUEGO) {
            this.laberinto.paint(g);
            this.personaje.paint(g);
        } else if (this.estadoJuego == Juego.EstadoJuego.FINALIZADO) {
            this.mostrarVentanaFinalizacion(g);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame miniventana = new JFrame("Laberinto Generativo");
        Juego game = new Juego();
        miniventana.add(game);
        miniventana.setSize(940, 580);
        miniventana.setLocation(200, 30);
        miniventana.setVisible(true);
        miniventana.setDefaultCloseOperation(3);

        while(true) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException var4) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, (String)null);
            }

            game.repaint();
        }
    }

    public int puntaje(int puntos) {
        if (this.nivelCompletado) {
            this.puntaje += puntos;
        }
        return this.puntaje;
    }

    private static enum EstadoJuego {
        MENU,
        JUEGO,
        FINALIZADO;

        private EstadoJuego() {
        }
    }
}