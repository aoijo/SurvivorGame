package UI.GameScreenPanels.Bag;

import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import UI.GameScreenPanels.GameScreen;

import javax.swing.*;

public class DetailPanel extends JPanel {
    private ItemPanel itemPanel;
    private PlayerPresenter playerPresenter;
    private int currentItemIndex;

    public DetailPanel(GameScreen gameScreen, BagPanel bagPanel) {
        this.itemPanel = bagPanel.getItemPanel();
        this.playerPresenter = gameScreen.getAdapterManager().getPlayerPresenter();
        this.currentItemIndex = 0;
    }
}
