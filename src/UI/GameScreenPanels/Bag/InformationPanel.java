package UI.GameScreenPanels.Bag;

import UI.GameScreenPanels.GameScreen;
import Utils.DefaultScrollPane;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class InformationPanel extends JPanel {
    private CardLayout cardLayout;
    private StatsPanel statsPanel;
    private JScrollPane skillPanel;
    private JScrollPane buffPanel;

    public InformationPanel(GameScreen gameScreen){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setPreferredSize(new Dimension(300, 103));
        setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));

        statsPanel = new StatsPanel(gameScreen);

        // Wrap BuffPanel and SkillPanel in a JScrollPane
        buffPanel = new DefaultScrollPane(new BuffPanel(gameScreen));
        skillPanel = new DefaultScrollPane(new SkillPanel(gameScreen));

        add(statsPanel, "StatsPanel");
        add(skillPanel, "SkillPanel");
        add(buffPanel, "BuffPanel"); // Add the JScrollPane instead of the BuffPanel directly

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
                } else if (comp == skillPanel) {
                    return "SkillPanel";
                } else if (comp == buffPanel) { // Check for the scroll pane
                    return "BuffPanel";
                }
            }
        }
        return null;
    }
}
