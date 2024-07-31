package UI.CombatPanel;

import InterfaceAdapter.CombatAdapter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CombatStatsPanel extends JPanel {
    private CombatAdapter combatAdapter;
    private boolean isPlayer;

    private JPanel attackPanel;
    private JPanel defensePanel;
    private JPanel lifeStealPanel;
    private JPanel damageReductionPanel;

    private Font textFont = new Font("Arial", Font.BOLD, 12);

    public CombatStatsPanel(CombatAdapter combatAdapter, boolean isPlayer) {
        this.combatAdapter = combatAdapter;
        this.isPlayer = isPlayer;
        this.attackPanel =attackPanel();
        this.defensePanel = defensePanel();
        this.lifeStealPanel = lifeStealPanel();
        this.damageReductionPanel = damageReductionPanel();

        attackPanel.setBorder(new MatteBorder(2, 0, 2, 2, Color.BLACK));
        defensePanel.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
        lifeStealPanel.setBorder(new MatteBorder(0, 0, 2, 2, Color.BLACK));
        damageReductionPanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));

        setLayout(new GridLayout(2,2));
        add(attackPanel);
        add(defensePanel);
        add(lifeStealPanel);
        add(damageReductionPanel);
    }

    private JPanel attackPanel(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("ATK: "+combatAdapter.getCurrentAttack(isPlayer));
        label.setFont(textFont);
        panel.add(label);
        return panel;
    }
    private JPanel defensePanel(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("DEF: "+combatAdapter.getCurrentDefence(isPlayer));
        label.setFont(textFont);
        panel.add(label);
        return panel;
    }
    private JPanel lifeStealPanel(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("LS.: "+combatAdapter.getCurrentLifeSteal(isPlayer));
        label.setFont(textFont);
        panel.add(label);
        return panel;
    }
    private JPanel damageReductionPanel(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("DR.: "+combatAdapter.getCurrentDamageReduction(isPlayer));
        label.setFont(textFont);
        panel.add(label);
        return panel;
    }
    public void update(){
        ((JLabel)attackPanel.getComponent(0)).setText("ATK: "+combatAdapter.getCurrentAttack(isPlayer));
        ((JLabel)defensePanel.getComponent(0)).setText("DEF: "+combatAdapter.getCurrentDefence(isPlayer));
        ((JLabel)lifeStealPanel.getComponent(0)).setText("LS.: "+combatAdapter.getCurrentLifeSteal(isPlayer));
        ((JLabel)damageReductionPanel.getComponent(0)).setText("DR.: "+combatAdapter.getCurrentDamageReduction(isPlayer));
        revalidate();
        repaint();
    }
}
