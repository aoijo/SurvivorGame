package InterfaceAdapter;

import UseCase.MapUseCase;
import DataTransferObjects.MapDTO;
import DataTransferObjects.TileDTO;


public class MapPresenter {
    private final MapUseCase mapUseCase;

    public MapPresenter(MapUseCase mapUseCase) {
        this.mapUseCase = mapUseCase;
    }

    public TileDTO[][] getMapTiles() {
        return mapUseCase.getMap().getTiles();
    }

    public int getMapWidth() {
        return mapUseCase.getMap().getWidth();
    }

    public int getMapHeight() {
        return mapUseCase.getMap().getHeight();
    }
}
