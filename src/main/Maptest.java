package main;

import InterfaceAdapter.MapPresenter;
import UI.MapPanel;
import UseCase.MapUseCase;
import Entity.MapType;

import javax.swing.*;

public class Maptest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapUseCase mapUseCase = new MapUseCase(50, 50, MapType.ISLAND, "MyMap", System.currentTimeMillis());
            MapPresenter mapPresenter = new MapPresenter(mapUseCase);
            MapPanel mapPanel = new MapPanel(mapPresenter);

            JFrame frame = new JFrame("Map Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(mapPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}