import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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
        grafico.fillOval(this.x, this.y, 40, 40);
        grafico.setColor(Color.BLACK);
        grafico.drawOval(this.x, this.y, 40, 40);
    }

    public void teclaPresionada(KeyEvent evento) {
        int[][] laberinto = this.lab.obtieneLaberinto(this.numeroLaberinto);
        if (evento.getKeyCode() == 37) {
            this.moverIzquierda(laberinto);
        } else if (evento.getKeyCode() == 39) {
            this.moverDerecha(laberinto);
        } else if (evento.getKeyCode() == 40) {
            this.moverAbajo(laberinto);
        } else if (evento.getKeyCode() == 38) {
            this.moverArriba(laberinto);
        }

    }

    private void moverIzquierda(int[][] laberinto) {
        if (this.x - 40 >= 0 && laberinto[this.y / 40][this.x / 40 - 1] != 1) {
            this.x -= 40;
        }

    }

    private void moverDerecha(int[][] laberinto) {
        if (this.x + 40 < laberinto[0].length * 40 && laberinto[this.y / 40][this.x / 40 + 1] != 1) {
            this.x += 40;
        }

    }

    private void moverAbajo(int[][] laberinto) {
        if (this.y + 40 < laberinto.length * 40 && laberinto[this.y / 40 + 1][this.x / 40] != 1) {
            this.y += 40;
        }

    }

    private void moverArriba(int[][] laberinto) {
        if (this.y - 40 >= 0 && laberinto[this.y / 40 - 1][this.x / 40] != 1) {
            this.y -= 40;
        }

    }

    public Point getCoordenadas() {
        return new Point(this.x, this.y);
    }
}
