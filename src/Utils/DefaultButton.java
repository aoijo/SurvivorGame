package Utils;

import Enums.Rarity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultButton extends JButton {
    private boolean isHovered = false;
    private int borderWidth;
    private Color borderColor;
    private Color hoverColor;

    /**
     * Creates a custom button with specified text, font, border width, text color, hover color, and border color.
     *
     * @param text        the text to be displayed on the button
     * @param font        the font to be used for the button text
     * @param borderWidth the width of the button border
     * @param textColor   the color of the button text
     * @param hoverColor  the color of the button when hovered over
     * @param borderColor the color of the button border
     */
    public DefaultButton(String text, Font font, int borderWidth, Color textColor, Color hoverColor, Color borderColor) {
        super(text);
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.hoverColor = hoverColor;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(font); // Set custom font
        setForeground(textColor); // Set custom text color

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isHovered) {
            g.setColor(hoverColor); // Hover color
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(borderColor); // Border color
        for (int i = 0; i < borderWidth; i++) {
            g.drawRect(i, i, getWidth() - 2 * i, getHeight() - 2 * i);
        }
    }

    public DefaultButton(String text) {
        this(text, new Font("Courier New", Font.PLAIN, 12), 3, Color.black, Color.LIGHT_GRAY, Color.black);
    }

    public DefaultButton(String text, Font font) {
        this(text, font, 3, Color.black, Color.LIGHT_GRAY, Color.black);
    }

    public DefaultButton(String text, Font font, Color textColor) {
        this(text, font, 3, textColor, Color.LIGHT_GRAY, Color.black);
    }

    public DefaultButton(String text, Rarity rarity) {
        this(text, new Font("Courier New", Font.PLAIN, 10), 3,
                getColorByRarity(rarity), Color.LIGHT_GRAY, Color.BLACK);
    }

    public static Color getColorByRarity(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> Color.BLACK;
            case UNCOMMON -> new Color(0, 175, 0);
            case RARE -> Color.BLUE;
            case LEGENDARY -> Color.ORANGE;
            case MYTHICAL -> Color.RED;
            case UNIQUE -> Color.MAGENTA;
            default -> Color.BLACK;
        };
    }
    public static Color getColorByRarity(int rarity) {
        return switch (rarity) {
            case 1 -> Color.BLACK;
            case 2 -> new Color(0, 175, 0);
            case 3 -> Color.BLUE;
            case 4 -> Color.ORANGE;
            case 5 -> Color.RED;
            case 0 -> Color.MAGENTA;
            default -> Color.BLACK;
        };
    }
}
