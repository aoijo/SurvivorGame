package UI.CombatPanel;

import Enums.Rarity;
import Enums.SkillType;
import InterfaceAdapter.CombatAdapter;
import UI.GameScreenPanels.GameScreen;
import UI.GameScreenPanels.World.TilePanel;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CombatSkillPanel extends JPanel {
    private CombatAdapter combatAdapter;
    private CombatLogPanel combatLogPanel;
    private CombatPanel combatPanel;
    private ChooseTargetPanel chooseTargetPanel;
    private TilePanel tilePanel;

    private Font buffButtonFont = new Font("Arial", Font.PLAIN, 10);
    private Dimension buttonSize = new Dimension(135, 20);

    private String[] skillNames;
    private int[] skillCoolDowns;
    private boolean[] skillWorldCoolDownOver;
    private Rarity[] skillRarities;

    public CombatSkillPanel(CombatAdapter combatAdapter, GameScreen gameScreen, CombatLogPanel combatLogPanel, CombatPanel combatPanel, ChooseTargetPanel chooseTargetPanel) {
        this.combatAdapter = combatAdapter;
        this.combatLogPanel = combatLogPanel;
        this.combatPanel = combatPanel;
        this.chooseTargetPanel = chooseTargetPanel;
        this.tilePanel = gameScreen.getTilePanel();

        setLayout(new BorderLayout());
        setBorder(new MatteBorder(2,0,0,0,Color.BLACK)); // Add padding around the panel
        updateSkillPanel();
    }
    public void updateSkillPanel() {
        skillNames = combatAdapter.getCurrentSkillNames(true);
        skillRarities = combatAdapter.getCurrentSkillRarity(true);
        skillCoolDowns = combatAdapter.getCurrentSkillCoolDown(true);
        skillWorldCoolDownOver = combatAdapter.checkWorldSkillCoolDown(true);
        removeAll(); // Remove all existing components before updating

        if (combatAdapter.getCurrentSkillNames(true) == null) {
            return;
        }

        int maxIndex = combatAdapter.getCurrentSkillNames(true).length - 1;
        int index = 0;
        int column = 3;

        JPanel gridPanel = new JPanel(new GridLayout(0, column, 5, 5));
        gridPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        for (int i = 0; i < maxIndex; i++) {
            if(skillWorldCoolDownOver[i]){
                gridPanel.add(SkillButton(i));
                index ++;
            }
        }

        // add an empty space to fill the grid
        if ((index + 1) % column == 0) {
            gridPanel.add(Box.createRigidArea(buttonSize));
        }else if((index + 2) % column == 0) {
            gridPanel.add(Box.createRigidArea(buttonSize));
            gridPanel.add(Box.createRigidArea(buttonSize));
        }

        add(gridPanel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }
    private JButton SkillButton(int skillIndex) {

        String buttonText;
        if (skillCoolDowns[skillIndex] > 1) {
            buttonText = skillNames[skillIndex] + " (" + skillCoolDowns[skillIndex] + " turns)";
        } else if (skillCoolDowns[skillIndex] == 1) {
            buttonText = skillNames[skillIndex] + " (1 turn)";
        } else {
            buttonText = skillNames[skillIndex] + " (ready)";
        }

        JButton skillButton = new DefaultButton(buttonText, skillRarities[skillIndex]);

        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkillType skillType = combatAdapter.getCurrentSkillTypesByIndex(skillIndex)[0];
                if (skillType == SkillType.ESCAPE){
                    if (combatAdapter.getCombatUseCase().tryEscape()){
                        combatLogPanel.addEscapeSuccessLog();
                        combatAdapter.getCombatUseCase().triggerCombatEnd();
                        combatPanel.update();
                        tilePanel.updateTilePanel();
                    }else{
                        combatLogPanel.addEscapeFailLog();
                    }
                }else{
                    if (chooseTargetPanel.getTarget() == 0){
                        if (skillType == SkillType.DAMAGE || skillType == SkillType.WEAKEN || skillType == SkillType.BUFF_REMOVE){
                            combatAdapter.getCombatUseCase().getSkillUseCase().useSkill(combatAdapter.getCombatUseCase().getPlayer(), combatAdapter.getCombatUseCase().getEnemy(),
                                    combatAdapter.getCombatUseCase().getSkillByIndex(true,skillIndex));
                        }else{
                            combatAdapter.getCombatUseCase().getSkillUseCase().useSkill(combatAdapter.getCombatUseCase().getPlayer(), combatAdapter.getCombatUseCase().getPlayer(),
                                    combatAdapter.getCombatUseCase().getSkillByIndex(true,skillIndex));
                        }
                    }else if (chooseTargetPanel.getTarget() == 1){
                        combatAdapter.getCombatUseCase().getSkillUseCase().useSkill(combatAdapter.getCombatUseCase().getPlayer(), combatAdapter.getCombatUseCase().getPlayer(),
                                combatAdapter.getCombatUseCase().getSkillByIndex(true,skillIndex));
                    }else if (chooseTargetPanel.getTarget() == 2){
                        combatAdapter.getCombatUseCase().getSkillUseCase().useSkill(combatAdapter.getCombatUseCase().getPlayer(), combatAdapter.getCombatUseCase().getEnemy(),
                                combatAdapter.getCombatUseCase().getSkillByIndex(true,skillIndex));
                    }
                    combatLogPanel.addSkillCombatLog();
                    combatAdapter.getCombatUseCase().enemyAttack();
                    combatLogPanel.addSkillCombatLog();
                    combatAdapter.getCombatUseCase().triggerTurnEnd();
                    combatPanel.update();
                    if(combatAdapter.checkInCombat()){
                        combatLogPanel.addNewTurnLog();
                    }else{
                        combatLogPanel.addVictoryCombatLog();
                        tilePanel.updateTilePanel();
                    }
                }
            }
        });

        skillButton.setFont(buffButtonFont);
        skillButton.setPreferredSize(buttonSize);
        skillButton.setMinimumSize(buttonSize);
        skillButton.setMaximumSize(buttonSize);
        return skillButton;
    }
    public CombatAdapter getCombatAdapter() {
        return combatAdapter;
    }
}
