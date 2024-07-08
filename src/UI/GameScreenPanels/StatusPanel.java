package UI.GameScreenPanels;

import InterfaceAdapter.PlayerPresenter;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    public StatusPanel(PlayerPresenter playerPresenter) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);
        Font textFont = new Font("Courier New", Font.BOLD, 12);

        constraints.gridx = 0;
        constraints.gridy = 0;
        // Row 1
        add(createLabel("Health", textFont), constraints);
        constraints.gridy++;
        add(createValueLabel(playerPresenter.getHealth(), playerPresenter.getMaxHealth(), textFont, Color.red), constraints);

        constraints.gridx++;
        constraints.gridy--;
        add(createLabel("Weight", textFont), constraints);
        constraints.gridy++;
        add(createValueLabel(playerPresenter.getWeight(), playerPresenter.getMaxWeight(), textFont, Color.BLACK), constraints);

        // Row 2
        constraints.gridy++;
        constraints.gridx--;
        add(createLabel("Hydration", textFont), constraints);
        constraints.gridy++;
        add(createValueLabel(playerPresenter.getHydration(), playerPresenter.getMaxHydration(), textFont, Color.BLUE), constraints);

        constraints.gridx++;
        constraints.gridy--;
        add(createLabel("Hunger", textFont), constraints);
        constraints.gridy++;
        add(createValueLabel(playerPresenter.getHunger(), playerPresenter.getMaxHunger(), textFont, Color.ORANGE), constraints);

        // Row 3
        constraints.gridy++;
        constraints.gridx--;
        add(createLabel("Level", textFont), constraints);
        constraints.gridy++;
        add(createLabel("" + playerPresenter.getLevel(), textFont), constraints);

        constraints.gridx++;
        constraints.gridy--;
        add(createLabel("EXP", textFont), constraints);
        constraints.gridy++;
        add(createValueLabel(playerPresenter.getExperience(), playerPresenter.getMaxExperience(), textFont, Color.BLACK), constraints);

        // Row 4
        constraints.gridy++;
        constraints.gridx--;
        add(createLabel("Sanity", textFont), constraints);
        constraints.gridy++;
        add(createValueLabel(playerPresenter.getSanity(), playerPresenter.getMaxSanity(), textFont, Color.BLACK), constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        add(new JLabel(""), constraints);
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
