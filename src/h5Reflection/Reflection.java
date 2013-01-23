package h5Reflection;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Reflection extends JComponent {

    private BufferedImage image;

    public Reflection() {
        try {
            image = ImageIO.read(this.getClass().getResource("/res/castle.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        int width = getWidth();
        int height = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int gap = 20;
        float opacity = 0.4f;
        float fadeHeight = 0.3f;

        g2d.setPaint(new GradientPaint(0, 0, Color.black, 0, height,
                                       Color.darkGray));
        g2d.fillRect(0, 0, width, height);
        g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight);
        g2d.drawRenderedImage(image, null);
        g2d.translate(0, 2 * imageHeight + gap);
        g2d.scale(1, -1);

        BufferedImage reflection =
            new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D rg = reflection.createGraphics();
        rg.drawRenderedImage(image, null);
        rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        rg.setPaint(new GradientPaint(0, imageHeight * fadeHeight,
                                      new Color(0.0f, 0.0f, 0.0f, 0.0f), 0,
                                      imageHeight,
                                      new Color(0.0f, 0.0f, 0.0f, opacity)));

        rg.fillRect(0, 0, imageWidth, imageHeight);
        rg.dispose();
        g2d.drawRenderedImage(reflection, null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(320, 390);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reflection");
//        Reflection r = new Reflection();
        frame.add(new Reflection());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}