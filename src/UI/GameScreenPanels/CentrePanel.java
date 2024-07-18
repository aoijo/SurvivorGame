package UI.GameScreenPanels;

import UI.CombatPanel.CombatScreen;
import UI.GameScreenPanels.Bag.BagPanel;
import UI.GameScreenPanels.World.MapPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CentrePanel extends JPanel {
    private GameScreen gameScreen;
    private CardLayout cardLayout;
    private MapPanel mapPanel;
    private BagPanel bagPanel;
    private CombatScreen combatScreen;

    public CentrePanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.mapPanel = new MapPanel(gameScreen.getAdapterManager(),10,50,12);
        this.bagPanel = new BagPanel(gameScreen);
        add(mapPanel,"MapPanel");
        add(bagPanel,"BagPanel");
        setBorder(new MatteBorder(0, 2, 2, 2, Color.black));

        //switchToScreen("BagPanel");
    }

    public void switchToScreen(String screenName) {
        cardLayout.show(this, screenName);
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }
    public BagPanel getBagPanel() {
        return bagPanel;
    }
}
