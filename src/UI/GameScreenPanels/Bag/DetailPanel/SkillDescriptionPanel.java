package UI.GameScreenPanels.Bag.DetailPanel;

import InterfaceAdapter.SkillAdapter;

import javax.swing.*;
import java.awt.*;

public class SkillDescriptionPanel extends JPanel {
    private DetailPanel detailPanel;
    private SkillAdapter skillAdapter;
    private SkillNamePanel skillNamePanel;
    private int currentSkillIndex;
    private Font textFont = new Font("Arial", Font.PLAIN, 13);

    public SkillDescriptionPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.skillAdapter = detailPanel.getSkillAdapter();
        this.skillNamePanel = detailPanel.getSkillNamePanel();
        this.currentSkillIndex = skillNamePanel.getCurrentSkillIndex();
        setLayout(new BorderLayout());
        add(createDescriptionContent(), BorderLayout.NORTH);
    }
    private JPanel createDescriptionContent() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setMinimumSize(new Dimension(280, 60));
        container.add(createSkillStatsPanel());
        container.add(createTextArea());
        return container;
    }

    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(skillAdapter.getPlayerSkillDescriptionByIndex(currentSkillIndex));
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

    private JPanel createSkillStatsPanel() {
        JPanel skillStatsPanel = new JPanel();
        skillStatsPanel.setLayout(new BoxLayout(skillStatsPanel, BoxLayout.Y_AXIS));
        LayoutManager layout = new FlowLayout(FlowLayout.LEFT);

        JPanel isGeneral = new JPanel(layout);
        if (skillAdapter.isPlayerSkillGeneralByIndex(currentSkillIndex)) {
            isGeneral.add(new JLabel("-- Can be used outside of Combat"));
        }else{
            isGeneral.add(new JLabel("-- Cannot be used outside of Combat"));
        }

        JPanel isCombat = new JPanel(layout);
        if (skillAdapter.isPlayerSkillCombatByIndex(currentSkillIndex)) {
            isCombat.add(new JLabel("-- Can be used during of Combat"));
        }else{
            isCombat.add(new JLabel("-- Cannot be used during of Combat"));
        }

        JPanel coolDown = new JPanel(layout);
        int currentCoolDown = skillAdapter.getPlayerCurrentSkillCooldownByIndex(currentSkillIndex);
        int maxCoolDown = skillAdapter.getPlayerSkillMaxCooldownByIndex(currentSkillIndex);
        coolDown.add(new JLabel("Ready in "+ currentCoolDown + "/" + maxCoolDown + "turns"));

        JPanel WorldCoolDown = new JPanel(layout);
        float worldCoolDown = skillAdapter.getPlayerCurrentSkillWorldCooldownByIndex(currentCoolDown);
        float maxWorldCoolDown = skillAdapter.getPlayerSkillMaxWorldCooldownByIndex(currentCoolDown);
        WorldCoolDown.add(new JLabel("Ready in "+ worldCoolDown + "/" + maxWorldCoolDown + "days"));

        skillStatsPanel.add(isGeneral);
        skillStatsPanel.add(isCombat);
        skillStatsPanel.add(coolDown);
        skillStatsPanel.add(WorldCoolDown);
        return skillStatsPanel;
    }
    public void update() {
        currentSkillIndex = skillNamePanel.getCurrentSkillIndex();
        removeAll();
        add(createDescriptionContent(), BorderLayout.NORTH);
        revalidate();
        repaint();
    }
}
