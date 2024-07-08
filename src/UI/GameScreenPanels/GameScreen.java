package UI.GameScreenPanels;

import Enums.Race;
import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import Enums.MapType;
import UI.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private GamePanel gamePanel;
    private MapPanel mapPanel;
    private MapPresenter mapPresenter;
    private PlayerPresenter playerPresenter;
    private MapController mapController;
    private PlayerController playerController;
    private long mapSeed = 12345L;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.playerController = new PlayerController("Player1", Color.GREEN, Race.HUMAN);
        this.playerPresenter = new PlayerPresenter(playerController.getPlayerUseCase());
        this.mapController = new MapController(32, 32, MapType.ISLAND, mapSeed, playerController.getPlayerUseCase());
        this.mapPresenter = new MapPresenter(mapController.getMapUseCase());
        mapPanel = new MapPanel(mapPresenter, mapController, playerPresenter, playerController);
        mapPanel.setPreferredSize(new Dimension(600, 600));

        setLayout(new BorderLayout());
        JPanel logPanel = logPanel();
        StatusPanel statusPanel = new StatusPanel(playerPresenter);
        ControlPanel controlPanel = new ControlPanel();

        add(statusPanel, BorderLayout.WEST);
        add(controlPanel, BorderLayout.EAST);
        add(mapPanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);

        // Request focus for mapPanel
        mapPanel.setFocusable(true);
        mapPanel.requestFocusInWindow();
    }

    public JPanel logPanel() {
        JPanel logPanel = new JPanel();
        return logPanel;
    }
}
