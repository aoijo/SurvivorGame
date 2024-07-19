package UseCase;

import Entity.Buff;
import Entity.Item.Item;
import Entity.Character.Player;
import Entity.Skills.Skill;
import Entity.World.Resource;
import Entity.World.Tile;
import Enums.Item.ItemType;
import Enums.Rarity;
import UseCase.Item.ItemUseCase;
import Utils.ReadCSV;

import java.awt.*;
import java.util.*;
import java.util.List;


public class PlayerUseCase {
    private Player player;
    private String[][] RaceData;
    private ItemUseCase itemUseCase;
    private TileUseCase tileUseCase;
    private TimeUseCase timeUseCase;
    private SkillUseCase skillUseCase;
    private BuffUseCase buffUseCase;

    public PlayerUseCase(TileUseCase tileUseCase, ItemUseCase itemUseCase, TimeUseCase timeUseCase,
    SkillUseCase skillUseCase, BuffUseCase buffUseCase) {
        this.tileUseCase = tileUseCase;
        this.itemUseCase = itemUseCase;
        this.timeUseCase = timeUseCase;
        this.skillUseCase = skillUseCase;
        this.buffUseCase = buffUseCase;
        loadRaceData();
    }

    private void initialize(Player player, int raceId) {
        String[] raceData = this.RaceData[raceId];
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
        gainItems(player,ReadCSV.readIntList(raceData[18]),ReadCSV.readIntList(raceData[19]));
        //Test part start
        //gainItems(player, new int[]{1,2,3,4,5,6,7,8,9,10,1001,1002,1003,1004,2001,2002,2003,2004,3001,4001}, new int[]{1,2,3,4,5,6,7,8,9,10,1001,1002,1003,1004,2001,2002,2003,2004,3001,4001});
        //gainBuffs(player,new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}, new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25});
        //gainBuff(player,1,0);
        //gainBuff(player,1,1);
        //gainSkills(player,new int[]{1,2,3,4,5});
        //skillTest(player);
        //buffTest(player);
        //player.setAttributePoint(3);
        //bagTest(player);
        //Test part end
    }

    public Player newPlayer(String name, Color color, int raceId) {
        Player newPlayer = new Player(color, raceId);
        initialize(newPlayer, raceId);
        newPlayer.setName(name);
        newPlayer.setHealth(newPlayer.getMaxHealth());
        newPlayer.setHunger(newPlayer.getMaxHunger());
        newPlayer.setHydration(newPlayer.getMaxHydration());
        newPlayer.setSanity(newPlayer.getMaxSanity());
        newPlayer.setPosition(new int[]{0,0});
        return newPlayer;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerPosition(int dx, int dy) {
        int[] position = new int[]{dx, dy};
        player.setPosition(position);
    }

    public void changeHealth(int dh) {
        int health = player.getHealth() + dh;
        health = Math.max(0, Math.min(health, player.getMaxHealth()));
        player.setHealth(health);
    }

    public void changeHunger(int dh) {
        int hunger = player.getHunger() + dh;
        hunger = Math.max(0, Math.min(hunger, player.getMaxHunger()));
        player.setHunger(hunger);
    }

    public void changeHydration(int dh) {
        int hydration = player.getHydration() + dh;
        hydration = Math.max(0, Math.min(hydration, player.getMaxHydration()));
        player.setHydration(hydration);
    }

    public void changeSanity(int ds) {
        int sanity = player.getSanity() + ds;
        sanity = Math.max(0, Math.min(sanity, player.getMaxSanity()));
        player.setSanity(sanity);
    }

    public void changeExperience(int de) {
        int experience = player.getExperience() + de;
        while (experience >= player.getMaxExperience()) {
            levelUp();
        }
        if (experience < 0) {
            experience = 0;
        }
        player.setExperience(experience);
    }

    public void changeAttack(int da) {
        player.setAttack(player.getAttack() + da);
    }

    public void changeDefense(int dd) {
        player.setDefense(player.getDefense() + dd);
    }

    private void loadRaceData(){
        this.RaceData = ReadCSV.read("Data/Race.csv");
    }


    private void levelUp() {
        player.setHealth(player.getHealth() + player.getLevelUpHealth());
        player.setMaxHealth(player.getMaxHealth() + player.getLevelUpHealth());
        player.setHunger(player.getHunger() + player.getLevelUpHunger());
        player.setMaxHunger(player.getMaxHunger() + player.getLevelUpHunger());
        player.setHydration(player.getHydration() + player.getLevelUpHydration());
        player.setMaxHydration(player.getMaxHydration() + player.getLevelUpHydration());

        player.setExperience(player.getExperience() - player.getMaxExperience());
        player.setLevel(player.getLevel() + 1);
        player.setMaxExperience(10 * player.getLevel() * player.getLevel());
        player.setAttributePoint(player.getAttributePoint() + player.getLevelUpAttributePoints());
    }

    public void gainItem(Player player, int itemId, int number) {
        // Retrieve the player's item bag
        ArrayList<Item> items = player.getItemInBag();

        // Initialize the items list if it is null
        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items);
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
                    updateItemWeight(item);
                    updateWeight(player);
                    return; // Exit after processing the item
                }
            }
            // If the item does not exist in the bag, add a new one
            Item newItem = itemUseCase.newItem(itemId);
            newItem.setQuantity(number);
            items.add(newItem);
            updateItemWeight(newItem);
        }
        updateWeight(player);
    }

    public void removeItem(int itemId, int number) {
        ArrayList<Item> items = player.getItemInBag();

        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items);
        }

        for (Item item : items) {
            if (item.getId() == itemId) {
                if (item.getQuantity() <= number) {
                    items.remove(item);
                } else{
                    item.setQuantity(item.getQuantity() - number);
                    updateItemWeight(item);
                }
            }
        }
    }
    public void gainItems(Player player,int[] itemIds, int[] numbers) {
        for(int i = 0; i < itemIds.length; i++) {
            gainItem(player,itemIds[i], numbers[i]);
        }
    }
    public void removeItems(int[] itemIds, int[] numbers) {
        for(int i = 0; i < itemIds.length; i++) {
            removeItem(itemIds[i], numbers[i]);
        }
    }
    public void gainSkill(Player player, int skillId) {
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
        player.getSkills().add(skillUseCase.newSkill(skillId));
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
    }
    public void gainSkills(Player player, int[] skillIds) {
        for(int skillId : skillIds) {
            gainSkill(player, skillId);
        }
    }
    public void removeSkills(Player players, int[] skillIds) {
        for(int skillId : skillIds) {
            removeSkill(players, skillId);
        }
    }
    public void gainBuff(Player player, int buffId ,int stack) {
        buffUseCase.characterGainBuff(buffId,stack,player,player);
    }
    public void removeBuff(Player player, int buffId , int stack) {
        buffUseCase.characterRemoveBuff(buffId,stack,player);
    }
    public void gainBuffs(Player player, int[] buffIds, int[] stacks) {
        for(int i = 0; i <buffIds.length; i++) {
            gainBuff(player,buffIds[i],stacks[i]);
        }
    }
    public void removeBuffs(Player player, int[] buffIds, int[] stacks) {
        for(int i = 0; i <buffIds.length; i++) {
            removeBuff(player,buffIds[i],stacks[i]);
        }
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
            return new String[4][0];
        }

        ArrayList<Item> itemsToShow = sortItemsByType(player.getItemInBag(), itemType, showAll, isAscend);
        if (itemsToShow.isEmpty()) {
            return new String[4][0];
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

        String[][] itemInfo = new String[4][itemsToShow.size()];
        for (int i = 0; i < itemsToShow.size(); i++) {
            itemInfo[0][i] = String.valueOf(itemsToShow.get(i).getId());
            itemInfo[1][i] = itemsToShow.get(i).getName();
            itemInfo[2][i] = String.valueOf(itemsToShow.get(i).getQuantity());
            itemInfo[3][i] = String.valueOf(itemsToShow.get(i).getRarity());
        }
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

    private int[] determineHarvestItem(int[] min, int[] max){
        int[] items = new int[min.length];
        for(int i = 0; i < min.length; i++) {
            Random random = new Random();
            items[i] = random.nextInt(max[i] - min[i]) + min[i];
        }
        return items;
    }

    private void updateItemWeight(Item item){
        item.setWeight(item.getQuantity() * item.getSingleWeight());
    }

    private void updateWeight(Player player){
        float Weight = 0;
        for (Item item : player.getItemInBag()) {
            Weight += item.getWeight();
        }
        player.setWeight(Weight);
    }

    public void updatePlayer(Player player){
        int currentAttack = player.getAttack();
        int currentDefense = player.getDefense();
        int currentLifeSteal = player.getLifeSteal();
        int currentDamageReduction = player.getDamageReduction();
        ArrayList<Skill> currentSkills = player.getSkills();
        ArrayList<Buff> currentBuffs = player.getBuffs();

        player.setCurrentAttack(currentAttack);
        player.setCurrentDefense(currentDefense);
        player.setCurrentLifeSteal(currentLifeSteal);
        player.setCurrentDamageReduction(currentDamageReduction);
        player.setCurrentSkills(currentSkills);
        player.setCurrentBuffs(currentBuffs);
        updateWeight(player);
    }
}
