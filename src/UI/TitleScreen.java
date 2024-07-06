package UI;

import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JPanel {
    private GamePanel gamePanel;

    public TitleScreen(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLayout(new BorderLayout());

        JPanel titlePanel = createTitlePanel();
        JPanel buttonPanel = createButtonPanel();

        add(titlePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Survivor Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 36));
        titleLabel.setForeground(Color.black);

        titlePanel.add(titleLabel, BorderLayout.CENTER);
        return titlePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        Font buttonFont = new Font("Courier New", Font.BOLD, 16);

        JButton newGameButton = new DefaultButton("New Game",buttonFont);
        JButton continueButton = new DefaultButton("Continue",buttonFont);
        JButton settingsButton = new DefaultButton("Settings",buttonFont);
        JButton exitButton = new DefaultButton("Exit",buttonFont);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.switchToScreen("CreateCharScreen");
            }
        });

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.switchToScreen("GameScreen");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.switchToScreen("MenuScreen");
            }
        });

        exitButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;  // Use NONE to control size directly
        constraints.anchor = GridBagConstraints.NORTH;  // Anchor components to the top
        constraints.insets = new Insets(5, 5, 5, 5);  // Margin around components
        Dimension buttonDimension = new Dimension(200, 30); //Set the dimension of all buttons

        // New Game button
        constraints.gridy = 0;
        newGameButton.setPreferredSize(buttonDimension);
        buttonPanel.add(newGameButton, constraints);

        // Continue button
        constraints.gridy = 1;
        continueButton.setPreferredSize(buttonDimension);
        buttonPanel.add(continueButton, constraints);

        // Setting button
        constraints.gridy = 2;
        settingsButton.setPreferredSize(buttonDimension);
        buttonPanel.add(settingsButton, constraints);

        // Exit button
        constraints.gridy = 3;
        exitButton.setPreferredSize(buttonDimension);
        buttonPanel.add(exitButton, constraints);

        return buttonPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = new ImageIcon("background.jpg").getImage();
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}


