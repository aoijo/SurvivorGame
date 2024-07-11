package UseCase;

import Entity.Buff;
import Entity.Character;
import Enums.BuffType;
import Utils.ReadCSV;

import java.util.ArrayList;

public class BuffUseCase {

    private String[][] buffData;

    public BuffUseCase() {}

    public Buff newBuff(int id, Character source, Character target){
        Buff buff = new Buff(id, source, target);
        initializeBuff(buff);
        return buff;
    }

    private void loadBuffData(){
        this.buffData = ReadCSV.read("Data/Buff.csv");
    }

    private void initializeBuff(Buff buff){
        int buffId = buff.getId();
        String[] buffData = this.buffData[buffId];
        buff.setName(buffData[1]);
        buff.setDescription(buffData[2]);
        buff.setType(determinBuffType(Integer.parseInt(buffData[3])));
        buff.setMaxStack(Integer.parseInt(buffData[4]));
        buff.setCombat(determineCombat(Integer.parseInt(buffData[5])));
        buff.setActive(true);
    }

    private BuffType determinBuffType(int TypeId){
        switch(TypeId){
            case 1->{return BuffType.POSITIVE;}
            case 2->{return BuffType.NEGATIVE;}
            case 3->{return BuffType.NEUTRAL;}
            case 4->{return BuffType.ENVIRONMENTAL;}
            case 5->{return BuffType.AURA;}
        }
        return null;
    }
    private Boolean determineCombat(int isCombat){
        return isCombat > 0;
    }

    private void gainBuff(int buffId, int stack, Character source, Character target){
        ArrayList<Buff> buffs = target.getBuffs();

        if(buffs == null) {
            buffs = new ArrayList<>();
            target.setBuffs(buffs);
        }
        for (Buff buff : buffs) {
            if(buff.getId() == buffId) {
                buff.setStack(Math.min(buff.getMaxStack(),buff.getStack() + stack));
                return;
            }
        }
        Buff newBuff = new Buff(buffId, source, target);
        newBuff.setStack(stack);
        buffs.add(newBuff);
    }
}
