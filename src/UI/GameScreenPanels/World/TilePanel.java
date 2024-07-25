package UI.GameScreenPanels.World;

import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.TileAdapter;
import UI.AdapterManager;
import UI.GameScreenPanels.Bag.BagPanel.ItemPanel;
import UI.GameScreenPanels.GameScreen;
import UI.GameScreenPanels.StatusPanel;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TilePanel extends JPanel {
    private TileAdapter tileAdapter;
    private PlayerController playerController;
    private StatusPanel statusPanel;
    private LogPanel logPanel;
    private ItemPanel itemPanel;
    private int[] playerPosition;
    private Font titleFont = new Font("Arial", Font.BOLD, 15);
    private Font buttonFont = new Font("Arial", Font.BOLD, 10);
    private GridBagConstraints constraints;

    public TilePanel(GameScreen gameScreen) {
        this.statusPanel = gameScreen.getStatusPanel();
        this.logPanel = gameScreen.getLogPanel();
        this.tileAdapter = gameScreen.getAdapterManager().getTileAdapter();
        this.playerController = gameScreen.getAdapterManager().getPlayerController();
        this.playerPosition = playerController.getPlayerPosition();

        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);

        setPreferredSize(new Dimension(150,600));
        updateTilePanel();
    }

    public void updateTilePanel() {
        removeButtons();
        addTitle("Resources");
        addResourceButtons();
        addTitle("Enemies");
        addEnemyButtons();
        pushToTop(); // Remove this line
        statusPanel.updateStatusPanel();
        repaint();
    }


    private void addTitle(String title){
        constraints.gridy ++;
        constraints.gridheight = 1;
        JPanel resourceTitle = new JPanel();
        resourceTitle.setLayout(new FlowLayout());
        JLabel resourceTitleLabel = new JLabel(title + ": ");
        resourceTitleLabel.setFont(titleFont);
        resourceTitle.add(resourceTitleLabel);
        resourceTitle.setPreferredSize(new Dimension(140, 30));
        add(resourceTitle, constraints);
    }

    private JButton createResourceButton(String resourceName, int resourceCount) {
        String buttonText = resourceName + String.format("(%d)", resourceCount);
        JButton resourceButton = new DefaultButton(buttonText, buttonFont);

        resourceButton.setPreferredSize(new Dimension(140, 30));
        resourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerController.getPlayerUseCase().harvestResource(
                        tileAdapter.getMapUseCase().getTile(playerPosition[0], playerPosition[1]), resourceName);
                updateTilePanel();
                itemPanel.updateItemPanel();
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
                constraints.gridy++;
                add(createResourceButton(resourceNames[i], resourceCount[i]), constraints);
            }
        }
        if (isAllZeros(resourceCount)) {
            constraints.gridy++;
            JLabel noResources = new JLabel("No resource left!");
            noResources.setFont(new Font("Arial", Font.ITALIC, 15));
            noResources.setForeground(Color.LIGHT_GRAY);
            add(noResources, constraints);
        }
    }

    private JButton createEnemyButton(String enemyName) {
        JButton enemyButton = new DefaultButton(enemyName, buttonFont);

        enemyButton.setPreferredSize(new Dimension(140, 30));
        enemyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.out.println("Started battle with " + enemyName);
                updateTilePanel();
            }
        });
        return enemyButton;
    }

    private void addEnemyButtons() {
        String[] enemyNames = tileAdapter.getEnemyNames(playerPosition);

        for (String enemyName : enemyNames) {
            constraints.gridy++;
            add(createEnemyButton(enemyName), constraints);
        }
        if (enemyNames.length == 0) {
            constraints.gridy++;
            JLabel noEnemy = new JLabel("No enemy left!");
            noEnemy.setFont(new Font("Arial", Font.ITALIC, 15));
            noEnemy.setForeground(Color.LIGHT_GRAY);
            add(noEnemy, constraints);
        }
    }

    private void removeButtons() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        removeAll();
    }

    private void pushToTop(){
        constraints.gridy++;
        constraints.weighty = 1;
        add(new JLabel(""), constraints);
        constraints.weighty = 0;
    }

    private boolean isAllZeros(int[] array) {
        for (int num : array) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("background.jpg").getImage();
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setItemPanel(ItemPanel itemPanel) {
        this.itemPanel = itemPanel;
    }
}
