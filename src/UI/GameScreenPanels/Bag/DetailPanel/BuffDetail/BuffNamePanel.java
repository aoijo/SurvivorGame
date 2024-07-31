package UI.GameScreenPanels.Bag.DetailPanel.BuffDetail;

import InterfaceAdapter.BuffAdapter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class BuffNamePanel extends JPanel {
    private DetailPanel detailPanel;
    private int currentBuffIndex;
    private BuffAdapter buffAdapter;
    private Font nameFont = new Font("Arial", Font.BOLD, 15);

    public BuffNamePanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        this.buffAdapter = detailPanel.getBuffAdapter();
        this.currentBuffIndex = 0;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(280, 30));
        setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
    }
    private JLabel createNameLabel() {
        JLabel nameLabel = new JLabel();
        if (buffAdapter.checkBuffExist()){
            String name = buffAdapter.getCurrentBuffNameByIndex(currentBuffIndex);
            nameLabel.setText(name);
        }else{
            nameLabel.setText("nothing");
        }
        nameLabel.setFont(nameFont);
        return nameLabel;
    }
    public void update(){
        removeAll();
        add(createNameLabel());
        revalidate();
        repaint();
    }
    public void setCurrentBuffIndex(int currentSkillIndex) {
        this.currentBuffIndex = currentSkillIndex;
    }
    public int getCurrentBuffIndex() {
        return currentBuffIndex;
    }
    public BuffAdapter getBuffAdapter(){
        return buffAdapter;
    }
}
