package UI.GameScreenPanels.Bag.BagPanel;

import InterfaceAdapter.BuffAdapter;
import UI.GameScreenPanels.Bag.DetailPanel.DetailPanel;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuffPanel extends JPanel {
    private GameScreen gameScreen;
    private BuffAdapter buffAdapter;
    private DetailPanel detailPanel;

    private Font buffButtonFont = new Font("Arial", Font.PLAIN, 10);
    private Dimension buttonSize = new Dimension(135, 20);

    public BuffPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.buffAdapter = gameScreen.getAdapterManager().getBuffAdapter();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 10, 5, 10)); // Add padding around the panel
        updateBuffPanel();
    }

    public void updateBuffPanel() {
        removeAll(); // Remove all existing components before updating

        if (gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentBuffs() == null) {
            return;
        }

        int maxIndex = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getCurrentBuffs().size();

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        for (int i = 0; i < maxIndex; i++) {
            gridPanel.add(BuffButton(i));
        }

        // If the number of buffs is odd, add an empty panel to fill the grid
        if (maxIndex % 2 != 0) {
            gridPanel.add(Box.createRigidArea(buttonSize));
        }

        add(gridPanel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    private JButton BuffButton(int buffIndex) {
        String buffName = buffAdapter.getCurrentBuffNameByIndex(buffIndex);
        int buffStack = buffAdapter.getCurrentBuffStackByIndex(buffIndex);

        String buttonText = buffName + " (" + buffStack + ")";
        JButton buffButton = new DefaultButton(buttonText);
        buffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailPanel.getBuffNamePanel().setCurrentBuffIndex(buffIndex);
                detailPanel.setInformationType("buff");
            }
        });

        buffButton.setFont(buffButtonFont);
        buffButton.setPreferredSize(buttonSize);
        buffButton.setMinimumSize(buttonSize);
        buffButton.setMaximumSize(buttonSize);
        return buffButton;
    }

    public void setDetailPanel(DetailPanel detailPanel) {
        this.detailPanel = detailPanel;
    }
}
