package UI.GameScreenPanels;

import InterfaceAdapter.MapController;
import InterfaceAdapter.MapPresenter;
import InterfaceAdapter.PlayerController;
import InterfaceAdapter.PlayerPresenter;
import Enums.MapType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class MapPanel extends JPanel {
    private MapPresenter mapPresenter;
    private MapController mapController;
    private PlayerPresenter playerPresenter;
    private PlayerController playerController;
    private Font tileFont;
    private int fontSize = 12;
    private int tileDimension = 40;
    private int tileNumber = 10;

    public MapPanel(MapPresenter mapPresenter, MapController mapController, PlayerPresenter playerPresenter, PlayerController playerController) {
        this.mapPresenter = mapPresenter;
        this.mapController = mapController;
        this.playerPresenter = playerPresenter;
        this.playerController = playerController;
        this.tileFont = new Font("Arial", Font.BOLD, fontSize);
        setPreferredSize(new Dimension(tileNumber * tileDimension, tileNumber * tileDimension));

        // Set up key listener for player movement
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP -> mapController.move(0, -1);
                    case KeyEvent.VK_DOWN -> mapController.move(0, 1);
                    case KeyEvent.VK_LEFT -> mapController.move(-1, 0);
                    case KeyEvent.VK_RIGHT -> mapController.move(1, 0);
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
        playerPresenter.setPlayerColor(playerColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color[][] tileColors = mapPresenter.getAllColors();
        String[][] tileShortNames = mapPresenter.getAllShortName();
        int[][] allTempleTiles = mapPresenter.getAllTemplePosition();
        int tileSize = tileDimension;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int mapWidth = tileColors.length * tileSize;
        int mapHeight = tileColors[0].length * tileSize;
        int[] playerPosition = playerPresenter.getPlayerPosition();
        int offsetX = calculateOffset(panelWidth, mapWidth, playerPosition[0] * tileSize);
        int offsetY = calculateOffset(panelHeight, mapHeight, playerPosition[1] * tileSize);

        g.setFont(tileFont);
        FontMetrics fm = g.getFontMetrics();

        for (int x = 0; x < tileColors.length; x++) {
            for (int y = 0; y < tileColors[x].length; y++) {
                int[] tilePosition = new int[]{x,y};
                int drawX = offsetX + x * tileSize;
                int drawY = offsetY + y * tileSize;

                if (Arrays.equals(tilePosition, playerPosition)) {
                    drawPlayerTile(g, drawX, drawY, tileSize, tileColors[x][y]);
                }else if(contains(allTempleTiles, tilePosition)){
                    drawSpecialTile(g, drawX, drawY, tileSize, tileColors[x][y]);
                } else {
                    drawTile(g, drawX, drawY, tileSize, tileColors[x][y]);
                }

                drawTileText(g, fm, drawX, drawY, tileSize, tileShortNames[x][y]);
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

    public static boolean contains(int[][] array2D, int[] target) {
        if (array2D == null || target == null) {
            return false;
        }

        for (int[] array : array2D) {
            if (Arrays.equals(array, target)) {
                return true;
            }
        }
        return false;
    }

    private void drawPlayerTile(Graphics g, int drawX, int drawY, int tileSize, Color tileColor) {
        g.setColor(playerPresenter.getPlayerColor());
        g.fillRect(drawX, drawY, tileSize, tileSize);
        g.setColor(tileColor);
    }

    private void drawTile(Graphics g, int drawX, int drawY, int tileSize,  Color tileColor) {
        g.setColor(Color.WHITE);
        g.fillRect(drawX, drawY, tileSize, tileSize);
        g.setColor(tileColor);
    }
    private void drawSpecialTile(Graphics g, int drawX, int drawY, int tileSize,  Color tileColor) {
        g.setColor(tileColor);
        g.fillRect(drawX, drawY, tileSize, tileSize);
        g.setColor(Color.WHITE);
    }

    private void drawTileText(Graphics g, FontMetrics fm, int drawX, int drawY, int tileSize, String text) {
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int textX = drawX + (tileSize - textWidth) / 2;
        int textY = drawY + (tileSize - textHeight) / 2 + fm.getAscent();
        g.drawString(text, textX, textY);
    }
}
