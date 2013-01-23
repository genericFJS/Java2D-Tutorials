package h2GrayImage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GrayImage extends JPanel {

    Image castle;
    BufferedImage bufferedImage;
    Dimension size;

    public GrayImage() {

        size = new Dimension();
        castle = new ImageIcon(this.getClass().getResource("/res/castle.jpg")).getImage();
        size = new Dimension();
        size.width = castle.getWidth(null);
        size.height = castle.getHeight(null);
        setPreferredSize(size);


        bufferedImage =  new BufferedImage(size.width, size.height, 
                             BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = bufferedImage.getGraphics();
        g.drawImage(castle, 0, 0, null);
        g.dispose();
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bufferedImage, null, 0, 0);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Grayscale");
        frame.add(new GrayImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
