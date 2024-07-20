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
    private Color switchButtonColor;

    public SwitchTopPanel(InformationPanel informationPanel) {
        this.informationPanel = informationPanel;
        this.switchButtonColor = informationPanel.getGameScreen().getAdapterManager().getPlayerPresenter().getPlayerColor();
        statusButton = new SwitchButton("Status", switchButtonColor);
        skillButton = new SwitchButton("Skill", switchButtonColor);
        buffButton = new SwitchButton("Buff", switchButtonColor);
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
        statusButton.setButtonColor(switchButtonColor);
        skillButton.setButtonColor(switchButtonColor);
        buffButton.setButtonColor(switchButtonColor);
        statusButton.repaint();
        skillButton.repaint();
        buffButton.repaint();
    }

    public void setSwitchButtonColor(Color switchButtonColor) {
        this.switchButtonColor = switchButtonColor;
    }
}
