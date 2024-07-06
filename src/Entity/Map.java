package Entity;

import Enums.MapType;
import Enums.TileType;

/**
 * Map represents a grid of tiles that forms the basis of the map.
 * It includes functionality to initialize and generate the map based on its type and other parameters.
 */
public class Map {
    private final MapType maptype; // Type of the map
    private final Tile[][] tiles; // 2D array of tiles representing the map
    private final String mapName; // Name of the map
    private int width; // Width of the map
    private int height; // Height of the map
    private long seed; // Seed for generating the map
    private TileType baseTile; // Base tile type for the map
    private MapGenerationStrategy strategy; // Strategy for generating the map

    /**
     * Constructor initializes the map with the specified parameters.
     * @param maptype The type of the map
     * @param seed The seed for generating the map
     */
    public Map(MapType maptype, long seed) {
        setMapType(maptype); // Set the map type and corresponding properties
        this.tiles = new Tile[width][height]; // Initialize the tile array
        this.seed = seed;
        this.maptype = maptype;
        this.mapName = maptype.toString().toLowerCase(); // Set the map name based on its type
        initializeMap(); // Initialize the map with default tiles
    }

    /**
     * Initializes the map by filling it with default tiles.
     */
    private void initializeMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y, baseTile); // Set each tile to the base tile type
            }
        }
        generateMap(); // Generate the map using the strategy
    }

    /**
     * Sets the map type and corresponding base tile and generation strategy.
     * @param mapType The type of the map
     */
    private void setMapType(MapType mapType) {
        switch (mapType) {
            case ISLAND:
                this.baseTile = TileType.PLAIN;
                this.width = 32;
                this.height = 32;
                this.strategy = new IslandStrategy();
                break;
            case VOLCANO:
                this.baseTile = TileType.LAVA;
                this.width = 32;
                this.height = 32;
                this.strategy = new VolcanoStrategy();
                break;
            case OCEAN:
                this.baseTile = TileType.OCEAN;
                this.width = 32;
                this.height = 32;
                this.strategy = new OceanStrategy();
                break;
            case UNDERGROUND:
                this.baseTile = TileType.CAVE;
                this.width = 32;
                this.height = 32;
                this.strategy = new UnderGroundStrategy();
                break;
            case PENINSULA:
                this.baseTile = TileType.FOREST;
                this.width = 32;
                this.height = 32;
                this.strategy = new PeninsulaStrategy();
                break;
            case CONTINENT:
                this.baseTile = TileType.HILL;
                this.width = 32;
                this.height = 32;
                this.strategy = new ContinentStrategy();
                break;
            case EMPIRE:
                this.baseTile = TileType.RUIN;
                this.width = 32;
                this.height = 32;
                this.strategy = new EmpireStrategy();
                break;
            case CUSTOM:
                this.baseTile = TileType.PLAIN;
                this.width = 32;
                this.height = 32;
                this.strategy = new CustomStrategy();
                break;
            default:
                throw new IllegalArgumentException("Invalid map type");
        }
    }

    /**
     * Generates the map using the set strategy.
     */
    private void generateMap() {
        strategy.generateMap(this);
    }

    /**
     * Returns the tile at the specified coordinates.
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     * @return The tile at the specified coordinates
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Tile coordinates out of bounds");
        }
        return tiles[x][y];
    }

    /**
     * Sets the tile at the specified coordinates.
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     * @param tile The tile to set
     */
    public void setTile(int x, int y, Tile tile) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Tile coordinates out of bounds");
        }
        tiles[x][y] = tile;
    }

    /**
     * Sets the seed for generating the map.
     * @param seed The seed to set
     */
    public void setSeed(long seed) {
        this.seed = seed;
    }

    /**
     * Returns the width of the map.
     * @return The width of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the map.
     * @return The height of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the name of the map.
     * @return The name of the map
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Returns the 2D array of tiles representing the map.
     * @return The 2D array of tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Returns the seed used for generating the map.
     * @return The seed used for generating the map
     */
    public long getSeed() {
        return seed;
    }
}