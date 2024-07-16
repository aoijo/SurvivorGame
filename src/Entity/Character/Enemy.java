package Entity.Character;

import Enums.EnemyType;
import Enums.Rarity;

public class Enemy extends Character {
    private Rarity rarity;

    private EnemyType[] enemyTypes;
    private int attackTimeRange; // 0 if attack all day, 1 if attack only at day, 2 if attack only at night
    private int attackChance;

    private int[] skillId;
    private float[] skillTriggerHealth;

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

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public int[] getSkillId() {
        return skillId;
    }

    public void setSkillId(int[] skillId) {
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

    public EnemyType[] getEnemyTypes() {
        return enemyTypes;
    }

    public void setEnemyTypes(EnemyType[] enemyTypes) {
        this.enemyTypes = enemyTypes;
    }

    public float[] getSkillTriggerHealth() {
        return skillTriggerHealth;
    }

    public void setSkillTriggerHealth(float[] skillTriggerHealth) {
        this.skillTriggerHealth = skillTriggerHealth;
    }
}
