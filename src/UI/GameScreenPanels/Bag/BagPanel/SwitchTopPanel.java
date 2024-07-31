package UI.GameScreenPanels.Bag.BagPanel;

import Utils.SwitchButton;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SwitchTopPanel extends JPanel {
    private InformationPanel informationPanel;
    private StatsPanel statsPanel;
    private SkillPanel skillPanel;
    private BuffPanel buffPanel;
    private EquipmentPanel equipmentPanel;
    private SwitchButton statusButton;
    private SwitchButton skillButton;
    private SwitchButton buffButton;
    private SwitchButton equipButton;
    private Color switchButtonColor;

    public SwitchTopPanel(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
        this.switchButtonColor = informationPanel.getGameScreen().getAdapterManager().getPlayerPresenter().getPlayerColor();
        this.statsPanel = informationPanel.getStatsPanel();
        this.skillPanel = informationPanel.getSkillPanel();
        this.buffPanel = informationPanel.getBuffPanel();
        this.equipmentPanel = informationPanel.getEquipmentPanel();

        statusButton = new SwitchButton("Status", switchButtonColor);
        statusButton.setBorder(new MatteBorder(2,0,2,0, Color.BLACK));
        skillButton = new SwitchButton("Skill", switchButtonColor);
        buffButton = new SwitchButton("Buff", switchButtonColor);
        equipButton = new SwitchButton("Equip", switchButtonColor);
        buffButton.setBorder(new MatteBorder(2,2,2,0, Color.BLACK));

        setLayout(new GridLayout(1, 4));
        setPreferredSize(new Dimension(300, 50));
        updateSwitchTopPanel();
        add(statusButton);
        add(skillButton);
        add(buffButton);
        add(equipButton);

        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("StatsPanel");
                informationPanel.getGameScreen().getUseCaseManager().getPlayerUseCase().updatePlayer();
                updateSwitchTopPanel();
                statsPanel.updateStatsPanel();
            }
        });
        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("SkillPanel");
                updateSwitchTopPanel();
                skillPanel.updateSkillPanel();
            }
        });
        buffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("BuffPanel");
                updateSwitchTopPanel();
                buffPanel.updateBuffPanel();
            }
        });
        equipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("EquipmentPanel");
                updateSwitchTopPanel();
                equipmentPanel.updateEquipmentPanel();
            }
        });
    }

    public void updateSwitchTopPanel() {
        String currentPanel = informationPanel.getCurrentScreenName();
        Map<String, boolean[]> panelStates = new HashMap<>();

        panelStates.put("StatsPanel", new boolean[]{true, false, false, false});
        panelStates.put("SkillPanel", new boolean[]{false, true, false, false});
        panelStates.put("BuffPanel", new boolean[]{false, false, true, false});
        panelStates.put("EquipmentPanel", new boolean[]{false, false, false, true});

        boolean[] states = panelStates.get(currentPanel);

        if (states != null) {
            statusButton.setActive(states[0]);
            skillButton.setActive(states[1]);
            buffButton.setActive(states[2]);
            equipButton.setActive(states[3]);
        } else {
            System.out.println("Unexpected panel name: " + currentPanel);  // Debug statement
        }
        statusButton.setButtonColor(switchButtonColor);
        skillButton.setButtonColor(switchButtonColor);
        buffButton.setButtonColor(switchButtonColor);
        equipButton.setButtonColor(switchButtonColor);

        statusButton.repaint();
        skillButton.repaint();
        buffButton.repaint();
        equipButton.repaint();
    }

    public void setSwitchButtonColor(Color switchButtonColor) {
        this.switchButtonColor = switchButtonColor;
    }
}
