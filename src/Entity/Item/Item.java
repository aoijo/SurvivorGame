package Entity.Item;

import Entity.Character.Character;
import Enums.Item.ItemType;
import Enums.RaceType;
import Enums.Rarity;

public class Item {
    private int id;
    private String name;
    private String description;
    private ItemType itemType;

    private int singlePrice;
    private int price;
    private float singleWeight;
    private float weight;
    private int singleDust;
    private int dust;
    private int quantity;
    private Character target;
    private Character source;

    private Rarity rarity;
    private int levelRequirement;

    public Item(int id) {
        this.id = id;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getDust() {
        return dust;
    }

    public void setDust(int dust) {
        this.dust = dust;
    }

    public int getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(int singlePrice) {
        this.singlePrice = singlePrice;
    }

    public float getSingleWeight() {
        return singleWeight;
    }

    public void setSingleWeight(float singleWeight) {
        this.singleWeight = singleWeight;
    }

    public int getSingleDust() {
        return singleDust;
    }

    public void setSingleDust(int singleDust) {
        this.singleDust = singleDust;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public Character getSource() {
        return source;
    }

    public void setSource(Character source) {
        this.source = source;
    }
}
