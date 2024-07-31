package UI.GameScreenPanels;

import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.TimeAdapter;
import Utils.DefaultToggleButton;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel {
    private GameScreen gameScreen;
    private PlayerPresenter playerPresenter;
    private MapPresenter mapPresenter;
    private TimeAdapter timeAdapter;
    private GridBagConstraints constraints;
    private CardLayout buttonCardLayout;

    private JLabel timeLabel;
    private JLabel locationLabel;
    private JLabel seasonLabel;
    private JLabel nameLabel;
    private JPanel dayPanel;
    private JPanel buttonSwitchPanel;
    private DefaultToggleButton characterButton;
    private DefaultToggleButton combatButton;

    private JPanel healthPanel;
    private JPanel weightPanel;
    private JPanel hydrationPanel;
    private JPanel hungerPanel;
    private JLabel levelLabel;
    private JPanel expPanel;
    private JPanel sanityPanel;
    private JLabel forgeExpPanel;

    public StatusPanel(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.playerPresenter = gameScreen.getAdapterManager().getPlayerPresenter();
        this.mapPresenter = gameScreen.getAdapterManager().getMapPresenter();
        this.timeAdapter = gameScreen.getAdapterManager().getTimeAdapter();

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(200, 600));
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.fill = GridBagConstraints.BOTH;
        buttonCardLayout = new CardLayout();

        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font timeFont = new Font("Arial", Font.PLAIN, 14);
        Font locationFont = new Font("Arial", Font.PLAIN, 14);
        Font seasonAndDayFont = new Font("Arial", Font.PLAIN, 16);
        Font nameFont = new Font("Arial", Font.ITALIC, 16);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;

        // Row 1
        constraints.gridwidth = 2;
        timeLabel = createTimeLabel(timeFont);
        JPanel row1 = labelContainer(timeLabel);
        add(row1, constraints);

        // Row 2
        constraints.gridy++;
        constraints.gridwidth = 1;
        locationLabel = createLocationLabel(locationFont);
        JPanel row2 = labelContainer(locationLabel);
        row2.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
        add(row2, constraints);

        // Row 3
        constraints.gridx++;
        nameLabel = createNameLabel(nameFont);
        JPanel row3 = labelContainer(nameLabel);
        row3.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
        add(row3, constraints);

        // Row 4
        constraints.gridy++;
        constraints.gridx--;
        constraints.gridheight = 2;
        seasonLabel = createSeasonLabel(seasonAndDayFont);
        dayPanel = createDayLabel(seasonAndDayFont);
        add(labelContainer(seasonLabel), constraints);
        constraints.gridx++;
        add(dayPanel, constraints);
        constraints.gridx--;
        constraints.gridheight = 1;

        // Row 5
        constraints.gridy += 2;
        add(createRowPanel(labelContainer(createLabel("Health", textFont)), healthPanel = createValueLabel(textFont, Color.red)), constraints);

        constraints.gridx++;
        add(createRowPanel(labelContainer(createLabel("Weight", textFont)), weightPanel = createValueLabel(textFont, Color.BLACK)), constraints);

        // Row 6
        constraints.gridy++;
        constraints.gridx--;
        add(createRowPanel(labelContainer(createLabel("Hydration", textFont)), hydrationPanel = createValueLabel(textFont, Color.BLUE)), constraints);

        constraints.gridx++;
        add(createRowPanel(labelContainer(createLabel("Hunger", textFont)), hungerPanel = createValueLabel(textFont, Color.ORANGE)), constraints);

        // Row 7
        constraints.gridy++;
        constraints.gridx--;
        add(createRowPanel(labelContainer(createLabel("Level", textFont)), labelContainer(levelLabel = createLabel("", textFont))), constraints);

        constraints.gridx++;
        add(createRowPanel(labelContainer(createLabel("EXP", textFont)), expPanel = createValueLabel(textFont, Color.BLACK)), constraints);


        // Row 8
        constraints.gridy++;
        constraints.gridx--;
        add(createRowPanel(labelContainer(createLabel("Sanity", textFont)), sanityPanel = createValueLabel(textFont, Color.BLACK)), constraints);

        constraints.gridx++;
        add(createRowPanel(labelContainer(createLabel("Forge EXP",textFont)), labelContainer(forgeExpPanel = createLabel("", textFont))), constraints);

        // Middle Space filler
        constraints.gridy++;
        constraints.gridx--;
        constraints.weighty = 1;
        constraints.gridwidth = 2;
        add(new JLabel(""), constraints);

        // Add bag button at the bottom just above the miniMap
        constraints.gridy++;
        constraints.weighty = 0;
        characterButton = createCharacterButton();
        combatButton = createCombatButton();
        JPanel buttonRow = new JPanel(new FlowLayout());
        buttonSwitchPanel = new JPanel(buttonCardLayout);
        buttonSwitchPanel.add(characterButton,"MapButton");
        buttonSwitchPanel.add(combatButton,"CombatButton");
        buttonRow.add(buttonSwitchPanel);
        add(buttonRow, constraints);

        // Initialize values
        playerPresenter.getPlayerUseCase().updatePlayer();
        updateStatusPanel();
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }
    private JPanel labelContainer(JLabel label) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label);
        return panel;
    }

    public void switchButton(String buttonName){
        buttonCardLayout.show(buttonSwitchPanel, buttonName);
    }

    private DefaultToggleButton createCharacterButton() {
        DefaultToggleButton bagButton = new DefaultToggleButton("Map", "Character", playerPresenter.getPlayerColor());
        bagButton.setPreferredSize(new Dimension(100, 30));
        bagButton.setMaximumSize(new Dimension(100, 30));
        bagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bagButton.isSelected()) {
                    gameScreen.getCentrePanel().switchToScreen("CharacterPanel");
                    gameScreen.getCentrePanel().getBagPanel().getItemPanel().updateItemPanel();
                    gameScreen.getCentrePanel().getDetailPanel().update();
                    gameScreen.getCentrePanel().getBagPanel().getInformationPanel().update();
                    gameScreen.setFocusable(false);
                } else {
                    gameScreen.getCentrePanel().switchToScreen("MapPanel");
                    gameScreen.setFocusable(true);
                    gameScreen.requestFocus();
                }
            }
        });
        return bagButton;
    }
    private DefaultToggleButton createCombatButton(){
        DefaultToggleButton combatButton = new DefaultToggleButton("Combat", "Character", playerPresenter.getPlayerColor());
        combatButton.setPreferredSize(new Dimension(100, 30));
        combatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (combatButton.isSelected()) {
                    gameScreen.getCentrePanel().switchToScreen("CharacterPanel");
                    gameScreen.getCentrePanel().getBagPanel().getItemPanel().updateItemPanel();
                    gameScreen.getCentrePanel().getDetailPanel().update();
                    gameScreen.getCentrePanel().getBagPanel().getInformationPanel().update();
                    gameScreen.setFocusable(false);
                } else {
                    gameScreen.getCentrePanel().switchToScreen("CombatPanel");
                }
            }
        });
        return combatButton;
    }

    private JPanel createRowPanel(JComponent... components) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new GridLayout(components.length, 1, 0, 0));
        for (JComponent component : components) {
            rowPanel.add(component);
        }
        rowPanel.setBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK));
        rowPanel.setPreferredSize(new Dimension(100, 48));
        return rowPanel;
    }

    private JLabel createLabel(String attribute, Font font) {
        JLabel label = new JLabel(attribute);
        label.setFont(font);
        return label;
    }

    private JPanel createValueLabel(Font font, Color valueColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel valueLabel = new JLabel();
        valueLabel.setFont(font);
        valueLabel.setForeground(valueColor);

        JLabel separatorLabel = new JLabel("/");
        separatorLabel.setFont(font);

        JLabel maxValueLabel = new JLabel();
        maxValueLabel.setFont(font);
        maxValueLabel.setForeground(valueColor);

        panel.add(valueLabel);
        panel.add(separatorLabel);
        panel.add(maxValueLabel);

        return panel;
    }

    private JLabel createTimeLabel(Font timeFont) {
        JLabel timeLabel = new JLabel();
        timeLabel.setFont(timeFont);
        return timeLabel;
    }

    private JLabel createLocationLabel(Font locationFont) {
        JLabel locationLabel = new JLabel();
        locationLabel.setFont(locationFont);
        return locationLabel;
    }

    private JLabel createNameLabel(Font nameFont) {
        JLabel nameLabel = new JLabel();
        nameLabel.setFont(nameFont);
        return nameLabel;
    }

    private JLabel createSeasonLabel(Font seasonFont) {
        JLabel seasonLabel = new JLabel();
        seasonLabel.setFont(seasonFont);
        return seasonLabel;
    }

    private JPanel createDayLabel(Font dayFont) {
        JPanel panel = new JPanel();
        JLabel dayLabel = new JLabel();
        dayLabel.setFont(dayFont);
        panel.add(dayLabel);
        return panel;
    }

    public void updateStatusPanel() {
        timeLabel.setText(getTimeString());
        locationLabel.setText(getLocationString());
        seasonLabel.setText(getSeasonString());
        nameLabel.setText(playerPresenter.getPlayerName());
        nameLabel.setForeground(playerPresenter.getPlayerColor());
        updateDayPanel();

        updateValuePanel(healthPanel, playerPresenter.getHealth(), playerPresenter.getCurrentMaxHealth());
        updateValuePanel(weightPanel, (int)playerPresenter.getWeight(), (int)playerPresenter.getCurrentMaxWeight());
        updateValuePanel(hydrationPanel, playerPresenter.getHydration(), playerPresenter.getCurrentMaxHydration());
        updateValuePanel(hungerPanel, playerPresenter.getHunger(), playerPresenter.getCurrentMaxHunger());
        levelLabel.setText("" + playerPresenter.getLevel());
        forgeExpPanel.setText("" + playerPresenter.getForgeEXP());
        updateValuePanel(expPanel, playerPresenter.getExperience(), playerPresenter.getMaxExperience());
        updateValuePanel(sanityPanel, playerPresenter.getSanity(), playerPresenter.getMaxSanity());
        if(playerPresenter.getPlayerInCombat()){
            switchButton("CombatButton");
            combatButton.setSelectedColor(playerPresenter.getPlayerColor());
            combatButton.repaint();
        }else{
            switchButton("CharacterButton");
            characterButton.setSelectedColor(playerPresenter.getPlayerColor());
            characterButton.repaint();
        }
    }

    private String getTimeString() {
        int year = timeAdapter.getYear();
        int month = timeAdapter.getMonthValue();
        int day = timeAdapter.getDay();
        int hour = timeAdapter.getHour();
        int minute = timeAdapter.getMinute();
        return String.format("%04d/%02d/%02d %02d:%02d", year, month, day, hour, minute);
    }

    private String getLocationString() {
        int[] position = playerPresenter.getPlayerPosition();
        String tileName = mapPresenter.getTileName(position[0], position[1]);
        return tileName.substring(0, 1).toUpperCase() + tileName.substring(1) + String.format("[%d,%d]", position[0], position[1]);
    }

    private String getSeasonString() {
        String seasonString = timeAdapter.getSeason().toString().charAt(0) + timeAdapter.getSeason().toString().substring(1).toLowerCase();
        return seasonString;
    }

    private void updateDayPanel() {
        JLabel dayLabel = (JLabel) dayPanel.getComponent(0);
        if (!timeAdapter.isNight()) {
            dayLabel.setText("Day");
            dayLabel.setForeground(Color.BLACK);
            dayPanel.setBackground(Color.WHITE);
        } else {
            dayLabel.setText("Night");
            dayLabel.setForeground(Color.BLUE);
            dayPanel.setBackground(Color.BLACK);
        }
    }

    private void updateValuePanel(JPanel panel, int value, int maxValue) {
        JLabel valueLabel = (JLabel) panel.getComponent(0);
        JLabel maxValueLabel = (JLabel) panel.getComponent(2);
        valueLabel.setText(String.valueOf(value));
        maxValueLabel.setText(String.valueOf(maxValue));
    }
    public DefaultToggleButton getCombatButton() {
        return combatButton;
    }
}
