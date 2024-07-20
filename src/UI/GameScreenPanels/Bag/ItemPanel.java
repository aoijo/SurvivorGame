package UI.GameScreenPanels.Bag;

import Enums.Item.ItemType;
import Enums.Rarity;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ItemPanel extends JPanel {
    private GameScreen gameScreen;
    private GridBagConstraints constraints;
    private Font buffButtonFont = new Font("Arial", Font.BOLD, 10);
    private Dimension buttonSize = new Dimension(135, 20);

    private ItemType ShowType = null;
    private String SortBy = "Name";
    private boolean sortAscending = true;
    private boolean showAll = true;

    private String[][] itemInformation;
    private String[] itemNames;
    private int[] itemIds;
    private int[] itemQuantities;
    private Rarity[] itemRarities;

    public ItemPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;

        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 0, 5);
        updateItemPanel();
    }

    public void updateItemPanel() {
        this.itemInformation = gameScreen.getUseCaseManager().getPlayerUseCase().getSortedItemInfo(
                ShowType,SortBy,showAll,sortAscending);
        this.itemIds = readIntArray(itemInformation[0]);
        this.itemNames = itemInformation[1];
        this.itemQuantities = readIntArray(itemInformation[2]);
        this.itemRarities = readRarityArray(itemInformation[3]);

        removeAll();

        if (gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getItemInBag() == null) {
            add(NoItem(),constraints);
            revalidate(); // Revalidate the layout
            repaint(); // Repaint the panel
            return;
        }

        int maxIndex = itemIds.length;

        if (maxIndex == 0){
            add(NoItem(),constraints);
            revalidate(); // Revalidate the layout
            repaint(); // Repaint the panel
            return;
        }
        for (int i = 0; i < (maxIndex + 1) / 2; i++) { // Use (maxIndex + 1) / 2 to handle odd numbers correctly
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

            int currentIndex = i * 2;
            rowPanel.add(ItemButton(currentIndex));
            rowPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons

            if (currentIndex + 1 < maxIndex) {
                rowPanel.add(ItemButton(currentIndex + 1));
            } else {
                rowPanel.add(Box.createRigidArea(buttonSize)); // Add empty space if odd number of buffs
            }

            constraints.gridy = i;
            add(rowPanel, constraints);
        }

        constraints.gridy++;
        constraints.weighty = 1;
        add(new JPanel(), constraints);
        constraints.weighty = 0;

        revalidate(); // Revalidate the layout
        repaint(); // Repaint the panel

    }

    private JButton ItemButton(int itemIndex){
        String itemName = itemNames[itemIndex];
        int itemQuantity = itemQuantities[itemIndex];
        Rarity itemRarity = itemRarities[itemIndex];

        String buttonText = itemName + "(" + itemQuantity + ")";
        JButton itemButton = new DefaultButton(buttonText, itemRarity);

        itemButton.setFont(buffButtonFont);
        itemButton.setPreferredSize(buttonSize);
        itemButton.setMaximumSize(buttonSize);
        itemButton.setMinimumSize(buttonSize);
        return itemButton;
    }

    private int[] readIntArray(String[] strings){
        int[] intArray = new int[strings.length];
        for (int i = 0; i < strings.length; i++){
            intArray[i] = Integer.parseInt(strings[i]);
        }
        return intArray;
    }

    private Rarity[] readRarityArray(String[] strings){
        Rarity[] rarityArray = new Rarity[strings.length];
        for (int i = 0; i < strings.length; i++){
            rarityArray[i] = Rarity.valueOf(strings[i]);
        }
        return rarityArray;
    }
    private JPanel NoItem(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("No item here!");
        label.setFont(new Font("Arial", Font.ITALIC, 15));
        label.setForeground(Color.lightGray);
        panel.add(label);
        return panel;
    }

    public void setShowType(ItemType showType) {
        ShowType = showType;
    }
    public void setSortBy(String sortBy) {
        SortBy = sortBy;
    }
    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }
    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

}
