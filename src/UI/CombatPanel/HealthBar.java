package UI.CombatPanel;

import InterfaceAdapter.CombatAdapter;
import Utils.DefaultProgressBar;

import java.awt.*;

public class HealthBar extends DefaultProgressBar {
    private CombatAdapter combatAdapter;
    private boolean isPlayer;
    private int health;
    private int maxHealth;

    public HealthBar(CombatAdapter combatAdapter, boolean isPlayer) {
        super();
        this.combatAdapter = combatAdapter;
        this.isPlayer = isPlayer;
        setMinimumSize(new Dimension(100,20));
        setPreferredSize(new Dimension(100,20));

        update();
    }
    public void update(){
        this.health = combatAdapter.getHealth(isPlayer);
        this.maxHealth = combatAdapter.getCurrentMaxHealth(isPlayer);
        setValue(health);
        setMaxValue(maxHealth);
        setText(health + " / " + maxHealth);
        revalidate();
        repaint();
    }
}
