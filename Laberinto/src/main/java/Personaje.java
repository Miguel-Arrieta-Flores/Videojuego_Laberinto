import java.awt.*;
import java.awt.event.KeyEvent;

public class Personaje {
    private Laberinto lab;
    private int x = 40;
    private int y = 40;
    private final int ancho = 40;
    private final int alto = 40;
    private final int movimiento = 40;
    private int numeroLaberinto;
    public Personaje(Laberinto laberinto, int numeroLaberinto) {
        this.lab = laberinto;
        this.numeroLaberinto = numeroLaberinto;
    }
    public void paint(Graphics grafico) {
        grafico.setColor(Color.RED);
        grafico.fillOval(x, y, ancho, alto);
        grafico.setColor(Color.BLACK);
        grafico.drawOval(x, y, ancho, alto);
    }
    public void teclaPresionada(KeyEvent evento) {
        int[][] laberinto = lab.obtieneLaberinto(numeroLaberinto);

        if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
            moverIzquierda(laberinto);
        } else if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
            moverDerecha(laberinto);
        } else if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
            moverAbajo(laberinto);
        } else if (evento.getKeyCode() == KeyEvent.VK_UP) {
            moverArriba(laberinto);
        }
    }
    private void moverIzquierda(int[][] laberinto) {
        if (x - movimiento >= 0 && laberinto[y / 40][(x / 40) - 1] != 1) {
            x = x - movimiento;
        }
    }
    private void moverDerecha(int[][] laberinto) {
        if (x + movimiento < laberinto[0].length * 40 && laberinto[y / 40][(x / 40) + 1] != 1) {
            x = x + movimiento;
        }
    }
    private void moverAbajo(int[][] laberinto) {
        if (y + movimiento < laberinto.length * 40 && laberinto[(y / 40) + 1][x / 40] != 1) {
            y = y + movimiento;
        }
    }
    private void moverArriba(int[][] laberinto) {
        if (y - movimiento >= 0 && laberinto[(y / 40) - 1][x / 40] != 1) {
            y = y - movimiento;
        }
    }
    public Point getCoordenadas() {
        return new Point(x, y);
    }
}