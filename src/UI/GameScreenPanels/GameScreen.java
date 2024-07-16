package UI.GameScreenPanels;

import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.TileAdapter;
import InterfaceAdapter.TimeAdapter;
import Enums.MapTile.MapType;
import InterfaceAdapter.UseCaseManager;
import UI.AdapterManager;
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
    private LogPanel logPanel;

    private UseCaseManager useCaseManager;
    private AdapterManager adapterManager;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.useCaseManager = new UseCaseManager();

        //For Testing
        useCaseManager.getPlayerUseCase().setPlayer(useCaseManager.getPlayerUseCase().newPlayer("Player 1", Color.GREEN,1));

        this.adapterManager = new AdapterManager(useCaseManager);
        this.mapPanel = new MapPanel(adapterManager, 10, 50, 12);
        MapPanel miniMap = new MapPanel(adapterManager, 4, 40, 9);
        this.statusPanel = new StatusPanel(adapterManager, miniMap);
        this.logPanel = new LogPanel(adapterManager);
        this.tilePanel = new TilePanel(adapterManager, statusPanel, logPanel);

        //Testing Ends

        setLayout(new BorderLayout());

        add(statusPanel, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);
        tilePanel.updateTilePanel();
        add(tilePanel, BorderLayout.EAST);

        // Set up key listener for player movement
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int[] originalPosition = adapterManager.getPlayerPresenter().getPlayerPosition();
                int width = adapterManager.getMapPresenter().getMapWidth();
                int height = adapterManager.getMapPresenter().getMapHeight();
                int x = originalPosition[0];
                int y = originalPosition[1];
                int key = e.getKeyCode();

                if (adapterManager.getPlayerPresenter().getPlayerUseCase().getPlayer().getWeight() <=
                        adapterManager.getPlayerPresenter().getPlayerUseCase().getPlayer().getMaxWeight()){
                    switch (key) {
                        case KeyEvent.VK_UP -> {
                            if (y > 0) {
                                adapterManager.getMapController().move(0, -1);
                                moveActions(originalPosition);
                            }
                        }
                        case KeyEvent.VK_DOWN -> {
                            if (y < height - 1) {
                                adapterManager.getMapController().move(0, 1);
                                moveActions(originalPosition);
                            }
                        }
                        case KeyEvent.VK_LEFT -> {
                            if (x > 0) {
                                adapterManager.getMapController().move(-1, 0);
                                moveActions(originalPosition);
                            }
                        }
                        case KeyEvent.VK_RIGHT -> {
                            if (x < width - 1) {
                                adapterManager.getMapController().move(1, 0);
                                moveActions(originalPosition);
                            }
                        }
                    }
                }
            }
        });
        // Request focus for key listener
        requestFocusInWindow();
    }

    public void moveActions(int[] originalPosition) {
        int[] newPosition = adapterManager.getPlayerPresenter().getPlayerPosition();
        if (originalPosition[0] == newPosition[0] && originalPosition[1] == newPosition[1]) {
            adapterManager.getTimeAdapter().timePass(15);
        }
        mapPanel.repaint();
        tilePanel.updateTilePanel();
        statusPanel.updateStatusPanel();
        logPanel.addMoveLog();
    }

    public JPanel logPanel() {
        JPanel logPanel = new JPanel();
        return logPanel;
    }

    public UseCaseManager getUseCaseManager() {
        return useCaseManager;
    }
    public AdapterManager getAdapterManager() {
        return adapterManager;
    }
}
