package UI.GameScreenPanels.Bag.DetailPanel;

import InterfaceAdapter.ItemAdapter;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class NumberPanel extends JPanel {
    private ItemAdapter itemAdapter;
    private int currentItemIndex;

    public NumberPanel(DetailPanel detailPanel) {
        this.itemAdapter = detailPanel.getItemAdapter();
        this.currentItemIndex = detailPanel.getCurrentItemIndex();
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        setPreferredSize(new Dimension(280, 30));
        setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        addComponents();
    }

    private void addComponents() {
        add(new JLabel("Weight: " + itemAdapter.getWeightByIndex(currentItemIndex) + " (" + itemAdapter.getSingleWeightByIndex(currentItemIndex) + ")"));
        add(new JLabel("Price: " + itemAdapter.getPriceByIndex(currentItemIndex) + " (" + itemAdapter.getSinglePriceByIndex(currentItemIndex) + ")"));
        add(new JLabel("Dust: " + itemAdapter.getDustByIndex(currentItemIndex) + " (" + itemAdapter.getSingleDustByIndex(currentItemIndex) + ")"));
    }
    public void update(){
        removeAll();
        addComponents();
        revalidate();
        repaint();
    }
}