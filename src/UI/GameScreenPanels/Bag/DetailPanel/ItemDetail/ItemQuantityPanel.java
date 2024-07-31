package UI.GameScreenPanels.Bag.DetailPanel.ItemDetail;

import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import Utils.SwitchButton;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ItemQuantityPanel extends JPanel {
    private DetailPanel detailPanel;
    private Color switchButtonColor;

    private SwitchButton allButton;
    private SwitchButton oneButton;
    private SwitchButton tenButton;
    private SwitchButton hundredButton;
    private SwitchButton thousandButton;

    public ItemQuantityPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.switchButtonColor = detailPanel.getPlayerPresenter().getPlayerColor();
        setPreferredSize(new Dimension(280, 20));
        setBorder(new MatteBorder(2,0,0,0,Color.black));
        setLayout(new GridLayout(1, 5));

        allButton = new SwitchButton("All",switchButtonColor);
        oneButton = new SwitchButton("X1",switchButtonColor);
        tenButton = new SwitchButton("X10",switchButtonColor);
        hundredButton = new SwitchButton("X100",switchButtonColor);
        thousandButton = new SwitchButton("X1000",switchButtonColor);

        allButton.setBorder(new MatteBorder(0,0,0,2,Color.black));
        oneButton.setBorder(new MatteBorder(0,0,0,2,Color.black));
        tenButton.setBorder(new MatteBorder(0,0,0,2,Color.black));
        hundredButton.setBorder(new MatteBorder(0,0,0,2,Color.black));
        thousandButton.setBorder(null);

        add(allButton);
        add(oneButton);
        add(tenButton);
        add(hundredButton);
        add(thousandButton);

        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.setItemUseCount(Integer.MAX_VALUE);
                updateItemQuantityPanel();

            }
        });
        oneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.setItemUseCount(1);
                updateItemQuantityPanel();
            }
        });
        tenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.setItemUseCount(10);
                updateItemQuantityPanel();
            }
        });
        hundredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.setItemUseCount(100);
                updateItemQuantityPanel();
            }
        });
        thousandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.setItemUseCount(1000);
                updateItemQuantityPanel();
            }
        });

        oneButton.setActive(true);
    }

    public void updateItemQuantityPanel() {
        SwitchButton[] buttons = {allButton, oneButton, tenButton, hundredButton,thousandButton};
        int itemQuantity = detailPanel.getItemUseCount();
        Map<Integer, boolean[]> panelStates = new HashMap<>();

        panelStates.put(Integer.MAX_VALUE, new boolean[]{true, false, false, false, false});
        panelStates.put(1, new boolean[]{false, true, false, false, false});
        panelStates.put(10, new boolean[]{false, false, true, false, false});
        panelStates.put(100, new boolean[]{false, false, false, true, false});
        panelStates.put(1000, new boolean[]{false, false, false, false, true});

        boolean[] states = panelStates.get(itemQuantity);

        if (states == null) {
            throw new IllegalArgumentException("Unexpected item use number: " + itemQuantity);
        }

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setActive(states[i]);
            buttons[i].setButtonColor(switchButtonColor);
            buttons[i].repaint();
        }
    }

    public void setSwitchButtonColor(Color switchButtonColor) {
        this.switchButtonColor = switchButtonColor;
    }
}
