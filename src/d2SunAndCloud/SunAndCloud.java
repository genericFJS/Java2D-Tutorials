package d2SunAndCloud;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class SunAndCloud extends JPanel implements ActionListener {

    Image sun;
    Image cloud;
    Timer timer;
    private float alpha;

    public SunAndCloud() {
        sun = new ImageIcon(this.getClass().getResource("/res/sun.jpg")).getImage();
        cloud = new ImageIcon(this.getClass().getResource("/res/cloud.jpg")).getImage();
        timer = new Timer(800, this);
        timer.start();
        alpha = 1f;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        BufferedImage buffImg = new BufferedImage(220, 140,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = buffImg.createGraphics();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);

        gbi.drawImage(sun, 40, 30, null);
        gbi.setComposite(ac);
        gbi.drawImage(cloud, 0, 0, null);

        g2d.drawImage(buffImg, 20, 20, null);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sun & Cloud");
        frame.add(new SunAndCloud());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 210);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        alpha -= 0.1;
        if (alpha <= 0) {
            alpha = 0;
            timer.stop();
        }
        repaint();
    }
}