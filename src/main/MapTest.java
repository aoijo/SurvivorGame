package main;

import UI.MapPanel;
import Enums.MapType;

import javax.swing.*;

public class MapTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            long seed = 12345L;
            MapPanel mapPanel = new MapPanel(32, 32, MapType.ISLAND, seed);

            JFrame frame = new JFrame("Map Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(mapPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
