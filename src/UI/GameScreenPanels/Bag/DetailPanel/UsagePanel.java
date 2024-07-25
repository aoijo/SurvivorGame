package UI.GameScreenPanels.Bag.DetailPanel;

import Enums.Item.EquipmentType;
import Enums.Item.ItemType;
import InterfaceAdapter.ItemAdapter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import UI.GameScreenPanels.Bag.BagPanel.*;
import UI.GameScreenPanels.StatusPanel;
import UI.GameScreenPanels.World.LogPanel;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsagePanel extends JPanel {
    private DetailPanel detailPanel;
    private PlayerPresenter playerPresenter;
    private PlayerController playerController;
    private ItemAdapter itemAdapter;
    private ItemPanel itemPanel;
    private LogPanel logPanel;
    private StatusPanel statusPanel;
    private EquipmentPanel equipmentPanel;
    private StatsPanel statsPanel;
    private BuffPanel buffPanel;
    private SkillPanel skillPanel;

    private int currentItemIndex;
    private int itemUseCount;

    private Font buttonFont = new Font("Arial", Font.BOLD, 12);

    public UsagePanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.playerPresenter = detailPanel.getPlayerPresenter();
        this.playerController = playerPresenter.getPlayerController();
        this.itemAdapter = detailPanel.getItemAdapter();
        this.currentItemIndex = detailPanel.getCurrentItemIndex();
        this.itemUseCount = detailPanel.getItemUseCount();
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(280, 30));
        setBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK));
        addButtons();
    }

    private void addButtons() {
        JPanel middleAreaPanel = new JPanel();
        middleAreaPanel.setPreferredSize(new Dimension(120, 30));
        middleAreaPanel.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));

        DefaultButton useItemButton = new DefaultButton("Use Item", buttonFont);
        useItemButton.setPreferredSize(new Dimension(80, 30));
        useItemButton.setBorder(null);

        add(dropItemButton());
        add(middleAreaPanel);
        if (itemAdapter.getItemTypeByIndex(currentItemIndex) == ItemType.EQUIPMENT){
            if (itemAdapter.getEquippedByIndex(currentItemIndex)){
                add(unEquipButton());
            } else{
                add(equipButton());
            }
        } else {
            add(useItemButton);
        }
    }

    private DefaultButton dropItemButton(){
        DefaultButton dropItemButton = new DefaultButton("Drop Item", buttonFont);
        dropItemButton.setPreferredSize(new Dimension(80, 30));
        dropItemButton.setBorder(null);
        dropItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemAdapter.getItemTypeByIndex(currentItemIndex) == ItemType.EQUIPMENT &&
                itemAdapter.getEquippedByIndex(currentItemIndex)){
                    logPanel.addCannotDropEquippedLog();
                } else{
                    itemUseCount = detailPanel.getItemUseCount();
                    if (itemUseCount >= itemAdapter.getQuantityByIndex(currentItemIndex) &&
                            playerPresenter.getSortedItemCount() == 1){
                        detailPanel.setInformationType("none");
                    }
                    playerPresenter.getPlayerUseCase().removeByIndex(currentItemIndex, itemUseCount);
                    playerPresenter.getPlayerUseCase().updatePlayer();
                    statusPanel.updateStatusPanel();
                    itemPanel.updateItemPanel();
                    detailPanel.update();
                }
            }
        });
        return dropItemButton;
    }

    private DefaultButton equipButton(){
        DefaultButton equipButton = new DefaultButton("Equip Item", buttonFont);
        equipButton.setPreferredSize(new Dimension(80, 30));
        equipButton.setBorder(null);
        equipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playerPresenter.getPlayerLevel() < itemAdapter.getLevelRequirementByIndex(currentItemIndex)){
                    logPanel.addCannotEquipLog();
                } else if(playerPresenter.getPlayerAmuletCount() == 4 &&
                        itemAdapter.getEquipmentTypeByIndex(currentItemIndex) == EquipmentType.AMULET){
                    logPanel.addFullAmuletLog();
                }else {
                    playerController.equipByIndex(currentItemIndex);
                    playerPresenter.getPlayerUseCase().updatePlayer();
                    skillPanel.updateSkillPanel();
                    buffPanel.updateBuffPanel();
                    statusPanel.updateStatusPanel();
                    statsPanel.updateStatsPanel();
                    itemPanel.updateItemPanel();
                    equipmentPanel.updateEquipmentPanel();
                    update();
                }
            }
        });
        return equipButton;
    }

    private DefaultButton unEquipButton(){
        DefaultButton unEquipButton = new DefaultButton("unEquip Item", buttonFont);
        unEquipButton.setPreferredSize(new Dimension(80, 30));
        unEquipButton.setBorder(null);
        unEquipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerController.unEquipByIndex(currentItemIndex);
                playerPresenter.getPlayerUseCase().updatePlayer();
                skillPanel.updateSkillPanel();
                buffPanel.updateBuffPanel();
                statusPanel.updateStatusPanel();
                statsPanel.updateStatsPanel();
                itemPanel.updateItemPanel();
                equipmentPanel.updateEquipmentPanel();
                update();
            }
        });
        return unEquipButton;
    }

    public void setEquipmentPanel(EquipmentPanel equipmentPanel) {
        this.equipmentPanel = equipmentPanel;
    }

    public void setLogPanel(LogPanel logPanel) {
        this.logPanel = logPanel;
    }
    public void setItemPanel(ItemPanel itemPanel) {
        this.itemPanel = itemPanel;
    }
    public void update(){
        this.currentItemIndex = detailPanel.getCurrentItemIndex();
        removeAll();
        addButtons();
        revalidate();
        repaint();
    }

    public void setStatusPanel(StatusPanel statusPanel) {
        this.statusPanel = statusPanel;
    }

    public void setStatsPanel(StatsPanel statsPanel) {
        this.statsPanel = statsPanel;
    }

    public void setBuffPanel(BuffPanel buffPanel) {
        this.buffPanel = buffPanel;
    }

    public void setSkillPanel(SkillPanel skillPanel) {
        this.skillPanel = skillPanel;
    }
}