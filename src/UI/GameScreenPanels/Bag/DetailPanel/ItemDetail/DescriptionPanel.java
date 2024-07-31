package UI.GameScreenPanels.Bag.DetailPanel.ItemDetail;

import Enums.Item.ItemType;
import InterfaceAdapter.ItemAdapter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;

import javax.swing.*;
import java.awt.*;

public class DescriptionPanel extends JPanel {
    private DetailPanel detailPanel;
    private ItemAdapter itemAdapter;
    private int currentItemIndex;
    private Font textFont = new Font("Arial", Font.PLAIN, 13);

    public DescriptionPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.itemAdapter = detailPanel.getItemAdapter();
        this.currentItemIndex = detailPanel.getCurrentItemIndex();
        setLayout(new BorderLayout());
        add(createDescriptionContent(), BorderLayout.NORTH);
    }

    private JPanel createDescriptionContent() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setMinimumSize(new Dimension(280, 60));
        if (itemAdapter.getItemTypeByIndex(currentItemIndex) == ItemType.EQUIPMENT) {
            container.add(createEquipmentStatsPanel());
        }
        container.add(createTextArea());
        return container;
    }

    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(itemAdapter.getDescriptionByIndex(currentItemIndex));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(textFont);
        textArea.setBackground(null);
        textArea.setPreferredSize(new Dimension(260, 100));
        textArea.setMaximumSize(new Dimension(260, Integer.MAX_VALUE));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the top, left, bottom, and right
        return textArea;
    }

    private JPanel createEquipmentStatsPanel() {
        JPanel equipmentStatsPanel = new JPanel();
        equipmentStatsPanel.setLayout(new BoxLayout(equipmentStatsPanel, BoxLayout.Y_AXIS));

        int[] equipmentStats = new int[]{
                itemAdapter.getAttackByIndex(currentItemIndex),
                itemAdapter.getDefenseByIndex(currentItemIndex),
                itemAdapter.getLifeStealByIndex(currentItemIndex),
                itemAdapter.getDamageReductionByIndex(currentItemIndex),
                itemAdapter.getMaxHealthByIndex(currentItemIndex),
                itemAdapter.getMaxDurabilityByIndex(currentItemIndex),
                itemAdapter.getLevelRequirementByIndex(currentItemIndex)
        };

        String[] equipmentStatNames = new String[]{
                "Attack", "Defense", "Life Steal", "Damage Reduction", "Max Health", "Max Durability", "Level Requirement"
        };

        for (int i = 0; i < equipmentStats.length; i++) {
            if (equipmentStats[i] != 0) {
                JPanel statPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                if (equipmentStatNames[i].equals("Life Steal") || equipmentStatNames[i].equals("Damage Reduction")) {
                    JLabel equipmentStatLabel = new JLabel(equipmentStatNames[i] + ": " + equipmentStats[i] + "%");
                    statPanel.add(equipmentStatLabel);
                }else{
                    JLabel equipmentStatLabel = new JLabel(equipmentStatNames[i] + ": " + equipmentStats[i]);
                    statPanel.add(equipmentStatLabel);
                }
                equipmentStatsPanel.add(statPanel);
            }
        }
        return equipmentStatsPanel;
    }

    public void update() {
        currentItemIndex = detailPanel.getCurrentItemIndex();
        removeAll();
        add(createDescriptionContent(), BorderLayout.NORTH);
        revalidate();
        repaint();
    }
}
