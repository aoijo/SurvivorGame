package Entity.Character;

import java.awt.Color;
import java.util.ArrayList;

import Entity.Item.Equipment;
import Entity.Item.Item;
import Enums.MapTile.MapType;
import Enums.RaceType;

public class Player extends Character {
    private RaceType race;
    private int raceId;
    private int[] position;
    private MapType currentMap;
    private Color color;

    private int hunger;
    private int hydration;
    private int sanity;
    private float weight;
    private int maxHunger;
    private int currentMaxHunger;
    private int maxHydration;
    private int currentMaxHydration;
    private int maxSanity;
    private int currentMaxSanity;
    private float maxWeight;
    private float currentMaxWeight;

    private int levelUpHealth;
    private int levelUpHunger;
    private int levelUpHydration;
    private int levelUpWeight;

    private int maxExperience;
    private int ForgeExperience;
    private int levelUpAttributePoints;
    private int attributePoint;
    private int currency;

    private boolean noHunger;
    private boolean noSanity;
    private boolean noHydration;

    private ArrayList<Item> itemInBag;
    private Equipment weapon;
    private Equipment armor;
    private Equipment tool;
    private Equipment bag;
    private Equipment[] Amulet;

    private boolean inCombat;

    public Player(Color color, int raceId) {
        super(1);
        this.color = color;
        this.raceId = raceId;
    }
    // Getters and Setters

    public RaceType getRace() {
        return race;
    }

    public void setRace(RaceType race) {
        this.race = race;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public int getSanity() {
        return sanity;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public int getMaxHydration() {
        return maxHydration;
    }

    public void setMaxHydration(int maxHydration) {
        this.maxHydration = maxHydration;
    }

    public int getMaxSanity() {
        return maxSanity;
    }

    public void setMaxSanity(int maxSanity) {
        this.maxSanity = maxSanity;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getLevelUpHealth() {
        return levelUpHealth;
    }

    public void setLevelUpHealth(int levelUpHealth) {
        this.levelUpHealth = levelUpHealth;
    }

    public int getLevelUpHunger() {
        return levelUpHunger;
    }

    public void setLevelUpHunger(int levelUpHunger) {
        this.levelUpHunger = levelUpHunger;
    }

    public int getLevelUpHydration() {
        return levelUpHydration;
    }

    public void setLevelUpHydration(int levelUpHydration) {
        this.levelUpHydration = levelUpHydration;
    }

    public int getLevelUpWeight() {
        return levelUpWeight;
    }

    public void setLevelUpWeight(int levelUpWeight) {
        this.levelUpWeight = levelUpWeight;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(int experience) {
        this.maxExperience = experience;
    }

    public int getLevelUpAttributePoints() {
        return levelUpAttributePoints;
    }

    public void setLevelUpAttributePoints(int levelUpAttributePoints) {
        this.levelUpAttributePoints = levelUpAttributePoints;
    }

    public int getAttributePoint() {
        return attributePoint;
    }

    public void setAttributePoint(int attributePoint) {
        this.attributePoint = attributePoint;
    }

    public boolean isNoHunger() {
        return noHunger;
    }

    public void setNoHunger(boolean noHunger) {
        this.noHunger = noHunger;
    }

    public boolean isNoSanity() {
        return noSanity;
    }

    public void setNoSanity(boolean noSanity) {
        this.noSanity = noSanity;
    }

    public boolean isNoHydration() {
        return noHydration;
    }

    public void setNoHydration(boolean noHydration) {
        this.noHydration = noHydration;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public ArrayList<Item> getItemInBag() {
        return itemInBag;
    }

    public void setItemInBag(ArrayList<Item> itemInBag) {
        this.itemInBag = itemInBag;
    }

    public Equipment[] getAmulet() {
        return Amulet;
    }

    public void setAmulet(Equipment[] equipments) {
        this.Amulet = equipments;
    }
    public void setSingleAmulet(Equipment equipment, int index) {
        this.Amulet[index] = equipment;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getForgeExperience() {
        return ForgeExperience;
    }

    public void setForgeExperience(int forgetExperience) {
        ForgeExperience = forgetExperience;
    }

    public Equipment getWeapon() {
        return weapon;
    }

    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }

    public Equipment getArmor() {
        return armor;
    }

    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    public Equipment getTool() {
        return tool;
    }

    public void setTool(Equipment tool) {
        this.tool = tool;
    }

    public Equipment getBag() {
        return bag;
    }

    public void setBag(Equipment bag) {
        this.bag = bag;
    }

    public boolean isInCombat() {
        return inCombat;
    }

    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    public int getCurrentMaxHunger() {
        return currentMaxHunger;
    }

    public void setCurrentMaxHunger(int currentMaxHunger) {
        this.currentMaxHunger = currentMaxHunger;
    }

    public int getCurrentMaxHydration() {
        return currentMaxHydration;
    }

    public void setCurrentMaxHydration(int currentMaxHydration) {
        this.currentMaxHydration = currentMaxHydration;
    }

    public int getCurrentMaxSanity() {
        return currentMaxSanity;
    }

    public void setCurrentMaxSanity(int currentMaxSanity) {
        this.currentMaxSanity = currentMaxSanity;
    }

    public float getCurrentMaxWeight() {
        return currentMaxWeight;
    }

    public void setCurrentMaxWeight(float currentMaxWeight) {
        this.currentMaxWeight = currentMaxWeight;
    }

    public MapType getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(MapType currentMap) {
        this.currentMap = currentMap;
    }
}
