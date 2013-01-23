package g3Puff;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Puff extends JPanel implements ActionListener {

    Timer timer;
    int x = 1;
    float alpha = 1;

    public Puff() {
        timer = new Timer(8, this);
        timer.setInitialDelay(190);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Font font = new Font("Dialog", Font.PLAIN, x);
        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics();
        String s = "ZetCode";
        Dimension size = getSize();

        int w = (int) size.getWidth();
        int h = (int) size.getHeight();

        int stringWidth = fm.stringWidth(s);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
             alpha));

        g2d.drawString(s, (w - stringWidth) / 2, h / 2);
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Puff");
        frame.add(new Puff());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        x += 1;

        if (x > 40)
            alpha -= 0.01;

        if (alpha <= 0.01)
            timer.stop();
        repaint();
    }
}