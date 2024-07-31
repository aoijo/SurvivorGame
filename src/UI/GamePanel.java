package UI;

import Entity.Character.Enemy;
import UI.CombatPanel.CombatPanel;
import UI.GameScreenPanels.GameScreen;
import Utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private GameScreen gameScreen;
    private TitleScreen titleScreen;
    private SettingScreen settingScreen;
    private CreateCharScreen createCharScreen;


    public GamePanel() {
        frame = new JFrame("Survivor Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(GraphicsUtils.loadImage("Images/placeHolder64x64.png"));
        frame.setSize(950, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        gameScreen = new GameScreen(this);
        titleScreen = new TitleScreen(this);
        settingScreen = new SettingScreen(this);
        createCharScreen = new CreateCharScreen(this);

        // Add different screens to mainPanel
        mainPanel.add(titleScreen, "TitleScreen");
        mainPanel.add(gameScreen, "GameScreen");
        mainPanel.add(settingScreen, "SettingScreen");
        mainPanel.add(createCharScreen, "CreateCharScreen");

        // Add mainPanel to frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Optionally, switch to GameScreen immediately to test
        switchToScreen("GameScreen");
    }

    public void switchToScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }
    public GameScreen getGameScreen() {
        return gameScreen;
    }
    public TitleScreen getTitleScreen() {
        return titleScreen;
    }
    public CreateCharScreen getCreateCharScreen() {
        return createCharScreen;
    }
    public SettingScreen getSettingScreen() {
        return settingScreen;
    }
}



