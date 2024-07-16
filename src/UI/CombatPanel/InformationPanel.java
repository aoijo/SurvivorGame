package UI.CombatPanel;

import Entity.Character.Character;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private Character character;
    private Font nameFont = new Font("Arial", Font.ITALIC, 14);
    private GridBagConstraints constraints = new GridBagConstraints();

    public InformationPanel(Character character) {
        this.character = character;

        setLayout(new GridBagLayout());
        initializePanel();
    }

    private JLabel getNameLabel() {
        JLabel nameLabel = new JLabel(character.getName());
        nameLabel.setFont(nameFont);
        nameLabel.setPreferredSize(new Dimension(150, 30));
        return nameLabel;
    }

    private JPanel getHealthBar(){
        JPanel HealthBar = new JPanel();

        JProgressBar healthBar = new JProgressBar(0, character.getMaxHealth());
        healthBar.setValue(character.getHealth());
        healthBar.setStringPainted(false);  // Disable the default string painting
        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.RED);

        // Create the health label
        JLabel healthLabel = new JLabel(character.getHealth() + "/" + character.getMaxHealth(), SwingConstants.CENTER);
        healthLabel.setForeground(Color.BLACK);

        HealthBar.add(healthBar);
        HealthBar.add(healthLabel);
        HealthBar.setPreferredSize(new Dimension(150, 30));

        return HealthBar;
    }

    private void initializePanel(){
        constraints.insets = new Insets(10, 5, 10, 5);
        add(getNameLabel(), constraints);
        constraints.gridy ++;
        add(getHealthBar(), constraints);
        constraints.gridy ++;
        constraints.weighty = 1;
        //add(new BuffPanel(character),constraints);
        constraints.gridy ++;
        //add(new SkillPanel(character), constraints);
        constraints.gridy ++;
        add(new JLabel(""), constraints);
        constraints.weighty = 0;
    }
}
