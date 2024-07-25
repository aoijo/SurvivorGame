package UI.GameScreenPanels.Bag.BagPanel;

import Enums.Rarity;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentPanel extends JPanel {
    private GridBagConstraints constraints;
    private PlayerPresenter playerPresenter;
    private DetailPanel detailPanel;

    private Font buttonFont = new Font("Arial", Font.BOLD, 10);
    private Font textFont = new Font("Arial", Font.BOLD, 12);
    private Dimension buttonSize = new Dimension(100, 40); // Increase the button size

    private DefaultButton weaponButton;
    private DefaultButton armorButton;
    private DefaultButton toolButton;
    private DefaultButton bagButton;
    private DefaultButton amuletButton1;
    private DefaultButton amuletButton2;
    private DefaultButton amuletButton3;
    private DefaultButton amuletButton4;

    public EquipmentPanel() {
        setLayout(new GridBagLayout());
        setBorder(null);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1; // Ensure equal vertical space
        constraints.insets = new Insets(0, 0, 0, 0);

        initializeEquipmentPanel();
    }

    public void initializeEquipmentPanel() {
        weaponButton = createEquipmentButton("weapon");
        armorButton = createEquipmentButton("armor");
        toolButton = createEquipmentButton("tool");
        bagButton = createEquipmentButton("bag");
        amuletButton1 = createEquipmentButton("amulet1");
        amuletButton2 = createEquipmentButton("amulet2");
        amuletButton3 = createEquipmentButton("amulet3");
        amuletButton4 = createEquipmentButton("amulet4");

        JPanel weaponPanel = singleEquipmentPanel("weapon", weaponButton,false,false);
        JPanel armorPanel = singleEquipmentPanel("armor", armorButton,false, true);
        JPanel toolPanel = singleEquipmentPanel("tool", toolButton, true,false);
        JPanel bagPanel = singleEquipmentPanel("bag", bagButton,true, true);
        JPanel amuletPanel1 = singleEquipmentPanel("amulet1", amuletButton1,false,false);
        JPanel amuletPanel2 = singleEquipmentPanel("amulet2", amuletButton2,false, true);
        JPanel amuletPanel3 = singleEquipmentPanel("amulet3", amuletButton3,false,false);
        JPanel amuletPanel4 = singleEquipmentPanel("amulet4", amuletButton4,false, true);

        constraints.gridy = 0;
        add(createRow(weaponPanel, armorPanel), constraints);
        constraints.gridy++;
        add(createRow(amuletPanel1, amuletPanel2), constraints);
        constraints.gridy++;
        add(createRow(amuletPanel3, amuletPanel4), constraints);
        constraints.gridy++;
        JPanel lastRow = createRow(toolPanel, bagPanel);
        lastRow.setBorder(null);
        add(lastRow, constraints);
    }

    private JPanel createRow(JPanel panel1, JPanel panel2) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.add(panel1);
        //rowPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between panels
        rowPanel.add(panel2);
        return rowPanel;
    }

    private JPanel singleEquipmentPanel(String equipmentType, DefaultButton equipmentButton, boolean isBottom, boolean isRight) {
        JPanel equipmentPanel = new JPanel(new BorderLayout());
        equipmentPanel.setPreferredSize(new Dimension(145, 45)); // Increase the preferred size

        JLabel equipmentNameLabel = new JLabel("  " + equipmentType); // Adjust the length as needed
        equipmentNameLabel.setFont(textFont);
        equipmentNameLabel.setPreferredSize(new Dimension(60, 45)); // Adjust size for label
        equipmentNameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        equipmentButton.setPreferredSize(new Dimension(85, 45)); // Adjust size for button
        JPanel equipmentNamePanel = new JPanel(new BorderLayout());
        if (isRight){
            if (! isBottom){
                equipmentNamePanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
                equipmentButton.setBorder(new MatteBorder(0, 2, 2, 0, Color.BLACK));
            }else{
                equipmentButton.setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));
            }
        } else{
            if (! isBottom){
                equipmentNamePanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
                equipmentButton.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK));
            }else{
                equipmentButton.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK));
            }
        }
        equipmentNamePanel.add(equipmentNameLabel, BorderLayout.WEST);
        equipmentPanel.add(equipmentNamePanel, BorderLayout.CENTER);
        equipmentPanel.add(equipmentButton, BorderLayout.EAST);

        return equipmentPanel;
    }

    private String truncateString(String text, int maxLength) {
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "...";
        } else {
            return text;
        }
    }

    private DefaultButton createEquipmentButton(String equipmentType) {
        if (playerPresenter == null) {
            return new DefaultButton("nothing");
        }
        if (playerPresenter.checkEquipment(equipmentType)) {
            String name = playerPresenter.getEquipmentName(equipmentType);
            Rarity equipmentRarity = playerPresenter.getEquippedRarity(equipmentType);
            DefaultButton equipmentButton = new DefaultButton(truncateString(name,15), equipmentRarity);
            equipmentButton.setPreferredSize(buttonSize);
            equipmentButton.setMaximumSize(buttonSize);
            equipmentButton.setMinimumSize(buttonSize);
            equipmentButton.setFont(buttonFont);
            equipmentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    detailPanel.setCurrentItemIndex(playerPresenter.getEquipmentIndex(equipmentType));
                    detailPanel.update();
                }
            });
            return equipmentButton;
        } else {
            DefaultButton equipmentButton = new DefaultButton("Nothing");
            equipmentButton.setPreferredSize(buttonSize);
            equipmentButton.setMaximumSize(buttonSize);
            equipmentButton.setMinimumSize(buttonSize);
            equipmentButton.setFont(buttonFont);
            return equipmentButton;
        }
    }

    public void setPlayerPresenter(PlayerPresenter playerPresenter) {
        this.playerPresenter = playerPresenter;
    }

    public void setDetailPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
    }

    public void updateEquipmentPanel() {
        replaceButton(weaponButton, "weapon");
        replaceButton(armorButton, "armor");
        replaceButton(toolButton, "tool");
        replaceButton(bagButton, "bag");
        replaceButton(amuletButton1, "amulet1");
        replaceButton(amuletButton2, "amulet2");
        replaceButton(amuletButton3, "amulet3");
        replaceButton(amuletButton4, "amulet4");
        revalidate();
        repaint();
    }

    private void replaceButton(DefaultButton oldButton, String equipmentType) {
        DefaultButton newButton = createEquipmentButton(equipmentType);
        oldButton.setText(newButton.getText());
        oldButton.setFont(newButton.getFont());
        oldButton.setForeground(newButton.getForeground());
        oldButton.setActionCommand(newButton.getActionCommand());
        for (ActionListener al : oldButton.getActionListeners()) {
            oldButton.removeActionListener(al);
        }
        for (ActionListener al : newButton.getActionListeners()) {
            oldButton.addActionListener(al);
        }
        oldButton.revalidate();
        oldButton.repaint();
    }
}
