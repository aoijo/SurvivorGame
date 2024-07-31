package UI.CombatPanel;

import InterfaceAdapter.CombatAdapter;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultProgressBar;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CombatInformationPanel extends JPanel {
    private CombatAdapter combatAdapter;
    private boolean isPlayer;

    private JPanel namePanel;
    private DefaultProgressBar healthBar;
    private CombatStatsPanel statsPanel;
    private CombatBuffPanel combatBuffPanel;

    private Font nameFont = new Font("Arial", Font.ITALIC, 14);
    private GridBagConstraints constraints = new GridBagConstraints();

    public CombatInformationPanel(boolean isPlayer, CombatAdapter combatAdapter, GameScreen gameScreen) {
        this.isPlayer = isPlayer;
        this.combatAdapter = combatAdapter;
        this.healthBar = new HealthBar(combatAdapter, isPlayer);
        this.namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.statsPanel = new CombatStatsPanel(combatAdapter, isPlayer);
        this.combatBuffPanel = new CombatBuffPanel(gameScreen,combatAdapter,isPlayer);
        namePanel.add(NameLabel());

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(117,30));
        constraints.insets = new Insets(5, 5, 0, 5);
        constraints.fill = GridBagConstraints.BOTH; // Ensure horizontal fill
        constraints.weightx = 1;

        if (isPlayer){
            setBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK));
        }else{
            setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));
        }
        initializePanel();
    }

    private void initializePanel() {
        constraints.gridy = 0;
        add(namePanel, constraints);

        constraints.gridy++;
        add(healthBar, constraints);

        constraints.gridy++;
        constraints.insets = new Insets(5, 0, 0, 0);
        add(statsPanel, constraints);
        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.gridy++;
        constraints.weighty = 1;
        add(combatBuffPanel, constraints);
        constraints.weighty = 0;
    }


    private JLabel NameLabel() {
        JLabel nameLabel = new JLabel(combatAdapter.getName(isPlayer));
        nameLabel.setFont(nameFont);
        return nameLabel;
    }

    public void update(){
        healthBar.update();
        statsPanel.update();
        combatBuffPanel.update();
    }
}
