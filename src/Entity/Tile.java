package Entity;

import Enums.MapTile.TileType;
import java.awt.*;

/**
 * Represents a tile on a map, with properties such as position, type, and a short name.
 */
public class Tile {
    private int id;
    private TileType type;
    private String shortName;
    private String Name;
    private String description;
    private Color tileColor;
    private int[] possibleResourceId;
    private int[] maxResource;
    private int[] possibleEnemiesId;
    private float[] enemySpawnChance;
    private int[] toolRequired;

    private int[] position;
    private boolean isUnique;
    private boolean isPassage;
    private boolean isTemple;
    private boolean isSettlement;
    private boolean isHome;

    private Resource[] currentResource;
    private Enemy[] currentEnemy;

    public Tile(int x, int y, int tileId) {
        this.position = new int[]{x, y};
        this.id = tileId;
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

    public float[] getEnemySpawnChance() {
        return enemySpawnChance;
    }

    public void setEnemySpawnChance(float[] enemySpawnChance) {
        this.enemySpawnChance = enemySpawnChance;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public int[] getToolRequired() {
        return toolRequired;
    }

    public void setToolRequired(int[] toolRequired) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(int[] maxResource) {
        this.maxResource = maxResource;
    }

    public Resource[] getCurrentResource() {
        return currentResource;
    }

    public void setCurrentResource(Resource[] currentResource) {
        this.currentResource = currentResource;
    }

    public Enemy[] getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy[] currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public String[] getCurrentResourceName() {
        String[] currentResourceName = new String[currentResource.length];
        for (int i = 0; i < currentResource.length; i++) {
            currentResourceName[i] = currentResource[i].getName();
        }
        return currentResourceName;
    }

    public int[] getCurrentResourceCount(){
        int[] currentResourceCount = new int[currentResource.length];
        for (int i = 0; i < currentResource.length; i++) {
            currentResourceCount[i] = currentResource[i].getHarvestCount();
        }
        return currentResourceCount;
    }
}
