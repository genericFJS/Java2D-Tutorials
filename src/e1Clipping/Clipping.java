package e1Clipping;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Clipping extends JPanel implements ActionListener {


    private int pos_x = 8;
    private int pos_y = 8;
    private int radius = 90;

    Timer timer;
    Image image;

    private double delta[] = { 3, 3 };

    public Clipping() {

        image = new ImageIcon(this.getClass().getResource("/res/castle.jpg")).getImage();

        timer = new Timer(15, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setClip(new Ellipse2D.Double(pos_x, pos_y, radius, radius));

        g2d.drawImage(image,  5, 5, null);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Clipping");
        frame.add(new Clipping());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int w = getWidth();
        int h = getHeight();

        if (pos_x < 0) {
            delta[0] = Math.random() % 4 + 5;
        } else if (pos_x > w - radius) {
            delta[0] = -(Math.random() % 4 + 5);
        }

        if (pos_y < 0 ) {
            delta[1] = Math.random() % 4 + 5;
        } else if (pos_y > h - radius) {
            delta[1] = -(Math.random() % 4 + 5);
        }

        pos_x += delta[0];
        pos_y += delta[1];

        repaint();
    }
}
