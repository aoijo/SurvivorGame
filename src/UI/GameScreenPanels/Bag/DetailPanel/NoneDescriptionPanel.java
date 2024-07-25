package UI.GameScreenPanels.Bag.DetailPanel;

import javax.swing.*;
import java.awt.*;

public class NoneDescriptionPanel extends JPanel {
    private DetailPanel detailPanel;
    private Font textFont = new Font("Arial", Font.PLAIN, 13);

    public NoneDescriptionPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.setLayout(new BorderLayout());
        add(createDescriptionContent(), BorderLayout.NORTH);
    }
    private JPanel createDescriptionContent() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setMinimumSize(new Dimension(280, 60));
        container.add(createTextArea());
        return container;
    }
    private JLabel createTextArea() {
        JLabel textArea = new JLabel("There is nothing here!");
        textArea.setFont(textFont);
        textArea.setForeground(Color.DARK_GRAY);
        textArea.setBackground(null);
        textArea.setPreferredSize(new Dimension(260, 100));
        textArea.setMaximumSize(new Dimension(260, Integer.MAX_VALUE));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the top, left, bottom, and right
        return textArea;
    }
    public void update(){
        removeAll();
        add(createDescriptionContent(), BorderLayout.NORTH);
        revalidate();
        repaint();
    }
}
