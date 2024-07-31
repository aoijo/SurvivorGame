package UseCase.Character;

import Entity.Buff;
import Entity.Item.Equipment;
import Entity.Item.Item;
import Entity.Character.Player;
import Entity.Skills.Skill;
import Entity.World.Resource;
import Entity.World.Tile;
import Enums.Item.ItemType;
import Enums.MapTile.MapType;
import Enums.Rarity;
import UseCase.BuffUseCase;
import UseCase.Item.EquipmentUseCase;
import UseCase.Item.ItemUseCase;
import UseCase.SkillUseCase;
import UseCase.World.TileUseCase;
import UseCase.World.TimeUseCase;
import Utils.ReadCSV;

import java.awt.*;
import java.util.*;
import java.util.List;


public class PlayerUseCase {
    private Player player;
    private String[][] RaceData;
    private Random random;

    private ItemUseCase itemUseCase;
    private EquipmentUseCase equipmentUseCase;
    private TileUseCase tileUseCase;
    private TimeUseCase timeUseCase;
    private SkillUseCase skillUseCase;
    private BuffUseCase buffUseCase;
    private CharacterStatsCalculation characterStatsCalculation;

    private ArrayList<Item> sortedItems;
    private String[][] sortedItemInfo;

    public PlayerUseCase(TileUseCase tileUseCase, ItemUseCase itemUseCase, TimeUseCase timeUseCase,
    SkillUseCase skillUseCase, BuffUseCase buffUseCase, CharacterStatsCalculation characterStatsCalculation) {
        this.tileUseCase = tileUseCase;
        this.itemUseCase = itemUseCase;
        this.equipmentUseCase = itemUseCase.getEquipmentUseCase();
        this.timeUseCase = timeUseCase;
        this.skillUseCase = skillUseCase;
        this.buffUseCase = buffUseCase;
        this.characterStatsCalculation = characterStatsCalculation;
        this.random = new Random();
        loadRaceData();

    }

    // For testing
    private void additionalInitialize(Player player) {
        player.setForgeExperience(1000000);
        player.setMaxWeight(10000);
        changeExperience(player,10000);
        //changeExperience(player,1000);
        //forgeEquipments(player,1,50);
        //forgeEquipments(player,10,50);
        //forgeEquipments(player,21,50);
        forgeEquipment(player,44);
        forgeEquipment(player,45);
        player.setPosition(new int[]{8,8});
        //gainItems(player, new int[]{1,2,3,4,5,6,7,8,9,10,1001,1002,1003,1004,2001,2002,2003,2004,3001,4001}, new int[]{1,2,3,4,5,6,7,8,9,10,1001,1002,1003,1004,2001,2002,2003,2004,3001,4001});
        //gainBuffs(player,new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}, new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}, new float[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
        //gainBuff(player,1,0);
        gainBuff(player,1,12,0,1);
        //gainBuff(player,2,12,2);
        //gainBuff(player,3,12,3);
        //removeBuff(player,player.getBuffs().get(0),6);
        //removeBuff(player,player.getBuffs().get(1),16);
        //skillTest(player);
        //buffTest(player);
        //player.setAttributePoint(3);
        //bagTest(player);
    }

    private void initialize(Player player, int raceId) {
        String[] raceData = this.RaceData[raceId];
        player.setCurrentMap(MapType.ISLAND);
        player.setDescription(raceData[2]);
        player.setMaxHealth(Integer.parseInt(raceData[3]));
        player.setMaxHunger(Integer.parseInt(raceData[4]));
        player.setMaxHydration(Integer.parseInt(raceData[5]));
        player.setMaxSanity(Integer.parseInt(raceData[6]));
        player.setMaxWeight(Integer.parseInt(raceData[7]));
        player.setAttack(Integer.parseInt(raceData[8]));
        player.setDefense(Integer.parseInt(raceData[9]));
        player.setSpeed(Integer.parseInt(raceData[10]));
        player.setLevelUpHealth(Integer.parseInt(raceData[11]));
        player.setLevelUpHunger(Integer.parseInt(raceData[12]));
        player.setLevelUpHydration(Integer.parseInt(raceData[13]));
        player.setLevelUpWeight(Integer.parseInt(raceData[14]));
        player.setLevelUpAttributePoints(Integer.parseInt(raceData[15]));
        player.setLevel(Integer.parseInt(raceData[16]));
        player.setMaxExperience(Integer.parseInt(raceData[17]));

        player.setHealth(player.getMaxHealth());
        player.setCurrentMaxHealth(player.getMaxHealth());
        player.setHunger(player.getMaxHunger());
        player.setCurrentMaxHunger(player.getMaxHunger());
        player.setHydration(player.getMaxHydration());
        player.setCurrentMaxHydration(player.getMaxHydration());
        player.setSanity(player.getMaxSanity());
        player.setCurrentMaxSanity(player.getMaxSanity());
        player.setCurrentMaxWeight(player.getMaxWeight());
        player.setCurrentSpeed(player.getSpeed());
        player.setAmulet(new Equipment[4]);
        player.setInCombat(false);

        player.setSkills(new ArrayList<>());
        player.setBuffs(new ArrayList<>());
        player.setCurrentBuffs(new ArrayList<>());
        player.setCurrentSkills(new ArrayList<>());

        gainItems(player,ReadCSV.readIntList(raceData[18]),ReadCSV.readIntList(raceData[19]));
        gainSkills(player,new int[]{1,2,9}, new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE});
        int[] durability = new int[ReadCSV.readIntList(raceData[20]).length];
        Arrays.fill(durability,Integer.MAX_VALUE);
        gainSkills(player,ReadCSV.readIntList(raceData[20]), durability);

        player.setPosition(generateRandomLocation(player));

        additionalInitialize(player);
    }

    public Player newPlayer(String name, Color color, int raceId) {
        Player newPlayer = new Player(color, raceId);
        initialize(newPlayer, raceId);
        newPlayer.setName(name);

        return newPlayer;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayerPosition(Player player, int dx, int dy) {
        int[] position = new int[]{dx, dy};
        player.setPosition(position);
    }
    public void changeHealth(Player player, int dh) {
        int health = player.getHealth() + dh;
        health = Math.max(0, Math.min(health, player.getCurrentMaxHealth()));
        player.setHealth(health);
    }
    public void changeHunger(Player player, int dh) {
        int hunger = player.getHunger() + dh;
        hunger = Math.max(0, Math.min(hunger, player.getMaxHunger()));
        player.setHunger(hunger);
    }

    public void changeHydration(Player player, int dh) {
        int hydration = player.getHydration() + dh;
        hydration = Math.max(0, Math.min(hydration, player.getCurrentMaxHydration()));
        player.setHydration(hydration);
    }

    public void changeSanity(Player player, int ds) {
        int sanity = player.getSanity() + ds;
        sanity = Math.max(0, Math.min(sanity, player.getMaxSanity()));
        player.setSanity(sanity);
    }

    public void changeExperience(Player player, int de) {
        int experience = player.getExperience() + de;
        while (experience >= player.getMaxExperience()) {
            experience -= player.getMaxExperience();
            levelUp(player);
        }
        if (experience < 0) {
            experience = 0;
        }
        player.setExperience(experience);
    }

    public void changeAttack(Player player, int da) {
        player.setAttack(player.getAttack() + da);
    }

    public void changeDefense(Player player, int dd) {
        player.setDefense(player.getDefense() + dd);
    }

    private void loadRaceData(){
        this.RaceData = ReadCSV.read("Data/Race.csv");
    }

    private void levelUp(Player player) {
        player.setHealth(player.getHealth() + player.getLevelUpHealth());
        player.setMaxHealth(player.getMaxHealth() + player.getLevelUpHealth());
        player.setHunger(player.getHunger() + player.getLevelUpHunger());
        player.setMaxHunger(player.getMaxHunger() + player.getLevelUpHunger());
        player.setHydration(player.getHydration() + player.getLevelUpHydration());
        player.setMaxHydration(player.getMaxHydration() + player.getLevelUpHydration());
        player.setAttributePoint(player.getAttributePoint() + player.getLevelUpAttributePoints());

        player.setExperience(player.getExperience() - player.getMaxExperience());
        player.setLevel(player.getLevel() + 1);
        player.setMaxExperience(10 * player.getLevel() * player.getLevel());
    }

    private int[] generateRandomLocation(Player player){
        if (Objects.requireNonNull(player.getCurrentMap()) == MapType.ISLAND) {
            return new int[]{random.nextInt(32), random.nextInt(32)};
        }else{return new int[2];}
    }

    public void gainItem(Player player, int itemId, int number) {
        // Retrieve the player's item bag
        ArrayList<Item> items = player.getItemInBag();

        // Initialize the items list if it is null
        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items);
        }
        if (itemId <= 1000){
            System.out.println("Cannot gain Equipment this way");
            return;
        }
        if (number == 0 ){
            return;
        }else if (number > 0) {
            // Handle addition or increment of item quantity
            for (Item item : items) {
                if (item.getId() == itemId) {
                    long newQuantity = (long) item.getQuantity() + number;
                    if (newQuantity > Integer.MAX_VALUE) {
                        item.setQuantity(Integer.MAX_VALUE);
                    } else {
                        item.setQuantity((int) newQuantity);
                    }
                    updateItemNumberChange(item);
                    updateWeight(player);
                    return; // Exit after processing the item
                }
            }
            // If the item does not exist in the bag, add a new one
            Item newItem = itemUseCase.newItem(itemId);
            newItem.setQuantity(number);
            items.add(newItem);
            updateItemNumberChange(newItem);
        }
        updateWeight(player);
    }
    public void removeItem(int itemId, int number) {
        ArrayList<Item> items = player.getItemInBag();

        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items);
        }

        Item itemToRemove = null;
        for (Item item : items) {
            if (item.getId() == itemId) {
                if (item.getQuantity() <= number) {
                    itemToRemove = item;
                } else{
                    item.setQuantity(item.getQuantity() - number);
                    updateItemNumberChange(item);
                    updateWeight(player);
                }
            }
        }
        items.remove(itemToRemove);
        updateWeight(player);
    }
    public void removeItem(Item item, int number) {
        ArrayList<Item> items = player.getItemInBag();

        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items);
        }
        Item itemToRemove = null;
        for (Item Item : items) {
            if (Item == item) {
                if (item.getQuantity() <= number) {
                    itemToRemove = item;
                } else{
                    item.setQuantity(item.getQuantity() - number);
                    updateItemNumberChange(item);
                }
            }
        }
        items.remove(itemToRemove);
    }
    public void removeByIndex(int index, int number) {
        removeItem(sortedItems.get(index), number);
    }
    public void gainItems(Player player,int[] itemIds, int[] numbers) {
        if (itemIds.length == 0) {
            return;
        }
        for(int i = 0; i < itemIds.length; i++) {
            gainItem(player,itemIds[i], numbers[i]);
        }
    }
    public void removeItems(int[] itemIds, int[] numbers) {
        if (itemIds.length == 0) {
            return;
        }
        for(int i = 0; i < itemIds.length; i++) {
            removeItem(itemIds[i], numbers[i]);
        }
    }
    public void gainSkill(Player player, int skillId, int durability) {
        ArrayList<Skill> skills = player.getSkills();
        if (skills == null) {
            skills = new ArrayList<>();
            player.setSkills(skills);
        }

        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return; // Skill already exists, no need to add
            }
        }

        // Skill does not exist, add new skill
        player.getSkills().add(skillUseCase.newSkill(skillId, durability));
        updateCurrentSkill(player);
    }
    public void removeSkill(Player player, int skillId) {
        ArrayList<Skill> skills = player.getSkills();
        if (skills == null) {
            skills = new ArrayList<>();
            player.setSkills(skills);
        }
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                player.getSkills().remove(skill);
            }else{
                return;
            }
        }
        updateCurrentSkill(player);
    }
    public void gainSkills(Player player, int[] skillIds, int[] durability) {
        if (skillIds.length == 0) {
            return;
        }
        for (int i = 0; i < skillIds.length; i++) {
            gainSkill(player, skillIds[i],durability[i]);
        }
    }
    public void removeSkills(Player players, int[] skillIds) {
        if (skillIds.length == 0) {
            return;
        }
        for(int skillId : skillIds) {
            removeSkill(players, skillId);
        }
    }
    public void gainBuff(Player player, int buffId ,int stack, float timeRemain, int affectTurns) {

        buffUseCase.characterGainBuff(buffId,stack,player,player,timeRemain,affectTurns);
        updateCurrentBuff(player);
    }
    public void removeBuff(Player player, Buff buff , int stack) {
        buffUseCase.characterRemoveBuff(buff,stack,player);
        updateCurrentBuff(player);
    }
    public void gainBuffs(Player player, int[] buffIds, int[] stacks, float[] timeRemains, int[] affectTurns) {
        if (buffIds.length == 0) {
            return;
        }
        for(int i = 0; i <buffIds.length; i++) {
            gainBuff(player,buffIds[i],stacks[i],timeRemains[i],affectTurns[i]);
        }
    }
    public void removeBuffs(Player player, Buff[] buffIds, int[] stacks) {
        if (buffIds.length == 0) {
            return;
        }
        for(int i = 0; i <buffIds.length; i++) {
            removeBuff(player,buffIds[i],stacks[i]);
        }
    }
    public void forgeEquipment(Player player, int equipmentId) {
        // Retrieve the player's item bag
        ArrayList<Item> items = player.getItemInBag();

        // Initialize the items list if it is null
        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items);
        }
        Equipment newEquipment = equipmentUseCase.forgeEquipment(player.getForgeExperience(),equipmentId);
        newEquipment.setQuantity((1));
        items.add(newEquipment);
        updateWeight(player);
    }
    public void forgeEquipments(Player player, int equipmentId,int equipmentNumber) {
        for (int i = 0; i < equipmentNumber; i++) {
            forgeEquipment(player, equipmentId);
        }
    }
    public void unEquipEquipment(Player player, Equipment equipment) {
        if (equipment == null){
            return;
        }

        equipment.setEquipped(false);
        switch(equipment.getEquipmentType()){
            case WEAPON -> {player.setWeapon(null);}
            case ARMOR -> player.setArmor(null);
            case TOOL -> player.setTool(null);
            case BAG -> player.setBag(null);
            case AMULET -> {
                for (int i = 0; i < player.getAmulet().length; i++){
                    if (player.getAmulet()[i] == equipment){
                        player.setSingleAmulet(null,i);
                        break;
                    }
                }
            }
        }
        updatePlayer();
    }
    public void equipEquipment(Player player, Equipment equipment) {
        if (equipment == null){
            return;
        }

        equipment.setEquipped(true);
        switch(equipment.getEquipmentType()){
            case WEAPON -> {
                unEquipEquipment(player, player.getWeapon());
                player.setWeapon(equipment);
            }
            case ARMOR -> {
                unEquipEquipment(player, player.getArmor());
                player.setArmor(equipment);
            }
            case TOOL -> {
                unEquipEquipment(player, player.getTool());
                player.setTool(equipment);
            }
            case BAG -> {
                unEquipEquipment(player, player.getBag());
                player.setBag(equipment);
            }
            case AMULET -> {
                for (int i = 0; i < player.getAmulet().length; i++){
                    if (player.getAmulet()[i] == null){
                        player.setSingleAmulet(equipment,i);
                        break;
                    }
                }
            }
        }
        updatePlayer();
    }
    public void equipByIndex(Player player, int index){
        Equipment equipment = (Equipment) sortedItems.get(index);
        equipEquipment(player, equipment);
    }
    public void unEquipByIndex(Player player, int index){
        Equipment equipment = (Equipment) sortedItems.get(index);
        unEquipEquipment(player, equipment);
    }

    /**
     * Sorts and filters a list of items by their type.
     *
     * @param items the list of items to sort and filter
     * @param itemType the type of items to filter by; if null, no filtering is applied
     * @param showAll if true, includes items that do not match the itemType at the end of the sorted list
     * @param isAscend if true, sorts the items in ascending order; otherwise, sorts in descending order
     * @return a sorted and filtered list of items
     */
    public ArrayList<Item> sortItemsByType(ArrayList<Item> items, ItemType itemType, boolean showAll, boolean isAscend) {
        if (items == null) {
            return new ArrayList<>();
        }
        ArrayList<Item> newItems = new ArrayList<>(items.size());
        ArrayList<Item> restItems = new ArrayList<>(items.size());

        if (itemType == null) {
            newItems.addAll(items);
        } else {
            for (Item item : items) {
                if (item.getItemType().equals(itemType)) {
                    newItems.add(item);
                } else {
                    restItems.add(item);
                }
            }
        }

        Comparator<Item> comparator = Comparator.comparing(Item::getName);
        if (!isAscend) {
            comparator = comparator.reversed();
        }

        newItems.sort(comparator);
        restItems.sort(comparator);

        if (showAll) {
            newItems.addAll(restItems);
        }

        return newItems;
    }
    /**
     * Sorts a list of items by their weight.
     *
     * @param items the list of items to sort
     * @param isAscend if true, sorts the items in ascending order; otherwise, sorts in descending order
     * @return a sorted list of items
     */
    public ArrayList<Item> sortItemsByWeight(ArrayList<Item> items, boolean isAscend) {
        if (items == null) {
            return new ArrayList<>();
        }
        ArrayList<Item> newItems = new ArrayList<>(items);

        Comparator<Item> comparator = Comparator.comparing(Item::getWeight);
        if (!isAscend) {
            comparator = comparator.reversed();
        }
        newItems.sort(comparator);

        return newItems;
    }
    /**
     * Sorts a list of items by their rarity and then by their name.
     *
     * @param items the list of items to sort
     * @param isAscend if true, sorts the items by ascending rarity; otherwise, sorts by descending rarity
     * @return a sorted list of items
     */
    public ArrayList<Item> sortItemsByRarity(ArrayList<Item> items, boolean isAscend) {
        if (items == null) {
            return new ArrayList<>();
        }

        Map<Rarity, List<Item>> itemsByRarity = new EnumMap<>(Rarity.class);
        for (Rarity rarity : Rarity.values()) {
            itemsByRarity.put(rarity, new ArrayList<>());
        }

        for (Item item : items) {
            itemsByRarity.get(item.getRarity()).add(item);
        }

        Comparator<Item> comparator = Comparator.comparing(Item::getName);
        for (List<Item> itemList : itemsByRarity.values()) {
            itemList.sort(comparator);
        }

        ArrayList<Item> sortedItems = new ArrayList<>();
        Rarity[] rarityOrder = Rarity.values();
        if (!isAscend) {
            for (int i = rarityOrder.length - 1; i >= 0; i--) {
                sortedItems.addAll(itemsByRarity.get(rarityOrder[i]));
            }
        } else {
            for (Rarity rarity : rarityOrder) {
                sortedItems.addAll(itemsByRarity.get(rarity));
            }
        }

        return sortedItems;
    }
    public ArrayList<Item> getItemByType(ArrayList<Item> items, ItemType itemType, boolean isAscend){
        if (items == null) {
            return new ArrayList<>();
        }
        ArrayList<Item> newItems = new ArrayList<>(items.size());

        if (itemType == null) {
            newItems.addAll(items);
        } else{
            for (Item item : items) {
                if (item.getItemType().equals(itemType)) {
                    newItems.add(item);
                }
            }
        }

        if (!isAscend){
            Collections.reverse(newItems);
        }
        return newItems;
    }
    /**
     * Retrieves sorted item information based on the given parameters.
     *
     * @param itemType the type of items to filter by; if null, no filtering is applied
     * @param sortBy the attribute to sort by; valid values are "Weight" and "Rarity"
     * @param showAll if true, includes items that do not match the itemType at the end of the sorted list
     * @param isAscend if true, sorts the items in ascending order; otherwise, sorts in descending order
     * @return a 2D array containing the sorted item information (ID, Name, Quantity)
     * @throws IllegalArgumentException if the sortBy parameter is not "Weight" or "Rarity"
     */
    public String[][] getSortedItemInfo(ItemType itemType, String sortBy, boolean showAll, boolean isAscend) {
        if (player == null || player.getItemInBag() == null) {
            return new String[5][0];
        }

        ArrayList<Item> itemsToShow = sortItemsByType(player.getItemInBag(), itemType, showAll, isAscend);
        if (itemsToShow.isEmpty()) {
            return new String[5][0];
        }

        switch (sortBy) {
            case "Name":
                Comparator<Item> nameComparator = Comparator.comparing(Item::getName);
                if (!isAscend) {
                    nameComparator = nameComparator.reversed();
                }
                itemsToShow.sort(nameComparator);
                break;
            case "Weight":
                itemsToShow = sortItemsByWeight(itemsToShow, isAscend);
                break;
            case "Rarity":
                itemsToShow = sortItemsByRarity(itemsToShow, isAscend);
                break;
            case "Time":
                itemsToShow = getItemByType(player.getItemInBag(),itemType,isAscend);
                break;
            default:
                throw new IllegalArgumentException("sort by option not supported");
        }
        sortedItems = itemsToShow;
        String[][] itemInfo = new String[5][itemsToShow.size()];
        for (int i = 0; i < itemsToShow.size(); i++) {
            itemInfo[0][i] = String.valueOf(itemsToShow.get(i).getId());
            itemInfo[1][i] = itemsToShow.get(i).getName();
            itemInfo[2][i] = String.valueOf(itemsToShow.get(i).getQuantity());
            itemInfo[3][i] = String.valueOf(itemsToShow.get(i).getRarity());
            itemInfo[4][i] = String.valueOf(false);
            if (itemsToShow.get(i).getItemType() == ItemType.EQUIPMENT){
                itemInfo[4][i] = String.valueOf(((Equipment)itemsToShow.get(i)).getEquipped());
            }
        }
        this.sortedItemInfo = itemInfo;
        return itemInfo;
    }

    public void harvestResource(Tile tile, String resourceName) {
        Resource[] resources = tile.getCurrentResource();
        for (Resource resource : resources) {
            if (resource.getName().equals(resourceName)) {
                tileUseCase.changeResourceCount(resource, -1);
                timeUseCase.timePass(resource.getHarvestTime());
                gainItems(this.player,resource.getItemDropId(),determineHarvestItem(resource.getItemDropMin(), resource.getItemDropMax()));
            }
        }
    }
    private int[] determineHarvestItem(int[] min, int[] max){
        int[] items = new int[min.length];
        for(int i = 0; i < min.length; i++) {
            Random random = new Random();
            items[i] = random.nextInt(max[i] - min[i]) + min[i];
        }
        return items;
    }

    private void updateItemNumberChange(Item item){
        item.setWeight(item.getQuantity() * item.getSingleWeight());
        item.setDust(item.getQuantity() * item.getSingleDust());
        item.setPrice(item.getQuantity() * item.getSinglePrice());
    }
    private void updateWeight(Player player){
        float Weight = 0;
        for (Item item : player.getItemInBag()) {
            Weight += item.getWeight();
        }
        player.setWeight(Weight);
    }
    private void updateCurrentSkill(Player player){
        ArrayList<Skill> currentSkills = new ArrayList<>();
        currentSkills.addAll(player.getSkills());
        currentSkills.addAll(getEquipmentSkills(player));
        player.setCurrentSkills(currentSkills);
    }
    private void updateCurrentBuff(Player player){
        ArrayList<Buff> currentBuffs = new ArrayList<>();
        currentBuffs.addAll(player.getBuffs());
        currentBuffs.addAll(getEquipmentBuffs(player));
        player.setCurrentBuffs(currentBuffs);
    }
    private void updateCurrentAttack(Player player){
        player.setCurrentAttack(characterStatsCalculation.CalculateAttack());
    }
    private void updateCurrentDefence(Player player){
        player.setCurrentDefense(characterStatsCalculation.CalculateDefense());
    }
    private void updateCurrentLifeSteal(Player player){
        player.setCurrentLifeSteal(characterStatsCalculation.CalculateLifeSteal());
    }
    private void updateCurrentDamageReduction(Player player){
        player.setCurrentDamageReduction(characterStatsCalculation.CalculateDamageReduction());
    }
    private void updateCurrentMaxHealth(Player player){
        player.setCurrentMaxHealth(characterStatsCalculation.CalculateMaxHealth());
    }
    private void updateCurrentSpeed(Player player){
        player.setCurrentSpeed(characterStatsCalculation.CalculateSpeed());
    }
    private void updateCurrentMaxHunger(Player player){
        player.setCurrentMaxHunger(characterStatsCalculation.CalculateHunger());
    }
    private void updateCurrentMaxHydration(Player player){
        player.setCurrentMaxHydration(characterStatsCalculation.CalculateHydration());
    }
    private void updateCurrentMaxWeight(Player player){
        player.setCurrentMaxWeight(characterStatsCalculation.CalculateWeight());
    }
    public void updatePlayer(){
        characterStatsCalculation.setCharacter(player);
        updateCurrentSkill(player);
        updateCurrentBuff(player);
        updateCurrentAttack(player);
        updateCurrentDefence(player);
        updateCurrentLifeSteal(player);
        updateCurrentDamageReduction(player);
        updateCurrentMaxHealth(player);
        updateCurrentSpeed(player);
        updateCurrentMaxHunger(player);
        updateCurrentMaxHydration(player);
        updateWeight(player);
        updateCurrentMaxWeight(player);
    }

    public ArrayList<Skill> getEquipmentSkills(Player player){
        ArrayList<Skill> skills = new ArrayList<>();
        if (player.getWeapon() != null){
            skills.addAll(player.getWeapon().getSkills());
        }
        if (player.getArmor() != null){
            skills.addAll(player.getArmor().getSkills());
        }
        if (player.getTool() != null){
            skills.addAll(player.getTool().getSkills());
        }
        if (player.getBag() != null){
            skills.addAll(player.getBag().getSkills());
        }
        for (Equipment amulet : player.getAmulet()){
            if (amulet != null){
                skills.addAll(amulet.getSkills());
            }
        }
        return skills;
    }
    public ArrayList<Buff> getEquipmentBuffs(Player player){
        ArrayList<Buff> buffs = new ArrayList<>();
        if (player.getWeapon() != null){
            buffs.addAll(player.getWeapon().getBuffs());
        }
        if (player.getArmor() != null){
            buffs.addAll(player.getArmor().getBuffs());
        }
        if (player.getTool() != null){
            buffs.addAll(player.getTool().getBuffs());
        }
        if (player.getBag() != null){
            buffs.addAll(player.getBag().getBuffs());
        }
        for (Equipment amulet : player.getAmulet()){
            if (amulet != null){
                buffs.addAll(amulet.getBuffs());
            }
        }
        return buffs;
    }
    public ArrayList<Item> getSortedItems() {
        return sortedItems;
    }
    public String[][] getSortedItemInfo() {
        return sortedItemInfo;
    }
    public Item getItemByIndex(int index){
        return sortedItems.get(index);
    }
    public int getIndexByItem(Item item){
        return sortedItems.indexOf(item);
    }

    public void bagTest(Player player) {
        ArrayList<Item> items = player.getItemInBag();

        // Display the item count and details for debugging
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                System.out.println("ID: " + item.getId() + ", Name: " + item.getName() + ", Weight: " + item.getWeight() +
                        ", Quantity: " + item.getQuantity());
            }
        } else {
            System.out.println("No items in the bag.");
        }
    }
    public void skillTest(Player player) {
        ArrayList<Skill> skills = player.getSkills();

        // Display the item count and details for debugging
        if (skills != null && !skills.isEmpty()) {
            for (Skill skill : skills) {
                System.out.println("ID: " + skill.getId() + ", Name: " + skill.getName() + ", Cooldown: " + skill.getCooldown());
            }
        } else {
            System.out.println("No Skills in the skill list.");
        }
        System.out.println("Total skills: " + skills.size());
    }
    public void buffTest(Player player) {
        ArrayList<Buff> buffs = player.getBuffs();

        // Display the item count and details for debugging
        if (buffs != null && !buffs.isEmpty()) {
            for (Buff buff : buffs) {
                System.out.println("ID: " + buff.getId() + ", Name: " + buff.getName() +
                        ", Stack: " + buff.getStack());
            }
        } else {
            System.out.println("No buffs in the buff list.");
        }
        System.out.println("Total buffs: " + buffs.size());
    }
    public void currentSkillTest(Player player) {
        ArrayList<Skill> skills = player.getCurrentSkills();

        // Display the item count and details for debugging
        if (skills != null && !skills.isEmpty()) {
            for (Skill skill : skills) {
                System.out.println("ID: " + skill.getId() + ", Name: " + skill.getName() + ", Cooldown: " + skill.getCooldown());
            }
        } else {
            System.out.println("No Skills in the skill list.");
        }
        System.out.println("Total skills: " + skills.size());
    }
}
