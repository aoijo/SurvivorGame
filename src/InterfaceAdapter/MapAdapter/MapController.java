package InterfaceAdapter.MapAdapter;

import Enums.MapTile.MapType;
import InterfaceAdapter.UseCaseManager;
import UseCase.Map.MapUseCase;
import UseCase.Players.PlayerUseCase;
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
        int width = mapUseCase.getMap().getWidth() - 1;
        int height = mapUseCase.getMap().getHeight() - 1;
        int[] position = playerUseCase.getPlayer().getPosition();
        int[] originalPosition = position.clone();
        position[0] += dx;
        position[1] += dy;
        if (position[0] >= 0 && position[0] <= width && position[1] >= 0 && position[1] <= height) {
            playerUseCase.getPlayer().setPosition(position);
        } else {
            playerUseCase.getPlayer().setPosition(originalPosition);
        }
    }
}
