package UI.GameScreenPanels.Bag;

import Enums.Rarity;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;

public class SkillPanel extends JPanel {
    private GameScreen gameScreen;
    private GridBagConstraints gbc;

    public SkillPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between elements
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        updateSkillPanel();
    }

    public void updateSkillPanel() {
        int maxIndex = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getSkills().size();
        removeAll(); // Remove all existing components before updating

        for (int i = 0; i < (maxIndex + 1) / 2; i++) { // Use (maxIndex + 1) / 2 to handle odd numbers correctly
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, 2, 5, 5));

            int currentIndex = i * 2;
            rowPanel.add(SkillButton(currentIndex));
            if (currentIndex + 1 < maxIndex) {
                rowPanel.add(SkillButton(currentIndex + 1));
            }
            gbc.gridy = i;
            add(rowPanel, gbc);
        }

        revalidate(); // Revalidate the layout
        repaint(); // Repaint the panel
    }

    private JButton SkillButton(int skillIndex) {
        String skillName = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getSkills().get(skillIndex).getName();
        float worldCoolDown = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getSkills().get(skillIndex).getWorldCooldown();
        int coolDown = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getSkills().get(skillIndex).getCooldown();
        Rarity rarity = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getSkills().get(skillIndex).getRarity();

        String buttonText = "";
        if (worldCoolDown > 1) {
            buttonText = skillName + "(" + (int) Math.ceil(worldCoolDown) + " days)";
        } else if(worldCoolDown > 0) {
            buttonText = skillName + "(" + (int) Math.ceil(worldCoolDown) + " day)";
        } else if (coolDown > 1) {
            buttonText = skillName + "(" + coolDown + " turns)";
        } else if (coolDown > 0) {
            buttonText = skillName + "(" + coolDown + " turn)";
        } else {
            buttonText = skillName + "(ready)";
        }
        return new DefaultButton(buttonText,rarity);
    }
}
