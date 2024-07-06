package InterfaceAdapter;

import UseCase.Map.MapOutputBoundary;
import UseCase.Map.MapOutputData;
import UseCase.TileData;

/**
 * MapPresenter is responsible for presenting the map data.
 * It implements the MapOutputBoundary interface to receive and store the map data.
 */
public class MapPresenter implements MapOutputBoundary {
    private MapOutputData mapData; // Stores the map data to be presented

    /**
     * Returns the 2D array of tiles representing the map.
     * @return The 2D array of tiles
     */
    public TileData[][] getMapTiles() {
        return mapData.getTiles();
    }

    /**
     * Returns the width of the map.
     * @return The width of the map
     */
    public int getMapWidth() {
        return mapData.getWidth();
    }

    /**
     * Returns the height of the map.
     * @return The height of the map
     */
    public int getMapHeight() {
        return mapData.getHeight();
    }

    /**
     * Receives the map data and stores it for presentation.
     * @param outputData The map data to be presented
     */
    @Override
    public void presentMap(MapOutputData outputData) {
        this.mapData = outputData; // Store the received map data
    }
}
