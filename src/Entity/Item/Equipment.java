package Entity.Item;

import Entity.Buff;
import Entity.Skills.Skill;
import Enums.Item.EquipmentType;

import java.util.ArrayList;

public class Equipment extends Item {
    private int attack;
    private int defense;
    private int lifeSteal;
    private int maxHealth;
    private int maxWeight;
    private int speed;
    private int damageReduction;

    private int durability;
    private int currentMaxDurability;
    private int maxDurability;
    private ArrayList<Skill> skills;
    private ArrayList<Buff> buffs;
    private boolean isEquipped;

    private EquipmentType equipmentType;

    public Equipment(int id) {
        super(id);
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

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public void setBuffs(ArrayList<Buff> buffsId) {
        this.buffs = buffsId;
    }

    public int getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(int lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
    }

    public int getCurrentMaxDurability() {
        return currentMaxDurability;
    }

    public void setCurrentMaxDurability(int currentMaxDurability) {
        this.currentMaxDurability = currentMaxDurability;
    }

    public Boolean getEquipped() {
        return isEquipped;
    }

    public void setEquipped(Boolean equipped) {
        isEquipped = equipped;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
