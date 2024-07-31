package UI.GameScreenPanels;

import InterfaceAdapter.CombatAdapter;
import UI.CombatPanel.CombatPanel;
import UI.GameScreenPanels.Bag.BagPanel.BagPanel;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import UI.GameScreenPanels.World.MapPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CentrePanel extends JPanel {
    private GameScreen gameScreen;
    private CardLayout cardLayout;
    private MapPanel mapPanel;
    private BagPanel bagPanel;
    private DetailPanel detailPanel;
    private JPanel characterPanel;
    private CombatPanel combatPanel;

    private CombatAdapter combatAdapter;

    public CentrePanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.cardLayout = new CardLayout();
        this.mapPanel = new MapPanel(gameScreen.getAdapterManager(),10,50,12);
        this.bagPanel = new BagPanel(gameScreen);
        this.detailPanel = bagPanel.getDetailPanel();
        this.combatAdapter = gameScreen.getAdapterManager().getCombatAdapter();

        characterPanel = new JPanel(new BorderLayout());
        characterPanel.add(bagPanel, BorderLayout.WEST);
        characterPanel.add(detailPanel, BorderLayout.CENTER);

        setLayout(cardLayout);
        add(mapPanel,"MapPanel");
        add(characterPanel,"CharacterPanel");
        setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));

        //switchToScreen("CharacterPanel");
    }

    public void switchToScreen(String screenName) {
        cardLayout.show(this, screenName);
        if (screenName.equals("CharacterPanel")) {
            gameScreen.getUseCaseManager().getPlayerUseCase().updatePlayer();
            bagPanel.getInformationPanel().getStatsPanel().updateStatsPanel();
            bagPanel.getItemPanel().updateItemPanel();
        }
    }
    public void startCombat(String enemyName){
        combatAdapter.startCombat(enemyName);
        combatPanel = new CombatPanel(gameScreen.getAdapterManager(),gameScreen);
        add(combatPanel,"CombatPanel");
        switchToScreen("CombatPanel");
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }
    public BagPanel getBagPanel() {
        return bagPanel;
    }
    public DetailPanel getDetailPanel() {
        return detailPanel;
    }

    public CombatPanel getCombatPanel() {
        return combatPanel;
    }

    public void setCombatPanel(CombatPanel combatPanel) {
        this.combatPanel = combatPanel;
    }
}
