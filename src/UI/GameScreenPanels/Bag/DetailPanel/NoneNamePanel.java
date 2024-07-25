package UI.GameScreenPanels.Bag.DetailPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class NoneNamePanel extends JPanel {
    private DetailPanel detailPanel;
    private Font nameFont = new Font("Arial", Font.BOLD, 15);

    public NoneNamePanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(280, 30));
        setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        add(createNameLabel());
    }
    private JLabel createNameLabel() {
        String name = "Nothing";
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(nameFont);
        return nameLabel;
    }
    public void update() {
        removeAll();
        add(createNameLabel());
        revalidate();
        repaint();
    }
}
