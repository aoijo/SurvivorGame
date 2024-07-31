package UI.GameScreenPanels.Bag.BagPanel;

import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultScrollPane;

import javax.swing.*;
import java.awt.*;

public class BagPanel extends JPanel {
    private GameScreen gameScreen;
    private GridBagConstraints constraints;
    private SwitchTopPanel switchTopPanel;
    private InformationPanel informationPanel;
    private SwitchBottomPanel switchBottomPanel;
    private ItemPanel itemPanel;
    private JPanel itemContainer;
    private DetailPanel detailPanel;

    public BagPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.itemPanel = new ItemPanel(gameScreen);
        this.informationPanel = new InformationPanel(this);
        this.switchTopPanel = new SwitchTopPanel(informationPanel);
        this.switchBottomPanel = new SwitchBottomPanel(this);
        this.itemContainer =new JPanel(new CardLayout());
        this.detailPanel = new DetailPanel(gameScreen);
        itemPanel.setDetailPanel(detailPanel);
        informationPanel.getSkillPanel().setDetailPanel(detailPanel);
        informationPanel.getBuffPanel().setDetailPanel(detailPanel);
        informationPanel.getEquipmentPanel().setPlayerPresenter(gameScreen.getAdapterManager().getPlayerPresenter());
        informationPanel.getEquipmentPanel().setDetailPanel(detailPanel);
        detailPanel.setItemPanel(itemPanel);
        detailPanel.getUsagePanel().setEquipmentPanel(informationPanel.getEquipmentPanel());
        detailPanel.getUsagePanel().setStatsPanel(informationPanel.getStatsPanel());
        detailPanel.getUsagePanel().setBuffPanel(informationPanel.getBuffPanel());
        detailPanel.getUsagePanel().setSkillPanel(informationPanel.getSkillPanel());

        itemContainer.add(new DefaultScrollPane(itemPanel));

        //itemScrollPane.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300, 600));
        constraints = new GridBagConstraints();

        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;

        add(switchTopPanel, constraints);

        constraints.gridy++;
        add(informationPanel, constraints);

        constraints.gridy++;
        add(switchBottomPanel, constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        add(itemContainer, constraints);
    }

    public SwitchTopPanel getSwitchTopPanel() {
        return switchTopPanel;
    }

    public InformationPanel getInformationPanel() {
        return informationPanel;
    }

    public SwitchBottomPanel getSwitchBottomPanel() {
        return switchBottomPanel;
    }

    public ItemPanel getItemPanel() {
        return itemPanel;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public DetailPanel getDetailPanel() {
        return detailPanel;
    }
}
