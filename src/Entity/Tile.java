package Entity;

import Enums.TileType;
import java.awt.*;

/**
 * Represents a tile on a map, with properties such as position, type, and a short name.
 */
public class Tile {
    private int[] position;
    private TileType type;
    private String shortName;
    private Color tileColor;
    private boolean isUnique;
    private boolean isBoss;
    private boolean isTemple;
    private boolean isSettlement;

    public Tile(int x, int y, TileType type) {
        this.position = new int[]{x, y};
        this.type = type;
    }

    public int[] getPosition() {
        return position;
    }
    public void setPosition(int x, int y) {
        this.position = new int[]{x, y};
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public void setTileColor(Color tileColor) {
        this.tileColor = tileColor;
    }

    public boolean isUnique() {
        return isUnique;
    }
    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        this.isBoss = boss;
    }

    public boolean isTemple() {
        return isTemple;
    }

    public void setTemple(boolean temple) {
        isTemple = temple;
    }

    public boolean isSettlement() {
        return isSettlement;
    }

    public void setSettlement(boolean settlement) {
        isSettlement = settlement;
    }
}
