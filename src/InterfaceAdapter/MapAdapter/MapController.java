package InterfaceAdapter.MapAdapter;

import Enums.MapType;
import UseCase.Map.MapUseCase;
import UseCase.Players.PlayerUseCase;

public class MapController {
    private MapUseCase mapUseCase;
    private PlayerUseCase playerUseCase;

    public MapController(MapUseCase mapUseCase, PlayerUseCase playerUseCase) {
        this.mapUseCase = mapUseCase;
        this.playerUseCase = playerUseCase;
    }

    public MapController(int width, int height, MapType mapType, long seed, PlayerUseCase playerUseCase){
        this.mapUseCase = new MapUseCase(width, height, mapType, seed);
        this.playerUseCase = playerUseCase;
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
