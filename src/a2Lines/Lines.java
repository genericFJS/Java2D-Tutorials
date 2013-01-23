package a2Lines;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Lines extends JFrame {

    int coords[][];
    int count;

    public Lines() {
        initUI();
    }

    public final void initUI() {

        coords = new int[100][2];
        count = 0;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        setTitle("Lines");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {

                    int x = event.getX();
                    int y = event.getY();

                    coords[count][0] = x;
                    coords[count][1] = y;
                    count++;
                }

                if (event.getButton() == MouseEvent.BUTTON3) {
                    drawLines();
                    count = 0;
                }
            }
        });
    }

    public void drawLines() {

        Graphics g = this.getGraphics();

        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        RenderingHints rh =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        g2d.clearRect(0, 0, w, h);


        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1; j++) {
                g2d.drawLine(coords[i][0], coords[i][1],
                        coords[j][0], coords[j][1]);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Lines lns = new Lines();
                lns.setVisible(true);
            }
        });

    }
}