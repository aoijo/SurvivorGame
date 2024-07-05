package Entity;

import java.util.Random;

public class Map {
    private final int width;
    private final int height;
    private final MapType maptype;
    private final Tile[][] tiles;
    private final String mapName;
    private long seed;
    private TileType baseTile;
    private MapGenerationStrategy strategy;

    public Map(int width, int height, MapType maptype, long seed) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        this.seed = seed;
        this.maptype = maptype;
        this.mapName = maptype.toString().toLowerCase();
        setMapType(maptype);
        initializeMap();
    }

    private void initializeMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y, baseTile, null);
            }
        }
        generateMap();
    }

    private void setMapType(MapType maptype) {
        switch (maptype) {
            case ISLAND:
                this.baseTile = TileType.PLAIN;
                this.strategy = new CustomStrategy();
                break;
            case VOLCANO:
                this.baseTile = TileType.LAVA;
                this.strategy = new CustomStrategy();
                break;
            case OCEAN:
                this.baseTile = TileType.OCEAN;
                this.strategy = new CustomStrategy();
                break;
            case UNDERGROUND:
                this.baseTile = TileType.CAVE;
                this.strategy = new CustomStrategy();
                break;
            case PENINSULA:
                this.baseTile = TileType.FOREST;
                this.strategy = new CustomStrategy();
                break;
            case CONTINENT:
                this.baseTile = TileType.HILL;
                this.strategy = new CustomStrategy();
                break;
            case EMPIRE:
                this.baseTile = TileType.RUIN;
                this.strategy = new CustomStrategy();
                break;
            case CUSTOM:
                this.baseTile = TileType.PLAIN;
                this.strategy = new CustomStrategy();
                break;
            default:
                throw new IllegalArgumentException("Invalid map type");
        }
    }

    private void generateMap() {
        strategy.generateMap(this);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Tile coordinates out of bounds");
        }
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Tile coordinates out of bounds");
        }
        tiles[x][y] = tile;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getMapName() {
        return mapName;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public long getSeed() {
        return seed;
    }
}
