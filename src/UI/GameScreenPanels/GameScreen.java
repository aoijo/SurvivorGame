package UI.GameScreenPanels;

import InterfaceAdapter.UseCaseManager;
import UI.AdapterManager;
import UI.GamePanel;
import UI.GameScreenPanels.World.LogPanel;
import UI.GameScreenPanels.World.MapPanel;
import UI.GameScreenPanels.World.TilePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScreen extends JPanel {
    private GamePanel gamePanel;
    private CentrePanel centrePanel;
    private StatusPanel statusPanel;
    private TilePanel tilePanel;
    private LogPanel logPanel;

    private UseCaseManager useCaseManager;
    private AdapterManager adapterManager;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.useCaseManager = new UseCaseManager();

        useCaseManager.getPlayerUseCase().setPlayer(useCaseManager.getPlayerUseCase().newPlayer("Player 1", Color.GREEN,1));

        this.adapterManager = new AdapterManager(useCaseManager);
        this.centrePanel = new CentrePanel(this);
        this.statusPanel = new StatusPanel(this);
        this.logPanel = new LogPanel(this);
        this.tilePanel = new TilePanel(this);
        centrePanel.getDetailPanel().getUsagePanel().setLogPanel(logPanel);
        centrePanel.getDetailPanel().getUsagePanel().setStatusPanel(statusPanel);
        tilePanel.setItemPanel(centrePanel.getBagPanel().getItemPanel());

        setLayout(new BorderLayout());

        add(statusPanel, BorderLayout.WEST);
        add(centrePanel, BorderLayout.CENTER);
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
                } else {
                    logPanel.addOverWeightLog();
                }
            }
        });
    }

    public void moveActions(int[] originalPosition) {
        int[] newPosition = adapterManager.getPlayerPresenter().getPlayerPosition();
        if (originalPosition[0] == newPosition[0] && originalPosition[1] == newPosition[1]) {
            adapterManager.getTimeAdapter().timePass(15);
        }
        centrePanel.getMapPanel().repaint();
        tilePanel.updateTilePanel();
        statusPanel.updateStatusPanel();
        logPanel.addMoveLog();
    }

    public UseCaseManager getUseCaseManager() {
        return useCaseManager;
    }
    public AdapterManager getAdapterManager() {
        return adapterManager;
    }
    public CentrePanel getCentrePanel() {
        return centrePanel;
    }
    public StatusPanel getStatusPanel() {
        return statusPanel;
    }
    public TilePanel getTilePanel() {
        return tilePanel;
    }
    public LogPanel getLogPanel() {
        return logPanel;
    }
}
