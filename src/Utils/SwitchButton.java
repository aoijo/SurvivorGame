package Utils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwitchButton extends JButton {
    private boolean isHovered;
    private Boolean isActive = false;
    private Color buttonColor;

    public SwitchButton(String text, Color buttonColor) {
        super(text);
        this.buttonColor = buttonColor;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setBorder(new MatteBorder(2,2,2,0,Color.black));
        setPreferredSize(new Dimension(100,30));
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
            g.setColor(buttonColor);
        }else if (isHovered) {
            g.setColor(Color.lightGray); // Hover color
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }
}
