package j1HitTesting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class HitTesting extends JPanel {

    private Rectangle2D rect;
    private Ellipse2D ellipse;

    private float alpha_rectangle;
    private float alpha_ellipse;


    public HitTesting() {

        this.addMouseListener(new HitTestAdapter());

        rect = new Rectangle2D.Float(20f, 20f, 80f, 50f);
        ellipse = new Ellipse2D.Float(120f, 30f, 60f, 60f);

        alpha_rectangle = 1f;
        alpha_ellipse = 1f;
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(new Color(50, 50, 50));

        RenderingHints rh =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                    alpha_rectangle));
        g2d.fill(rect);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                    alpha_ellipse));
        g2d.fill(ellipse);
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Hit testing");
        frame.add(new HitTesting());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    class RectRunnable implements Runnable {

        private Thread runner;

        public RectRunnable() {
            runner = new Thread(this);
            runner.start();
        }

        public void run() {

            while (alpha_rectangle >= 0) {
                repaint();
                alpha_rectangle += -0.01f;

                if (alpha_rectangle < 0) {
                    alpha_rectangle = 0;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
        }
    }


    class HitTestAdapter extends MouseAdapter implements Runnable {

        @SuppressWarnings("unused")
		private RectRunnable rectAnimator;
        private Thread ellipseAnimator;


        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            if (rect.contains(x, y)) {

                rectAnimator = new RectRunnable();
            }

            if (ellipse.contains(x, y)) {

                ellipseAnimator = new Thread(this);
                ellipseAnimator.start();
            }
        }

        public void run() {
            while (alpha_ellipse >= 0) {

                repaint();
                alpha_ellipse += -0.01f;

                if (alpha_ellipse < 0) {
                    alpha_ellipse = 0;

                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
        }
    }
}