package Entity;

import Enums.EnemyType;
import Enums.Rarity;

public class Enemy extends Character{
    private Rarity rarity;
    private int experience;

    private EnemyType[] enemyTypes;
    private int attackTimeRange; // 0 if attack all day, 1 if attack only at day, 2 if attack only at night
    private int attackChance;

    private int attackUncertainty;
    private int defenseUncertainty;
    private Skill[] skillId;

    private boolean isBoss;
    private boolean isCursed;

    private int[] itemDropId;
    private int[] itemDropMin;
    private int[] itemDropMax;

    public Enemy(int id) {
        super(id);
    }

    public Rarity getRarity() {
        return rarity;
    }
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getAttackUncertainty() {
        return attackUncertainty;
    }

    public void setAttackUncertainty(int attackUncertainty) {
        this.attackUncertainty = attackUncertainty;
    }


    public int getDefenseUncertainty() {
        return defenseUncertainty;
    }

    public void setDefenseUncertainty(int defenseUncertainty) {
        this.defenseUncertainty = defenseUncertainty;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public Skill[] getSkillId() {
        return skillId;
    }

    public void setSkillId(Skill[] skillId) {
        this.skillId = skillId;
    }

    public boolean isCursed() {
        return isCursed;
    }

    public void setCursed(boolean cursed) {
        isCursed = cursed;
    }

    public int[] getItemDropMin() {
        return itemDropMin;
    }

    public void setItemDropMin(int[] itemDropMin) {
        this.itemDropMin = itemDropMin;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAttackTimeRange() {
        return attackTimeRange;
    }

    public void setAttackTimeRange(int attackTimeRange) {
        this.attackTimeRange = attackTimeRange;
    }

    public int getAttackChance() {
        return attackChance;
    }

    public void setAttackChance(int attackChance) {
        this.attackChance = attackChance;
    }

    public int[] getItemDropId() {
        return itemDropId;
    }

    public void setItemDropId(int[] itemDropId) {
        this.itemDropId = itemDropId;
    }

    public int[] getItemDropMax() {
        return itemDropMax;
    }

    public void setItemDropMax(int[] itemDropMax) {
        this.itemDropMax = itemDropMax;
    }
}
