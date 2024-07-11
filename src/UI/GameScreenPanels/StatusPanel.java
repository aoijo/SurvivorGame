package UI.GameScreenPanels;

import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.TimeAdapter;
import UI.GameScreenPanels.MapPanel;
import Utils.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel {
    private PlayerPresenter playerPresenter;
    private MapPresenter mapPresenter;
    private TimeAdapter timeAdapter;

    private JLabel timeLabel;
    private JLabel locationLabel;
    private JLabel seasonLabel;
    private JPanel dayPanel;
    private MapPanel miniMap;

    private JPanel healthPanel;
    private JPanel weightPanel;
    private JPanel hydrationPanel;
    private JPanel hungerPanel;
    private JLabel levelLabel;
    private JPanel expPanel;
    private JPanel sanityPanel;

    public StatusPanel(PlayerPresenter playerPresenter, MapPresenter mapPresenter, TimeAdapter timeAdapter, MapPanel miniMap) {
        this.playerPresenter = playerPresenter;
        this.mapPresenter = mapPresenter;
        this.timeAdapter = timeAdapter;
        this.miniMap = miniMap;

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);
        Font textFont = new Font("Courier New", Font.BOLD, 12);
        Font timeFont = new Font("Arial", Font.PLAIN, 12);
        Font locationFont = new Font("Arial", Font.BOLD, 12);
        Font seasonAndDayFont = new Font("Arial", Font.BOLD, 14);

        constraints.gridx = 0;
        constraints.gridy = 0;

        // Row 1
        constraints.gridwidth = 2;
        timeLabel = createTimeLabel(timeFont);
        add(timeLabel, constraints);

        // Row 2
        constraints.gridy++;
        locationLabel = createLocationLabel(locationFont);
        add(locationLabel, constraints);

        // Row 3
        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        seasonLabel = createSeasonLabel(seasonAndDayFont);
        add(seasonLabel, constraints);
        constraints.gridx++;
        dayPanel = createDayLabel(seasonAndDayFont);
        add(dayPanel, constraints);
        constraints.gridheight = 1;

        // Row 4
        constraints.gridx--;
        constraints.gridy += 2;
        add(createLabel("Health", textFont), constraints);
        constraints.gridy++;
        healthPanel = createValueLabel(textFont, Color.red);
        add(healthPanel, constraints);

        constraints.gridx++;
        constraints.gridy--;
        add(createLabel("Weight", textFont), constraints);
        constraints.gridy++;
        weightPanel = createValueLabel(textFont, Color.BLACK);
        add(weightPanel, constraints);

        // Row 5
        constraints.gridy++;
        constraints.gridx--;
        add(createLabel("Hydration", textFont), constraints);
        constraints.gridy++;
        hydrationPanel = createValueLabel(textFont, Color.BLUE);
        add(hydrationPanel, constraints);

        constraints.gridx++;
        constraints.gridy--;
        add(createLabel("Hunger", textFont), constraints);
        constraints.gridy++;
        hungerPanel = createValueLabel(textFont, Color.ORANGE);
        add(hungerPanel, constraints);

        // Row 6
        constraints.gridy++;
        constraints.gridx--;
        add(createLabel("Level", textFont), constraints);
        constraints.gridy++;
        levelLabel = createLabel("", textFont);
        add(levelLabel, constraints);

        constraints.gridx++;
        constraints.gridy--;
        add(createLabel("EXP", textFont), constraints);
        constraints.gridy++;
        expPanel = createValueLabel(textFont, Color.BLACK);
        add(expPanel, constraints);

        // Row 7
        constraints.gridy++;
        constraints.gridx--;
        add(createLabel("Sanity", textFont), constraints);
        constraints.gridy++;
        sanityPanel = createValueLabel(textFont, Color.BLACK);
        add(sanityPanel, constraints);

        constraints.gridy++;
        add(bagButton(timeFont));


        // Middle Space filler
        constraints.gridy++;
        constraints.weighty = 1;
        add(new JLabel(""), constraints);

        // Last Row
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.weighty = 0;
        add(miniMap, constraints);

        // Initialize values
        updateStatusPanel();
    }

    private JButton bagButton(Font font){
        JButton button = new DefaultButton("Bag", font);
        button.setPreferredSize(new Dimension(100,50));
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                playerPresenter.getPlayerUseCase().bagTest();
            }
        });
        return button;
    }

    private JLabel createLabel(String attribute, Font font) {
        JLabel label = new JLabel(attribute);
        label.setFont(font);
        return label;
    }

    private JPanel createValueLabel(Font font, Color valueColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

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

    private JLabel createTimeLabel(Font timefont) {
        JLabel timeLabel = new JLabel();
        timeLabel.setFont(timefont);
        return timeLabel;
    }

    private JLabel createLocationLabel(Font locationFont) {
        JLabel locationLabel = new JLabel();
        locationLabel.setFont(locationFont);
        return locationLabel;
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
        updateDayPanel();

        updateValuePanel(healthPanel, playerPresenter.getHealth(), playerPresenter.getMaxHealth());
        updateValuePanel(weightPanel, playerPresenter.getWeight(), playerPresenter.getMaxWeight());
        updateValuePanel(hydrationPanel, playerPresenter.getHydration(), playerPresenter.getMaxHydration());
        updateValuePanel(hungerPanel, playerPresenter.getHunger(), playerPresenter.getMaxHunger());
        levelLabel.setText("" + playerPresenter.getLevel());
        updateValuePanel(expPanel, playerPresenter.getExperience(), playerPresenter.getMaxExperience());
        updateValuePanel(sanityPanel, playerPresenter.getSanity(), playerPresenter.getMaxSanity());
        miniMap.repaint();
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
}
