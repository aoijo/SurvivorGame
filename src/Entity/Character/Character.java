package Entity.Character;

import Entity.Buff;
import Entity.Skills.Skill;

import java.util.ArrayList;

public class Character {
    private int id;
    private String name;
    private String description;

    private int level;
    private int health;
    private int currentHealth;
    private int maxHealth;
    private int currentMaxHealth;
    private int attack;
    private int currentAttack;
    private int defense;
    private int currentDefense;
    private int lifeSteal;
    private int currentLifeSteal;
    private int damageReduction;
    private int currentDamageReduction;
    private int speed;
    private int currentSpeed;
    private int experience;

    private ArrayList<Skill> skills;
    private ArrayList<Skill> currentSkills;
    private int[] skillCooldown;
    private int[] currentSkillCooldown;

    private ArrayList<Buff>  Buffs;
    private ArrayList<Buff>  currentBuffs;

    public Character(int id) {
        this.id = id;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentAttack() {
        return currentAttack;
    }

    public void setCurrentAttack(int currentAttack) {
        this.currentAttack = currentAttack;
    }

    public int getCurrentDefense() {
        return currentDefense;
    }

    public void setCurrentDefense(int currentDefense) {
        this.currentDefense = currentDefense;
    }

    public int getCurrentLifeSteal() {
        return currentLifeSteal;
    }

    public void setCurrentLifeSteal(int currentLifeSteal) {
        this.currentLifeSteal = currentLifeSteal;
    }

    public int getCurrentDamageReduction() {
        return currentDamageReduction;
    }

    public void setCurrentDamageReduction(int currentDamageReduction) {
        this.currentDamageReduction = currentDamageReduction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public ArrayList<Buff> getBuffs() {
        return Buffs;
    }

    public void setBuffs(ArrayList<Buff> buffs) {
        Buffs = buffs;
    }

    public int[] getSkillCooldown() {
        return skillCooldown;
    }

    public void setSkillCooldown(int[] skillCooldown) {
        this.skillCooldown = skillCooldown;
    }

    public int[] getCurrentSkillCooldown() {
        return currentSkillCooldown;
    }

    public void setCurrentSkillCooldown(int[] currentSkillCooldown) {
        this.currentSkillCooldown = currentSkillCooldown;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public ArrayList<Skill> getCurrentSkills() {
        return currentSkills;
    }

    public void setCurrentSkills(ArrayList<Skill> currentSkills) {
        this.currentSkills = currentSkills;
    }

    public ArrayList<Buff> getCurrentBuffs() {
        return currentBuffs;
    }

    public void setCurrentBuffs(ArrayList<Buff> currentBuffs) {
        this.currentBuffs = currentBuffs;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getCurrentMaxHealth() {
        return currentMaxHealth;
    }

    public void setCurrentMaxHealth(int currentMaxHealth) {
        this.currentMaxHealth = currentMaxHealth;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}
