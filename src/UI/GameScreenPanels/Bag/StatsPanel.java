package UI.GameScreenPanels.Bag;

import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsPanel extends JPanel {
    private GameScreen gameScreen;
    private GridBagConstraints constraints;
    private Font StatsFont = new Font("Arial", Font.BOLD, 12);
    private Font attributeButtonFont = new Font("Arial", Font.BOLD, 15);

    public StatsPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        initializeStatsPanel();
    }

    private void initializeStatsPanel() {
        JPanel colorPanel = createColorPanel();
        JPanel speedPanel = createAttributePanel("Speed", gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getSpeed());
        JPanel attackPanel = createAttributePanel("Attack", gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentAttack());
        JPanel defensePanel = createAttributePanel("Defense", gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentDefense());
        JPanel lifeStealPanel = createGeneralPanel("LifeSteal", "%", gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentLifeSteal());
        JPanel damageReductionPanel = createGeneralPanel("DMG Re.", "%(Max 90%)", gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentDamageReduction());
        JPanel currencyPanel = createGeneralPanel("Currency", "", gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrency());
        JPanel attributePanel = createGeneralPanel("Attribute Pt.", "", gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getAttributePoint());

        constraints.gridy = 0;
        add(createRow(colorPanel, speedPanel), constraints);
        constraints.gridy++;
        add(createRow(attackPanel, defensePanel), constraints);
        constraints.gridy++;
        add(createRow(lifeStealPanel, damageReductionPanel), constraints);
        constraints.gridy++;
        add(createRow(currencyPanel, attributePanel), constraints);
        constraints.gridy++;
        constraints.weighty = 1;
        add(new JPanel(), constraints);
        constraints.weighty = 0;
    }

    private JPanel createRow(JPanel panel1, JPanel panel2) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 2));
        rowPanel.add(panel1);
        panel1.setBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK));
        rowPanel.add(panel2);
        rowPanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        return rowPanel;
    }

    private JPanel createColorPanel() {
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel colorLabel = new JLabel("Color: ");
        colorLabel.setFont(StatsFont);
        JButton colorButton = new JButton();
        Color playerColor = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getColor();

        colorButton.setBackground(playerColor);
        Dimension colorButtonDimension = new Dimension(15, 15);
        colorButton.setMinimumSize(colorButtonDimension);
        colorButton.setPreferredSize(colorButtonDimension);
        colorButton.setMaximumSize(colorButtonDimension);

        // Add action listener to open color chooser when button is clicked
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color for player", colorButton.getBackground());
                if (newColor != null) {
                    colorButton.setBackground(newColor);
                    // Update the player's color here
                    gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().setColor(newColor);
                    gameScreen.getStatusPanel().updateStatusPanel();
                }
            }
        });

        colorPanel.add(colorLabel);
        colorPanel.add(colorButton);
        return colorPanel;
    }

    private JPanel createAttributePanel(String name, int value) {
        JPanel attributePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel attributeLabel = new JLabel(name + ": " + value);
        attributeLabel.setFont(StatsFont);

        int attributePoint = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getAttributePoint();
        JButton attributeButton = new DefaultButton("+");
        attributeButton.setFont(attributeButtonFont);
        Dimension attributeButtonDimension = new Dimension(15, 15);
        attributeButton.setMinimumSize(attributeButtonDimension);
        attributeButton.setPreferredSize(attributeButtonDimension);
        attributeButton.setMaximumSize(attributeButtonDimension);

        if (attributePoint > 0) {
            attributeButton.setForeground(Color.BLACK);
            attributeButton.setBackground(Color.GREEN);
        } else {
            attributeButton.setForeground(Color.DARK_GRAY);
            attributeButton.setBackground(Color.LIGHT_GRAY);
        }

        attributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attributePoint > 0) {
                    gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().setAttributePoint(attributePoint - 1);
                    switch (name) {
                        case "Speed":
                            gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().setSpeed(gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getSpeed() + 1);
                            break;
                        case "Attack":
                            gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().setAttack(gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getAttack() + 1);
                            break;
                        case "Defense":
                            gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().setDefense(gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getDefense() + 1);
                            break;
                    }
                }
                gameScreen.getUseCaseManager().getPlayerUseCase().updatePlayer(gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer());
                updateStatsPanel();
            }
        });
        attributePanel.add(attributeLabel);
        attributePanel.add(attributeButton);
        return attributePanel;
    }

    private JPanel createGeneralPanel(String name, String extra, int value) {
        JPanel generalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel generalLabel = new JLabel(name + ": " + value + extra);
        generalLabel.setFont(StatsFont);
        generalPanel.add(generalLabel);
        return generalPanel;
    }

    public void updateStatsPanel() {
        // Implementation to update the panel
        removeAll();
        initializeStatsPanel();
        revalidate();
        repaint();
    }
}