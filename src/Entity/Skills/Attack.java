package Entity.Skills;

public class Attack extends Skill {

    public Attack() {
        super(2);
    }
    @Override
    public void activate(){
        this.getTarget().setHealth(this.getTarget().getHealth() - this.getSource().getAttack());
    }
}

