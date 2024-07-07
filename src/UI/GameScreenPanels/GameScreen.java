package UI.GameScreenPanels;

import Enums.Race;
import UI.GamePanel;
import UseCase.CreateNewPlayer;
import Entity.Player;
import Enums.MapType;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private GamePanel gamePanel;
    private MapPanel mapPanel;

    public GameScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLayout(new BorderLayout());

        ControlPanel controlPanel = new ControlPanel();

        long seed = 12345L;
        Player player = CreateNewPlayer.createPlayer("Player1",new int[]{0, 0}, Color.GREEN, Race.HUMAN);
        mapPanel = new MapPanel(32, 32, MapType.ISLAND, seed, player);
        mapPanel.setPreferredSize(new Dimension(600, 600));

        JPanel logPanel = logPanel();
        JPanel statusPanel = statusPanel(player);

        add(statusPanel, BorderLayout.WEST);
        add(controlPanel, BorderLayout.EAST);
        add(mapPanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);

        // Request focus for mapPanel
        mapPanel.setFocusable(true);
        mapPanel.requestFocusInWindow();
    }

    public JPanel logPanel(){
        JPanel logPanel = new JPanel();
        return logPanel;
    }

    public JPanel statusPanel(Player player){
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);
        Font textFont = new Font("Courier New", Font.BOLD, 12);

        constraints.gridx = 0;
        constraints.gridy = 0;
        //Row 1
        statusPanel.add(createLabel("Health", textFont), constraints);
        constraints.gridy++;
        statusPanel.add(createValueLabel(player.getHealth(), player.getMaxHealth(), textFont, Color.red), constraints);

        constraints.gridx++;
        constraints.gridy--;
        statusPanel.add(createLabel("Weight", textFont), constraints);
        constraints.gridy++;
        statusPanel.add(createValueLabel(player.getWeight(), player.getMaxWeight(), textFont, Color.BLACK), constraints);

        //Row 2
        constraints.gridy++;
        constraints.gridx--;
        statusPanel.add(createLabel("Hydration", textFont), constraints);
        constraints.gridy++;
        statusPanel.add(createValueLabel(player.getHydration(), player.getMaxHydration(), textFont, Color.BLUE), constraints);

        constraints.gridx++;
        constraints.gridy--;
        statusPanel.add(createLabel("Hunger", textFont), constraints);
        constraints.gridy++;
        statusPanel.add(createValueLabel(player.getHunger(), player.getMaxHunger(), textFont, Color.ORANGE), constraints);

        //Row 3
        constraints.gridy++;
        constraints.gridx--;
        statusPanel.add(createLabel("Level", textFont), constraints);
        constraints.gridy++;
        statusPanel.add(createLabel("" + player.getLevel(),textFont), constraints);

        constraints.gridx++;
        constraints.gridy--;
        statusPanel.add(createLabel("EXP", textFont), constraints);
        constraints.gridy++;
        statusPanel.add(createValueLabel(player.getExperience(), player.getMaxExperience(), textFont, Color.BLACK), constraints);

        //Row 4
        constraints.gridy++;
        constraints.gridx--;
        statusPanel.add(createLabel("Sanity", textFont), constraints);
        constraints.gridy++;
        statusPanel.add(createValueLabel(player.getSanity(), player.getMaxSanity(), textFont, Color.BLACK), constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        statusPanel.add(new JLabel(""), constraints);
        return statusPanel;
    }

    private JLabel createLabel(String attribute, Font font) {
        JLabel label = new JLabel(attribute);
        label.setFont(font);
        return label;
    }

    private JPanel createValueLabel(int value, int maxValue, Font font, Color valueColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel valueLabel = new JLabel(String.valueOf(value));
        valueLabel.setFont(font);
        valueLabel.setForeground(valueColor);

        JLabel separatorLabel = new JLabel("/");
        separatorLabel.setFont(font);

        JLabel maxValueLabel = new JLabel(String.valueOf(maxValue));
        maxValueLabel.setFont(font);
        maxValueLabel.setForeground(valueColor);

        panel.add(valueLabel);
        if (maxValue >= 0) {
            panel.add(separatorLabel);
            panel.add(maxValueLabel);
        }

        return panel;
    }
}
