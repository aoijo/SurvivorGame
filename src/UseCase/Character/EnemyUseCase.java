package UseCase.Character;

import Entity.Buff;
import Entity.Character.Enemy;
import Entity.Character.Player;
import Entity.Skills.Skill;
import Enums.EnemyType;
import Enums.Rarity;
import UseCase.BuffUseCase;
import UseCase.SkillUseCase;
import Utils.ReadCSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EnemyUseCase {
    private String[][] EnemyData;
    private SkillUseCase skillUseCase;
    private BuffUseCase buffUseCase;
    private CharacterStatsCalculation characterStatsCalculation;
    private Random rand = new Random();

    public EnemyUseCase(SkillUseCase skillUseCase, BuffUseCase buffUseCase, CharacterStatsCalculation characterStatsCalculation){
        this.skillUseCase = skillUseCase;
        this.buffUseCase = buffUseCase;
        this.characterStatsCalculation =characterStatsCalculation;
        loadEnemyData();
    }

    public Enemy newEnemy(int enemyId, boolean isBoss, boolean isCursed){
        Enemy enemy = new Enemy(enemyId);
        enemy.setBoss(isBoss);
        enemy.setCursed(isCursed);
        initializeEnemy(enemy, enemyId);
        return enemy;
    }

    private void loadEnemyData(){
        this.EnemyData = ReadCSV.read("Data/Enemy.csv");

    }
    private void initializeEnemy(Enemy enemy, int enemyId){
        String[] enemyData = this.EnemyData[enemyId];

        enemy.setName(enemyData[1]);
        enemy.setDescription(enemyData[2]);
        enemy.setLevel(Integer.parseInt(enemyData[3]));
        enemy.setMaxHealth(Integer.parseInt(enemyData[4]));
        enemy.setAttack(Integer.parseInt(enemyData[5]));
        enemy.setDefense(Integer.parseInt(enemyData[6]));
        enemy.setLifeSteal(Integer.parseInt(enemyData[7]));
        enemy.setSpeed(Integer.parseInt(enemyData[8]));
        enemy.setDamageReduction(Integer.parseInt(enemyData[9]));
        enemy.setRarity(determineRarity(Integer.parseInt(enemyData[10])));
        enemy.setEnemyTypes(determineEnemyTypes(ReadCSV.readIntList(enemyData[11])));
        enemy.setAttackTimeRange(Integer.parseInt(enemyData[12]));
        enemy.setAttackChance(Integer.parseInt(enemyData[13]));
        enemy.setExperience(Integer.parseInt(enemyData[14]));
        enemy.setSkillId(ReadCSV.readIntList(enemyData[15]));
        enemy.setSkillTriggerHealth(ReadCSV.readFloatList(enemyData[16]));
        enemy.setMaxSkillCooldown(ReadCSV.readIntList(enemyData[17]));
        enemy.setItemDropId(ReadCSV.readIntList(enemyData[18]));
        enemy.setItemDropMin(ReadCSV.readIntList(enemyData[19]));
        enemy.setItemDropMax(ReadCSV.readIntList(enemyData[20]));

        initializeStats(enemy);
    }

    private EnemyType[] determineEnemyTypes(int[] enemyTypeId) {
        EnemyType[] enemyTypes = new EnemyType[enemyTypeId.length];
        for (int i = 0; i < enemyTypeId.length; i++) {
            enemyTypes[i] = determineEnemyType(enemyTypeId[i]);
        }
        return enemyTypes;
    }

    private EnemyType determineEnemyType(int typeId) {
        return switch (typeId) {
            case 1 -> EnemyType.HUMAN_LIKE;
            case 2 -> EnemyType.DRAGON;
            case 3 -> EnemyType.BEAST;
            case 4 -> EnemyType.UNDEAD;
            case 5 -> EnemyType.GOLEM;
            case 6 -> EnemyType.MONSTER;
            case 7 -> EnemyType.ELEMENTAL;
            case 8 -> EnemyType.GIANT_CREATURE;
            case 9 -> EnemyType.GIANT;
            case 10 -> EnemyType.CORRUPTED;
            case 11 -> EnemyType.ASCENDED;
            case 12 -> EnemyType.GOD;
            case 13 -> EnemyType.GOBLIN;
            case 14 -> EnemyType.SLIME;
            default -> throw new IllegalArgumentException("Invalid typeId: " + typeId);
        };
    }

    private Rarity determineRarity(int rarityId){
        switch (rarityId){
            case 1: return Rarity.COMMON;
            case 2: return Rarity.UNCOMMON;
            case 3: return Rarity.RARE;
            case 4: return Rarity.LEGENDARY;
            case 5: return Rarity.MYTHICAL;
            default: System.out.println("Invalid rarityId");
        }
        return null;
    }

    private void initializeStats(Enemy enemy){
        enemy.setHealth(enemy.getMaxHealth());
        enemy.setCurrentAttack((int) (enemy.getAttack() * (0.4 * rand.nextFloat() - 0.2f)));
        enemy.setCurrentDefense((int) (enemy.getDefense() * (0.4 * rand.nextFloat() - 0.2f)));
        enemy.setCurrentLifeSteal(enemy.getLifeSteal());
        enemy.setCurrentDamageReduction(enemy.getDamageReduction());
        enemy.setCurrentSpeed(enemy.getSpeed());

        gainSkills(enemy,new int[]{1,2,9},new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE});
        int[] durability = new int[enemy.getSkillId().length];
        Arrays.fill(durability,-1);
        if (enemy.getSkillId().length != 0){
            gainSkills(enemy,enemy.getSkillId(),durability);
        }

        enemy.setCurrentSkills(enemy.getSkills());
        enemy.setCurrentSkillCooldown(new int[enemy.getSkills().size()]);
        enemy.setBuffs(new ArrayList<>());
        enemy.setCurrentBuffs(new ArrayList<>());
    }
    public void gainSkill(Enemy enemy, int skillId, int durability) {
        ArrayList<Skill> skills = enemy.getSkills();
        if (skills == null) {
            skills = new ArrayList<>();
            enemy.setSkills(skills);
        }

        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return; // Skill already exists, no need to add
            }
        }

        // Skill does not exist, add new skill
        enemy.getSkills().add(skillUseCase.newSkill(skillId, durability));
        updateCurrentSkill(enemy);
    }
    public void gainSkills(Enemy enemy, int[] skillIds, int[] durability) {
        if (skillIds.length == 0) {
            return;
        }
        for (int i = 0; i < skillIds.length; i++) {
            gainSkill(enemy, skillIds[i],durability[i]);
        }
    }
    public void gainBuff(Enemy enemy, int buffId ,int stack, float timeRemain, int affectTurns) {

        buffUseCase.characterGainBuff(buffId,stack,enemy,enemy,timeRemain,affectTurns);
        updateCurrentBuff(enemy);
    }
    private void updateCurrentAttack(Enemy enemy){
        enemy.setCurrentAttack(characterStatsCalculation.CalculateAttack());
    }
    private void updateCurrentDefense(Enemy enemy){
        enemy.setCurrentDefense(characterStatsCalculation.CalculateDefense());
    }
    private void updateCurrentLifeSteal(Enemy enemy){
        enemy.setCurrentLifeSteal(characterStatsCalculation.CalculateLifeSteal());
    }
    private void updateCurrentDamageReduction(Enemy enemy){
        enemy.setCurrentDamageReduction(characterStatsCalculation.CalculateDamageReduction());
    }
    private void updateCurrentMaxHealth(Enemy enemy){
        enemy.setCurrentMaxHealth(characterStatsCalculation.CalculateMaxHealth());
        //System.out.println(enemy.getCurrentMaxHealth());
    }
    private void updateCurrentSpeed(Enemy enemy){
        enemy.setCurrentSpeed(characterStatsCalculation.CalculateSpeed());
    }
    private void updateCurrentSkill(Enemy enemy){
        ArrayList<Skill> currentSkills = new ArrayList<>();
        currentSkills.addAll(enemy.getSkills());
        enemy.setCurrentSkills(currentSkills);
    }
    private void updateCurrentBuff(Enemy enemy){
        ArrayList<Buff> currentBuffs = new ArrayList<>();
        currentBuffs.addAll(enemy.getBuffs());
        enemy.setCurrentBuffs(currentBuffs);
    }
    public void updateEnemy(Enemy enemy){
        characterStatsCalculation.setCharacter(enemy);
        updateCurrentAttack(enemy);
        updateCurrentDefense(enemy);
        updateCurrentSpeed(enemy);
        updateCurrentLifeSteal(enemy);
        updateCurrentDamageReduction(enemy);
        updateCurrentMaxHealth(enemy);
        updateCurrentSkill(enemy);
        updateCurrentBuff(enemy);
    }
}
