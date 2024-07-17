package UI.GameScreenPanels.Bag;

import UI.GameScreenPanels.GameScreen;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private GameScreen gameScreen;
    private CardLayout cardLayout;
    private StatsPanel statsPanel;
    private SkillPanel skillPanel;
    private BuffPanel buffPanel;

    public InformationPanel(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        statsPanel = new StatsPanel(gameScreen);
        skillPanel = new SkillPanel(gameScreen);
        buffPanel = new BuffPanel(gameScreen);

        add(statsPanel, "StatsPanel");
        add(skillPanel, "SkillPanel");
        add(buffPanel, "BuffPanel");
    }

    public void switchToScreen(String screenName){
        cardLayout.show(this, screenName);
    }
}
