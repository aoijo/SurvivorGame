package Entity.Skills;

public class Restore extends Skill {
    public Restore() { super(3);}
    @Override
    public void activate(){
        int newHealth = this.getTarget().getHealth() + 2 * this.getSource().getAttack();
        if (newHealth > this.getTarget().getMaxHealth()){
            newHealth = this.getTarget().getMaxHealth();
        }
        this.getTarget().setHealth(newHealth);
    }
}
