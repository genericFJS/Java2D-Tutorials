package i4ShadowText;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class ShadowText extends JFrame {

    private int width = 490;
    private int height = 150;

    private String text = "Disziplin ist Macht.";
    private TextLayout textLayout;


    public ShadowText() throws IOException {

        BufferedImage image = createImage();
        add(new JLabel(new ImageIcon(image)));

        setTitle("Shadowed Text");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    static void setRenderingHints(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                           RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                           RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }


    public BufferedImage createImage() throws IOException {

        int x = 10;
        int y = 100;

        Font font = new Font("Georgia", Font.ITALIC, 50);
        BufferedImage image =
            new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g1 = image.createGraphics();
        setRenderingHints(g1);
        textLayout = new TextLayout(text, font, g1.getFontRenderContext());
        g1.setPaint(Color.WHITE);
        g1.fillRect(0, 0, width, height);

        g1.setPaint(new Color(150, 150, 150));
        textLayout.draw(g1, x+3, y+3);
        g1.dispose();

        float[] kernel = {
          1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 
          1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f 
        };

        ConvolveOp op =
            new ConvolveOp(new Kernel(3, 3, kernel), ConvolveOp.EDGE_NO_OP,
                           null);
        BufferedImage image2 = op.filter(image, null);

        Graphics2D g2 = image2.createGraphics();
        setRenderingHints(g2);
        g2.setPaint(Color.BLACK);
        textLayout.draw(g2, x, y);

        return image2;
    }

    public static void main(String[] args) throws IOException {
        new ShadowText();
    }
}