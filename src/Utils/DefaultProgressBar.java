package Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DefaultProgressBar extends JComponent {
    private int value;
    private int maxValue;

    private String text;
    private Font font = new Font("Arial", Font.BOLD, 12);
    private Color textColor = Color.BLACK;
    private Color foregroundColor = Color.GREEN;
    private Color backgroundColor = Color.RED;

    public DefaultProgressBar(int value, int maxValue, String text) {
        this.value = value;
        this.maxValue = maxValue;
        this.text = text;
        setPreferredSize(new Dimension(100, 20));
        setBorder(new LineBorder(Color.black, 3));
    }

    public DefaultProgressBar(int value, int maxValue) {
        this(value, maxValue, "");
    }
    public DefaultProgressBar() {
        this(0, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int healthWidth = (int) ((double) value / maxValue * width);

        // Draw background
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, width, height);

        // Draw foreground
        g2.setColor(foregroundColor);
        g2.fillRect(0, 0, healthWidth, height);

        // Draw border
        g2.setColor(Color.black);
        g2.drawRect(0, 0, width - 1, height - 1);

        // Draw text
        g2.setFont(font);
        g2.setColor(textColor);
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = (height + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(text, x, y);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public void update() {
        revalidate();
        repaint();
    }
}
