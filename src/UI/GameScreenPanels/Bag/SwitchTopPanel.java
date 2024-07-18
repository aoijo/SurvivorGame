package UI.GameScreenPanels.Bag;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchTopPanel extends JPanel {
    private InformationPanel informationPanel;
    private SwitchButton statusButton;
    private SwitchButton skillButton;
    private SwitchButton buffButton;

    public SwitchTopPanel(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
        statusButton = new SwitchButton("Status");
        skillButton = new SwitchButton("Skill");
        buffButton = new SwitchButton("Buff");
        buffButton.setBorder(new MatteBorder(2,2,2,2, Color.BLACK));

        setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(300, 50));
        updateSwitchTopPanel();
        add(statusButton);
        add(skillButton);
        add(buffButton);

        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("StatsPanel");
                updateSwitchTopPanel();
            }
        });
        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("SkillPanel");
                updateSwitchTopPanel();
            }
        });
        buffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informationPanel.switchToScreen("BuffPanel");
                updateSwitchTopPanel();
            }
        });
    }

    public void updateSwitchTopPanel() {
        String currentPanel = informationPanel.getCurrentScreenName();
        switch (currentPanel) {
            case "StatsPanel":
                statusButton.setActive(true);
                skillButton.setActive(false);
                buffButton.setActive(false);
                break;
            case "SkillPanel":
                statusButton.setActive(false);
                skillButton.setActive(true);
                buffButton.setActive(false);
                break;
            case "BuffPanel":
                statusButton.setActive(false);
                skillButton.setActive(false);
                buffButton.setActive(true);
                break;
        }
        statusButton.repaint();
        skillButton.repaint();
        buffButton.repaint();
    }
}
