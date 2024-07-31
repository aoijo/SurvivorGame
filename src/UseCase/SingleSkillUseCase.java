package UseCase;

import Entity.Buff;
import Entity.Character.Character;

import java.util.Random;

public class SingleSkillUseCase {
    private BuffUseCase buffUseCase;
    private Random random = new Random();
    private String combatLog;

    private String sourceName;
    private String targetName;
    private String skillName;

    public SingleSkillUseCase(BuffUseCase buffUseCase){
        this.buffUseCase = buffUseCase;
    }

    private void dealDamage(float damage, Character source, Character target){
        combatLog += generateDamageCombatLog(damage);
        target.setHealth(target.getHealth() - (int)(damage * (1f - ((float) target.getCurrentDamageReduction() /100))));
        target.setHealth(target.getHealth() + (int) (damage * source.getCurrentLifeSteal()));
    }
    private void restoreHealth(float amount, Character target){
        combatLog += generateRestoreCombatLog(amount);
        target.setHealth(target.getHealth() + (int) amount);
    }
    private void gainBuff(int buffId, int stack, Character source, Character target,float timeRemain, int affectTurns){
        combatLog += generateGainBuffCombatLog(stack,buffId);
        buffUseCase.characterGainBuff(buffId, stack, source, target, timeRemain,affectTurns);
    }
    private void removeBuff(Buff buff, int stack, Character target){
        buffUseCase.characterRemoveBuff(buff, stack, target);
    }

    public void useSingleSkill(Character source, Character target, int skillId, String skillName){
        combatLog = "";

        sourceName = source.getName();
        targetName = target.getName();
        this.skillName = skillName;
        int attack = source.getCurrentAttack();

        switch(skillId){
            case 1:{
                combatLog = sourceName + " waited for one turn";
                break;
            }
            case 2:{
                dealDamage(attack,source,target);
                break;
            }
            case 3:{
                float value = attack * 2;
                restoreHealth(value, target);
                break;
            }
            case 4:{
                restoreHealth(Integer.MAX_VALUE, target);
                combatLog = String.format("%s healed %s to full using %s", sourceName, targetName, skillName);
                break;
            }
            case 5:{
                float value = attack * 1.5f;
                dealDamage(value,source,target);
                break;
            }
            case 6:{
                float value = source.getCurrentAttack() + ((float) source.getCurrentMaxHealth() /10f);
                dealDamage(value, target, source);
                break;
            }
            case 7:{
                dealDamage(source.getCurrentAttack() * 1.5f,source,target);
                if(random.nextFloat() > 0.9){
                    combatLog += " and ";
                    gainBuff(36,1,source,target,0,Integer.MAX_VALUE);
                }
                break;
            }
            case 8:{
                dealDamage(source.getCurrentAttack() * 2f,source,target);
                break;
            }
            case 9:{
                break;
            }
        }
    }
    private String generateDamageCombatLog(float damage) {
        return String.format("%s dealt %d damage to %s with %s", sourceName, (int) damage, targetName, skillName);
    }

    private String generateRestoreCombatLog(float value) {
        return String.format("%s healed %d health to %s with %s", sourceName, (int) value, targetName, skillName);
    }

    private String generateGainBuffCombatLog(int stack, int buffId) {
        String buffName = buffUseCase.getBuffNameById(buffId);
        return String.format("%s applied %d stack of %s to %s with %s", sourceName, stack, buffName, targetName, skillName);
    }


    public String getCombatLog() {
        return combatLog;
    }
}
