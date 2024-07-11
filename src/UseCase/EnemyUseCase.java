package UseCase;

import Entity.Enemy;
import Utils.ReadCSV;

public class EnemyUseCase {
    private String[][] EnemyData;

    public EnemyUseCase(){
        loadEnemyData();
    }

    public Enemy newEnemy(int enemyId, boolean isBoss){
        Enemy enemy = new Enemy(enemyId);
        enemy.setBoss(isBoss);
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
        enemy.setDamageReduction(Integer.parseInt(enemyData[8]));
        enemy.setAttackTimeRange(Integer.parseInt(enemyData[9]));
        enemy.setAttackChance(Integer.parseInt(enemyData[10]));
        enemy.setExperience(Integer.parseInt(enemyData[11]));
        enemy.setItemDropId(ReadCSV.readIntList(enemyData[12]));
        enemy.setItemDropMin(ReadCSV.readIntList(enemyData[13]));
        enemy.setItemDropMax(ReadCSV.readIntList(enemyData[14]));
    }
}
