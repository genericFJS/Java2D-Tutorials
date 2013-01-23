package i2Soulmate;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Soulmate extends JPanel {


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        g2d.setFont(new Font("Vivaldii", Font.PLAIN, 22));

        g2d.drawString("Most relationships seem so transitory", 20, 30);
        g2d.drawString("They're all good but not the permanent one", 20, 60);
        g2d.drawString("Who doesn't long for someone to hold", 20, 90);
        g2d.drawString("Who knows how to love you without being told", 20, 120);
        g2d.drawString("Somebody tell me why I'm on my own", 20, 150);
        g2d.drawString("If there's a soulmate for everyone", 20, 180);

    }
 
    public static void main(String[] args) {

        JFrame frame = new JFrame("Soulmate");
        frame.add(new Soulmate());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}