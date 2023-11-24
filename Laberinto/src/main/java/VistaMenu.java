import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VistaMenu extends JFrame {
    private JLabel label1, label2,label3,espacio1,espacio2,nombre,fecha;
    private JPanel panel1;
    private GridBagLayout layout;
    Juego game = new Juego();
    public static String text="";
    Date fechaActual = new Date();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    String timeActual = formatoFecha.format(fechaActual);
    private int puntos;

    public VistaMenu() throws HeadlessException {
        layout=new GridBagLayout();
        this.setLayout(layout);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Laberinto Generativo");

        this.setIconImage(new ImageIcon("iconoL.jpg").getImage());
        GridBagConstraints gbc=new GridBagConstraints();

        panel1=new JPanel(new FlowLayout());
        panel1.setBackground(new Color(0,0,0,0));

        String rutaImagen = "iconoL.jpg";
        label1 = new JLabel();
        redimensionarYEstablecerImagen(label1, rutaImagen, 60, 60);
        label1.setBounds(0,20,130,130);
        panel1.add(label1);

        nombre=new JLabel();
        nombre.setFont(new Font("Arial", Font.BOLD, 13));
        nombre.setForeground(Color.BLACK);
        nombre.setText("VIDEOJUEGO DE LABERINTOS");
        panel1.add(nombre);

        espacio1=new JLabel();
        espacio1.setText("                         ");
        panel1.add(espacio1);

        label3=new JLabel();
        label3.setFont(new Font("Arial", Font.BOLD, 19));
        label3.setForeground(Color.BLACK);
        label3.setText("ESTRUCTURA DE DATOS 1310");
        panel1.add(label3);

        espacio2=new JLabel();
        espacio2.setText("                                    ");
        panel1.add(espacio2);

        fecha=new JLabel();
        fecha.setFont(new Font("Arial", Font.BOLD, 12));
        fecha.setForeground(Color.BLACK);
        fecha.setText("Fecha:");
        panel1.add(fecha);

        label2 = new JLabel(timeActual+" ");
        label2.setFont(new Font("Andale Mono", 3, 18));
        label2.setBounds(100,20,130,130);
        label2.setForeground(new Color(0,0,0));
        panel1.add(label2);

        panel1.setBackground(new Color(189, 124, 124));

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=0;
        gbc.gridheight=1;
        gbc.weightx=0.01;
        gbc.weighty=0.01;
        gbc.fill=GridBagConstraints.ABOVE_BASELINE;
        add(panel1,gbc);


        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.weightx=1.5;
        gbc.weighty=1.5;
        gbc.fill=GridBagConstraints.BOTH;
        add(game,gbc);



        this.setVisible(true);
    }

    public void redimensionarYEstablecerImagen(JLabel label, String rutaOriginal, int ancho, int alto) {
        try {
            // Leer la imagen original
            BufferedImage imagenOriginal = ImageIO.read(new File(rutaOriginal));

            // Redimensionar la imagen manteniendo la proporción
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

            // Crear un ImageIcon con la imagen redimensionada
            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

            // Establecer el ImageIcon en el JLabel
            label.setIcon(iconoRedimensionado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VistaMenu vistaMenu=new VistaMenu();
        vistaMenu.setBounds(0,0,935,632);
        vistaMenu.setVisible(true);
        vistaMenu.setResizable(false);
        vistaMenu.setLocationRelativeTo(null);

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, (String) null);
            }
            vistaMenu.game.repaint();
        }
    }
}
