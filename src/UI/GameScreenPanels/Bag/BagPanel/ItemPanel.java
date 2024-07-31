package UI.GameScreenPanels.Bag.BagPanel;

import Enums.Item.ItemType;
import Enums.Rarity;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel {
    private GameScreen gameScreen;
    private DetailPanel detailPanel;
    private Font buttonFont = new Font("Arial", Font.BOLD, 10);
    private Dimension buttonSize = new Dimension(135, 20);
    private Border equippedBorder = new LineBorder(Color.BLUE, 3);

    private ItemType ShowType = null;
    private String SortBy = "Name";
    private boolean sortAscending = true;
    private boolean showAll = true;

    private String[][] itemInformation;
    private String[] itemNames;
    private int[] itemIds;
    private int[] itemQuantities;
    private Rarity[] itemRarities;
    private boolean[] itemsEquipped;

    public ItemPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 10, 5, 10)); // Add padding around the panel
        updateItemPanel();
    }

    public void updateItemPanel() {
        this.itemInformation = gameScreen.getUseCaseManager().getPlayerUseCase().getSortedItemInfo(
                ShowType, SortBy, showAll, sortAscending);
        this.itemIds = readIntArray(itemInformation[0]);
        this.itemNames = itemInformation[1];
        this.itemQuantities = readIntArray(itemInformation[2]);
        this.itemRarities = readRarityArray(itemInformation[3]);
        this.itemsEquipped = readBooleanArray(itemInformation[4]);
        removeAll();

        if (gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getItemInBag() == null) {
            add(NoItem(), BorderLayout.CENTER);
            revalidate(); // Revalidate the layout
            repaint(); // Repaint the panel
            return;
        }

        int maxIndex = itemIds.length;

        if (maxIndex == 0) {
            add(NoItem(), BorderLayout.CENTER);
            revalidate(); // Revalidate the layout
            repaint(); // Repaint the panel
            return;
        }

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        for (int i = 0; i < maxIndex; i++) {
            gridPanel.add(ItemButton(i));
        }

        // If the number of items is odd, add an empty space to fill the grid
        if (maxIndex % 2 != 0) {
            gridPanel.add(Box.createRigidArea(buttonSize));
        }

        add(gridPanel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    private JButton ItemButton(int itemIndex) {
        String itemName = itemNames[itemIndex];
        int itemQuantity = itemQuantities[itemIndex];
        Rarity itemRarity = itemRarities[itemIndex];
        boolean itemEquipped = itemsEquipped[itemIndex];

        String buttonText = itemName + " (" + itemQuantity + ")";
        JButton itemButton = new DefaultButton(buttonText, itemRarity);
        if (itemEquipped) {
            itemButton.setBorder(equippedBorder);
        }

        itemButton.setFont(buttonFont);
        itemButton.setPreferredSize(buttonSize);
        itemButton.setMaximumSize(buttonSize);
        itemButton.setMinimumSize(buttonSize);

        itemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.setCurrentItemIndex(itemIndex);
                detailPanel.setInformationType("item");
            }
        });
        return itemButton;
    }

    private boolean[] readBooleanArray(String[] input) {
        boolean[] booleanArray = new boolean[input.length];
        for (int i = 0; i < input.length; i++) {
            booleanArray[i] = Boolean.parseBoolean(input[i]);
        }
        return booleanArray;
    }

    private int[] readIntArray(String[] strings) {
        int[] intArray = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            intArray[i] = Integer.parseInt(strings[i]);
        }
        return intArray;
    }

    private Rarity[] readRarityArray(String[] strings) {
        Rarity[] rarityArray = new Rarity[strings.length];
        for (int i = 0; i < strings.length; i++) {
            rarityArray[i] = Rarity.valueOf(strings[i]);
        }
        return rarityArray;
    }

    private JPanel NoItem() {
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

    public DetailPanel getDetailPanel() {
        return detailPanel;
    }

    public void setDetailPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
    }

    public Font getButtonFont() {
        return buttonFont;
    }

    public Dimension getButtonSize() {
        return buttonSize;
    }

    public String[] getItemNames() {
        return itemNames;
    }

    public Rarity[] getItemRarities() {
        return itemRarities;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }
}
