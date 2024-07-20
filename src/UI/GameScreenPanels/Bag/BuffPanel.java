package UI.GameScreenPanels.Bag;

import InterfaceAdapter.MapAdapter.BuffAdapter;
import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;

public class BuffPanel extends JPanel {
    private GameScreen gameScreen;
    private BuffAdapter buffAdapter;
    private GridBagConstraints constraints;

    private Font buffButtonFont = new Font("Arial", Font.PLAIN, 10);
    private Dimension buttonSize = new Dimension(135, 20);

    public BuffPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.buffAdapter = gameScreen.getAdapterManager().getBuffAdapter();
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 0, 5); // Padding between elements
        constraints.fill = GridBagConstraints.HORIZONTAL;
        updateBuffPanel();
    }

    public void updateBuffPanel() {
        removeAll(); // Remove all existing components before updating

        if (gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getBuffs() == null) {
            return;
        }

        int maxIndex = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getBuffs().size();

        for (int i = 0; i < (maxIndex + 1) / 2; i++) { // Use (maxIndex + 1) / 2 to handle odd numbers correctly
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

            int currentIndex = i * 2;
            rowPanel.add(BuffButton(currentIndex));
            rowPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons

            if (currentIndex + 1 < maxIndex) {
                rowPanel.add(BuffButton(currentIndex + 1));
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

    private JButton BuffButton(int buffIndex) {
        String buffName = buffAdapter.getBuffNameByIndex(buffIndex);
        int buffStack = buffAdapter.getBuffStackByIndex(buffIndex);

        String buttonText = buffName + " (" + buffStack + ")";
        JButton buffButton = new DefaultButton(buttonText);

        buffButton.setFont(buffButtonFont);
        buffButton.setPreferredSize(buttonSize);
        buffButton.setMinimumSize(buttonSize);
        buffButton.setMaximumSize(buttonSize);
        return buffButton;
    }
}
