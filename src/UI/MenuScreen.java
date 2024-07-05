package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JPanel {
    private GamePanel gamePanel;

    public MenuScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Settings Menu"));

        JButton backButton = new JButton("Back to Title Screen");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.switchToScreen("TitleScreen");
            }
        });

        add(backButton);
    }
}

