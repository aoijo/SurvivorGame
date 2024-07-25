package UI.GameScreenPanels.Bag.DetailPanel;

import InterfaceAdapter.ItemAdapter;
import InterfaceAdapter.BuffAdapter;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.SkillAdapter;
import UI.GameScreenPanels.Bag.BagPanel.ItemPanel;
import UI.GameScreenPanels.GameScreen;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class DetailPanel extends JPanel {
    private GameScreen gameScreen;
    private PlayerPresenter playerPresenter;
    private ItemAdapter itemAdapter;
    private SkillAdapter skillAdapter;
    private BuffAdapter buffAdapter;
    private int currentItemIndex;
    private int itemUseCount;
    private GridBagConstraints constraints;
    private ItemPanel itemPanel;

    private NamePanel namePanel;
    private DescriptionPanel descriptionPanel;
    private NumberPanel numberPanel;
    private ItemQuantityPanel itemQuantityPanel;
    private UsagePanel usagePanel;

    private String informationType; //"item","skill","buff", "None"
    private SkillNamePanel skillNamePanel;
    private BuffNamePanel buffNamePanel;
    private SkillDescriptionPanel skillDescriptionPanel;
    private BuffDescriptionPanel buffDescriptionPanel;
    private NoneNamePanel noneNamePanel;
    private NoneDescriptionPanel noneDescriptionPanel;

    public DetailPanel(GameScreen gameScreen) {
        this.playerPresenter = gameScreen.getAdapterManager().getPlayerPresenter();
        this.itemAdapter = gameScreen.getAdapterManager().getItemAdapter();
        this.skillAdapter = gameScreen.getAdapterManager().getSkillAdapter();
        this.buffAdapter = gameScreen.getAdapterManager().getBuffAdapter();
        this.currentItemIndex = 0;
        this.itemUseCount = 1;
        this.namePanel = new NamePanel(this);
        this.numberPanel = new NumberPanel(this);
        this.descriptionPanel = new DescriptionPanel(this);
        this.itemQuantityPanel = new ItemQuantityPanel(this);
        this.usagePanel = new UsagePanel(this);
        this.skillNamePanel = new SkillNamePanel(this);
        this.skillDescriptionPanel = new SkillDescriptionPanel(this);
        this.buffNamePanel = new BuffNamePanel(this);
        this.buffDescriptionPanel = new BuffDescriptionPanel(this);
        this.noneNamePanel = new NoneNamePanel(this);
        this.noneDescriptionPanel = new NoneDescriptionPanel(this);
        this.informationType = "item";

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(280, 400));
        setMaximumSize(new Dimension(280, 400));
        setBorder(new MatteBorder(2, 2, 0, 0, Color.BLACK));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.NORTH; // Align components to the top

        // Add panels
        addItemPanels();
    }

    public void update() {
        if (informationType.equals("item")) {
            namePanel.update();
            numberPanel.update();
            descriptionPanel.update();
            usagePanel.update();
        } else if (informationType.equals("skill")) {
            skillNamePanel.update();
            skillDescriptionPanel.update();
        } else if (informationType.equals("buff")) {
            buffNamePanel.update();
            buffDescriptionPanel.update();
        } else if (informationType.equals("none")) {
            noneNamePanel.update();
            noneDescriptionPanel.update();
        }

        revalidate();
        repaint();
    }
    public void addItemPanels(){
        add(namePanel, constraints);

        constraints.gridy++;
        add(numberPanel, constraints);

        constraints.gridy++;
        constraints.weighty = 1;

        add(descriptionPanel, constraints);
        constraints.weighty = 0;

        constraints.gridy++;
        add(itemQuantityPanel, constraints);

        constraints.gridy++;
        add(usagePanel, constraints);
    }
    public void addSkillPanels(){
        add(skillNamePanel, constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        add(skillDescriptionPanel, constraints);
        constraints.weighty = 0;
    }
    public void addBuffPanels(){
        add(buffNamePanel, constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        add(buffDescriptionPanel, constraints);
        constraints.weighty = 0;
    }
    public void addNonePanel(){
        add(noneNamePanel, constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        add(noneDescriptionPanel, constraints);
        constraints.weighty = 0;
    }

    // Getters and setters
    public int getCurrentItemIndex() {
        return currentItemIndex;
    }

    public void setCurrentItemIndex(int currentItemIndex) {
        this.currentItemIndex = currentItemIndex;
    }

    public void setItemUseCount(int itemUseCount) {
        this.itemUseCount = itemUseCount;
    }

    public int getItemUseCount() {
        return itemUseCount;
    }

    public ItemQuantityPanel getItemQuantityPanel() {
        return itemQuantityPanel;
    }
    public PlayerPresenter getPlayerPresenter() {
        return playerPresenter;
    }
    public ItemAdapter getItemAdapter() {
        return itemAdapter;
    }
    public void setItemPanel(ItemPanel itemPanel) {
        this.itemPanel = itemPanel;
        usagePanel.setItemPanel(itemPanel);
    }
    public ItemPanel getItemPanel() {
        return itemPanel;
    }
    public UsagePanel getUsagePanel() {
        return usagePanel;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public SkillNamePanel getSkillNamePanel() {
        return skillNamePanel;
    }

    public BuffNamePanel getBuffNamePanel() {
        return buffNamePanel;
    }

    public SkillDescriptionPanel getSkillDescriptionPanel() {
        return skillDescriptionPanel;
    }

    public BuffDescriptionPanel getBuffDescriptionPanel() {
        return buffDescriptionPanel;
    }
    public void setInformationType(String informationType) {
        this.informationType = informationType;
        if (informationType.equals("item")) {
            removeAll();
            addItemPanels();
        } else if(informationType.equals("skill")) {
            removeAll();
            addSkillPanels();
        } else if(informationType.equals("buff")) {
            removeAll();
            addBuffPanels();
        } else if (informationType.equals("none")){
            removeAll();
            addNonePanel();
        }
        update();
    }

    public SkillAdapter getSkillAdapter() {
        return skillAdapter;
    }

    public void setSkillAdapter(SkillAdapter skillAdapter) {
        this.skillAdapter = skillAdapter;
    }

    public BuffAdapter getBuffAdapter() {
        return buffAdapter;
    }

    public void setBuffAdapter(BuffAdapter buffAdapter) {
        this.buffAdapter = buffAdapter;
    }
}
