package main;

import UI.GameScreenPanels.MapPanel;
import Enums.MapType;
import UseCase.CreateNewPlayer;
import Entity.Player;
import Enums.Race;

import javax.swing.*;
import java.awt.*;

public class MapTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            long seed = 12345L;
            Player player = CreateNewPlayer.createPlayer("Player1",new int[]{0, 0}, Color.GREEN, Race.HUMAN);
            MapPanel mapPanel = new MapPanel(32, 32, MapType.ISLAND, seed, player);
            mapPanel.setPreferredSize(new Dimension(600, 600));

            JFrame frame = new JFrame("Map Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(mapPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
