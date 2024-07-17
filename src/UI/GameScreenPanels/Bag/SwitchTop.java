package UI.GameScreenPanels.Bag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchTop extends JPanel {
    private InformationPanel informationPanel;
    private SwitchButton statusButton;
    private SwitchButton skillButton;
    private SwitchButton buffButton;

    public SwitchTop(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
        statusButton = new SwitchButton("Status");
        skillButton = new SwitchButton("Skill");
        buffButton = new SwitchButton("Buff");
        setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(300, 50));
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("StatsPanel");
                statusButton.setActive(true);
                skillButton.setActive(false);
                buffButton.setActive(false);
            }
        });
        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("SkillPanel");
                statusButton.setActive(false);
                skillButton.setActive(true);
                buffButton.setActive(false);
            }
        });
        buffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("BuffPanel");
                statusButton.setActive(false);
                skillButton.setActive(false);
                buffButton.setActive(true);
            }
        });
        add(statusButton);
        add(skillButton);
        add(buffButton);
    }
}
