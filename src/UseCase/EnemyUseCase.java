package UseCase;

import Entity.Character.Enemy;
import Entity.Skill;
import Enums.EnemyType;
import Enums.Rarity;
import Utils.ReadCSV;

import java.util.ArrayList;
import java.util.Random;

public class EnemyUseCase {
    private String[][] EnemyData;
    private SkillUseCase skillUseCase;
    private Random rand = new Random();

    public EnemyUseCase(SkillUseCase skillUseCase){
        this.skillUseCase = skillUseCase;
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
        enemy.setSkillCooldown(ReadCSV.readIntList(enemyData[17]));
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
        ArrayList<Skill> skills = new ArrayList<>();
        for (int id : enemy.getSkillId()){
            skills.add(skillUseCase.newSkill(id));
        }

        enemy.setSkills(skills);
        enemy.setCurrentSkillCooldown(new int[skills.size()]);
        enemy.setHealth(enemy.getMaxHealth());
        enemy.setCurrentAttack((int) (enemy.getAttack() * (0.4 * rand.nextFloat() - 0.2f)));
        enemy.setCurrentDefense((int) (enemy.getDefense() * (0.4 * rand.nextFloat() - 0.2f)));
        enemy.setCurrentLifeSteal(enemy.getLifeSteal());
        enemy.setCurrentDamageReduction(enemy.getDamageReduction());
        enemy.setBuffs(new ArrayList<>());
    }
}
