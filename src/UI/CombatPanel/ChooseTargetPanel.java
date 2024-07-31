package UI.CombatPanel;

import Utils.SwitchButton;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChooseTargetPanel extends JPanel {
    private int target; // Auto: 0, Player: 1, Enemy: 2
    private Color switchButtonColor;
    private Dimension switchButtonSize;

    private SwitchButton autoButton;
    private SwitchButton playerButton;
    private SwitchButton enemyButton;

    public ChooseTargetPanel(Color switchButtonColor) {
        this.target = 0;
        this.switchButtonColor = switchButtonColor;
        this.switchButtonSize = new Dimension(70, 15);

        setPreferredSize(new Dimension(280, 30));
        setBorder(new MatteBorder(2,0,0,0,Color.black));
        setLayout(new GridLayout(1,4));
        JPanel gridPanel = new JPanel(new GridLayout(1,3));

        JLabel ChooseTargetText = new JLabel("   Choose skill target");
        ChooseTargetText.setFont(new Font("Arial", Font.BOLD, 12));
        autoButton = new SwitchButton("Auto", switchButtonColor);
        playerButton = new SwitchButton("Player", switchButtonColor);
        enemyButton = new SwitchButton("Enemy", switchButtonColor);

        autoButton.setPreferredSize(switchButtonSize);
        playerButton.setPreferredSize(switchButtonSize);
        enemyButton.setPreferredSize(switchButtonSize);

        autoButton.setBorder(new MatteBorder(0,2,0,2,Color.black));
        playerButton.setBorder(new MatteBorder(0,0,0,2,Color.black));
        enemyButton.setBorder(null);

        add(ChooseTargetText);
        add(autoButton);
        add(playerButton);
        add(enemyButton);

        autoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                target = 0;
                updatePanel();
            }
        });
        playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                target = 1;
                updatePanel();
            }
        });
        enemyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                target = 2;
                updatePanel();
            }
        });
        updatePanel();
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public void updatePanel() {
        SwitchButton[] buttons = {autoButton, playerButton, enemyButton};
        Map<Integer, boolean[]> panelStates = new HashMap<>();

        panelStates.put(0, new boolean[]{true, false, false});
        panelStates.put(1, new boolean[]{false, true, false});
        panelStates.put(2, new boolean[]{false, false, true});

        boolean[] states = panelStates.get(target);

        if (states == null) {
            throw new IllegalArgumentException("Unexpected target number: " + target);
        }

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setActive(states[i]);
            buttons[i].setButtonColor(switchButtonColor);
            buttons[i].repaint();
        }
    }
}
