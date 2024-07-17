package Entity.Skills;

public class SlowRestore extends Skill {
    public SlowRestore() { super(4);}
    @Override
    public void activate(){
        this.getTarget().setHealth(this.getTarget().getMaxHealth());
    }
}
