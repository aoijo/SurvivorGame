package UI.GameScreenPanels;

import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.TileAdapter;
import UI.AdapterManager;
import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TilePanel extends JPanel {
    private TileAdapter tileAdapter;
    private PlayerController playerController;
    private StatusPanel statusPanel;
    private LogPanel logPanel;
    private int[] playerPosition;

    public TilePanel(AdapterManager adapterManager, StatusPanel statusPanel, LogPanel logPanel) {
        this.statusPanel = statusPanel;
        this.logPanel = logPanel;
        this.tileAdapter = adapterManager.getTileAdapter();
        this.playerController = adapterManager.getPlayerController();
        this.playerPosition = playerController.getPlayerPosition();
        setLayout(new FlowLayout());
        //setPreferredSize(new Dimension(150,600));
        updateTilePanel();
    }

    public void updateTilePanel() {
        removeResourceButtons();
        addResourceButtons();
        repaint();
        statusPanel.updateStatusPanel();
    }

    private JButton createResourceButton(String resourceName, int resourceCount) {
        Font resourceButtonFont = new Font("Arial", Font.BOLD, 10);
        String buttonText = resourceName + String.format("(%d)", resourceCount);
        JButton resourceButton = new DefaultButton(buttonText, resourceButtonFont);
        resourceButton.setPreferredSize(new Dimension(140, 30));
        resourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerController.getPlayerUseCase().harvestResource(
                        tileAdapter.getMapUseCase().getTile(playerPosition[0], playerPosition[1]), resourceName);
                updateTilePanel();
                logPanel.addHarvestLog(resourceName);
            }
        });
        return resourceButton;
    }

    private void addResourceButtons() {
        String[] resourceNames = tileAdapter.getResourceNames(playerPosition);
        int[] resourceCount = tileAdapter.getResourcesCount(playerPosition);
        for (int i = 0; i < resourceNames.length; i++) {
            if (resourceCount[i] > 0) {
                add(createResourceButton(resourceNames[i], resourceCount[i]));
            }
        }
        if (resourceNames.length == 0){
            add(new JLabel("There is no resource left!"));
        }
    }

    private void removeResourceButtons() {
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("background.jpg").getImage();
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
