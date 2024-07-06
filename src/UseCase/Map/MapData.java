package UseCase.Map;

import UseCase.TileData;

public class MapData {
    private final TileData[][] tiles;
    private final int width;
    private final int height;

    public MapData(TileData[][] tiles, int width, int height) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
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
