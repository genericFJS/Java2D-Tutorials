package i5TextAttributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;

import java.text.AttributedString;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TextAttributes extends JPanel {

  String words = "Valour fate kinship darkness";
  String java = "Java TM";

  public void paint(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    Font font = new Font("Serif", Font.PLAIN, 40);

    AttributedString as1 = new AttributedString(words);
    as1.addAttribute(TextAttribute.FONT, font);

    as1.addAttribute(TextAttribute.FOREGROUND, Color.red, 0, 6);
    as1.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 7, 11);
    as1.addAttribute(TextAttribute.BACKGROUND, Color.LIGHT_GRAY, 12, 19);
    as1.addAttribute(TextAttribute.STRIKETHROUGH,
        TextAttribute.STRIKETHROUGH_ON, 20, 28);

    g2d.drawString(as1.getIterator(), 15, 60);

    AttributedString as2 = new AttributedString(java);

    as2.addAttribute(TextAttribute.SIZE, 40);
    as2.addAttribute(TextAttribute.SUPERSCRIPT, 
        TextAttribute.SUPERSCRIPT_SUPER, 5, 7);

    g2d.drawString(as2.getIterator(), 130, 125);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Text attributes");
        frame.add(new TextAttributes());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(620, 190);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
  }
}