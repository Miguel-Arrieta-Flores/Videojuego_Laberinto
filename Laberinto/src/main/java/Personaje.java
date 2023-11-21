import java.awt.*;
import java.awt.event.KeyEvent;

public class Personaje {
    Laberinto lab=new Laberinto();
    private int x=40;
    private int y=40;
    private final int ancho=40;
    private final int alto=40;
    private final int movieminto=40;
        public void paint(Graphics grafico){
        grafico.setColor(Color.RED);
        grafico.fillOval(x,y,ancho,alto);
        grafico.setColor(Color.BLACK);
        grafico.drawOval(x,y,ancho,alto);
    }
    public void teclaPresionada(KeyEvent evento){
         int [][] laberinto= lab.obtieneLaberinto();
         if(evento.getKeyCode()==37){//Izquierda
             if(laberinto[y/40][(x/40)-1]!=1){
                 x=x-movieminto;
             }
         }
         if(evento.getKeyCode()==39){//Derecha
             if(laberinto[y/40][(x/40)+1]!=1) {
                 x = x + movieminto;
             }
         }
         if(evento.getKeyCode()==40){//Abajo
             if(laberinto[(y/40)+1][x/40]!=1) {
                 y = y + movieminto;
             }
         }
         if(evento.getKeyCode()==38){//Arriba
             if(laberinto[(y/40)-1][x/40]!=1) {
                 y = y - movieminto;
             }
         }
    }
}
