package InterfaceAdapter.MapAdapter;

import Enums.MapTile.TileType;
import InterfaceAdapter.UseCaseManager;
import UseCase.Map.MapUseCase;

import java.awt.Color;

public class MapPresenter{
    private MapUseCase mapUseCase;

    public MapPresenter(UseCaseManager useCaseManager) {
        this.mapUseCase = useCaseManager.getMapUseCase();
    }

    public MapUseCase getMapUseCase() {
        return mapUseCase;
    }

    public void setMapUseCase(MapUseCase mapUseCase) {
        this.mapUseCase = mapUseCase;
    }

    public TileType getTileType(int x, int y){
        return mapUseCase.getMap().getTileType(x,y);
    }
    public void setTileType(int x, int y, TileType tileType){
        mapUseCase.getMap().setTileType(x,y,tileType);
    }

    public Color getTileColor(int x, int y){
        return mapUseCase.getMap().getTile(x,y).getTileColor();
    }
    public void setTileColor(int x, int y, Color color){
        mapUseCase.getMap().getTile(x,y).setTileColor(color);
    }

    public String getTileName(int x, int y){
        return mapUseCase.getMap().getTile(x,y).getName();
    }


    public TileType[][] getAllTileTypes(){
        int width = mapUseCase.getMap().getWidth();
        int height = mapUseCase.getMap().getHeight();
        TileType[][] allTiles = new TileType[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                allTiles[x][y] = mapUseCase.getMap().getTile(x,y).getType();
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
                allColors[x][y] = mapUseCase.getMap().getTile(x,y).getTileColor();
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
                allShortName[x][y] = mapUseCase.getMap().getTile(x,y).getShortName();
            }
        }
        return allShortName;
    }
    public int[][] getAllTemplePosition(){
        return new int[][]{mapUseCase.getSpecialPosition(TileType.WATER_TEMPLE),
                mapUseCase.getSpecialPosition(TileType.FORGE_TEMPLE),
                mapUseCase.getSpecialPosition(TileType.HUNT_TEMPLE)};
    }

    public int getMapWidth(){
        return mapUseCase.getMap().getWidth();
    }
    public int getMapHeight(){
        return mapUseCase.getMap().getHeight();
    }
}
