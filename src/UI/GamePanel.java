package UI;

import UI.GameScreenPanels.GameScreen;
import Utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public GamePanel() {
        frame = new JFrame("Survivor Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(GraphicsUtils.loadImage("Images/placeHolder64x64.png"));
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different screens to mainPanel
        mainPanel.add(new TitleScreen(this), "TitleScreen");
        mainPanel.add(new GameScreen(this), "GameScreen");
        mainPanel.add(new SettingScreen(this), "SettingScreen");
        mainPanel.add(new CreateCharScreen(this), "CreateCharScreen");

        // Add mainPanel to frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Optionally, switch to GameScreen immediately to test
        switchToScreen("GameScreen");
    }

    public void switchToScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }
}



