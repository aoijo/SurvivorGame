package InterfaceAdapter;

import Entity.Item.Equipment;
import Enums.Item.ItemType;
import Enums.Rarity;
import UseCase.PlayerUseCase;

public class ItemAdapter {
    private PlayerUseCase playerUseCase;

    public ItemAdapter(UseCaseManager useCaseManager) {
        this.playerUseCase = useCaseManager.getPlayerUseCase();
    }
    public int getIdByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getId();
    }

    public String getNameByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getName();
    }

    public String getDescriptionByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getDescription();
    }

    public int getSinglePriceByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getSinglePrice();
    }

    public int getPriceByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getPrice();
    }

    public float getSingleWeightByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getSingleWeight();
    }

    public float getWeightByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getWeight();
    }

    public int getSingleDustByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getSingleDust();
    }

    public int getDustByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getDust();
    }

    public int getQuantityByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getQuantity();
    }

    public ItemType getItemTypeByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getItemType();
    }

    public Rarity getRarityByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getRarity();
    }

    public int getLevelRequirementByIndex(int index) {
        return playerUseCase.getSortedItems().get(index).getLevelRequirement();
    }

    public int getAttackByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getAttack();
    }

    public int getDefenseByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getDefense();
    }

    public int getLifeStealByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getLifeSteal();
    }

    public int getMaxHealthByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getMaxHealth();
    }

    public int getMaxWeightByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getMaxWeight();
    }

    public int getDamageReductionByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getDamageReduction();
    }

    public int getDurabilityByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getDurability();
    }

    public int getCurrentMaxDurabilityByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getCurrentMaxDurability();
    }

    public int getMaxDurabilityByIndex(int index) {
        return ((Equipment) playerUseCase.getSortedItems().get(index)).getMaxDurability();
    }
}
