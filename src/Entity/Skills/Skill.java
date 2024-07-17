package Entity.Skills;

import Entity.Character.Character;
import Enums.Rarity;

public class Skill {
    private int id;
    private String name;
    private String description;

    private float powerLevel;
    private int cooldown;
    private float worldCooldown;
    private int maxCooldown;
    private int maxWorldCooldown;
    private int Usage;
    private Character source;
    private Character target;

    private boolean isGeneral; // Check if the skill be used outside of combat
    private boolean isCombat; // Check if the skill can be used in combat
    private int[] raceRequirement;
    private Rarity rarity;

    public Skill(int id){
        this.id = id;
    }

    public void activate(){
        System.out.println("Activating Skill " + id);
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

    public float getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(float powerLevel) {
        this.powerLevel = powerLevel;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }

    public boolean isCombat() {
        return isCombat;
    }

    public void setCombat(boolean combat) {
        isCombat = combat;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
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

    public float getWorldCooldown() {
        return worldCooldown;
    }

    public void setWorldCooldown(float worldCooldown) {
        this.worldCooldown = worldCooldown;
    }

    public int getUsage() {
        return Usage;
    }

    public void setUsage(int usage) {
        Usage = usage;
    }

    public int[] getRaceRequirement() {
        return raceRequirement;
    }

    public void setRaceRequirement(int[] raceRequirement) {
        this.raceRequirement = raceRequirement;
    }

    public int getMaxCooldown() {
        return maxCooldown;
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public int getMaxWorldCooldown() {
        return maxWorldCooldown;
    }

    public void setMaxWorldCooldown(int maxWorldCooldown) {
        this.maxWorldCooldown = maxWorldCooldown;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
