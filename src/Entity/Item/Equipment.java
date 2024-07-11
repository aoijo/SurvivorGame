package Entity.Item;

import java.util.ArrayList;

import Entity.Buff;
import Entity.Skill;
import Enums.Item.EquipmentType;

public class Equipment extends Item {
    private int attack;
    private int defense;
    private int lifeSteal;
    private int maxHealth;
    private int maxWeight;
    private int damageReduction;

    private int durability;
    private int[] skills;
    private int[] buffsId;
    private int[] buffStack;

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

    public int[] getSkills() {
        return skills;
    }

    public void setSkills(int[] skills) {
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

    public int[] getBuffsId() {
        return buffsId;
    }

    public void setBuffsId(int[] buffsId) {
        this.buffsId = buffsId;
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

    public int[] getBuffStack() {
        return buffStack;
    }

    public void setBuffStack(int[] buffStack) {
        this.buffStack = buffStack;
    }
}
