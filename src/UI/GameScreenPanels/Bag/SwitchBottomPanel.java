package UI.GameScreenPanels.Bag;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Enums.Item.ItemType;
import Utils.DefaultComboBox;
import Utils.DefaultToggleButton;

public class SwitchBottomPanel extends JPanel {
    private ItemPanel itemPanel;
    private BagPanel bagPanel;
    private Color sortingButtonColor;
    private Dimension panelDimension = new Dimension(300,34);
    private JComboBox itemTypeSelection;
    private JComboBox itemSortingDependency;
    private DefaultToggleButton sortingDirection;

    public SwitchBottomPanel(BagPanel bagPanel) {
        this.itemPanel = bagPanel.getItemPanel();
        this.bagPanel = bagPanel;
        this.sortingButtonColor = bagPanel.getGameScreen().getAdapterManager().getPlayerPresenter().getPlayerColor();
        setLayout(new FlowLayout());
        setPreferredSize(panelDimension);
        setMaximumSize(panelDimension);
        setMinimumSize(panelDimension);
        setBorder(new MatteBorder(2, 2, 2, 2, Color.black));

        String[] itemTypes = new String[]{"All", "Equipment", "Material", "Consumable", "Key", "Quest"};
        String[] SortingDependencies = new String[]{"Name", "Time", "Weight", "Rarity"};
        itemTypeSelection = new DefaultComboBox(itemTypes);
        itemSortingDependency = new DefaultComboBox(SortingDependencies);
        sortingDirection = new DefaultToggleButton("Descend","Ascend", sortingButtonColor);

        itemTypeSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemType = (String) itemTypeSelection.getSelectedItem();
                if (itemType.equals("All")) {
                    itemPanel.setShowAll(true);
                } else{
                    itemPanel.setShowAll(false);
                    itemPanel.setShowType(determineItemType(itemType));
                }
                itemPanel.updateItemPanel();
            }
        });

        itemSortingDependency.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sortBy = (String) itemSortingDependency.getSelectedItem();
                itemPanel.setSortBy(sortBy);
                itemPanel.updateItemPanel();
            }

        });

        sortingDirection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sortingDirection.isSelected()) {
                    itemPanel.setSortAscending(false);
                }else {
                    itemPanel.setSortAscending(true);
                }
                itemPanel.updateItemPanel();
            }
        });

        add(itemTypeSelection);
        add(itemSortingDependency);
        add(sortingDirection);
    }

    private ItemType determineItemType(String itemType){
        return switch (itemType){
            case "Equipment" -> ItemType.EQUIPMENT;
            case "Material" -> ItemType.MATERIAL;
            case "Consumable" -> ItemType.CONSUMABLE;
            case "Key" -> ItemType.KEY;
            case "Quest" -> ItemType.QUEST;
            default -> null;
        };
    }
    public void updateSortingButtonColor(Color sortingButtonColor){
        this.sortingButtonColor = sortingButtonColor;
        sortingDirection.setSelectedColor(sortingButtonColor);
    }
}
