package UseCase;

import Entity.Buff;
import Entity.Character.Character;
import Enums.BuffType;
import Utils.ReadCSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BuffUseCase {

    private String[][] buffData;

    public BuffUseCase() {
        loadBuffData();
    }

    public Buff newBuff(int id, Character source, Character target){
        Buff buff = new Buff(id, source, target);
        initializeBuff(buff, id);
        return buff;
    }

    private void loadBuffData(){
        this.buffData = ReadCSV.read("Data/Buff.csv");
    }

    private void initializeBuff(Buff buff, int buffId){
        String[] buffData = this.buffData[buffId];

        buff.setName(buffData[1]);
        buff.setDescription(buffData[2]);
        buff.setType(determineBuffType(Integer.parseInt(buffData[3])));
        buff.setMaxStack(determineBuffMaxStack(Integer.parseInt(buffData[4])));
        buff.setCombat(determineCombat(Integer.parseInt(buffData[5])));
        buff.setActive(true);
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

    public void gainBuff(int buffId, int stack, Character source, Character target){
        ArrayList<Buff> buffs = target.getBuffs();
        if(buffs == null) {
            buffs = new ArrayList<>();
            target.setBuffs(buffs);
        }
        for (Buff buff : buffs) {
            if(buff.getId() == buffId) {
                long newStack = (long) buff.getStack() + stack;
                if (newStack > buff.getMaxStack()) {
                    buff.setStack(buff.getMaxStack());
                } else {
                    buff.setStack((int) newStack);
                }
                return;
            }
        }
        if (stack == 0){return;}

        Buff newBuff = newBuff(buffId, source, target);
        newBuff.setStack(stack);
        buffs.add(newBuff);
    }

    public void removeBuff(int buffId, int stack, Character target){
        ArrayList<Buff> buffs = target.getBuffs();
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
}
