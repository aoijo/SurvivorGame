package UseCase.Map;

import Entity.Map;
import Enums.MapTile.MapType;
import Enums.MapTile.TileType;
import UseCase.TileUseCase;

public class MapUseCase {
    private Map map;
    private MapGenerationStrategy strategy;

    public MapUseCase(Map map) {
        this.map = map;
    }

    public MapUseCase(int width, int height, MapType mapType, long seed) {
        this.map = new Map(mapType, width, height, seed);
        setMapType(mapType); // Set the map type and corresponding properties
        initializeMap(); // Initialize the map with default tiles
    }

    private void setMapType(MapType mapType) {
        switch (mapType) {
            case ISLAND:
                map.setBaseTile(TileType.PLAIN);
                map.setWidth(32);
                map.setHeight(32);
                this.strategy = new IslandStrategy();
                break;
            case VOLCANO:
                map.setBaseTile(TileType.LAVA);
                map.setWidth(32);                map.setHeight(32);
                this.strategy = new VolcanoStrategy();
                break;
            case OCEAN:
                map.setBaseTile(TileType.OCEAN);
                map.setWidth(32);
                map.setHeight(32);
                this.strategy = new OceanStrategy();
                break;
            case UNDERGROUND:
                map.setBaseTile(TileType.CAVE);
                map.setWidth(32);
                map.setHeight(32);
                this.strategy = new UnderGroundStrategy();
                break;
            case PENINSULA:
                map.setBaseTile(TileType.FOREST);
                map.setWidth(32);
                map.setHeight(32);
                this.strategy = new PeninsulaStrategy();
                break;
            case CONTINENT:
                map.setBaseTile(TileType.HILL);
                map.setWidth(32);
                map.setHeight(32);
                this.strategy = new ContinentStrategy();
                break;
            case EMPIRE:
                map.setBaseTile(TileType.RUIN);
                map.setWidth(32);
                map.setHeight(32);
                this.strategy = new EmpireStrategy();
                break;
            case CUSTOM:
                map.setBaseTile(TileType.PLAIN);
                map.setWidth(32);
                map.setHeight(32);
                this.strategy = new CustomStrategy();
                break;
            default:
                throw new IllegalArgumentException("Invalid map type");
        }
    }

    private void initializeMap() {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                map.setTile(x, y, new TileUseCase(x, y, map.getBaseTile()).getTile()); // Set each tile to the base tile type
            }
        }
        generateMap(); // Generate the map using the strategy
    }

    private void generateMap() {
        strategy.generateMap(map);
    }

    public Map getMap(){
        return map;
    }

    public void setMap(Map map){
        this.map = map;
    }

    public void setMapSeed(long seed) {
        this.map.setSeed(seed);
        initializeMap(); // Initialize the map with default tiles
    }

    public int[] getSpecialPosition(TileType tileType){
        switch (tileType) {
            case WATER_TEMPLE -> {return this.map.findTileLocation(TileType.WATER_TEMPLE);}
            case FORGE_TEMPLE -> {return this.map.findTileLocation(TileType.FORGE_TEMPLE);}
            case HUNT_TEMPLE -> {return this.map.findTileLocation(TileType.HUNT_TEMPLE);}
            default -> {throw new IllegalArgumentException("Invalid tile type: " + tileType);}
        }

    }
}
