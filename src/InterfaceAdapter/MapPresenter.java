package InterfaceAdapter;

import UseCase.Map.MapOutputBoundary;
import UseCase.Map.MapOutputData;
import UseCase.TileData;

import java.awt.*;

public class MapPresenter implements MapOutputBoundary {
    private MapOutputData mapData;
    private int[] playerPosition;
    private Color playerColor;

    public TileData[][] getMapTiles() {
        return mapData.getTiles();
    }

    public int getMapWidth() {
        return mapData.getWidth();
    }

    public int getMapHeight() {
        return mapData.getHeight();
    }

    @Override
    public void presentMap(MapOutputData outputData) {
        this.mapData = outputData;
    }

    @Override
    public void setPlayerPosition(int[] position) {
        this.playerPosition = position;
    }

    @Override
    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public int[] getPlayerPosition() {
        return playerPosition;
    }

    public Color getPlayerColor() {
        return playerColor;
    }
}
