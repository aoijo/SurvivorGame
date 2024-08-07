package InterfaceAdapter;

import Enums.Rarity;
import UseCase.Map.MapUseCase;

public class TileAdapter {
    private MapUseCase mapUseCase;

    public TileAdapter(UseCaseManager useCaseManager) {
        this.mapUseCase = useCaseManager.getMapUseCase();
    }

    public MapUseCase getMapUseCase() {
        return mapUseCase;
    }
    public String[] getResourceNames(int[] position) {
        return mapUseCase.getTile(position[0],position[1]).getCurrentResourceName();
    }

    public int[] getResourcesCount(int[] position) {
        return mapUseCase.getTile(position[0],position[1]).getCurrentResourceCount();
    }

    public String[] getEnemyNames(int[] position){
        return mapUseCase.getTile(position[0],position[1]).getCurrenEnemyName();
    }
    public Rarity[] getEnemyRarities(int[] position){
        return mapUseCase.getTile(position[0],position[1]).getCurrenEnemyRarity();
    }
    public int[] getEnemyLevels(int[] position){
        return mapUseCase.getTile(position[0],position[1]).getCurrentEnemyLevel();
    }
}
