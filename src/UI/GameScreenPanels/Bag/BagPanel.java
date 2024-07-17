package UI.GameScreenPanels.Bag;

import UI.GameScreenPanels.GameScreen;

import javax.swing.*;
import java.awt.*;

public class BagPanel extends JPanel {
    private GameScreen gameScreen;
    private GridBagConstraints constraints;

    public BagPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;

        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 5, 10, 5);

    }

}
