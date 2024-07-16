package UI;

import Entity.Character.Enemy;
import UI.CombatPanel.CombatScreen;
import UI.GameScreenPanels.GameScreen;
import Utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private GameScreen gameScreen;
    private CombatScreen combatScreen;
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
        Enemy testEnemy = this.getGameScreen().getUseCaseManager().getEnemyUseCase().newEnemy(1,false,false);
        combatScreen = new CombatScreen(this.getGameScreen().getUseCaseManager().getPlayerUseCase().getPlayer(),testEnemy);


        // Add different screens to mainPanel
        mainPanel.add(titleScreen, "TitleScreen");
        mainPanel.add(gameScreen, "GameScreen");
        mainPanel.add(settingScreen, "SettingScreen");
        mainPanel.add(createCharScreen, "CreateCharScreen");
        mainPanel.add(combatScreen, "CombatScreen");

        // Add mainPanel to frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Optionally, switch to GameScreen immediately to test
        switchToScreen("CombatScreen");
    }

    public void switchToScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }
    public GameScreen getGameScreen() {
        return gameScreen;
    }
    public CombatScreen getCombatScreen() {
        return combatScreen;
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



