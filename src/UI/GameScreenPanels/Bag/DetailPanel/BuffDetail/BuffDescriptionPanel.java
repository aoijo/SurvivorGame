package UI.GameScreenPanels.Bag.DetailPanel.BuffDetail;

import InterfaceAdapter.BuffAdapter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;

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

        JPanel timeRemainingPanel = new JPanel(layout);
        float timeRemain = buffAdapter.getBuffTimeLeftByIndex(currentBuffIndex);

        if (timeRemain == Float.POSITIVE_INFINITY) {
            timeRemainingPanel.add(new JLabel("Time remaining: ∞"));
        } else {
            int dayCount = (int) (timeRemain / (24 * 60));
            timeRemain -= dayCount * 24 * 60;
            int hourCount = (int) (timeRemain / 60);
            timeRemain -= hourCount * 60;
            int minuteCount = (int) timeRemain;
            String timeString = String.format("  Time remain: %d day, %2d hr, %2d min", dayCount, hourCount, minuteCount);
            timeRemainingPanel.add(new JLabel(timeString));
        }
        JPanel turnRemainingPanel = new JPanel(layout);
        int turnRemain = buffAdapter.getBuffTurnLeftByIndex(currentBuffIndex);
        turnRemainingPanel.add(new JLabel("  Turn Remaining: " + turnRemain));

        buffStatsPanel.add(buffTypePanel);
        buffStatsPanel.add(stackPanel);
        buffStatsPanel.add(timeRemainingPanel);
        if (turnRemain != Integer.MIN_VALUE) {
            buffStatsPanel.add(turnRemainingPanel);
        }
        return buffStatsPanel;
    }
    private JLabel createNothingPanel(){
        JLabel textArea = new JLabel("There is nothing here!");
        textArea.setFont(new Font("Arial", Font.ITALIC, 15));
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setBackground(null);
        textArea.setPreferredSize(new Dimension(260, 100));
        textArea.setMaximumSize(new Dimension(260, Integer.MAX_VALUE));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the top, left, bottom, and right
        return textArea;
    }
    public void update() {
        removeAll();
        if (buffAdapter.checkBuffExist()){
            currentBuffIndex = buffNamePanel.getCurrentBuffIndex();
            add(createDescriptionContent(), BorderLayout.NORTH);
        }else{
            add(createNothingPanel(), BorderLayout.NORTH);
        }
        revalidate();
        repaint();
    }
}
