package UI.GameScreenPanels.Bag.DetailPanel;

import InterfaceAdapter.SkillAdapter;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SkillNamePanel extends JPanel {
    private DetailPanel detailPanel;
    private int currentSkillIndex;
    private SkillAdapter skillAdapter;
    private Font nameFont = new Font("Arial", Font.BOLD, 15);

    public SkillNamePanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.skillAdapter = detailPanel.getSkillAdapter();
        this.currentSkillIndex = 0;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(280, 30));
        setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        add(createNameLabel());
    }
    private JLabel createNameLabel() {
        String name = skillAdapter.getPlayerCurrentSkillNameByIndex(currentSkillIndex);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(nameFont);
        nameLabel.setForeground(getNameColor());
        return nameLabel;
    }
    private Color getNameColor() {
        return switch (skillAdapter.getPlayerCurrentSkillRarityByIndex(currentSkillIndex)) {
            case COMMON -> Color.BLACK;
            case UNCOMMON -> new Color(0, 175, 0);
            case RARE -> Color.BLUE;
            case LEGENDARY -> Color.ORANGE;
            case MYTHICAL -> Color.RED;
            case UNIQUE -> Color.MAGENTA;
            default -> Color.BLACK;
        };
    }

    public void update(){
        removeAll();
        add(createNameLabel());
        revalidate();
        repaint();
    }
    public void setCurrentSkillIndex(int currentSkillIndex) {
        this.currentSkillIndex = currentSkillIndex;
    }
    public int getCurrentSkillIndex() {
        return currentSkillIndex;
    }
    public SkillAdapter getSkillAdapter(){
        return skillAdapter;
    }
}
