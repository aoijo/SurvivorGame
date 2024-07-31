package UI.GameScreenPanels.Bag.DetailPanel.ItemDetail;

import Enums.Item.ItemType;
import InterfaceAdapter.ItemAdapter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class NamePanel extends JPanel {
    private DetailPanel detailPanel;
    private ItemAdapter itemAdapter;
    private int currentItemIndex;
    private Font nameFont = new Font("Arial", Font.BOLD, 15);

    public NamePanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.itemAdapter = detailPanel.getItemAdapter();
        this.currentItemIndex = detailPanel.getCurrentItemIndex();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(280, 30));
        setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        add(createNameLabel());
    }

    private JLabel createNameLabel() {
        String name = itemAdapter.getNameByIndex(currentItemIndex);
        if (itemAdapter.getItemTypeByIndex(currentItemIndex) == ItemType.EQUIPMENT) {
            name += " (" + itemAdapter.getDurabilityByIndex(currentItemIndex) + ")";
        } else {
            name += " (" + itemAdapter.getQuantityByIndex(currentItemIndex) + ")";
        }
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(nameFont);
        nameLabel.setForeground(getNameColor());
        return nameLabel;
    }

    private Color getNameColor() {
        return switch (itemAdapter.getRarityByIndex(currentItemIndex)) {
            case COMMON -> Color.BLACK;
            case UNCOMMON -> new Color(0, 175, 0);
            case RARE -> Color.BLUE;
            case LEGENDARY -> Color.ORANGE;
            case MYTHICAL -> Color.RED;
            case UNIQUE -> Color.MAGENTA;
            default -> Color.BLACK;
        };
    }

    public void update(){
        currentItemIndex = detailPanel.getCurrentItemIndex();
        removeAll();
        add(createNameLabel());
        revalidate();
        repaint();
    }
}
