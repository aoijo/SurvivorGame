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

    private int health = 0;
    private int hunger = 0;
    private int hydration = 0;
    private int sanity = 0;
    private int weight = 0;
    private int maxHealth = 0;
    private int maxHunger = 0;
    private int maxHydration = 0;
    private int maxSanity = 0;
    private int maxWeight = 0;
    private int levelUpHealth = 0;
    private int levelUpHunger = 0;
    private int levelUpHydration = 0;
    private int levelUpWeight = 0;

    private int level = 1;
    private int experience = 0;
    private int maxExperience = 10;
    private int levelUpPoints = 0;
    private int attributePoint = 0;

    private int baseAttack = 0;
    private int baseDefense = 0;
    private int attack = 0;
    private int defense = 0;

    private int lifeSteal = 0;
    private int damageReduction = 0;
    private boolean noHunger = false;
    private boolean noSanity = false;
    private boolean noHydration = false;

    private Armor armor = null;
    private Weapon weapon = null;
    private Amulet[] amulet = new Amulet[4];

    public Player(String name, int[] position, Color color, Race race) {
        this.name = name;
        this.position = position;
        this.color = color;
        this.race = race;
        Initialize(race);
        this.health = this.maxHealth;
        this.hunger = this.maxHunger;
        this.hydration = this.maxHydration;
        this.sanity = this.maxSanity;
        this.attack = this.baseAttack;
        this.defense = this.baseDefense;
    }

    private void Initialize(Race race) {
        switch (race) {
            case HUMAN:
                maxHealth = 200;
                maxHunger = 100;
                maxHydration = 100;
                maxSanity = 100;
                maxWeight = 100;
                baseAttack = 3;
                baseDefense = 0;
                levelUpHealth = 10;
                levelUpHunger = 2;
                levelUpHydration = 2;
                levelUpWeight = 5;
                levelUpPoints = 1;
                break;
            case SLIME:
                maxHealth = 250;
                maxHunger = 120;
                maxHydration = 120;
                maxSanity = 100;
                maxWeight = 20;
                baseAttack = 2;
                baseDefense = 0;
                levelUpHealth = 10;
                levelUpHunger = 2;
                levelUpHydration = 2;
                levelUpWeight = 5;
                levelUpPoints = 1;
                break;
        }
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
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

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Amulet[] getAmulet() {
        return amulet;
    }

    public void setAmulet(Amulet[] amulet) {
        this.amulet = amulet;
    }

    public void move(int dx, int dy) {
        this.position[0] += dx;
        this.position[1] += dy;
    }

    public void healthChange(int dh) {
        this.health += dh;
    }

    public void hungerChange(int dh) {
        this.hunger += dh;
    }

    public void hydrationChange(int dh) {
        this.hydration += dh;
    }

    public void sanityChange(int ds) {
        this.sanity += ds;
    }

    public void experienceChange(int de) {
        this.experience += de;
        if (this.experience >= this.maxExperience){
            levelUp();
        }
    }

    public void baseAttackChange(int da){
        this.baseAttack += da;
    }
    public void baseDefenseChange(int dd){
        this.baseDefense += dd;
    }

    public void levelUp() {
        this.health += this.levelUpHealth;
        this.maxHealth += this.levelUpHealth;
        this.hunger += this.levelUpHunger;
        this.maxHunger += this.levelUpHunger;
        this.hydration += this.levelUpHydration;
        this.maxHydration += this.levelUpHydration;

        this.experience -= this.maxExperience;
        this.level += 1;
        this.maxExperience = 10 * this.level * this.level;
        this.attributePoint += this.levelUpPoints;
    }

}
