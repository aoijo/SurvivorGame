package Entity;

import Enums.TileType;

import java.util.Arrays;
import java.util.Random;

public interface MapGenerationStrategy {
    void generateMap(Map map);
}

class IslandStrategy implements MapGenerationStrategy {

    @Override
    public void generateMap(Map map) {
        Tile[][] tiles = map.getTiles();
        int width = map.getWidth();
        int height = map.getHeight();
        //Random random = new Random(map.getSeed()); //set seed
        Random random = new Random(); //for random seed test
        TileType[][] tileMap= new TileType[width][height];

        generateForest(width,height,map,tileMap,random);
        generateDessert(width,height,map,tileMap,random);
        generateMountain(width,height,map,tileMap,random);

        generateOcean(width,height,map,tileMap,random);
        generateCoast(width,height,map,tileMap,random);
        generateVolcano(width,height,map,tileMap,random);

        setTile(0,0,map,tileMap,TileType.DEEP_SEA);
        generateAllSpecials(width,height,map,tileMap,random);
    }

    private void setTile(int x,int y, Map map, TileType[][] tileMap, TileType tileType) {
        tileMap[x][y] = tileType;
        map.setTile(x, y, new Tile(x, y, tileType));
    }

    private float distanceToPosition(int x, int y, float positionX, float positionY) {
        float dx = x - positionX;
        float dy = y - positionY;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private int[] randomPosition(Random random) {
        return new int[]{random.nextInt(32), random.nextInt(32)};
    }

    private void generateForest(int width, int height, Map map, TileType[][] tileMap, Random random) {
        int forestNumber = 20;
        for (int n = 0; n < forestNumber; n++) {
            int[] forestPosition = randomPosition(random);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    float distance = distanceToPosition(x, y, forestPosition[0] / 2 + 15.5f, forestPosition[1] / 2);
                    if (distance < 3) {
                        if (random.nextFloat() > 0.2f){
                            setTile(x, y, map, tileMap, TileType.FOREST);
                        } else{
                            setTile(x, y, map, tileMap, TileType.SWAMP);
                        }
                    }
                }
            }
        }
    }

    private void generateDessert(int width, int height, Map map, TileType[][] tileMap, Random random) {
        int DessertNumber = random.nextInt(3) + 7;
        for (int n = 0; n < DessertNumber; n++) {
            int[] dessertPosition = randomPosition(random);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    float distance = distanceToPosition(x, y, (float) dessertPosition[0] / 2 + 5f, (float) dessertPosition[1] / 2 + 13f);
                    if (distance < 4) {
                        if (random.nextFloat() > 0.1f){
                            setTile(x, y, map, tileMap, TileType.DESERT);
                        } else if (random.nextFloat() > 0.1f){
                            setTile(x, y, map, tileMap, TileType.OASIS);
                        } else{
                            setTile(x, y, map, tileMap, TileType.RUIN);
                        }

                    }
                }
            }
        }
    }

    private void generateMountain(int width, int height, Map map, TileType[][] tileMap, Random random) {
        int MountainNumber = random.nextInt(2) + 5;
        for (int n = 0; n < MountainNumber; n++) {
            int[] mountainPosition = randomPosition(random);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    float distance = distanceToPosition(x, y, (float) mountainPosition[0] / 2 + 15.5f, (float) mountainPosition[1] / 2 + 15.5f);
                    if (distance < 4) {
                        if (random.nextFloat() > 0.3f){
                            setTile(x, y, map, tileMap, TileType.MOUNTAIN);
                        } else if (random.nextFloat() > 0.1f){
                            setTile(x, y, map, tileMap, TileType.CAVE);
                        } else{
                            setTile(x, y, map, tileMap, TileType.TEMPLE);
                        }
                    }
                }
            }
        }
    }

    private void generateOcean(int width, int height, Map map, TileType[][] tileMap, Random random) {
        setTile(0,0,map,tileMap,TileType.OCEAN);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x > 0 && y == 0 && tileMap[x - 1][y] == TileType.OCEAN) {
                    if (x <= 5){
                        setTile(x,y,map,tileMap,TileType.OCEAN);
                    }
                    if (random.nextFloat() > 0.2f) {
                        setTile(x,y,map,tileMap,TileType.OCEAN);
                    }
                } else if (y > 0 && x == 0 && tileMap[x][y - 1] == TileType.OCEAN) {
                    if (y <= 5){
                        setTile(x,y,map,tileMap,TileType.OCEAN);
                    }
                    if (random.nextFloat() > 0.2f) {
                        setTile(x,y,map,tileMap,TileType.OCEAN);
                    }
                } else if (x > 0 && y > 0) {
                    float distanceToCorner = distanceToPosition(x,y,0,0);
                    if (distanceToCorner < 6) {
                        setTile(x,y,map,tileMap,TileType.OCEAN);
                    }else if (tileMap[x][y - 1] == TileType.OCEAN && tileMap[x - 1][y] == TileType.OCEAN) {
                        if (random.nextFloat() > 0.15f) {
                            setTile(x,y,map,tileMap,TileType.OCEAN);
                        }
                    } else if (tileMap[x][y - 1] == TileType.OCEAN || tileMap[x - 1][y] == TileType.OCEAN) {
                        if (random.nextFloat() > 0.7f) {
                            setTile(x,y,map,tileMap,TileType.OCEAN);
                        }
                    }
                }
            }
        }
    }

    private void generateCoast(int width, int height, Map map, TileType[][] tileMap, Random random){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1){
                    if (random.nextFloat() > 0.5f){
                        setTile(x,y,map,tileMap,TileType.OCEAN);
                    }
                    if (tileMap[x][y] != TileType.OCEAN){
                        setTile(x,y,map,tileMap,TileType.BEACH);
                    }
                }
            }
        }
    }

    private void generateVolcano(int width, int height, Map map, TileType[][] tileMap, Random random){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float distanceToCenter = distanceToPosition(x,y,15.5f,15.5f);
                if (distanceToCenter < 2){
                    setTile(x,y,map,tileMap,TileType.LAVA);
                } else if (distanceToCenter < 6){
                    if (random.nextFloat() > 0.9f){
                        setTile(x,y,map,tileMap,TileType.LAVA);
                    } else{
                        setTile(x,y,map,tileMap,TileType.MOUNTAIN);
                    }
                } else if (distanceToCenter < 10){
                    if (random.nextFloat() > 0.99f){
                        setTile(x,y,map,tileMap,TileType.LAVA);
                    }
                }
            }
        }
    }

    private void generateSpecial(int width, int height, Map map, TileType[][] tileMap, Random random, TileType templeType) {
        int x, y;
        do {
            int[] templePosition = randomPosition(random);
            x = templePosition[0];
            y = templePosition[1];
        } while (tileMap[x][y] == TileType.DEEP_SEA || tileMap[x][y] == TileType.OCEAN || tileMap[x][y] == TileType.LAVA || tileMap[x][y] == TileType.WATER_TEMPLE || tileMap[x][y] == TileType.FORGE_TEMPLE || tileMap[x][y] == TileType.HUNT_TEMPLE);

        setTile(x, y, map, tileMap, templeType);
    }

    private void generateAllSpecials(int width, int height, Map map, TileType[][] tileMap, Random random) {
        generateSpecial(width, height, map, tileMap, random, TileType.WATER_TEMPLE);
        generateSpecial(width, height, map, tileMap, random, TileType.FORGE_TEMPLE);
        generateSpecial(width, height, map, tileMap, random, TileType.HUNT_TEMPLE);
    }
}