package UseCase.Players;

import Entity.Item.Item;
import Entity.Player;
import UseCase.Item.ItemUseCase;
import Utils.ReadCSV;

import java.awt.*;
import java.util.ArrayList;


public class PlayerUseCase {
    private Player player;
    private String[][] RaceData;
    private ItemUseCase itemUseCase;

    public PlayerUseCase(Player player) {
        this.player = player;
    }

    public PlayerUseCase(String name, Color color, int raceId, ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;

        if (name == null || color == null) {
            throw new IllegalArgumentException("None of the parameters can be null");
        } else {
            this.player = new Player(color, raceId);
        }
        loadRaceData();
        initialize(player, raceId);
        this.player.setName(name);
        this.player.setHealth(this.player.getMaxHealth());
        this.player.setHunger(this.player.getMaxHunger());
        this.player.setHydration(this.player.getMaxHydration());
        this.player.setSanity(this.player.getMaxSanity());
        this.player.setPosition(new int[]{0,0});
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
        if (experience >= player.getMaxExperience()) {
            levelUp();
        } else if (experience < 0) {
            experience = 0;
        }
        player.setExperience(experience);
    }

    public void increaseAttack(int da) {
        player.setAttack(player.getAttack() + da);
    }

    public void increaseDefense(int dd) {
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
        gainItems(ReadCSV.readIntList(raceData[18]),ReadCSV.readIntList(raceData[19]));
        //bagTest();
    }

    public void gainItem(int itemId, int number) {
        // Retrieve the player's item bag
        ArrayList<Item> items = player.getItemInBag();

        // Initialize the items list if it is null
        if (items == null) {
            items = new ArrayList<>();
            player.setItemInBag(items); // Make sure the player's bag is updated with the new list
        }

        if (number < 0) {
            // Handle removal or decrement of item quantity
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                if (item.getId() == itemId) {
                    if (item.getQuantity() <= -1 * number) {
                        items.remove(i);
                    } else {
                        item.setQuantity(item.getQuantity() + number);
                    }
                    return; // Exit after processing the item
                }
            }
        } else if (number > 0) {
            // Handle addition or increment of item quantity
            for (Item item : items) {
                if (item.getId() == itemId) {
                    item.setQuantity(item.getQuantity() + number);
                    return; // Exit after processing the item
                }
            }
            // If the item does not exist in the bag, add a new one
            Item newItem = itemUseCase.newItem(itemId);
            newItem.setQuantity(number);
            items.add(newItem);
        }
    }

    public void gainItems(int[] itemIds, int[] numbers) {
        for(int i = 0; i < itemIds.length; i++) {
            gainItem(itemIds[i], numbers[i]);
        }
    }

    private void bagTest() {
        ArrayList<Item> items = player.getItemInBag();

        // Display the item count and details for debugging
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                System.out.println("ID: " + item.getId() + ", Name: " + item.getName() + ", Description: " + item.getDescription() +
                        ", Quantity: " + item.getQuantity());
            }
        } else {
            System.out.println("No items in the bag.");
        }
    }
}
