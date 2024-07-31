package UI.CombatPanel;

import InterfaceAdapter.CombatAdapter;
import UI.GameScreenPanels.Bag.BagPanel.InformationPanel;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import UI.GameScreenPanels.CentrePanel;
import UI.GameScreenPanels.GameScreen;
import UI.GameScreenPanels.StatusPanel;
import Utils.DefaultButton;
import Utils.DefaultScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CombatBuffPanel extends JPanel {
    private CentrePanel centrePanel;
    private InformationPanel informationPanel;
    private DetailPanel detailPanel;
    private StatusPanel statusPanel;
    private CombatAdapter combatAdapter;

    private boolean isPlayer;
    private JPanel buffPanel;
    private GridBagConstraints constraints;
    private Dimension buttonSize = new Dimension(100, 20);

    public CombatBuffPanel(GameScreen gameScreen, CombatAdapter combatAdapter, boolean isPlayer) {
        this.isPlayer = isPlayer;
        this.statusPanel = gameScreen.getStatusPanel();
        this.centrePanel = gameScreen.getCentrePanel();
        this.informationPanel = centrePanel.getBagPanel().getInformationPanel();
        this.detailPanel = centrePanel.getBagPanel().getDetailPanel();
        this.combatAdapter = combatAdapter;

        setLayout(new CardLayout());
        buffPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(5, 0, 0, 5);
        DefaultScrollPane scrollPane = new DefaultScrollPane(buffPanel);
        add(scrollPane);
        addBuffButtons();
        constraints.weighty = 1;
        buffPanel.add(new JLabel(""), constraints);
        constraints.weighty = 0;

    }

    private void addBuffButtons(){
        String[] buffNames = combatAdapter.getCurrentBuffNames(isPlayer);
        int[] buffStacks = combatAdapter.getCurrentBuffStacks(isPlayer);
        int maxIndex = buffNames.length - 1;

        for (int i = 0; i < buffStacks.length; i++) {
            constraints.gridy = i + 1;
            buffPanel.add(createBuffButton(buffNames[i], buffStacks[i], maxIndex), constraints);
        }
    }
    private DefaultButton createBuffButton(String buffName, int buffStack, int buffIndex){
        DefaultButton buffButton = new DefaultButton(buffName + " ("+buffStack+")");
        buffButton.setMinimumSize(buttonSize);
        buffButton.setPreferredSize(buttonSize);
        buffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centrePanel.switchToScreen("CharacterPanel");
                informationPanel.switchToScreen("BuffPanel");
                detailPanel.getBuffNamePanel().setCurrentBuffIndex(buffIndex);
                detailPanel.setInformationType("buff");
                statusPanel.getCombatButton().setSelected(true);
            }
        });
        return buffButton;
    }
    public void update(){
        buffPanel.removeAll();
        addBuffButtons();
        revalidate();
        repaint();
    }
}
