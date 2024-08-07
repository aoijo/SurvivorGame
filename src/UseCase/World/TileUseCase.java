package UseCase.World;

import Entity.Character.Enemy;
import Entity.World.Resource;
import Entity.World.Tile;
import Enums.MapTile.TileType;
import UseCase.Character.EnemyUseCase;
import UseCase.Map.MapUseCase;
import Utils.GraphicsUtils;
import Utils.ReadCSV;

import java.util.ArrayList;
import java.util.Random;

public class TileUseCase {
    private String[][] TileData;
    private ResourceUseCase resourceUseCase;
    private EnemyUseCase enemyUseCase;
    private MapUseCase mapUseCase;
    private Random random = new Random();

    public TileUseCase(ResourceUseCase resourceUseCase, EnemyUseCase enemyUseCase, MapUseCase mapUseCase) {
        this.enemyUseCase = enemyUseCase;
        this.resourceUseCase = resourceUseCase;
        this.mapUseCase = mapUseCase;
        loadTileData();
    }

    public Tile newTile(int x, int y, TileType type){
        int tileId = determineTileId(type);
        Tile tile = new Tile(x, y, tileId);
        initializeTile(tile, tileId);
        return tile;
    }

    public Tile newTile(int x, int y, int tileId){
        Tile tile = new Tile(x, y, tileId);
        initializeTile(tile, tileId);
        return tile;
    }

    private void loadTileData(){
        this.TileData = ReadCSV.read("Data/Tile.csv");
    }

    private void initializeTile(Tile tile, int tileId){
        String[] tileData = this.TileData[tileId];

        tile.setType(determineTileType(Integer.parseInt(tileData[0])));
        tile.setName(tileData[1]);
        tile.setDescription(tileData[2]);
        tile.setTileColor(GraphicsUtils.hexToColor(tileData[3]));
        tile.setPossibleResourceId(ReadCSV.readIntList(tileData[4]));
        tile.setMaxResource(ReadCSV.readIntList(tileData[5]));
        tile.setPossibleEnemiesId(ReadCSV.readIntList(tileData[6]));
        tile.setEnemySpawnChance(ReadCSV.readFloatList(tileData[7]));
        tile.setToolRequired(ReadCSV.readIntList(tileData[8]));
        tile.setShortName(generateShortName(tile.getName(),4));
        initializeResource(tile);
        respawnNormalEnemy(tile);
    }

    private void initializeResource(Tile tile){
        Resource[] currentResource = new Resource[tile.getPossibleResourceId().length];
        int[] maxResource = tile.getMaxResource();
        int[] possibleResourceId = tile.getPossibleResourceId();

        for (int i = 0; i < currentResource.length; i++) {
            currentResource[i] = resourceUseCase.newResource(possibleResourceId[i]);
            int maxResourceCount = maxResource[i];
            currentResource[i].setMaxHarvestCount(maxResourceCount);
            currentResource[i].setHarvestCount(initialResourceCount(maxResourceCount));
        }
        tile.setCurrentResource(currentResource);
    }

    public void respawnNormalEnemy(Tile tile){
        ArrayList<Enemy> currentEnemy = new ArrayList<>();
        int[] possibleEnemyId = tile.getPossibleEnemiesId();
        float[] enemySpawnChance = tile.getEnemySpawnChance();
        for (int i = 0; i < possibleEnemyId.length; i++){
            if (random.nextFloat() < enemySpawnChance[i]){
                currentEnemy.add(enemyUseCase.newEnemy(possibleEnemyId[i], false, false));
            }
        }
        tile.setCurrentEnemy(currentEnemy);
    }
    public void respawnNormalEnemy(int[] playerPosition){
        respawnNormalEnemy(mapUseCase.getTile(playerPosition[0], playerPosition[1]));
    }

    public void addEnemy(Tile tile, int Id, boolean isBoss, Boolean isCursed){
        ArrayList<Enemy> currentEnemy = tile.getCurrentEnemy();
        currentEnemy.add(enemyUseCase.newEnemy(Id, isBoss, isCursed));
        tile.setCurrentEnemy(currentEnemy);
    }

    public void removeEnemy(Tile tile, int Id){
        ArrayList<Enemy> currentEnemy = tile.getCurrentEnemy();
        for (Enemy enemy : currentEnemy){
            if (enemy.getId() == Id){
                currentEnemy.remove(enemy);
            }
        }
        tile.setCurrentEnemy(currentEnemy);
    }

    public void changeResourceCount(Resource resource, int number){
        int newCount = resource.getHarvestCount() + number;
        if (number >0){
            resource.setHarvestCount(Math.min(resource.getMaxHarvestCount(), newCount));
        } else {
            resource.setHarvestCount(Math.max(newCount, 0));
        }
    }

    private TileType determineTileType(int tileId) {
        return switch (tileId) {
            case 1 -> TileType.CITY;
            case 2 -> TileType.VILLAGE;
            case 3 -> TileType.ASH_PIT;
            case 4 -> TileType.FORGE_TEMPLE;
            case 5 -> TileType.HUNT_TEMPLE;
            case 6 -> TileType.WATER_TEMPLE;
            case 7 -> TileType.JELLY;
            case 8 -> TileType.BACKYARD;
            case 9 -> TileType.ABYSS;
            case 10 -> TileType.PLAIN;
            case 11 -> TileType.FOREST;
            case 12 -> TileType.SWAMP;
            case 13 -> TileType.HILL;
            case 14 -> TileType.CAVE;
            case 15 -> TileType.MINE;
            case 16 -> TileType.LAKE;
            case 17 -> TileType.BEACH;
            case 18 -> TileType.OCEAN;
            case 19 -> TileType.DEEP_SEA;
            case 20 -> TileType.HEAT_VENT;
            case 21 -> TileType.ISLAND;
            case 22 -> TileType.VORTEX;
            case 23 -> TileType.CORPSE;
            case 24 -> TileType.RUIN;
            case 25 -> TileType.TEMPLE;
            case 26 -> TileType.MOUNTAIN;
            case 27 -> TileType.DESERT;
            case 28 -> TileType.OASIS;
            case 29 -> TileType.LAVA;
            default -> throw new IllegalArgumentException("Invalid tileId: " + tileId);
        };
    }

    private int determineTileId(TileType tileType) {
        return switch (tileType) {
            case CITY -> 1;
            case VILLAGE -> 2;
            case ASH_PIT -> 3;
            case FORGE_TEMPLE -> 4;
            case HUNT_TEMPLE -> 5;
            case WATER_TEMPLE -> 6;
            case JELLY -> 7;
            case BACKYARD -> 8;
            case ABYSS -> 9;
            case PLAIN -> 10;
            case FOREST -> 11;
            case SWAMP -> 12;
            case HILL -> 13;
            case CAVE -> 14;
            case MINE -> 15;
            case LAKE -> 16;
            case BEACH -> 17;
            case OCEAN -> 18;
            case DEEP_SEA -> 19;
            case HEAT_VENT -> 20;
            case ISLAND -> 21;
            case VORTEX -> 22;
            case CORPSE -> 23;
            case RUIN -> 24;
            case TEMPLE -> 25;
            case MOUNTAIN -> 26;
            case DESERT -> 27;
            case OASIS -> 28;
            case LAVA -> 29;
            default -> throw new IllegalArgumentException("Invalid tileType: " + tileType);
        };
    }

    private String generateShortName(String typeName, int length) {
        if (typeName.length() > length + 1) {
            return typeName.substring(0, length).toUpperCase() + ".";
        } else {
            return typeName.toUpperCase();
        }
    }

    private void checkSpecial(Tile tile,TileType type) {
        if (type == TileType.WATER_TEMPLE || type == TileType.FORGE_TEMPLE || type == TileType.HUNT_TEMPLE) {
            tile.setTemple(true);
        } else if (type == TileType.VILLAGE || type == TileType.CITY) {
            tile.setSettlement(true);
        }
        if (tile.isPassage() || tile.isTemple() || tile.isSettlement()) {
            tile.setUnique(true);
        }
    }

    private int initialResourceCount(int maxCount){
        float halfCount = ((float) maxCount) / 2;
        return random.nextBoolean() ? (int)halfCount : (int) Math.ceil(halfCount);
    }

    public Enemy getEnemyByName(int[] playerPosition, String enemyName){
        ArrayList<Enemy> enemies = mapUseCase.getTile(playerPosition[0],playerPosition[1]).getCurrentEnemy();
        for (Enemy enemy : enemies){
            if(enemy.getName().equals(enemyName)){
                return enemy;
            }
        }
        return null;
    }
}
