package UI.GameScreenPanels.Bag;

import UI.GameScreenPanels.GameScreen;
import Utils.DefaultScrollPane;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class BagPanel extends JPanel {
    private GameScreen gameScreen;
    private GridBagConstraints constraints;
    private SwitchTopPanel switchTopPanel;
    private InformationPanel informationPanel;
    private SwitchBottomPanel switchBottomPanel;
    private ItemPanel itemPanel;
    private JScrollPane itemScrollPane;

    public BagPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.informationPanel = new InformationPanel(this);
        this.switchTopPanel = new SwitchTopPanel(informationPanel);
        this.itemPanel = new ItemPanel(gameScreen);
        this.itemScrollPane = new DefaultScrollPane(itemPanel);
        this.switchBottomPanel = new SwitchBottomPanel(this);

        itemScrollPane.setPreferredSize(new Dimension(300,300));
        itemScrollPane.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK));

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300, 800));
        constraints = new GridBagConstraints();

        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(switchTopPanel, constraints);

        constraints.gridy++;
        add(informationPanel, constraints);

        constraints.gridy++;
        add(switchBottomPanel, constraints);

        constraints.gridy++;
        constraints.fill = GridBagConstraints.BOTH;
        //constraints.weighty = 1.0; // Allow the itemScrollPane to take up remaining vertical space
        add(itemScrollPane, constraints);
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
}
