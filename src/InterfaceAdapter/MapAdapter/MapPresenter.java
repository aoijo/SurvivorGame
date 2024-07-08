package InterfaceAdapter.MapAdapter;

import Enums.TileType;
import UseCase.Map.MapUseCase;

import java.awt.Color;

public class MapPresenter{
    private MapUseCase mapUseCase;
    private int[] playerPosition;
    private Color playerColor;

    public MapPresenter(MapUseCase mapUseCase) {
        this.mapUseCase = mapUseCase;
    }

    public MapUseCase getMapUseCase() {
        return mapUseCase;
    }

    public void setMapUseCase(MapUseCase mapUseCase) {
        this.mapUseCase = mapUseCase;
    }

    public TileType[][] getAllTiles(){
        int width = mapUseCase.getMap().getWidth();
        int height = mapUseCase.getMap().getHeight();
        TileType[][] allTiles = new TileType[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                allTiles[x][y] = mapUseCase.getMap().getAllTiles()[x][y].getType();
            }
        }
        return allTiles;
    }

    public Color[][] getAllColors(){
        int width = mapUseCase.getMap().getWidth();
        int height = mapUseCase.getMap().getHeight();
        Color[][] allColors = new Color[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                allColors[x][y] = mapUseCase.getMap().getAllTiles()[x][y].getTileColor();
            }
        }
        return allColors;
    }

    public String[][] getAllShortName(){
        int width = mapUseCase.getMap().getWidth();
        int height = mapUseCase.getMap().getHeight();
        String[][] allShortName = new String[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                allShortName[x][y] = mapUseCase.getMap().getAllTiles()[x][y].getShortName();
            }
        }
        return allShortName;
    }
    public int[][] getAllTemplePosition(){
        return new int[][]{mapUseCase.getSpecialPosition(TileType.WATER_TEMPLE),
                mapUseCase.getSpecialPosition(TileType.FORGE_TEMPLE),
                mapUseCase.getSpecialPosition(TileType.HUNT_TEMPLE)};
    }
}
