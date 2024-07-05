package Entity;

import java.awt.*;

public class Tile {
    private final int[] position;
    private final TileType type;
    public final Color tileColor;
    private final String shortName;

    // Add more properties like terrain type, obstacles, etc.

    public Tile(int x, int y, TileType type, MapType mapType) {
        this.position = new int[]{x, y};
        this.type = type;
        this.tileColor = getTileColor();
        this.shortName = generateShortName(type.toString(), 4);
    }

    // Getter methods for x and y
    public int[] getPosition() {
        return position;
    }

    public TileType getType() {
        return type;
    }

    public String getShortName() {
        return shortName;
    }

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

    @Override
    public String toString() {
        return type.toString().toLowerCase().replace("_", " ");
    }

    private String generateShortName(String typeName, int length) {
        if (typeName.length() > length + 1) {
            return typeName.substring(0, length) + ".";
        } else {
            return typeName;
        }
    }

    // Additional methods for tile logic
}

