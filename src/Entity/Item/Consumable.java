package Entity.Item;

public class Consumable extends Item {

    private boolean isUsable; // If the item is usable in the world
    private boolean isCombatItem; //If the item is usable in combat

    private int maxInBag; // Maximum of this item in player's bag

    public Consumable(int id) {
        super(id);
    }

    public int getMaxInBag() {
        return maxInBag;
    }

    public void setMaxInBag(int maxInBag) {
        this.maxInBag = maxInBag;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

    public boolean isCombatItem() {
        return isCombatItem;
    }

    public void setCombatItem(boolean combatItem) {
        isCombatItem = combatItem;
    }
}
