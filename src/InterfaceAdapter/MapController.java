package InterfaceAdapter;

import UseCase.Map.MapUseCase;

public class MapController {
    private final MapUseCase mapUseCase;

    public MapController(MapUseCase mapUseCase) {
        this.mapUseCase = mapUseCase;
    }

    public void movePlayer(int dx, int dy) {
        mapUseCase.movePlayer(dx, dy);
    }
}
