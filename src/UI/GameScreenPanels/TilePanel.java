package UI.GameScreenPanels;

import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.TileAdapter;
import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TilePanel extends JPanel {
    private TileAdapter tileAdapter;
    private PlayerController playerController;
    private int[] playerPosition;

    public TilePanel(TileAdapter tileAdapter, PlayerController playerController) {
        this.tileAdapter = tileAdapter;
        this.playerController = playerController;
        this.playerPosition = playerController.getPlayerPosition();
        setLayout(new FlowLayout());
        updateTilePanel();
    }

    public void updateTilePanel() {
        if (tileAdapter.getResourceNames(playerPosition).length == 0){
            setVisible(false);
        } else{
            setVisible(true);

        }
        removeResourceButtons();
        addResourceButtons();
        repaint();
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
