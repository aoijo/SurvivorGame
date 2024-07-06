package UseCase.Map;

import Entity.Map;
import Enums.MapType;

/**
 * MapUseCase is responsible for generating the map based on provided parameters.
 * It interacts with the Map entity and presents the generated map through the presenter.
 */
public class MapUseCase {
    private final Map map; // The Map entity that holds the map data
    private final MapOutputBoundary presenter; // The presenter for outputting the map data

    /**
     * Constructor initializes the MapUseCase with map parameters and a presenter.
     * @param width The width of the map
     * @param height The height of the map
     * @param mapType The type of the map
     * @param seed The seed for generating the map
     * @param presenter The presenter for outputting the map data
     */
    public MapUseCase(int width, int height, MapType mapType, long seed, MapOutputBoundary presenter) {
        this.map = new Map(mapType, seed); // Initialize the Map entity
        this.presenter = presenter;
    }

    /**
     * Generates the map and passes the data to the presenter.
     */
    public void generateMap() {
        MapOutputData outputData = new MapOutputData(map.getWidth(), map.getHeight());
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                outputData.setTileData(i, j, map.getTiles()[i][j].getShortName(), map.getTiles()[i][j].getTileColor(),map.getTiles()[i][j].isSpecial());
            }
        }
        presenter.presentMap(outputData); // Pass the generated map data to the presenter
    }
}