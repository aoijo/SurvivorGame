package InterfaceAdapter;

import UseCase.*;
import UseCase.Item.EquipmentUseCase;
import UseCase.Item.ItemUseCase;
import UseCase.Map.MapUseCase;

import java.awt.*;

public class UseCaseManager {
    private EquipmentUseCase equipmentUseCase;
    private ItemUseCase itemUseCase;
    private MapUseCase mapUseCase;
    private BuffUseCase buffUseCase;
    private ResourceUseCase resourceUseCase;
    private PlayerUseCase playerUseCase;
    private TileUseCase tileUseCase;
    private TimeUseCase timeUseCase;
    private SkillUseCase skillUseCase;
    private EnemyUseCase enemyUseCase;

    public UseCaseManager() {
        mapUseCase = new MapUseCase();
        buffUseCase = new BuffUseCase();
        resourceUseCase = new ResourceUseCase();
        timeUseCase = new TimeUseCase();
        skillUseCase = new SkillUseCase();
        equipmentUseCase = new EquipmentUseCase(skillUseCase,buffUseCase);
        itemUseCase = new ItemUseCase(equipmentUseCase);
        enemyUseCase = new EnemyUseCase(skillUseCase);
        tileUseCase = new TileUseCase(resourceUseCase, enemyUseCase);
        playerUseCase = new PlayerUseCase(tileUseCase,itemUseCase,timeUseCase,skillUseCase,buffUseCase);
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

    public SkillUseCase getSkillUseCase() {
        return skillUseCase;
    }

    public void setSkillUseCase(SkillUseCase skillUseCase) {
        this.skillUseCase = skillUseCase;
    }

    public EquipmentUseCase getEquipmentUseCase() {
        return equipmentUseCase;
    }

    public void setEquipmentUseCase(EquipmentUseCase equipmentUseCase) {
        this.equipmentUseCase = equipmentUseCase;
    }
}
