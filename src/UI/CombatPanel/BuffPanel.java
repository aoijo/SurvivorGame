package UI.CombatPanel;

import Entity.Character.Character;

import javax.swing.*;

public class BuffPanel extends JPanel {
    private Character character;
    private JPanel buffPanel;

    public BuffPanel(Character character) {
        this.character = character;
        setLayout(new ScrollPaneLayout());
        setBorder(BorderFactory.createTitledBorder("Buff"));
        JPanel buffPanel = new JPanel();
        buffPanel.setLayout(new BoxLayout(buffPanel, BoxLayout.Y_AXIS));


    }
}
