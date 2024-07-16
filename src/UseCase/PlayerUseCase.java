package UseCase;

import Entity.Item.Item;
import Entity.Character.Player;
import Entity.World.Resource;
import Entity.World.Tile;
import UseCase.Item.ItemUseCase;
import Utils.ReadCSV;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class PlayerUseCase {
    private Player player;
    private String[][] RaceData;
    private ItemUseCase itemUseCase;
    private TileUseCase tileUseCase;
    private TimeUseCase timeUseCase;

    public PlayerUseCase(TileUseCase tileUseCase, ItemUseCase itemUseCase, TimeUseCase timeUseCase) {
        this.tileUseCase = tileUseCase;
        this.itemUseCase = itemUseCase;
        this.timeUseCase = timeUseCase;
        loadRaceData();
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
        player.setAttributePoint(player.getAttributePoint() + player.getLevelUpPoints());
    }

    private void loadRaceData(){
        this.RaceData = ReadCSV.read("Data/Race.csv");
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
        player.setLevelUpPoints(Integer.parseInt(raceData[15]));
        player.setLevel(Integer.parseInt(raceData[16]));
        player.setMaxExperience(Integer.parseInt(raceData[17]));
        gainItems(player,ReadCSV.readIntList(raceData[18]),ReadCSV.readIntList(raceData[19]));
        //bagTest();
    }

    public void gainItem(Player player, int itemId, int number) {
        // Retrieve the player's item bag
        ArrayList<Item> items = player.getItemInBag();

        // Initialize the items list if it is null
        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items); // Make sure the player's bag is updated with the new list
        }
        if (number > 0) {
            // Handle addition or increment of item quantity
            for (Item item : items) {
                if (item.getId() == itemId) {
                    item.setQuantity(item.getQuantity() + number);
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

    public void bagTest() {
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
        System.out.println("Total items: " + items.size());
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
}
