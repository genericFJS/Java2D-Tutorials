package j3ResizeRectangle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ResizeRectangle extends JPanel {

    private Point2D[] points;
    private int SIZE = 8;
    private int pos;


    public ResizeRectangle() {

        addMouseListener(new ShapeTestAdapter());
        addMouseMotionListener(new ShapeTestAdapter());
        pos = -1;

        points = new Point2D[2];
        points[0] = new Point2D.Double(50, 50);
        points[1] = new Point2D.Double(150, 100);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < points.length; i++) {
            double x = points[i].getX() - SIZE / 2;
            double y = points[i].getY() - SIZE / 2;
            g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
        }

        Rectangle2D s = new Rectangle2D.Double();
        s.setFrameFromDiagonal(points[0], points[1]);

        g2.draw(s);
    }


    private class ShapeTestAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent event) {

            Point p = event.getPoint();

            for (int i = 0; i < points.length; i++) {
                double x = points[i].getX() - SIZE / 2;
                double y = points[i].getY() - SIZE / 2;

                Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);

                if (r.contains(p)) {
                    pos = i;
                    return;
                }
            }
        }

        public void mouseReleased(MouseEvent event) {
            pos = -1;
        }

        public void mouseDragged(MouseEvent event) {
            if (pos == -1)
                return;

            points[pos] = event.getPoint();
            repaint();
        }
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Resize Rectangle");

        frame.add(new ResizeRectangle());
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
