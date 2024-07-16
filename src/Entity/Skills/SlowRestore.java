package Entity.Skills;

import Entity.Skill;

public class SlowRestore extends Skill {
    public SlowRestore() { super(4);}
    @Override
    public void activate(){
        this.getTarget().setHealth(this.getTarget().getMaxHealth());
    }
}
