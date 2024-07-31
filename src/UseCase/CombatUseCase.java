package UseCase;

import Entity.Character.Enemy;
import Entity.Character.Player;
import Entity.Skills.Skill;
import Enums.SkillType;
import UseCase.Character.EnemyUseCase;
import UseCase.Character.PlayerUseCase;
import UseCase.Item.ItemUseCase;
import UseCase.World.TileUseCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CombatUseCase {
    private PlayerUseCase playerUseCase;
    private EnemyUseCase enemyUseCase;
    private SkillUseCase skillUseCase;
    private BuffUseCase buffUseCase;
    private ItemUseCase itemUseCase;
    private TileUseCase tileUseCase;

    private Player player;
    private Enemy enemy;
    private int turnCount;
    private String enemyLootDropString;
    private int enemyEXP;

    private Random random = new Random();

    public CombatUseCase(PlayerUseCase playerUseCase, EnemyUseCase enemyUseCase, SkillUseCase skillUseCase,
                         BuffUseCase buffUseCase, ItemUseCase itemUseCase, TileUseCase tileUseCase) {
        this.playerUseCase = playerUseCase;
        this.enemyUseCase = enemyUseCase;
        this.skillUseCase = skillUseCase;
        this.buffUseCase = buffUseCase;
        this.itemUseCase = itemUseCase;
        this.tileUseCase = tileUseCase;
    }

    public void startCombat(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        this.turnCount = 0;

        triggerCombatStart();
    }

    private void triggerCombatStart() {
        triggerTurnStart();
    }

    private void triggerTurnStart(){
        turnCount ++;

        buffUseCase.buffTurnPass(player);
        skillUseCase.skillPassTurn(player);
        playerUseCase.updatePlayer();

        skillUseCase.skillPassTurn(enemy);
        buffUseCase.buffTurnPass(enemy);
        enemyUseCase.updateEnemy(enemy);
    }

    public void triggerTurnEnd(){
        if (enemy.getHealth() <= 0){
            gainExperience();
            generateLootDrop();
            triggerCombatEnd();
        }else{
            triggerTurnStart();
        }
    }

    public void triggerCombatEnd(){
        player.setInCombat(false);
        tileUseCase.respawnNormalEnemy(player.getPosition());
    }

    public void enemyAttack(){
        Skill enemySkill = enemyChooseSkill();
        SkillType skillType = enemySkill.getSkillType()[0];
        if (skillType == SkillType.DAMAGE || skillType == SkillType.WEAKEN || skillType == SkillType.BUFF_REMOVE){
            skillUseCase.useSkill(enemy, player, enemySkill);
        }else{
            skillUseCase.useSkill(enemy,enemy,enemySkill);
        }
    }
    public Skill enemyChooseSkill() {
        ArrayList<Skill> allSkills = enemy.getCurrentSkills();
        float[] skillPowerLevel = new float[allSkills.size()];

        // Step 1: Collect power levels of combat skills with no cooldown
        for (int i = 0; i < allSkills.size(); i++) {
            Skill skill = allSkills.get(i);
            if (skill.isCombat() && skill.getCooldown() == 0) {
                skillPowerLevel[i] = skill.getPowerLevel();
            } else {
                skillPowerLevel[i] = -1; // Mark non-eligible skills with -1
            }
        }

        // Step 2: Find the maximum power level
        float maxPowerLevel = -1;
        for (float powerLevel : skillPowerLevel) {
            if (powerLevel > maxPowerLevel) {
                maxPowerLevel = powerLevel;
            }
        }

        // Step 3: Collect all skills with the maximum power level
        ArrayList<Skill> possibleSkills = new ArrayList<>();
        for (int i = 0; i < skillPowerLevel.length; i++) {
            if (skillPowerLevel[i] == maxPowerLevel) {
                possibleSkills.add(allSkills.get(i));
            }
        }

        // Step 4: Choose a random skill from the possible skills
        if (!possibleSkills.isEmpty()) {
            Random random = new Random();
            return possibleSkills.get(random.nextInt(possibleSkills.size()));
        }

        return null; // No eligible skill found
    }

    public boolean tryEscape(){
        int playerLevel = player.getLevel();
        int enemyLevel = enemy.getLevel();
        int playerSpeed = player.getSpeed();
        int enemySpeed = enemy.getSpeed();
        float escapeChance = Math.max((float) Math.sqrt((float) (playerLevel-enemyLevel) * (float) (playerLevel-enemyLevel)
                + (float) (playerSpeed-enemySpeed) * (float) (playerSpeed-enemySpeed)) / 2, 0.05f);
        return random.nextFloat() < escapeChance;
    }
    private void generateLootDrop() {
        int[] possibleLootDrop = enemy.getItemDropId();
        int[] maxLootCount = enemy.getItemDropMax();
        int[] minLootCount = enemy.getItemDropMin();

        Map<Integer, Integer> lootDrop = new HashMap<>();

        for (int i = 0; i < possibleLootDrop.length; i++) {
            int dropCount = random.nextInt(maxLootCount[i] - minLootCount[i] + 1) + minLootCount[i];
            if (dropCount > 0) {
                playerUseCase.gainItem(player, possibleLootDrop[i], dropCount);
                lootDrop.put(possibleLootDrop[i], dropCount);
            }
        }
        this.enemyLootDropString = generateLootDropString(lootDrop);
    }

    private String generateLootDropString(Map<Integer, Integer> lootDrop) {
        String lootDropString = "";
        for (Map.Entry<Integer, Integer> entry : lootDrop.entrySet()) {
            lootDropString = String.format("Gained item: %s*%d, ", itemUseCase.getItemNameById(entry.getKey()), entry.getValue());
        }
        lootDropString = lootDropString.substring(0, lootDropString.length() - 2) + "\n";
        return lootDropString;
    }

    private void gainExperience() {
        int levelDifference = player.getLevel() - enemy.getLevel();
        float multiplier = 1 - (levelDifference * 0.05f);

        // Ensure that the multiplier does not go below 0
        multiplier = Math.max(multiplier, 0);

        enemyEXP = (int)(multiplier * enemy.getExperience());
        playerUseCase.changeExperience(player, enemyEXP);
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerUseCase getPlayerUseCase() {
        return playerUseCase;
    }

    public EnemyUseCase getEnemyUseCase() {
        return enemyUseCase;
    }

    public String getEnemyLootDrop() {
        return enemyLootDropString;
    }

    public Skill getSkillByIndex(boolean isPlayer, int skillIndex) {
        if (isPlayer){
            return player.getCurrentSkills().get(skillIndex);
        }else{
            return enemy.getCurrentSkills().get(skillIndex);
        }
    }

    public int getEnemyEXP(){
        return enemyEXP;
    }
    public SkillUseCase getSkillUseCase() {
        return skillUseCase;
    }
}
