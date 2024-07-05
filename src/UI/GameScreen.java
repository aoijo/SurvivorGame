package UI;

import javax.swing.*;

public class GameScreen extends JPanel {
    private GamePanel gamePanel;
    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        add(new JLabel("load game"));
    }
}

