package b5Textures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Textures extends JPanel {

    BufferedImage slate;
    BufferedImage java;
    BufferedImage pane;

    TexturePaint slatetp;
    TexturePaint javatp;
    TexturePaint panetp;


    public Textures() {

        try {
            slate = ImageIO.read(this.getClass().getResource("/res/slate.jpg"));
            java = ImageIO.read(this.getClass().getResource("/res/java.jpg"));
            pane = ImageIO.read(this.getClass().getResource("/res/pane.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
            super.paintComponent(g); 

            Graphics2D g2d = (Graphics2D) g;

            slatetp = new TexturePaint(slate, new Rectangle(0, 0, 90, 60));
            javatp = new TexturePaint(java, new Rectangle(0, 0, 90, 60));
            panetp = new TexturePaint(pane, new Rectangle(0, 0, 90, 60));

            g2d.setPaint(slatetp);
            g2d.fillRect(10, 15, 90, 60);

            g2d.setPaint(javatp);
            g2d.fillRect(130, 15, 90, 60);

            g2d.setPaint(panetp);
            g2d.fillRect(250, 15, 90, 60);

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Textures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Textures());
        frame.setSize(360, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}