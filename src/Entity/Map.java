package Entity;

import Enums.MapTile.MapType;
import Enums.MapTile.TileType;
import java.util.HashMap;

/**
 * Map represents a grid of tiles that forms the basis of the map.
 */
public class Map {
    private final MapType mapType; // Type of the map
    private final Tile[][] tiles; // 2D array of tiles representing the map
    private String mapName; // Name of the map
    private int width; // Width of the map
    private int height; // Height of the map
    private long seed; // Seed for generating the map
    private TileType baseTile; // Base tile type for the map
    private final HashMap<TileType, int[]> tileLocations; // HashMap to store tile locations


    /**
     * Constructor initializes the map with the specified parameters.
     * @param mapType The type of the map
     * @param width The width of the map
     * @param height The height of the map
     * @param seed The seed for generating the map
     */
    public Map(MapType mapType, int width, int height, long seed) {
        this.mapType = mapType;
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height]; // Initialize the tile array
        this.seed = seed;
        this.mapName = mapType.toString().toLowerCase();
        this.tileLocations = new HashMap<>(); // Initialize the HashMap
    }

    // Getters and setters
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public Tile[][] getAllTiles() {
        return tiles;
    }

    public MapType getMapType() {
        return mapType;
    }

    public TileType getBaseTile() {
        return baseTile;
    }

    public void setBaseTile(TileType baseTile) {
        this.baseTile = baseTile;
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
        tileLocations.put(tile.getType(), new int[]{x, y}); // Update the HashMap
    }

    public TileType getTileType(int x, int y){
        return getTile(x,y).getType();
    }

    public void setTileType(int x, int y, TileType tileType){
        getTile(x,y).setType(tileType);
    }

    public int[] findTileLocation(TileType targetTileType) {
        return tileLocations.get(targetTileType);
    }
}
