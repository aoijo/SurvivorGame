package UI.GameScreenPanels.World;

import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import UI.AdapterManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

public class MapPanel extends JPanel {
    private MapPresenter mapPresenter;
    private MapController mapController;
    private PlayerPresenter playerPresenter;
    private PlayerController playerController;
    private Font tileFont;
    private int fontSize;
    private int tileDimension;
    private int tileNumber;

    public MapPanel(AdapterManager adapterManager, int tileNumber, int tileDimension, int fontSize) {
        this.mapPresenter = adapterManager.getMapPresenter();
        this.mapController = adapterManager.getMapController();
        this.playerPresenter = adapterManager.getPlayerPresenter();
        this.playerController = adapterManager.getPlayerController();
        this.tileNumber = tileNumber;
        this.tileDimension = tileDimension;
        this.fontSize = fontSize;
        this.tileFont = new Font("Arial", Font.BOLD, fontSize);
        setPreferredSize(new Dimension((int)tileNumber * tileDimension, (int)tileNumber * tileDimension));
    }

    public int getTileNumber() {
        return tileNumber;
    }
    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public int getTileDimension() {
        return tileDimension;
    }
    public void setTileDimension(int tileDimension) {
        this.tileDimension = tileDimension;
    }

    public int getFontSize() {
        return fontSize;
    }
    public void setTileFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getPlayerColor() {
        return playerPresenter.getPlayerColor();
    }
    public void setPlayerColor(Color playerColor) {
        playerPresenter.setPlayerColor(playerColor);
    }

    public MapPresenter getMapPresenter() {
        return mapPresenter;
    }
    public void setMapPresenter(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;
    }

    public MapController getMapController() {
        return mapController;
    }
    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public PlayerPresenter getPlayerPresenter() {
        return playerPresenter;
    }
    public void setPlayerPresenter(PlayerPresenter playerPresenter) {
        this.playerPresenter = playerPresenter;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
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
                int[] tilePosition = new int[]{x, y};
                int drawX = offsetX + x * tileSize;
                int drawY = offsetY + y * tileSize;

                if (Arrays.equals(tilePosition, playerPosition)) {
                    drawPlayerTile(g, drawX, drawY, tileSize, tileColors[x][y]);
                } else if (contains(allTempleTiles, tilePosition)) {
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
