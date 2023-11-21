import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Juego extends JPanel {
    Laberinto laberinto= new Laberinto();
    Personaje personaje = new Personaje();

    public Juego(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                personaje.teclaPresionada(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setFocusable(true);
    }

    @Override
    public void paint(Graphics grafico){
        super.paint(grafico);
        laberinto.paint(grafico);
        personaje.paint(grafico);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame miniventana = new JFrame("Laberinto");
        Juego game = new Juego();
        miniventana.add(game);

        miniventana.setSize(920,540);
        miniventana.setLocation(300,200);
        miniventana.setVisible(true);

        miniventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            try {
                Thread.sleep(10);
            }catch (InterruptedException ex){
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, (String) null);
            }
            game.repaint();
        }
    }
}