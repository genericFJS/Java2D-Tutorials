package a4Caps;

import java.awt.BasicStroke;
import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Caps extends JPanel {


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, 
            RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        BasicStroke bs1 = new BasicStroke(8, BasicStroke.CAP_BUTT, 
            BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        g2d.drawLine(20, 30, 250, 30);

        BasicStroke bs2 = new BasicStroke(8, BasicStroke.CAP_ROUND, 
            BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs2);
        g2d.drawLine(20, 80, 250, 80);

        BasicStroke bs3 = new BasicStroke(8, BasicStroke.CAP_SQUARE, 
            BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs3);
        g2d.drawLine(20, 130, 250, 130);

        BasicStroke bs4 = new BasicStroke();
        g2d.setStroke(bs4);

        g2d.drawLine(20, 20, 20, 140);
        g2d.drawLine(250, 20, 250, 140);
        g2d.drawLine(254, 20, 254, 140);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Caps");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Caps());
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}