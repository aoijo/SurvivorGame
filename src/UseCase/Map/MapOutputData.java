package UseCase.Map;

import UseCase.TileData;

import java.awt.*;

public class MapOutputData {
    private final int width;
    private final int height;
    private final TileData[][] tiles;

    public MapOutputData(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new TileData[width][height];
    }

    public void setTileData(int x, int y, String shortName, Color color, boolean isSpecial) {
        this.tiles[x][y] = new TileData(shortName, color,isSpecial);
    }

    public TileData[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

