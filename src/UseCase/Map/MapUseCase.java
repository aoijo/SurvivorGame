package UseCase.Map;

import Entity.Map;
import Enums.MapTile.MapType;
import Enums.MapTile.TileType;
import UseCase.TileUseCase;

public class MapUseCase {
    private Map map;
    private TileUseCase tileUseCase;

    public MapUseCase() {
    }


    public Map newMap (int width, int height, MapType mapType, long seed, TileUseCase tileUseCase){
        this.tileUseCase = tileUseCase;
        this.map = new Map(mapType, width, height, seed);
        setMapType(mapType); // Set the map type and corresponding properties
        return map;
    }

    private void setMapType(MapType mapType) {
        switch (mapType) {
            case ISLAND:
                map.setBaseTile(TileType.PLAIN);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            case VOLCANO:
                map.setBaseTile(TileType.LAVA);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            case OCEAN:
                map.setBaseTile(TileType.OCEAN);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            case UNDERGROUND:
                map.setBaseTile(TileType.CAVE);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            case PENINSULA:
                map.setBaseTile(TileType.FOREST);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            case CONTINENT:
                map.setBaseTile(TileType.HILL);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            case EMPIRE:
                map.setBaseTile(TileType.RUIN);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            case CUSTOM:
                map.setBaseTile(TileType.PLAIN);
                map.setWidth(32);
                map.setHeight(32);
                initializeMap();
                new IslandStrategy().generateMap(map,tileUseCase);
                break;
            default:
                throw new IllegalArgumentException("Invalid map type");
        }
    }

    private void initializeMap() {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                map.setTile(x, y, tileUseCase.newTile(x,y,map.getBaseTile())); // Set each tile to the base tile type
            }
        }
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
