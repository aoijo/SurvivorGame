package InterfaceAdapter;

import UseCase.BuffUseCase;
import UseCase.Item.ItemUseCase;
import UseCase.Map.MapUseCase;
import UseCase.Players.PlayerUseCase;
import UseCase.ResourceUseCase;
import UseCase.TileUseCase;
import UseCase.TimeUseCase;

import java.awt.*;

public class UseCaseManager {
    private ItemUseCase itemUseCase;
    private MapUseCase mapUseCase;
    private BuffUseCase buffUseCase;
    private ResourceUseCase resourceUseCase;
    private PlayerUseCase playerUseCase;
    private TileUseCase tileUseCase;
    private TimeUseCase timeUseCase;

    public UseCaseManager() {
        itemUseCase = new ItemUseCase();
        mapUseCase = new MapUseCase();
        buffUseCase = new BuffUseCase();
        resourceUseCase = new ResourceUseCase();
        tileUseCase = new TileUseCase(resourceUseCase);
        timeUseCase = new TimeUseCase();
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
}
