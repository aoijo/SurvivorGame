package UI;

import javax.swing.*;

public class CreateCharScreen extends JPanel {
    private GamePanel gamePanel;
    public CreateCharScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        add(new JLabel("newCharScreen"));
    }
}
