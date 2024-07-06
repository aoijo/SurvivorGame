package UI;

import Enums.MapType;
import UI.GameScreenPanels.ControlPanel;
import UI.GameScreenPanels.MapPanel;
import UI.GameScreenPanels.StatusPanel;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private GamePanel gamePanel;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLayout(new BorderLayout());

        StatusPanel statusPanel = new StatusPanel();
        ControlPanel controlPanel = new ControlPanel();

        long seed = 12345L;
        MapPanel mapPanel = new MapPanel(32, 32, MapType.ISLAND, seed);
        mapPanel.setPlayerColor(Color.GREEN);
        mapPanel.setPreferredSize(new Dimension(200, 200));

        JPanel logPanel = logPanel();

        add(statusPanel, BorderLayout.WEST);
        add(controlPanel, BorderLayout.EAST);
        add(mapPanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);

    }


    public JPanel logPanel(){
        JPanel logPanel = new JPanel();
        return logPanel;
    }

}

