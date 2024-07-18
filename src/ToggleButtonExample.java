import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleButtonExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("JToggleButton Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Create a JToggleButton
        JToggleButton toggleButton = new JToggleButton("Toggle Me") {
            @Override
            protected void paintComponent(Graphics g) {
                if (isSelected()) {
                    setBackground(Color.GREEN); // Change to desired color
                } else {
                    setBackground(Color.LIGHT_GRAY); // Change to another color if needed
                }
                super.paintComponent(g);
            }
        };

        // Set border to null and disable focus painting
        toggleButton.setPreferredSize(new Dimension(100, 50));
        toggleButton.setBorder(new LineBorder(Color.BLACK,2 ));
        toggleButton.setFocusPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setOpaque(true);

        // Add an ActionListener to repaint when the state changes
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButton.repaint();
            }
        });

        // Add the toggle button to the frame
        frame.add(toggleButton);

        // Make the frame visible
        frame.setVisible(true);
    }
}
