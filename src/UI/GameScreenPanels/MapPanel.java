package UI.GameScreenPanels;

import InterfaceAdapter.MapController;
import InterfaceAdapter.MapPresenter;
import UseCase.Map.MapUseCase;
import UseCase.TileData;
import Enums.MapType;
import Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MapPanel extends JPanel {
    private final MapPresenter mapPresenter;
    private final MapController mapController;
    private final Font tileFont;
    public int fontSize = 12;
    public int tileDimension = 40;
    public int tileNumber = 32;

    public MapPanel(int width, int height, MapType mapType, long seed, Player player) {
        this.mapPresenter = new MapPresenter();
        this.tileFont = new Font("Arial", Font.BOLD, fontSize);
        MapUseCase mapUseCase = new MapUseCase(width, height, mapType, seed, mapPresenter, player);
        mapUseCase.generateMap();
        this.mapController = new MapController(mapUseCase);
        setPreferredSize(new Dimension(tileNumber * tileDimension, tileNumber * tileDimension));

        // Set up key listener for player movement
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP -> mapController.movePlayer(0, -1);
                    case KeyEvent.VK_DOWN -> mapController.movePlayer(0, 1);
                    case KeyEvent.VK_LEFT -> mapController.movePlayer(-1, 0);
                    case KeyEvent.VK_RIGHT -> mapController.movePlayer(1, 0);
                }
                repaint();
            }
        });
    }

    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public void setTileFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setPlayerColor(Color playerColor) {
        mapPresenter.setPlayerColor(playerColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TileData[][] tiles = mapPresenter.getMapTiles();
        int tileSize = tileDimension;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int mapWidth = tiles.length * tileSize;
        int mapHeight = tiles[0].length * tileSize;
        int[] playerPosition = mapPresenter.getPlayerPosition();
        int offsetX = calculateOffset(panelWidth, mapWidth, playerPosition[0] * tileSize);
        int offsetY = calculateOffset(panelHeight, mapHeight, playerPosition[1] * tileSize);

        g.setFont(tileFont);
        FontMetrics fm = g.getFontMetrics();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                int drawX = offsetX + x * tileSize;
                int drawY = offsetY + y * tileSize;

                if (x == playerPosition[0] && y == playerPosition[1]) {
                    drawPlayerTile(g, drawX, drawY, tileSize, tiles[x][y].getTileColor());
                } else {
                    drawTile(g, drawX, drawY, tileSize, tiles[x][y]);
                }

                drawTileText(g, fm, drawX, drawY, tileSize, tiles[x][y].getShortName());
                g.setColor(Color.BLACK);
                g.drawRect(drawX, drawY, tileSize, tileSize);
            }
        }
    }

    private int calculateOffset(int panelSize, int mapSize, int playerPosition) {
        if (mapSize <= panelSize) {
            return (panelSize - mapSize) / 2;
        } else {
            int offset = Math.max(panelSize / 2 - playerPosition, panelSize - mapSize);
            return Math.min(offset, 0);
        }
    }

    private void drawPlayerTile(Graphics g, int drawX, int drawY, int tileSize, Color tileColor) {
        g.setColor(mapPresenter.getPlayerColor());
        g.fillRect(drawX, drawY, tileSize, tileSize);
        g.setColor(tileColor);
    }

    private void drawTile(Graphics g, int drawX, int drawY, int tileSize, TileData tile) {
        if (tile.isSpecial()) {
            g.setColor(tile.getTileColor());
            g.fillRect(drawX, drawY, tileSize, tileSize);
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(drawX, drawY, tileSize, tileSize);
            g.setColor(tile.getTileColor());
        }
    }

    private void drawTileText(Graphics g, FontMetrics fm, int drawX, int drawY, int tileSize, String text) {
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int textX = drawX + (tileSize - textWidth) / 2;
        int textY = drawY + (tileSize - textHeight) / 2 + fm.getAscent();
        g.drawString(text, textX, textY);
    }
}
