package UI.GameScreenPanels.Bag.BagPanel;

import Enums.Rarity;
import InterfaceAdapter.SkillAdapter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillPanel extends JPanel {
    private GameScreen gameScreen;
    private SkillAdapter skillAdapter;
    private DetailPanel detailPanel;

    private GridBagConstraints constraints;
    private Font buffButtonFont = new Font("Arial", Font.PLAIN, 10);
    private Dimension buttonSize = new Dimension(135, 20);

    public SkillPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.skillAdapter = gameScreen.getAdapterManager().getSkillAdapter();
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 0, 5); // Padding between elements
        constraints.fill = GridBagConstraints.HORIZONTAL;

        updateSkillPanel();
    }

    public void updateSkillPanel() {
        removeAll(); // Remove all existing components before updating
        if (gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentSkills() == null){
            return;
        }

        int maxIndex = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentSkills().size();

        for (int i = 0; i < (maxIndex + 1) / 2; i++) { // Use (maxIndex + 1) / 2 to handle odd numbers correctly
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

            int currentIndex = i * 2;
            rowPanel.add(SkillButton(currentIndex));
            rowPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons

            if (currentIndex + 1 < maxIndex) {
                rowPanel.add(SkillButton(currentIndex + 1));
            } else {
                rowPanel.add(Box.createRigidArea(buttonSize)); // Add empty space if odd number of buffs
            }

            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.weightx = 1.0;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            add(rowPanel, constraints);
        }

        constraints.gridy++;
        constraints.weighty = 1;
        add(new JPanel(), constraints);
        constraints.weighty = 0;

        revalidate(); // Revalidate the layout
        repaint(); // Repaint the panel
    }

    private JButton SkillButton(int skillIndex) {
        String skillName = skillAdapter.getPlayerCurrentSkillNameByIndex(skillIndex);
        float worldCoolDown = skillAdapter.getPlayerCurrentSkillWorldCooldownByIndex(skillIndex);
        int coolDown = skillAdapter.getPlayerCurrentSkillCooldownByIndex(skillIndex);
        Rarity rarity = skillAdapter.getPlayerCurrentSkillRarityByIndex(skillIndex);

        String buttonText = "";
        if (worldCoolDown > 1) {
            buttonText = skillName + " (" + (int) Math.ceil(worldCoolDown) + " days)";
        } else if(worldCoolDown > 0) {
            buttonText = skillName + " (" + (int) Math.ceil(worldCoolDown) + " day)";
        } else if (coolDown > 1) {
            buttonText = skillName + " (" + coolDown + " turns)";
        } else if (coolDown > 0) {
            buttonText = skillName + " (" + coolDown + " turn)";
        } else {
            buttonText = skillName + " (ready)";
        }
        JButton skillButton =  new DefaultButton(buttonText,rarity);

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
