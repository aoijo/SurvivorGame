package InterfaceAdapter.MapAdapter;

import Enums.MapTile.MapType;
import InterfaceAdapter.UseCaseManager;
import UseCase.Map.MapUseCase;
import UseCase.PlayerUseCase;
import UseCase.TileUseCase;

public class MapController {
    private MapUseCase mapUseCase;
    private PlayerUseCase playerUseCase;
    private TileUseCase tileUseCase;

    public MapController(int width, int height, MapType mapType, long seed, UseCaseManager useCaseManager){
        this.tileUseCase = useCaseManager.getTileUseCase();
        this.mapUseCase = useCaseManager.getMapUseCase();
        this.playerUseCase = useCaseManager.getPlayerUseCase();
        mapUseCase.setMap(mapUseCase.newMap(width,height,mapType,seed, tileUseCase));
    }

    public MapUseCase getMapUseCase() {
        return mapUseCase;
    }

    public void setMapUseCase(MapUseCase mapUseCase) {
        this.mapUseCase = mapUseCase;
    }

    public PlayerUseCase getPlayerUseCase() {
        return playerUseCase;
    }

    public void setPlayerUseCase(PlayerUseCase playerUseCase) {
        this.playerUseCase = playerUseCase;
    }

    public void move(int dx, int dy) {
        int[] position = playerUseCase.getPlayer().getPosition();
        position[0] += dx;
        position[1] += dy;
        playerUseCase.getPlayer().setPosition(position);
    }
}
