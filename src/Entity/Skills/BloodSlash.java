package Entity.Skills;

public class BloodSlash extends Skill {
    public BloodSlash() {super(6);}
    @Override
    public void activate(){
        this.getTarget().setHealth(this.getTarget().getHealth() - this.getSource().getAttack()- (int)(this.getSource().getMaxHealth() / 10f));
    }
}
