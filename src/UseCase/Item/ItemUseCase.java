package UseCase.Item;

import Entity.Item.*;
import Enums.Item.EquipmentType;
import Enums.Item.ItemType;
import Enums.RaceType;
import Enums.Rarity;
import Utils.ReadCSV;

public class ItemUseCase {
    private Item item;
    private EquipmentUseCase equipmentUseCase;
    private String[][] materialData;
    private String[][] consumableData;
    private String[][] keyData;
    private String[][] questData;

    public ItemUseCase(EquipmentUseCase equipmentUseCase){
        loadItemData();
    }

    public ItemUseCase(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    private void loadItemData(){
        this.materialData = ReadCSV.read("Data/Item/Material.csv");
        this.consumableData = ReadCSV.read("Data/Item/Consumable.csv");
        this.keyData = ReadCSV.read("Data/Item/Key.csv");
        this.questData = ReadCSV.read("Data/Item/Quest.csv");
    }

    public Item newItem(int itemId) {
        ItemType itemType = determineItemType(itemId);
        if (itemType != null) {
            switch (itemType) {
                case EQUIPMENT:
                    return equipmentUseCase.newEquipment(itemId);
                case MATERIAL:
                    return initializeMaterial(itemId);
                case CONSUMABLE:
                    return initializeConsumable(itemId);
                case KEY:
                    return initializeKey(itemId);
                case QUEST:
                    return initializeQuest(itemId);
                default:
                    System.out.println("Invalid item type");
            }
        } else {
            System.out.println("Item type is null");
        }
        return null;
    }


    private ItemType determineItemType(int itemId){
        if (itemId < 1000){
            return ItemType.EQUIPMENT;
        } else if (itemId < 2000){
            return ItemType.MATERIAL;
        } else if (itemId < 3000){
            return ItemType.CONSUMABLE;
        } else if (itemId < 4000){
            return ItemType.KEY;
        } else if (itemId < 5000){
            return ItemType.QUEST;
        }
        System.out.println("Invalid itemId");
        return null;
    }

    private Rarity determineRarity(int rarityId){
        switch (rarityId){
            case 1: return Rarity.COMMON;
            case 2: return Rarity.UNCOMMON;
            case 3: return Rarity.RARE;
            case 4: return Rarity.LEGENDARY;
            case 5: return Rarity.MYTHICAL;
            case 6: return Rarity.UNIQUE;
            default: System.out.println("Invalid rarityId");
        }
        return null;
    }

    private RaceType[] determineRaceType(int[] raceTypeId){
        RaceType[] raceTypes = new RaceType[raceTypeId.length];
        if(raceTypeId[0] == 0){
            return null;
        }
        for(int i = 0; i < raceTypeId.length; i++){
            switch(raceTypeId[i]){
                case 1: {raceTypes[i] = RaceType.HUMAN;}
                case 2: {raceTypes[i] = RaceType.GOBLIN;}
                case 3: {raceTypes[i] = RaceType.SLIME;}
                case 4: {raceTypes[i] = RaceType.UNDEAD;}
                case 5: {raceTypes[i] = RaceType.MACHINE;}
                case 6: {raceTypes[i] = RaceType.VAMPIRE;}
            }
        }
        return raceTypes;
    }

    private Material initializeMaterial(int itemId){
        Material item = new Material(itemId);
        String[] materialData = this.materialData[itemId - 1000];

        item.setName(materialData[1]);
        item.setDescription(materialData[2]);
        item.setSinglePrice(Integer.parseInt(materialData[3]));
        item.setSingleWeight(Integer.parseInt(materialData[4]));
        item.setSingleDust(Integer.parseInt(materialData[5]));
        item.setRarity(determineRarity(Integer.parseInt(materialData[6])));

        item.setItemType(ItemType.MATERIAL);
        return item;
    }

    private Consumable initializeConsumable(int itemId){
        Consumable item = new Consumable(itemId);
        String[] consumableData = this.consumableData[itemId - 2000];
        item.setName(consumableData[1]);
        item.setDescription(consumableData[2]);
        item.setSinglePrice(Integer.parseInt(consumableData[3]));
        item.setSingleWeight(Integer.parseInt(consumableData[4]));
        item.setSingleDust(Integer.parseInt(consumableData[5]));
        item.setLevelRequirement(Integer.parseInt(consumableData[6]));
        item.setRaceRequirementId(determineRaceType(ReadCSV.readIntList(consumableData[7])));
        item.setRarity(determineRarity(Integer.parseInt(consumableData[8])));

        item.setItemType(ItemType.CONSUMABLE);
        return item;
    }

    private Key initializeKey(int itemId){
        Key item = new Key(itemId);
        String[] keyData = this.keyData[itemId - 3000];
        item.setName(keyData[1]);
        item.setDescription(keyData[2]);
        item.setRarity(Rarity.UNIQUE);

        item.setItemType(ItemType.KEY);
        return item;
    }

    private Quest initializeQuest(int itemId){
        Quest item = new Quest(itemId);
        String[] questData = this.questData[itemId - 4000];
        item.setName(questData[1]);
        item.setDescription(questData[2]);
        item.setRarity(determineRarity(Integer.parseInt(questData[3])));

        item.setItemType(ItemType.QUEST);
        return item;
    }

    public EquipmentUseCase getEquipmentUseCase() {
        return equipmentUseCase;
    }

    public void setEquipmentUseCase(EquipmentUseCase equipmentUseCase) {
        this.equipmentUseCase = equipmentUseCase;
    }
}
