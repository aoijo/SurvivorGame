package Entity.Skills;

public class HeavySlam extends Skill {
    public HeavySlam() {super(5);}
    @Override
    public void activate(){
        this.getTarget().setHealth(this.getTarget().getHealth() - (int) (1.5f * this.getSource().getAttack()));
    }
}
