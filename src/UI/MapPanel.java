package UI;

import DataTransferObjects.TileDTO;
import InterfaceAdapter.MapPresenter;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private final MapPresenter mapPresenter;
    private final Font tileFont;
    private int tileDimension = 70;
    public int[] playerPosition = new int[]{5, 0};

    public MapPanel(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;
        this.tileFont = new Font("Arial", Font.BOLD, 12);
        setPreferredSize(new Dimension(
                10 * tileDimension,
                10 * tileDimension
        ));
    }

    public void setPlayerPosition(int[] playerPosition) {
        this.playerPosition = playerPosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TileDTO[][] tiles = mapPresenter.getMapTiles();
        int tileSize = tileDimension;

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int mapWidth = tiles.length * tileSize;
        int mapHeight = tiles[0].length * tileSize;

        int offsetX;
        int offsetY;

        if (mapWidth <= panelWidth) {
            offsetX = (panelWidth - mapWidth) / 2;
        } else {
            offsetX = Math.max(panelWidth / 2 - playerPosition[0] * tileSize, panelWidth - mapWidth);
            offsetX = Math.min(offsetX, 0);
        }

        if (mapHeight <= panelHeight) {
            offsetY = (panelHeight - mapHeight) / 2;
        } else {
            offsetY = Math.max(panelHeight / 2 - playerPosition[1] * tileSize, panelHeight - mapHeight);
            offsetY = Math.min(offsetY, 0);
        }

        g.setFont(tileFont);
        FontMetrics fm = g.getFontMetrics();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {

                int drawX = offsetX + x * tileSize;
                int drawY = offsetY + y * tileSize;

                if (x == playerPosition[0] && y == playerPosition[1]) {
                    g.setColor(tiles[x][y].getTileColor());
                    g.fillRect(drawX, drawY, tileSize, tileSize);
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(drawX, drawY, tileSize, tileSize);
                    g.setColor(tiles[x][y].getTileColor());
                }

                String shortName = tiles[x][y].getShortName();
                int textWidth = fm.stringWidth(shortName);
                int textHeight = fm.getHeight();
                int textX = drawX + (tileSize - textWidth) / 2;
                int textY = drawY + (tileSize - textHeight) / 2 + fm.getAscent();
                g.drawString(shortName, textX, textY);
                g.setColor(Color.BLACK);
                g.drawRect(drawX, drawY, tileSize, tileSize);
            }
        }
    }
}
