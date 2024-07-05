package Entity;

import java.util.Random;

public interface MapGenerationStrategy {
    void generateMap(Map map);
}

class CustomStrategy implements MapGenerationStrategy {
    @Override
    public void generateMap(Map map) {
        Tile[][] tiles = map.getTiles();
        int width = map.getWidth();
        int height = map.getHeight();
        Random random = new Random(map.getSeed());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TileType newTileType = determineTileType(x, y, width, height, random);
                Tile newTile = new Tile(x, y, newTileType, MapType.CUSTOM);
                map.setTile(x, y, newTile);
            }
        }
    }

    private TileType determineTileType(int x, int y, int width, int height, Random random) {
        double distanceToCenter = Math.sqrt(Math.pow(x - width / 2.0, 2) + Math.pow(y - height / 2.0, 2));
        double maxDistance = Math.sqrt(Math.pow(width / 2.0, 2) + Math.pow(height / 2.0, 2));
        double normalizedDistance = distanceToCenter / maxDistance;

        if (normalizedDistance < 0.2) {
            return TileType.PLAIN;
        } else if (normalizedDistance < 0.4) {
            return random.nextBoolean() ? TileType.WATER_TEMPLE : TileType.PLAIN;
        } else if (normalizedDistance < 0.6) {
            return random.nextBoolean() ? TileType.SWAMP : TileType.FOREST;
        } else if (normalizedDistance < 0.8) {
            return TileType.DESERT;
        } else {
            return TileType.MOUNTAIN;
        }
    }
}
