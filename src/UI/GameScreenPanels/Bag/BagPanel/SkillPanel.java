package UI.GameScreenPanels.Bag.BagPanel;

import Enums.Rarity;
import InterfaceAdapter.SkillAdapter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillPanel extends JPanel {
    private GameScreen gameScreen;
    private SkillAdapter skillAdapter;
    private DetailPanel detailPanel;

    private Font buffButtonFont = new Font("Arial", Font.PLAIN, 10);
    private Dimension buttonSize = new Dimension(135, 20);

    public SkillPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.skillAdapter = gameScreen.getAdapterManager().getSkillAdapter();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 10, 5, 10)); // Add padding around the panel
        updateSkillPanel();
    }

    public void updateSkillPanel() {
        removeAll(); // Remove all existing components before updating
        if (gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentSkills() == null) {
            return;
        }

        int maxIndex = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentSkills().size();

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        for (int i = 0; i < maxIndex; i++) {
            gridPanel.add(SkillButton(i));
        }

        // If the number of skills is odd, add an empty space to fill the grid
        if (maxIndex % 2 != 0) {
            gridPanel.add(Box.createRigidArea(buttonSize));
        }

        add(gridPanel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    private JButton SkillButton(int skillIndex) {
        String skillName = skillAdapter.getPlayerCurrentSkillNameByIndex(skillIndex);
        float worldCoolDown = skillAdapter.getPlayerCurrentSkillWorldCooldownByIndex(skillIndex);
        int coolDown = skillAdapter.getPlayerCurrentSkillCooldownByIndex(skillIndex);
        Rarity rarity = skillAdapter.getPlayerCurrentSkillRarityByIndex(skillIndex);

        String buttonText;
        if (worldCoolDown > 1) {
            buttonText = skillName + " (" + (int) Math.ceil(worldCoolDown) + " days)";
        } else if (worldCoolDown > 0) {
            buttonText = skillName + " (" + (int) Math.ceil(worldCoolDown) + " day)";
        } else if (coolDown > 1) {
            buttonText = skillName + " (" + coolDown + " turns)";
        } else if (coolDown > 0) {
            buttonText = skillName + " (" + coolDown + " turn)";
        } else {
            buttonText = skillName + " (ready)";
        }

        JButton skillButton = new DefaultButton(buttonText, rarity);

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.getSkillNamePanel().setCurrentSkillIndex(skillIndex);
                detailPanel.setInformationType("skill");
            }
        });

        skillButton.setFont(buffButtonFont);
        skillButton.setPreferredSize(buttonSize);
        skillButton.setMinimumSize(buttonSize);
        skillButton.setMaximumSize(buttonSize);
        return skillButton;
    }

    public void setDetailPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
    }
}
