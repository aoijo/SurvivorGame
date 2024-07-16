package Entity.Character;

import java.awt.Color;
import java.util.ArrayList;

import Entity.Item.Equipment;
import Entity.Item.Item;
import Enums.RaceType;

public class Player extends Character {
    private RaceType race;
    private int raceId;
    private int[] position;
    private Color color;

    private int hunger;
    private int hydration;
    private int sanity;
    private float weight;
    private int maxHunger;
    private int maxHydration;
    private int maxSanity;
    private float maxWeight;
    private int levelUpHealth;
    private int levelUpHunger;
    private int levelUpHydration;
    private int levelUpWeight;
    private int maxExperience;
    private int levelUpPoints;
    private int attributePoint;
    private int money;

    private boolean noHunger;
    private boolean noSanity;
    private boolean noHydration;

    private ArrayList<Item> itemInBag;
    private Equipment[] equipments;

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

    public int getLevelUpPoints() {
        return levelUpPoints;
    }

    public void setLevelUpPoints(int levelUpPoints) {
        this.levelUpPoints = levelUpPoints;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Item> getItemInBag() {
        return itemInBag;
    }

    public void setItemInBag(ArrayList<Item> itemInBag) {
        this.itemInBag = itemInBag;
    }

    public Equipment[] getEquipments() {
        return equipments;
    }

    public void setEquipments(Equipment[] equipments) {
        this.equipments = equipments;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }
}
