package Entity;

public class Resources {
    private int id;
    private String name;
    private String description;

    private int harvestTime; // Time required to do one Harvest (Minutes)
    private int respawnTime; // Time required to grow back one resource (Days)
    private int harvestCount; // Current number of this resource in the tile
    private int maxHarvestCount; // Max number of this resource can exist in one tile
    private int[] HarvestToolId;
    private int[] itemDropId;
    private int[] itemDropMin; // Lower boundary of item drop per harvest
    private int[] itemDropMax; // Upper boundary of item drop per harvest

    private boolean canRespawn;
    private boolean canHarvest;

    public Resources(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(int harvestTime) {
        this.harvestTime = harvestTime;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }

    public int getHarvestCount() {
        return harvestCount;
    }

    public void setHarvestCount(int harvestCount) {
        this.harvestCount = harvestCount;
    }

    public int getMaxHarvestCount() {
        return maxHarvestCount;
    }

    public void setMaxHarvestCount(int maxHarvestCount) {
        this.maxHarvestCount = maxHarvestCount;
    }

    public int[] getItemDropMin() {
        return itemDropMin;
    }

    public void setItemDropMin(int[] itemDropMin) {
        this.itemDropMin = itemDropMin;
    }

    public boolean isCanRespawn() {
        return canRespawn;
    }

    public void setCanRespawn(boolean canRespawn) {
        this.canRespawn = canRespawn;
    }

    public boolean isCanHarvest() {
        return canHarvest;
    }

    public void setCanHarvest(boolean canHarvest) {
        this.canHarvest = canHarvest;
    }

    public int[] getHarvestToolId() {
        return HarvestToolId;
    }

    public void setHarvestToolId(int[] harvestToolId) {
        HarvestToolId = harvestToolId;
    }

    public int[] getItemDropId() {
        return itemDropId;
    }

    public void setItemDropId(int[] itemDropId) {
        this.itemDropId = itemDropId;
    }

    public int[] getItemDropMax() {
        return itemDropMax;
    }

    public void setItemDropMax(int[] itemDropMax) {
        this.itemDropMax = itemDropMax;
    }
}
