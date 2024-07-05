package UseCase;

import Entity.Map;
import Entity.MapType;
import DataTransferObjects.MapDTO;
import DataTransferObjects.TileDTO;

public class MapUseCase {
    private final Map map;

    public MapUseCase(int width, int height, MapType mapType, String mapName, long seed) {
        this.map = new Map(width, height, mapType, seed);
    }

    public MapDTO getMap() {
        TileDTO[][] tiles = new TileDTO[map.getWidth()][map.getHeight()];
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                tiles[i][j] = new TileDTO(map.getTiles()[i][j].getShortName(), map.getTiles()[i][j].getTileColor());
            }
        }
        return new MapDTO(tiles, map.getWidth(), map.getHeight());
    }
}
