package UseCase.Map;

import Entity.Map;
import Entity.Player;
import Enums.MapType;

public class MapUseCase {
    private final Map map;
    private final MapOutputBoundary presenter;
    private final Player player;

    public MapUseCase(int width, int height, MapType mapType, long seed, MapOutputBoundary presenter, Player player) {
        this.map = new Map(mapType, seed);
        this.presenter = presenter;
        this.player = player;
    }

    public void generateMap() {
        MapOutputData outputData = new MapOutputData(map.getWidth(), map.getHeight());
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                outputData.setTileData(i, j, map.getTiles()[i][j].getShortName(), map.getTiles()[i][j].getTileColor(), map.getTiles()[i][j].isSpecial());
            }
        }
        presenter.presentMap(outputData);
        presenter.setPlayerPosition(player.getPosition());
        presenter.setPlayerColor(player.getColor());
    }

    public void movePlayer(int dx, int dy) {
        player.move(dx, dy);
        int[] newPosition = player.getPosition();
        if (newPosition[0] >= 0 && newPosition[1] >= 0 && newPosition[0] <= map.getWidth() && newPosition[1] <= map.getHeight()){
            presenter.setPlayerPosition(player.getPosition());
        } else {
            player.move(-1*dx, -1*dy);
        }
    }
}
