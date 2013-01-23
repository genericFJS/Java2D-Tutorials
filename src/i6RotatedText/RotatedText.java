package i6RotatedText;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RotatedText extends JPanel {

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    String s = "ZetCode, tutorials for programmers";

    Font font = new Font("Courier", Font.PLAIN, 12);

    g2d.translate(20, 20);

    FontRenderContext frc = g2d.getFontRenderContext();

    GlyphVector gv = font.createGlyphVector(frc, s);
    int length = gv.getNumGlyphs();

    for (int i = 0; i < length; i++) {
      Point2D p = gv.getGlyphPosition(i);
      double theta = (double) i / (double) (length - 1) * Math.PI / 3;
      AffineTransform at = AffineTransform.getTranslateInstance(p.getX(),
          p.getY());
      at.rotate(theta);

      Shape glyph = gv.getGlyphOutline(i);
      Shape transformedGlyph = at.createTransformedShape(glyph);
      g2d.fill(transformedGlyph);
    }
  }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotated text");
        frame.add(new RotatedText());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
  }
}
