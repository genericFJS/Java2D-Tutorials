package h4BlurredImage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BlurredImage extends JPanel {

    BufferedImage castle;
    BufferedImage filteredImage;
    Dimension size;

    public BlurredImage() {

        try {
            castle = ImageIO.read(this.getClass().getResource("/res/castle.jpg"));
        } catch (IOException e) {
            System.out.println("cannot read image");
        }

        filteredImage = new BufferedImage(castle.getWidth(null),
                                          castle.getHeight(null),
                                          BufferedImage.TYPE_BYTE_GRAY);

        size = new Dimension();
        size.width = castle.getWidth(null);
        size.height = castle.getHeight(null);
        setPreferredSize(size);

        Graphics g = filteredImage.getGraphics();
        g.drawImage(castle, 455, 255, null);

        float[] blurKernel = {
            1/9f, 1/9f, 1/9f,
            1/9f, 1/9f, 1/9f,
            1/9f, 1/9f, 1/9f
        };

        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        castle = blur.filter(castle, new BufferedImage(castle.getWidth(),
            castle.getHeight(),castle.getType()));
        g.dispose();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(castle, null, 3, 3);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Blurred Image");
        frame.add(new BlurredImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

