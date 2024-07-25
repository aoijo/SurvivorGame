package UI.GameScreenPanels.Bag.BagPanel;

import UI.GameScreenPanels.GameScreen;
import Utils.DefaultScrollPane;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private BagPanel bagPanel;
    private GameScreen gameScreen;
    private CardLayout cardLayout;
    private StatsPanel statsPanel;
    private SkillPanel skillPanel;
    private BuffPanel buffPanel;
    private JScrollPane skillScrollPane;
    private JScrollPane buffScrollPane;
    private EquipmentPanel equipmentPanel;

    public InformationPanel(BagPanel bagPanel){
        this.gameScreen = bagPanel.getGameScreen();
        this.bagPanel = bagPanel;
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setPreferredSize(new Dimension(300, 103));
        //setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));

        statsPanel = new StatsPanel(this);
        skillPanel = new SkillPanel(gameScreen);
        buffPanel = new BuffPanel(gameScreen);
        equipmentPanel = new EquipmentPanel();

        // Wrap BuffPanel and SkillPanel in a JScrollPane
        skillScrollPane = new DefaultScrollPane(skillPanel);
        buffScrollPane = new DefaultScrollPane(buffPanel);

        add(statsPanel, "StatsPanel");
        add(skillScrollPane, "SkillPanel");
        add(buffScrollPane, "BuffPanel"); // Add the JScrollPane instead of the BuffPanel directly
        add(equipmentPanel, "EquipmentPanel");

        //switchToScreen("BuffPanel");
    }

    public void switchToScreen(String screenName){
        cardLayout.show(this, screenName);
    }

    public String getCurrentScreenName() {
        for (Component comp : getComponents()) {
            if (comp.isVisible()) {
                if (comp == statsPanel) {
                    return "StatsPanel";
                } else if (comp == skillScrollPane) {
                    return "SkillPanel";
                } else if (comp == buffScrollPane) { // Check for the scroll pane
                    return "BuffPanel";
                } else if (comp == equipmentPanel) {
                    return "EquipmentPanel";
                }
            }
        }
        return null;
    }
    public GameScreen getGameScreen() {
        return gameScreen;
    }
    public BagPanel getBagPanel() {
        return bagPanel;
    }
    public EquipmentPanel getEquipmentPanel() {
        return equipmentPanel;
    }
    public StatsPanel getStatsPanel() {
        return statsPanel;
    }
    public SkillPanel getSkillPanel() {
        return skillPanel;
    }
    public BuffPanel getBuffPanel() {
        return buffPanel;
    }
}
