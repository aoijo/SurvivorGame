package UI.GameScreenPanels;

import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.TileAdapter;
import InterfaceAdapter.TimeAdapter;
import Enums.MapTile.MapType;
import InterfaceAdapter.UseCaseManager;
import UI.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel {
    private GamePanel gamePanel;
    private MapPanel mapPanel;
    private StatusPanel statusPanel;
    private TilePanel tilePanel;
    private MapPresenter mapPresenter;
    private PlayerPresenter playerPresenter;
    private MapController mapController;
    private PlayerController playerController;
    private TimeAdapter timeAdapter;
    private TileAdapter tileAdapter;
    private UseCaseManager useCaseManager;
    private long mapSeed = 12345L;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.useCaseManager = new UseCaseManager();
        this.playerController = new PlayerController(useCaseManager);
        this.playerPresenter = new PlayerPresenter(useCaseManager);
        this.mapController = new MapController(32, 32, MapType.ISLAND, mapSeed, useCaseManager);
        this.mapPresenter = new MapPresenter(useCaseManager);
        this.timeAdapter = new TimeAdapter(useCaseManager);
        this.tileAdapter = new TileAdapter(useCaseManager);
        this.mapPanel = new MapPanel(mapPresenter, mapController, playerPresenter, playerController,10,50, 12);
        MapPanel miniMap = new MapPanel(mapPresenter, mapController, playerPresenter, playerController,4,40, 9);
        this.statusPanel = new StatusPanel(playerPresenter, mapPresenter, timeAdapter, miniMap);
        this.tilePanel = new TilePanel(tileAdapter, playerController);

        setLayout(new BorderLayout());
        JPanel logPanel = logPanel();


        add(statusPanel, BorderLayout.WEST);
        add(tilePanel, BorderLayout.EAST);
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
        tilePanel.updateTilePanel();
        statusPanel.updateStatusPanel();
    }

    public JPanel logPanel() {
        JPanel logPanel = new JPanel();
        return logPanel;
    }
}
