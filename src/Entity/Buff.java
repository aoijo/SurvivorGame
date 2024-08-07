package Entity;

import Entity.Character.Character;
import Entity.Item.Equipment;
import Enums.BuffType;

public class Buff {
    private int id;
    private String name;
    private String description;

    private BuffType type;
    private Character source;
    private Character target;
    private int stack;
    private int maxStack;
    private float timeRemain;
    private int turnRemain;

    private boolean isCombat; // Check if the buff works in combat
    private boolean isActive;

    public Buff(int id, Character source, Character target){
        this.id = id;
    }
    public Buff(int id, Equipment source){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BuffType getType() {
        return type;
    }

    public void setType(BuffType type) {
        this.type = type;
    }

    public Character getSource() {
        return source;
    }

    public void setSource(Character source) {
        this.source = source;
    }

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public boolean isCombat() {
        return isCombat;
    }

    public void setCombat(boolean combat) {
        isCombat = combat;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public float getTimeRemain() {
        return timeRemain;
    }

    public void setTimeRemain(float timeRemain) {
        this.timeRemain = timeRemain;
    }

    public int getTurnRemain() {
        return turnRemain;
    }

    public void setTurnRemain(int turnRemain) {
        this.turnRemain = turnRemain;
    }
}
