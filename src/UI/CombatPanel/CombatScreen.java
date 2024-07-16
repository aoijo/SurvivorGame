package UI.CombatPanel;

import Entity.Character.Enemy;
import Entity.Character.Player;


import javax.swing.*;
import java.awt.*;

public class CombatScreen extends JPanel {
    private Player player;
    private Enemy enemy;

    private MainCombatPanel mainCombatPanel;
    private InformationPanel enemyInformationPanel;
    private InformationPanel playerInformationPanel;

    public CombatScreen(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.mainCombatPanel = new MainCombatPanel(this);
        this.enemyInformationPanel = new InformationPanel(enemy);
        this.playerInformationPanel = new InformationPanel(player);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 1;
        add(playerInformationPanel, constraints);
        constraints.weightx = 2;
        constraints.gridx ++;
        add(mainCombatPanel,constraints);
        constraints.weightx = 1;
        constraints.gridx ++;
        add(enemyInformationPanel, constraints);
    }
}
