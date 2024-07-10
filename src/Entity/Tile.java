package Entity;

import Enums.MapTile.TileType;
import java.awt.*;

/**
 * Represents a tile on a map, with properties such as position, type, and a short name.
 */
public class Tile {
    private int id;
    private int[] position;
    private TileType type;
    private String shortName;
    private String Name;
    private Color tileColor;
    private int[] possibleResourceId;
    private int[] possibleEnemiesId;
    private int[] enemySpawnChance;
    private int toolRequired;

    private boolean isUnique;
    private boolean isPassage;
    private boolean isTemple;
    private boolean isSettlement;
    private boolean isHome;

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

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
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

    public boolean isPassage() {
        return isPassage;
    }

    public void setPassage(boolean passage) {
        this.isPassage = passage;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getEnemySpawnChance() {
        return enemySpawnChance;
    }

    public void setEnemySpawnChance(int[] enemySpawnChance) {
        this.enemySpawnChance = enemySpawnChance;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public int getToolRequired() {
        return toolRequired;
    }

    public void setToolRequired(int toolRequired) {
        this.toolRequired = toolRequired;
    }

    public int[] getPossibleResourceId() {
        return possibleResourceId;
    }

    public void setPossibleResourceId(int[] possibleResourceId) {
        this.possibleResourceId = possibleResourceId;
    }

    public int[] getPossibleEnemiesId() {
        return possibleEnemiesId;
    }

    public void setPossibleEnemiesId(int[] possibleEnemiesId) {
        this.possibleEnemiesId = possibleEnemiesId;
    }
}
