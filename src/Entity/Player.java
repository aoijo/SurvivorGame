package Entity;

import java.awt.Color;
import Enums.Amulet;
import Enums.Armor;
import Enums.Weapon;
import Enums.Race;

public class Player {
    private String name;
    private Race race;
    private int[] position;
    private Color color;

    private int health;
    private int hunger;
    private int hydration;
    private int sanity;
    private int weight;
    private int maxHealth;
    private int maxHunger;
    private int maxHydration;
    private int maxSanity;
    private int maxWeight;
    private int levelUpHealth;
    private int levelUpHunger;
    private int levelUpHydration;
    private int levelUpWeight;

    private int level;
    private int experience;
    private int maxExperience;
    private int levelUpPoints;
    private int attributePoint;

    private int baseAttack;
    private int baseDefense;

    private int lifeSteal;
    private int damageReduction;
    private boolean noHunger;
    private boolean noSanity;
    private boolean noHydration;

    public Player(String name, Color color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;
    }
    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public int getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(int lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
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

}
