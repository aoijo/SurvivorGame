package UI.GameScreenPanels.Bag;

import UI.GameScreenPanels.GameScreen;
import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;

public class BuffPanel extends JPanel {
    private GameScreen gameScreen;
    private GridBagConstraints gbc;

    public BuffPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between elements
        gbc.fill = GridBagConstraints.HORIZONTAL;

        updateBuffPanel();
    }

    public void updateBuffPanel() {
        removeAll(); // Remove all existing components before updating
        int maxIndex = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getBuffs().size();

        for (int i = 0; i < (maxIndex + 1) / 2; i++) { // Use (maxIndex + 1) / 2 to handle odd numbers correctly
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, 2, 5, 5));

            int currentIndex = i * 2;
            rowPanel.add(BuffButton(currentIndex));
            if (currentIndex + 1 < maxIndex) {
                rowPanel.add(BuffButton(currentIndex + 1));
            }
            gbc.gridy = i;
            add(rowPanel, gbc);
        }

        revalidate(); // Revalidate the layout
        repaint(); // Repaint the panel
    }

    private JButton BuffButton(int buffIndex) {
        String buffName = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getBuffs().get(buffIndex).getName();
        int buffStack = gameScreen.getUseCaseManager().getPlayerUseCase().getPlayer().getBuffs().get(buffIndex).getStack();
        String buttonText = buffName + "(" + buffStack + ")";
        return new DefaultButton(buttonText);
    }
}
