import java.awt.*;

public class Laberinto {
    private static final int PARED = 1;
    private static final int SALIDA = 2;

    private final int numeroFilas = 13;
    private final int numeroColumnas = 23;
    private final int anchoBloque = 40;
    private final int altoBloque = 40;
    private int[][] laberinto;

    public Laberinto(int numeroLaberinto) {
        this.laberinto = obtieneLaberinto(numeroLaberinto);
    }

    public void paint(Graphics grafico) {
        dibujarParedes(grafico);
        dibujarSalida(grafico);
    }
    private void dibujarParedes(Graphics grafico) {
        for (int fila = 0; fila < numeroFilas; fila++) {
            for (int columna = 0; columna < numeroColumnas; columna++) {
                if (laberinto[fila][columna] == PARED) {
                    dibujarCelda(grafico, Color.GRAY, columna, fila);
                }
            }
        }
    }
    private void dibujarSalida(Graphics grafico) {
        Point coordenadasSalida = obtenerCoordenadasSalida();
        dibujarCelda(grafico, Color.GREEN, coordenadasSalida.x, coordenadasSalida.y);
    }
    private void dibujarCelda(Graphics grafico, Color color, int columna, int fila) {
        grafico.setColor(color);
        grafico.fillRect(columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
        grafico.setColor(Color.BLACK);
        grafico.drawRect(columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
    }
    private Point obtenerCoordenadasSalida() {
        for (int fila = 0; fila < numeroFilas; fila++) {
            for (int columna = 0; columna < numeroColumnas; columna++) {
                if (laberinto[fila][columna] == SALIDA) {
                    return new Point(columna, fila);
                }
            }
        }
        return new Point(-1, -1);
    }
    public boolean haLlegadoALaSalida(Personaje jugador) {
        Point coordenadasSalida = obtenerCoordenadasSalida();
        Point coordenadasJugador = jugador.getCoordenadas();
        boolean llegoASalida = coordenadasJugador.equals(new Point(coordenadasSalida.x * anchoBloque, coordenadasSalida.y * altoBloque));

        if (llegoASalida) {
            // Puedes agregar acciones adicionales aquí, como mostrar un mensaje o pasar al siguiente nivel.
            System.out.println("¡Felicidades! Has llegado a la salida del laberinto.");
        }

        return llegoASalida;
    }
    public int[][] obtieneLaberinto(int numeroLaberinto) {
        switch (numeroLaberinto) {
            case 1:
                return obtenerLaberinto1();
            case 2:
                return obtenerLaberinto2();
            case 3:
                return obtenerLaberinto3();
            case 4:
                return obtenerLaberinto4();
            default:
                throw new IllegalArgumentException("Número de laberinto no válido: " + numeroLaberinto);
        }
    }

    private int[][] obtenerLaberinto1() {
        int laberinto [][]=
                {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        { 1,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,1,1,2,0,0,1,1},
                        { 1,1,1,0,1,0,1,0,1,1,1,0,0,0,1,0,0,0,1,1,0,1,1},
                        { 1,1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,1,0,0,1,0,1,1},
                        { 1,1,0,1,1,1,1,0,1,0,0,1,1,0,1,0,0,1,0,1,0,1,1},
                        { 1,1,0,0,0,0,1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,1,1},
                        { 1,1,1,0,1,0,1,1,1,1,0,0,0,0,1,0,0,1,0,1,0,1,1},
                        { 1,1,0,0,0,1,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,1,1},
                        { 1,1,0,1,1,0,0,1,0,1,0,0,0,0,0,1,0,0,1,1,0,1,1},
                        { 1,1,0,1,0,0,1,1,0,1,0,1,1,1,1,0,0,1,0,0,0,1,1},
                        { 1,1,0,0,0,1,1,1,0,1,0,0,0,0,0,1,0,0,0,1,0,1,1},
                        { 1,1,0,1,0,0,0,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,1},
                        { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
        return laberinto;
    }

    private int[][] obtenerLaberinto2() {
        int laberinto[][]=
                {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        { 1,0,0,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,1},
                        { 1,1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1},
                        { 1,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1,1},
                        { 1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,1,0,1,0,1,1},
                        { 1,1,0,0,1,0,1,0,1,1,1,0,1,0,1,0,0,0,0,1,0,1,1},
                        { 1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1,1,1},
                        { 1,1,0,1,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,1,0,1,1},
                        { 1,1,0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1},
                        { 1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,0,1,1},
                        { 1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1},
                        { 1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,2,1},
                        { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
        return laberinto;
    }
    private int[][] obtenerLaberinto3(){
        int laberinto [][]=
                {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        { 1,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,1,1,2,0,0,1,1},
                        { 1,1,1,0,1,0,1,0,1,1,1,0,0,0,1,0,0,0,1,1,0,1,1},
                        { 1,1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,1,0,0,1,0,1,1},
                        { 1,1,0,1,1,1,1,0,1,0,0,1,1,0,1,0,0,1,0,1,0,1,1},
                        { 1,1,0,0,0,0,1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,1,1},
                        { 1,1,1,0,1,0,1,1,1,1,0,0,0,0,1,0,0,1,0,1,0,1,1},
                        { 1,1,0,0,0,1,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,1,1},
                        { 1,1,0,1,1,0,0,1,0,1,0,0,0,0,0,1,0,0,1,1,0,1,1},
                        { 1,1,0,1,0,0,1,1,0,1,0,1,1,1,1,0,0,1,0,0,0,1,1},
                        { 1,1,0,0,0,1,1,1,0,1,0,0,0,0,0,1,0,0,0,1,0,1,1},
                        { 1,1,0,1,0,0,0,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,1},
                        { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
        return laberinto;
    }
    private int[][] obtenerLaberinto4(){
        int laberinto[][]=
                {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        { 1,0,0,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,1},
                        { 1,1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1},
                        { 1,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1,1},
                        { 1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,1,0,1,0,1,1},
                        { 1,1,0,0,1,0,1,0,1,1,1,0,1,0,1,0,0,0,0,1,0,1,1},
                        { 1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1,1,1},
                        { 1,1,0,1,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,1,0,1,1},
                        { 1,1,0,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1},
                        { 1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,0,1,1},
                        { 1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1},
                        { 1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,2,1},
                        { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
        return laberinto;
    }
}