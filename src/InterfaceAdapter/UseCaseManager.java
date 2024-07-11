package InterfaceAdapter;

import UseCase.*;
import UseCase.Item.ItemUseCase;
import UseCase.Map.MapUseCase;

import java.awt.*;

public class UseCaseManager {
    private ItemUseCase itemUseCase;
    private MapUseCase mapUseCase;
    private BuffUseCase buffUseCase;
    private ResourceUseCase resourceUseCase;
    private PlayerUseCase playerUseCase;
    private TileUseCase tileUseCase;
    private TimeUseCase timeUseCase;
    private EnemyUseCase enemyUseCase;

    public UseCaseManager() {
        itemUseCase = new ItemUseCase();
        mapUseCase = new MapUseCase();
        buffUseCase = new BuffUseCase();
        resourceUseCase = new ResourceUseCase();
        tileUseCase = new TileUseCase(resourceUseCase);
        timeUseCase = new TimeUseCase();
        enemyUseCase = new EnemyUseCase();
        playerUseCase = new PlayerUseCase(tileUseCase,itemUseCase,timeUseCase,"Player 1", Color.GREEN, 1);
    }

    public ItemUseCase getItemUseCase() {
        return itemUseCase;
    }

    public void setItemUseCase(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    public MapUseCase getMapUseCase() {
        return mapUseCase;
    }

    public void setMapUseCase(MapUseCase mapUseCase) {
        this.mapUseCase = mapUseCase;
    }

    public BuffUseCase getBuffUseCase() {
        return buffUseCase;
    }

    public void setBuffUseCase(BuffUseCase buffUseCase) {
        this.buffUseCase = buffUseCase;
    }

    public ResourceUseCase getResourceUseCase() {
        return resourceUseCase;
    }

    public void setResourceUseCase(ResourceUseCase resourceUseCase) {
        this.resourceUseCase = resourceUseCase;
    }

    public PlayerUseCase getPlayerUseCase() {
        return playerUseCase;
    }

    public void setPlayerUseCase(PlayerUseCase playerUseCase) {
        this.playerUseCase = playerUseCase;
    }

    public TileUseCase getTileUseCase() {
        return tileUseCase;
    }

    public void setTileUseCase(TileUseCase tileUseCase) {
        this.tileUseCase = tileUseCase;
    }

    public TimeUseCase getTimeUseCase() {
        return timeUseCase;
    }

    public void setTimeUseCase(TimeUseCase timeUseCase) {
        this.timeUseCase = timeUseCase;
    }

    public EnemyUseCase getEnemyUseCase() {
        return enemyUseCase;
    }

    public void setEnemyUseCase(EnemyUseCase enemyUseCase) {
        this.enemyUseCase = enemyUseCase;
    }
}
