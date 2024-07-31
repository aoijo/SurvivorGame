package UseCase;

import Entity.Buff;
import Entity.Character.Character;
import Entity.Item.Equipment;
import Entity.Item.Item;
import Enums.BuffType;
import Utils.ReadCSV;

import java.util.ArrayList;
import java.util.Iterator;

public class BuffUseCase {

    private String[][] buffData;

    public BuffUseCase() {
        loadBuffData();
    }

    public Buff newBuff(int id, Character source, Character target, float timeRemain,int affectTurns){
        Buff buff = new Buff(id, source, target);
        initializeBuff(buff, id, timeRemain, affectTurns);
        return buff;
    }
    public Buff newBuff(int id, Equipment source, float timeRemain,int affectTurns){
        Buff buff = new Buff(id, source);
        initializeBuff(buff, id, timeRemain, affectTurns);
        return buff;
    }

    private void loadBuffData(){
        this.buffData = ReadCSV.read("Data/Buff.csv");
    }

    private void initializeBuff(Buff buff, int buffId, float timeRemain, int affectTurns){
        String[] buffData = this.buffData[buffId];

        buff.setName(buffData[1]);
        buff.setDescription(buffData[2]);
        buff.setType(determineBuffType(Integer.parseInt(buffData[3])));
        buff.setMaxStack(determineBuffMaxStack(Integer.parseInt(buffData[4])));
        buff.setCombat(determineCombat(Integer.parseInt(buffData[5])));
        buff.setActive(true);
        buff.setTimeRemain(timeRemain * 24 * 60);
        buff.setTurnRemain(affectTurns);
    }

    private BuffType determineBuffType(int TypeId){
        switch(TypeId){
            case 1: return BuffType.POSITIVE;
            case 2: return BuffType.NEGATIVE;
            case 3: return BuffType.NEUTRAL;
            case 4: return BuffType.ENVIRONMENTAL;
            case 5: return BuffType.AURA;
            default: return null;
        }
    }
    private boolean determineCombat(int isCombat){
        return isCombat > 0;
    }
    private int determineBuffMaxStack(int maxStack){
        if(maxStack > 0) return maxStack;
        else return Integer.MAX_VALUE;
    }
    public void characterGainBuff(int buffId, int stack, Character source, Character target,float timeRemain, int affectTurns){
        ArrayList<Buff> buffs = target.getBuffs();
        if(buffs == null) {
            buffs = new ArrayList<>();
            target.setBuffs(buffs);
        }
        for (Buff buff : buffs) {
            if(buff.getId() == buffId && buff.getTimeRemain() == timeRemain && buff.getTurnRemain() == affectTurns) {
                int newStack = buff.getStack() + stack;
                if (newStack > buff.getMaxStack()) {
                    buff.setStack(buff.getMaxStack());
                } else {
                    buff.setStack(newStack);
                }
                return;
            }
        }
        if (stack == 0){return;}

        Buff newBuff = newBuff(buffId, source, target, timeRemain,affectTurns);
        newBuff.setStack(stack);
        buffs.add(newBuff);
    }
    public void characterRemoveBuff(Buff buff, int stack, Character target){
        ArrayList<Buff> buffs = target.getBuffs();
        if (buffs == null) {
            return;
        }
        Iterator<Buff> iterator = buffs.iterator();
        while(iterator.hasNext()) {
            Buff newBuff = iterator.next();
            if(newBuff == buff) {
                if (newBuff.getStack() > stack) {
                    newBuff.setStack(newBuff.getStack() - stack);
                } else {
                    iterator.remove();
                }
                break;
            }
        }
    }
    public void checkCharacterBuffTimeUp(Character character){
        ArrayList<Buff> buffToRemove = new ArrayList<>();
        for (Buff buff : character.getBuffs()) {
            if(buff.getTimeRemain() <= 0){
                buffToRemove.add(buff);
            }
        }
        for (Buff buff : buffToRemove) {
            character.getBuffs().remove(buff);
        }
    }
    public void checkCharacterBuffTurnRemain(Character character){
        ArrayList<Buff> buffToRemove = new ArrayList<>();
        for (Buff buff : character.getBuffs()) {
            if(buff.getTurnRemain() <= 0){
                buffToRemove.add(buff);
            }
        }
        for (Buff buff : buffToRemove) {
            character.getBuffs().remove(buff);
        }
    }
    public void buffTurnPass(Character character){
        for (Buff buff : character.getBuffs()) {
            if(buff.getTurnRemain() != Integer.MAX_VALUE){
                buff.setTurnRemain(buff.getTurnRemain() - 1);
            }
        }
        checkCharacterBuffTurnRemain(character);
    }
    public void buffTimePass(Character character, int minute){
        for (Buff buff : character.getBuffs()) {
            buff.setTimeRemain(buff.getTimeRemain() - minute);
        }
        checkCharacterBuffTimeUp(character);
    }
    public void equipmentGainBuff(int buffId, int stack, Equipment source,float timeRemain,int affectTurns){
        ArrayList<Buff> buffs = source.getBuffs();
        if(buffs == null) {
            buffs = new ArrayList<>();
            source.setBuffs(buffs);
        }

        for (Buff buff : buffs) {
            if(buff.getId() == buffId && buff.getTimeRemain() == timeRemain && buff.getTurnRemain() == affectTurns) {
                int newStack = buff.getStack() + stack;
                if (newStack > buff.getMaxStack()) {
                    buff.setStack(buff.getMaxStack());
                } else {
                    buff.setStack(newStack);
                }
                return;
            }
        }
        if (stack == 0){return;}

        Buff newBuff = newBuff(buffId, source,timeRemain,affectTurns);
        newBuff.setStack(stack);
        buffs.add(newBuff);
    }
    public void equipmentRemoveBuff(int buffId, int stack, Equipment source){
        ArrayList<Buff> buffs = source.getBuffs();
        if (buffs == null) {
            return;
        }
        Iterator<Buff> iterator = buffs.iterator();
        while(iterator.hasNext()) {
            Buff buff = iterator.next();
            if(buff.getId() == buffId) {
                if (buff.getStack() > stack) {
                    buff.setStack(buff.getStack() - stack);
                } else {
                    iterator.remove();
                }
                break;
            }
        }
    }
    public String getBuffNameById(int buffId){
        return buffData[buffId][1];
    }
}
