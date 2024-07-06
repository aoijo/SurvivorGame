package UI.GameScreenPanels;

import InterfaceAdapter.MapPresenter;
import UseCase.Map.MapUseCase;
import UseCase.TileData;
import Enums.MapType;

import javax.swing.*;
import java.awt.*;

/**
 * MapPanel is a custom JPanel that displays a map grid.
 * It uses MapPresenter to fetch the map tiles and render them with appropriate colors and text.
 */
public class MapPanel extends JPanel {
    private final MapPresenter mapPresenter; // Responsible for presenting the map data
    private final Font tileFont; // Font used for rendering text on the tiles
    public int fontSize = 12;
    public int tileDimension = 40; // Dimension of each tile
    public int tileNumber = 32;
    public int[] playerPosition = new int[]{0, 0}; // Player's position on the map grid
    public Color playerColor = Color.BLACK;

    /**
     * Constructor initializes the MapPanel with map parameters and sets the preferred size.
     * @param width The width of the map
     * @param height The height of the map
     * @param mapType The type of the map
     * @param seed The seed for generating the map
     */
    public MapPanel(int width, int height, MapType mapType, long seed) {
        this.mapPresenter = new MapPresenter(); // Initialize the MapPresenter
        this.tileFont = new Font("Arial", Font.BOLD, fontSize); // Initialize the font for tile text
        MapUseCase mapUseCase = new MapUseCase(width, height, mapType, seed, mapPresenter); // Initialize the MapUseCase
        mapUseCase.generateMap(); // Generate the map
        setPreferredSize(new Dimension(
                tileNumber * tileDimension, // Width of the panel based on tile dimensions
                tileNumber * tileDimension  // Height of the panel based on tile dimensions
        ));
    }

    /**
     * Sets the player's position on the map.
     * @param playerPosition An array representing the player's position [x, y]
     */
    public void setPlayerPosition(int[] playerPosition) {
        this.playerPosition = playerPosition;
    }

    /**
     * Sets the number of tiles to display.
     * @param tileNumber The number of tiles
     */
    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    /**
     * Sets the font size for the tile text.
     * @param fontSize The font size
     */
    public void setTileFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Sets the color of player's tile for the tile text.
     * @param playerColor The player's color
     */
    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Overridden paintComponent method to render the map tiles and the player.
     * @param g The Graphics object for rendering
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call to the superclass method to ensure proper painting behavior
        TileData[][] tiles = mapPresenter.getMapTiles(); // Fetch the map tiles from the presenter
        int tileSize = tileDimension;

        // Calculate panel dimensions and map dimensions in pixels
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int mapWidth = tiles.length * tileSize;
        int mapHeight = tiles[0].length * tileSize;

        int offsetX;
        int offsetY;

        // Calculate the offset for centering the map or following the player position
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

        g.setFont(tileFont); // Set the font for rendering text
        FontMetrics fm = g.getFontMetrics(); // Get font metrics for text alignment

        // Loop through each tile and render it
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                int drawX = offsetX + x * tileSize;
                int drawY = offsetY + y * tileSize;

                // If the tile is the player's position, render it differently
                if (x == playerPosition[0] && y == playerPosition[1]) {
                    g.setColor(playerColor);
                    g.fillRect(drawX, drawY, tileSize, tileSize);
                    g.setColor(tiles[x][y].getTileColor());
                }else if (tiles[x][y].isSpecial()){
                    g.setColor(tiles[x][y].getTileColor());
                    g.fillRect(drawX, drawY, tileSize, tileSize);
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(drawX, drawY, tileSize, tileSize);
                    g.setColor(tiles[x][y].getTileColor());
                }

                // Render the text (short name) on the tile
                String shortName = tiles[x][y].getShortName();
                int textWidth = fm.stringWidth(shortName);
                int textHeight = fm.getHeight();
                int textX = drawX + (tileSize - textWidth) / 2;
                int textY = drawY + (tileSize - textHeight) / 2 + fm.getAscent();
                g.drawString(shortName, textX, textY);
                g.setColor(Color.BLACK);
                g.drawRect(drawX, drawY, tileSize, tileSize); // Draw tile border
            }
        }
    }
}
