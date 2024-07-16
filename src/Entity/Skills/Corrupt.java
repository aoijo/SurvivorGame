package Entity.Skills;

import Entity.Skill;

public class Corrupt extends Skill {
    public Corrupt() {super(8);}
    @Override
    public void activate(){
        this.getTarget().setHealth(this.getTarget().getHealth() - (2 * this.getSource().getAttack()));
    }
}
