package UI.CombatPanel;

import InterfaceAdapter.CombatAdapter;
import UI.AdapterManager;
import UI.GameScreenPanels.CentrePanel;
import UI.GameScreenPanels.GameScreen;
import UI.GameScreenPanels.StatusPanel;
import UI.GameScreenPanels.World.TilePanel;
import Utils.DefaultScrollPane;

import javax.swing.*;
import java.awt.*;

public class CombatPanel extends JPanel {
    private CombatAdapter combatAdapter;
    private AdapterManager adapterManager;
    private CentrePanel centrePanel;
    private StatusPanel statusPanel;
    private TilePanel tilePanel;

    private CombatLogPanel combatLogPanel;
    private CombatInformationPanel enemyInformationPanel;
    private CombatInformationPanel playerInformationPanel;
    private ChooseTargetPanel chooseTargetPanel;
    private CombatSkillPanel combatSkillPanel;

    public CombatPanel(AdapterManager adapterManager, GameScreen gameScreen) {
        this.adapterManager = adapterManager;
        this.combatAdapter = adapterManager.getCombatAdapter();
        this.centrePanel = gameScreen.getCentrePanel();
        this.statusPanel = gameScreen.getStatusPanel();
        this.tilePanel = gameScreen.getTilePanel();
        this.combatLogPanel = new CombatLogPanel(combatAdapter);
        this.playerInformationPanel = new CombatInformationPanel(true, combatAdapter,gameScreen);
        this.enemyInformationPanel = new CombatInformationPanel(false, combatAdapter,gameScreen);
        this.chooseTargetPanel = new ChooseTargetPanel(adapterManager.getPlayerPresenter().getPlayerColor());
        this.combatSkillPanel = new CombatSkillPanel(combatAdapter,gameScreen,combatLogPanel,this,chooseTargetPanel);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0,0,0,0);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        add(playerInformationPanel, constraints);

        constraints.weightx = 2;
        constraints.gridx++;
        add(combatLogPanel, constraints);

        constraints.gridx++;
        constraints.weightx = 1;
        add(enemyInformationPanel, constraints);

        constraints.gridy ++;
        constraints.gridx = 0;
        constraints.gridwidth=3;
        constraints.weighty = 0;
        add(chooseTargetPanel, constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        add(combatSkillPanel, constraints);
    }

    public AdapterManager getAdapterManager() {
        return adapterManager;
    }

    public void update(){
        if (combatAdapter.checkInCombat()){
            combatSkillPanel.updateSkillPanel();
            playerInformationPanel.update();
            enemyInformationPanel.update();
        } else{
            centrePanel.switchToScreen("MapPanel");
            statusPanel.switchButton("MapButton");
            statusPanel.updateStatusPanel();
        }
    }
}
