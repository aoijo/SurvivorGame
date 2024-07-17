package UI.GameScreenPanels.Bag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwitchButton extends JButton {
    private boolean isHovered;
    private Boolean isActive;

    public SwitchButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(new Font("Courier New", Font.PLAIN, 10));
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
        if (isActive) {
            g.setColor(Color.GREEN);
        }else if (isHovered) {
            g.setColor(Color.lightGray); // Hover color
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLACK); // Border color
        for (int i = 0; i < 4; i++) {
            g.drawRect(i, i, getWidth() - 2 * i, getHeight() - 2 * i);
        }
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
