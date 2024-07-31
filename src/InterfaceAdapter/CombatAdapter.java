package InterfaceAdapter;

import Enums.Rarity;
import Enums.SkillType;
import UseCase.Character.EnemyUseCase;
import UseCase.Character.PlayerUseCase;
import UseCase.CombatUseCase;
import UseCase.SkillUseCase;
import UseCase.World.TileUseCase;

public class CombatAdapter {
    private CombatUseCase combatUseCase;
    private PlayerUseCase playerUseCase;
    private EnemyUseCase enemyUseCase;
    private SkillUseCase skillUseCase;
    private TileUseCase tileUseCase;

    public CombatAdapter(UseCaseManager useCaseManager) {
        this.combatUseCase = useCaseManager.getCombatUseCase();
        this.playerUseCase = useCaseManager.getPlayerUseCase();
        this.enemyUseCase = useCaseManager.getEnemyUseCase();
        this.skillUseCase = useCaseManager.getSkillUseCase();
        this.tileUseCase = useCaseManager.getTileUseCase();
    }

    public void startCombat(String enemyName){
        int[] playerPosition = playerUseCase.getPlayer().getPosition();
        playerUseCase.getPlayer().setInCombat(true);
        combatUseCase.startCombat(playerUseCase.getPlayer(),tileUseCase.getEnemyByName(playerPosition,enemyName));
    }
    public int getTurnCount(){
        return combatUseCase.getTurnCount();
    }
    public int getHealth(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getHealth();
        }else {
            return combatUseCase.getEnemy().getHealth();
        }
    }
    public int getCurrentMaxHealth(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getCurrentMaxHealth();
        }else{
            return combatUseCase.getEnemy().getCurrentMaxHealth();
        }
    }
    public String getName(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getName();
        } else{
            return combatUseCase.getEnemy().getName();
        }
    }
    public int getCurrentAttack(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getCurrentAttack();
        }else{
            return combatUseCase.getEnemy().getCurrentAttack();
        }
    }
    public int getCurrentDefence(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getCurrentDefense();
        }else{
            return combatUseCase.getEnemy().getCurrentDefense();
        }
    }
    public int getCurrentSpeed(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getCurrentSpeed();
        }else{
            return combatUseCase.getEnemy().getCurrentSpeed();
        }
    }
    public int getCurrentLifeSteal(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getCurrentLifeSteal();
        }else{
            return combatUseCase.getEnemy().getCurrentLifeSteal();
        }
    }
    public int getCurrentDamageReduction(boolean isPlayer){
        if(isPlayer){
            return combatUseCase.getPlayer().getCurrentDamageReduction();
        }else{
            return combatUseCase.getEnemy().getCurrentDamageReduction();
        }
    }
    public String[] getCurrentSkillNames(boolean isPlayer){
        int playerSkillSize = combatUseCase.getPlayer().getCurrentSkills().size();
        int enemySkillSize = combatUseCase.getEnemy().getCurrentSkills().size();
        if(isPlayer){
            String[] currentSkillNames = new String[playerSkillSize];
            for(int i=0;i<playerSkillSize;i++){
                currentSkillNames[i] = combatUseCase.getPlayer().getCurrentSkills().get(i).getName();
            }
            return currentSkillNames;
        }else{
            String[] currentSkillNames = new String[enemySkillSize];
            for(int i=0;i<enemySkillSize;i++){
                currentSkillNames[i] = combatUseCase.getEnemy().getCurrentSkills().get(i).getName();
            }
            return currentSkillNames;
        }
    }
    public Rarity[] getCurrentSkillRarity(boolean isPlayer){
        int playerSkillSize = combatUseCase.getPlayer().getCurrentSkills().size();
        int enemySkillSize = combatUseCase.getEnemy().getCurrentSkills().size();

        if(isPlayer){
            Rarity[] currentSkillRarities = new Rarity[playerSkillSize];
            for(int i=0;i<playerSkillSize;i++){
                currentSkillRarities[i] = combatUseCase.getPlayer().getCurrentSkills().get(i).getRarity();
            }
            return currentSkillRarities;
        }else{
            Rarity[] currentSkillRarities = new Rarity[enemySkillSize];
            for(int i=0;i<enemySkillSize;i++){
                currentSkillRarities[i] = combatUseCase.getEnemy().getCurrentSkills().get(i).getRarity();
            }
            return currentSkillRarities;
        }
    }
    public int[] getCurrentSkillCoolDown(boolean isPlayer){
        int playerSkillSize = combatUseCase.getPlayer().getCurrentSkills().size();
        int enemySkillSize = combatUseCase.getEnemy().getCurrentSkills().size();

        if(isPlayer){
            int[] currentSkillCoolDown = new int[playerSkillSize];
            for(int i=0;i<playerSkillSize;i++){
                currentSkillCoolDown[i] = combatUseCase.getPlayer().getCurrentSkills().get(i).getCooldown();
            }
            return currentSkillCoolDown;
        }else{
            int[] currentSkillCoolDown = new int[enemySkillSize];
            for(int i=0;i<enemySkillSize;i++){
                currentSkillCoolDown[i] = combatUseCase.getEnemy().getCurrentSkills().get(i).getCooldown();
            }
            return currentSkillCoolDown;
        }
    }
    public boolean[] checkWorldSkillCoolDown(boolean isPlayer){
        int playerSkillSize = combatUseCase.getPlayer().getCurrentSkills().size();
        int enemySkillSize = combatUseCase.getEnemy().getCurrentSkills().size();

        if(isPlayer){
            boolean[] currentSkillWorldCoolDown = new boolean[playerSkillSize];
            for(int i=0;i<playerSkillSize;i++){
                currentSkillWorldCoolDown[i] = combatUseCase.getPlayer().getCurrentSkills().get(i).getWorldCooldown() == 0;
            }
            return currentSkillWorldCoolDown;
        }else{
            boolean[] currentSkillWorldCoolDown = new boolean[enemySkillSize];
            for(int i=0;i<enemySkillSize;i++){
                currentSkillWorldCoolDown[i] = combatUseCase.getEnemy().getCurrentSkills().get(i).getWorldCooldown() == 0;
            }
            return currentSkillWorldCoolDown;
        }
    }
    public String[] getCurrentBuffNames(boolean isPlayer){
        int playerBuffSize = combatUseCase.getPlayer().getCurrentBuffs().size();
        int enemyBuffSize = combatUseCase.getEnemy().getCurrentBuffs().size();
        if(isPlayer){
            String[] currentBuffNames = new String[playerBuffSize];
            for(int i=0;i<playerBuffSize;i++){
                currentBuffNames[i] = combatUseCase.getPlayer().getCurrentBuffs().get(i).getName();
            }
            return currentBuffNames;
        }else{
            String[] currentBuffNames = new String[enemyBuffSize];
            for(int i=0;i<enemyBuffSize;i++){
                currentBuffNames[i] = combatUseCase.getEnemy().getCurrentBuffs().get(i).getName();
            }
            return currentBuffNames;
        }
    }
    public int[] getCurrentBuffStacks(boolean isPlayer) {
        int playerBuffSize = combatUseCase.getPlayer().getCurrentBuffs().size();
        int enemyBuffSize = combatUseCase.getEnemy().getCurrentBuffs().size();

        if (isPlayer) {
            int[] currentBuffStacks = new int[playerBuffSize];
            for (int i = 0; i < playerBuffSize; i++) {
                currentBuffStacks[i] = combatUseCase.getPlayer().getCurrentBuffs().get(i).getStack();
            }
            return currentBuffStacks;
        } else {
            int[] currentBuffStacks = new int[enemyBuffSize];
            for (int i = 0; i < enemyBuffSize; i++) {
                currentBuffStacks[i] = combatUseCase.getEnemy().getCurrentBuffs().get(i).getStack();
            }
            return currentBuffStacks;
        }
    }
    public SkillType[] getCurrentSkillTypesByIndex(int index) {
        return combatUseCase.getPlayer().getCurrentSkills().get(index).getSkillType();
    }
    public void playerUseSkillByIndex(int index){
        skillUseCase.useSkill(combatUseCase.getPlayer(),combatUseCase.getEnemy(),playerUseCase.getPlayer().getCurrentSkills().get(index));
    }
    public CombatUseCase getCombatUseCase(){
        return combatUseCase;
    }
    public String getCurrentCombatLog(){
        return skillUseCase.getSingleSkillUseCase().getCombatLog();
    }
    public boolean checkInCombat(){
        return combatUseCase.getPlayer().isInCombat();
    }
}
