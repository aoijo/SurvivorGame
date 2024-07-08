package UseCase;

import Entity.Tile;
import Enums.TileType;

import java.awt.*;

public class TileUseCase {
    private Tile tile;

    public TileUseCase(Tile tile) {
        this.tile = tile;
    }
    public TileUseCase(int x, int y,TileType tileType){
        this.tile = new Tile(x,y,tileType);
        tile.setTileColor(InitializeTileColor());
        tile.setShortName(InitializeShortName(4));
        checkSpecial(tileType);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }


    public Color InitializeTileColor() {
        return switch (tile.getType()) {
            case VILLAGE -> new Color(190, 90, 0);
            case CITY, RUIN -> new Color(0, 120, 110);
            case ASH_PIT -> new Color(50, 50, 50);
            case FORGE_TEMPLE -> new Color(150, 0, 0);
            case HUNT_TEMPLE -> new Color(0, 153, 0);
            case WATER_TEMPLE -> new Color(0, 0, 150);
            case JELLY, SWAMP -> new Color(102, 0, 204);
            case BACKYARD, PLAIN -> new Color(0, 186, 0);
            case ABYSS -> new Color(0, 0, 0);
            case FOREST, OASIS -> new Color(0, 102, 0);
            case HILL, BEACH -> new Color(255, 153, 51);
            case CAVE, DESERT -> new Color(153, 76, 0);
            case MINE -> new Color(99, 99, 0);
            case LAKE -> new Color(0, 204, 204);
            case OCEAN -> new Color(0, 0, 255);
            case DEEP_SEA -> new Color(0, 0, 102);
            case LAVA, HEAT_VENT -> new Color(255, 0, 0);
            case ISLAND -> new Color(255, 128, 0);
            case VORTEX -> new Color(0, 153, 204);
            case CORPSE -> new Color(102, 0, 51);
            case TEMPLE -> new Color(60, 60, 60);
            case MOUNTAIN -> new Color(74, 40, 0);
            default -> throw new IllegalArgumentException("Invalid tile type");
        };
    }

    public String InitializeShortName(int maxLength) {
        return generateShortName(tile.getType().toString(), maxLength);
    }

    public String generateShortName(String typeName, int length) {
        if (typeName.length() > length + 1) {
            return typeName.substring(0, length) + ".";
        } else {
            return typeName;
        }
    }

    public void checkSpecial(TileType type) {
        if (type == TileType.WATER_TEMPLE || type == TileType.FORGE_TEMPLE || type == TileType.HUNT_TEMPLE) {
            tile.setTemple(true);
        } else if (type == TileType.VILLAGE || type == TileType.CITY) {
            tile.setSettlement(true);
        }
        if (tile.isBoss() || tile.isTemple() || tile.isSettlement()) {
            tile.setUnique(true);
        }
    }
}
