package UI.GameScreenPanels;

import Enums.RaceType;
import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.TimeAdapter;
import Enums.MapTile.MapType;
import UI.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel {
    private GamePanel gamePanel;
    private MapPanel mapPanel;
    private StatusPanel statusPanel;
    private MapPresenter mapPresenter;
    private PlayerPresenter playerPresenter;
    private MapController mapController;
    private PlayerController playerController;
    private TimeAdapter timeAdapter;
    private long mapSeed = 12345L;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.playerController = new PlayerController("Player1", Color.GREEN, RaceType.HUMAN);
        this.playerPresenter = new PlayerPresenter(playerController.getPlayerUseCase());
        this.mapController = new MapController(32, 32, MapType.ISLAND, mapSeed, playerController.getPlayerUseCase());
        this.mapPresenter = new MapPresenter(mapController.getMapUseCase());
        this.timeAdapter = new TimeAdapter();
        this.mapPanel = new MapPanel(mapPresenter, mapController, playerPresenter, playerController,10,50, 12);
        MapPanel miniMap = new MapPanel(mapPresenter, mapController, playerPresenter, playerController,4,40, 9);
        this.statusPanel = new StatusPanel(playerPresenter, mapPresenter, timeAdapter, miniMap);

        setLayout(new BorderLayout());
        JPanel logPanel = logPanel();

        ControlPanel controlPanel = new ControlPanel();

        add(statusPanel, BorderLayout.WEST);
        add(controlPanel, BorderLayout.EAST);
        add(mapPanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);

        // Set up key listener for player movement
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int[] originalPosition = playerPresenter.getPlayerPosition();
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP -> mapController.move(0, -1);
                    case KeyEvent.VK_DOWN -> mapController.move(0, 1);
                    case KeyEvent.VK_LEFT -> mapController.move(-1, 0);
                    case KeyEvent.VK_RIGHT -> mapController.move(1, 0);
                }
                moveActions(originalPosition);
            }
        });

        // Request focus for key listener
        requestFocusInWindow();
    }
    public void moveActions(int[] originalPosition){
        int[] newPosition = playerPresenter.getPlayerPosition();
        if (originalPosition[0] == newPosition[0] && originalPosition[1] == newPosition[1]) {
            timeAdapter.timePass(15);
        }
        mapPanel.repaint();
        statusPanel.updateStatusPanel();
    }

    public JPanel logPanel() {
        JPanel logPanel = new JPanel();
        return logPanel;
    }
}
