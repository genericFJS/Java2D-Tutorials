package f4Shear;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Shear extends JPanel {

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;


        AffineTransform tx1 = new AffineTransform();
        tx1.translate(50, 90);

        g2d.setTransform(tx1);
        g2d.setColor(Color.green);
        g2d.drawRect(0, 0, 160, 50);

        AffineTransform tx2 = new AffineTransform();
        tx2.translate(50, 90);
        tx2.shear(0, 1);

        g2d.setTransform(tx2);
        g2d.setColor(Color.blue);

        g2d.draw(new Rectangle(0, 0, 80, 50));

        AffineTransform tx3 = new AffineTransform();
        tx3.translate(130, 10);
        tx3.shear(0, 1);

        g2d.setTransform(tx3);
        g2d.setColor(Color.red);
        g2d.drawRect(0, 0, 80, 50);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Shearing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Shear());
        frame.setSize(330, 270);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
