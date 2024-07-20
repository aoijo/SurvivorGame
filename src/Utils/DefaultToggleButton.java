package Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultToggleButton extends JToggleButton {
    boolean isHovered = false;
    private String selectedText;
    private String unselectedText;
    private Color selectedColor;
    private Color unselectedColor;
    private Dimension size = new Dimension(80,20);

    public DefaultToggleButton(String selectedText, String unselectedText, Color selectedCcolor, Color unselectedCcolor, Font font) {
        super(unselectedText);
        this.selectedText = selectedText;
        this.unselectedText = unselectedText;
        this.selectedColor = selectedCcolor;
        this.unselectedColor = unselectedCcolor;

        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);

        setBackground(unselectedCcolor);
        setFont(font);
        setBorder(new LineBorder(Color.black,2));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

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

        // Add an ActionListener to repaint when the state changes
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
    }

    public DefaultToggleButton(String selectedText, String unselectedText) {
        this(selectedText, unselectedText, Color.GREEN, Color.WHITE, new Font("Arial", Font.BOLD, 12));
    }
    public DefaultToggleButton(String selectedText, String unselectedText, Color selectedCcolor) {
        this(selectedText, unselectedText, selectedCcolor, Color.WHITE, new Font("Arial", Font.BOLD, 12));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected()) {
            setBackground(selectedColor); // Change to desired color
            setText(selectedText);
        } else if(isHovered){
            setBackground(Color.LIGHT_GRAY);
            setText(unselectedText);
        }else {
            setBackground(unselectedColor); // Change to another color if needed
            setText(unselectedText);
        }
        super.paintComponent(g);
    }
    public void setSelectedColor(Color selectedCcolor) {
        this.selectedColor = selectedCcolor;
    }
}
