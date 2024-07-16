package UseCase;

import Entity.Character.Enemy;
import Entity.Character.Player;

public class CombatUseCase {
    private Player player;
    private Enemy enemy;
    private int turnCount;

    public CombatUseCase(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void startCombat(){
        TriggerCombatStart();
        while(player.getHealth() > 0 && enemy.getHealth() > 0){
            newTurn();
        }
        TriggerCombatEnd();
    }

    public void newTurn(){
        turnCount ++;
        TriggerTurnStart();
        PlayerAttack();
        EnemyAttack();
        TriggerTurnEnd();
    }

    private void TriggerCombatStart() {

    }

    private void TriggerTurnStart(){

    }

    private void TriggerTurnEnd(){

    }

    private void TriggerCombatEnd(){

    }

    private void PlayerAttack(){

    }

    private void EnemyAttack(){

    }



}
