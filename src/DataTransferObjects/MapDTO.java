package DataTransferObjects;

public class MapDTO {
    private final TileDTO[][] tiles;
    private final int width;
    private final int height;

    public MapDTO(TileDTO[][] tiles, int width, int height) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
    }

    public TileDTO[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
