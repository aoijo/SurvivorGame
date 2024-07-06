package Entity;

import Enums.MapType;
import Enums.TileType;

import java.awt.*;

/**
 * Represents a tile on a map, with properties such as position, type, and a short name.
 */
public class Tile {
    private final int[] position;
    private final TileType type;
    private final String shortName;
    private boolean isSpecial = false;
    private boolean isBoss = false;
    private boolean isTemple = false;
    private boolean isSettlement = false;

    // Add more properties like terrain type, obstacles, etc.

    /**
     * Constructs a Tile object with specified position, type, and map type.
     * Generates a short name for the tile based on its type.
     *
     * @param x       the x-coordinate of the tile
     * @param y       the y-coordinate of the tile
     * @param type    the type of the tile
     */
    public Tile(int x, int y, TileType type) {
        this.position = new int[]{x, y};
        this.type = type;
        this.shortName = generateShortName(type.toString(), 4);
        checkSpecial(type);
    }

    /**
     * Gets the position of the tile.
     *
     * @return an array containing the x and y coordinates of the tile
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Gets the type of the tile.
     *
     * @return the type of the tile
     */
    public TileType getType() {
        return type;
    }

    /**
     * Gets the short name of the tile.
     *
     * @return the short name of the tile
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Gets the color associated with the tile type.
     *
     * @return the color corresponding to the tile type
     */
    public Color getTileColor() {
        return switch (this.type) {
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

    public boolean isSpecial() {
        return isSpecial;
    }

    /**
     * Returns a string representation of the tile, with the type in lowercase and underscores replaced with spaces.
     *
     * @return a string representation of the tile
     */
    public String toString() {
        return type.toString().toLowerCase().replace("_", " ");
    }

    public void setBoss(){
        this.isBoss = true;
    }

    /**
     * Generates a short name for the tile based on its type name and a specified length.
     * If the type name is longer than the specified length, it is truncated and appended with a dot.
     *
     * @param typeName the name of the tile type
     * @param length   the maximum length of the short name
     * @return the generated short name
     */
    private String generateShortName(String typeName, int length) {
        if (typeName.length() > length + 1) {
            return typeName.substring(0, length) + ".";
        } else {
            return typeName;
        }
    }

    private void checkSpecial(TileType type) {
        if (type == TileType.WATER_TEMPLE || type == TileType.FORGE_TEMPLE || type == TileType.HUNT_TEMPLE) {
            this.isTemple = true;
        } else if (type == TileType.VILLAGE || type == TileType.CITY) {
            this.isSettlement = true;
        }
        if (this.isBoss || this.isTemple || this.isSettlement) {
            this.isSpecial = true;
        }
    }
    // Additional methods for tile logic

}
