package UI.GameScreenPanels;

import UI.CombatPanel.CombatScreen;
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
    private CombatScreen combatScreen;

    public CentrePanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.mapPanel = new MapPanel(gameScreen.getAdapterManager(),10,50,12);
        this.bagPanel = new BagPanel(gameScreen);
        this.detailPanel = bagPanel.getDetailPanel();
        JPanel CharacterPanel = new JPanel(new BorderLayout());
        CharacterPanel.add(bagPanel, BorderLayout.WEST);
        CharacterPanel.add(detailPanel, BorderLayout.CENTER);
        add(mapPanel,"MapPanel");
        add(CharacterPanel,"CharacterPanel");
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

    public MapPanel getMapPanel() {
        return mapPanel;
    }
    public BagPanel getBagPanel() {
        return bagPanel;
    }
    public DetailPanel getDetailPanel() {
        return detailPanel;
    }
}
