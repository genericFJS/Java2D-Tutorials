package c2FadeOut;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FadeOut extends JPanel implements ActionListener {

	Image castle;
	Timer timer;
	private float alpha = 1f;

	public FadeOut() {
		castle = new ImageIcon(this.getClass().getResource("/res/castle.jpg")).getImage();
		timer = new Timer(50, this);
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setBackground(new Color(0,0,0));
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(castle, 10, 10, null);

	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Fade out");
		frame.add(new FadeOut());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(280, 240);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		alpha += -0.01f;
		System.out.println(alpha);
		if (alpha <= 0) {
			alpha = 0;
			timer.stop();
		}
		repaint();
	}
}