package UI.GameScreenPanels.Bag.DetailPanel;

import InterfaceAdapter.BuffAdapter;

import javax.swing.*;
import java.awt.*;

public class BuffDescriptionPanel extends JPanel {
    private DetailPanel detailPanel;
    private BuffAdapter buffAdapter;
    private BuffNamePanel buffNamePanel;
    private int currentBuffIndex;
    private Font textFont = new Font("Arial", Font.PLAIN, 13);

    public BuffDescriptionPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.buffAdapter = detailPanel.getBuffAdapter();
        this.buffNamePanel = detailPanel.getBuffNamePanel();
        this.currentBuffIndex = buffNamePanel.getCurrentBuffIndex();
        setLayout(new BorderLayout());
    }
    private JPanel createDescriptionContent() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setMinimumSize(new Dimension(280, 60));
        container.add(createBuffStatsPanel());
        container.add(createTextArea());
        return container;
    }
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(buffAdapter.getBuffDescriptionByIndex(currentBuffIndex));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(textFont);
        textArea.setBackground(null);
        textArea.setPreferredSize(new Dimension(260, 100));
        textArea.setMaximumSize(new Dimension(260, Integer.MAX_VALUE));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the top, left, bottom, and right
        return textArea;
    }

    private JPanel createBuffStatsPanel() {
        JPanel buffStatsPanel = new JPanel();
        buffStatsPanel.setLayout(new BoxLayout(buffStatsPanel, BoxLayout.Y_AXIS));
        LayoutManager layout = new FlowLayout(FlowLayout.LEFT);

        JPanel buffTypePanel = new JPanel(layout);
        buffTypePanel.add(new JLabel("  Buff Type: " + buffAdapter.getBuffTypeByIndex(currentBuffIndex)));

        JPanel stackPanel = new JPanel(layout);
        int stack = buffAdapter.getCurrentBuffStackByIndex(currentBuffIndex);
        int maxStack = buffAdapter.getBuffMaxStackByIndex(currentBuffIndex);
        if (maxStack == -1 || maxStack == Integer.MAX_VALUE) {
            stackPanel.add(new JLabel("  Current buff stack: " + stack + "/ ∞"));
        } else {
            stackPanel.add(new JLabel("  Current buff stack: " + stack + "/ " + maxStack));
        }

        buffStatsPanel.add(buffTypePanel);
        buffStatsPanel.add(stackPanel);
        return buffStatsPanel;
    }
    public void update() {
        currentBuffIndex = buffNamePanel.getCurrentBuffIndex();
        removeAll();
        add(createDescriptionContent(), BorderLayout.NORTH);
        revalidate();
        repaint();
    }
}
