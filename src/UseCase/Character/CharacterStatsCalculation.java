package UseCase.Character;

import Entity.Buff;
import Entity.Character.Character;
import Entity.Character.Player;
import Entity.Item.Equipment;

import java.util.ArrayList;
import java.util.Collections;

public class CharacterStatsCalculation {
    private Character character;
    private ArrayList<Equipment> equipments;

    public CharacterStatsCalculation() {}

    public int CalculateAttack() {
        int baseAttack = character.getAttack();
        if (character instanceof Player) {
            int equipmentAttack = statFromEquipment("attack");
            baseAttack += equipmentAttack;
        }
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseAttack;
        }

        int attackUp = 0;
        int attackDown = 0;
        int attackIncrease = 0;
        int attackDecrease = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 9) {
                attackUp += buff.getStack();
            }
            if (buff.getId() == 10) {
                attackDown += buff.getStack();
            }
            if (buff.getId() == 28) {
                attackIncrease += buff.getStack();
            }
            if (buff.getId() == 29) {
                attackDecrease += buff.getStack();
            }
        }
        int additionLayer = attackIncrease - attackDecrease;
        float multiplyLayer = attackUp - attackDown;
        int currentAttack = (int)((baseAttack + additionLayer) * (1 + multiplyLayer/100));
        return Math.max(1,currentAttack);
    }

    public int CalculateDefense() {
        int baseDefense = character.getDefense();
        if (character instanceof Player) {
            int equipmentDefense = statFromEquipment("defense");
            baseDefense += equipmentDefense;
        }
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseDefense;
        }

        int defenseUp = 0;
        int defenseDown = 0;
        int defenseIncrease = 0;
        int defenseDecrease = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 11) { // Assuming buff ID 11 is for defense up
                defenseUp += buff.getStack();
            }
            if (buff.getId() == 12) { // Assuming buff ID 12 is for defense down
                defenseDown += buff.getStack();
            }
            if (buff.getId() == 30) { // Assuming buff ID 30 is for defense increase
                defenseIncrease += buff.getStack();
            }
            if (buff.getId() == 31) { // Assuming buff ID 31 is for defense decrease
                defenseDecrease += buff.getStack();
            }
        }
        int additionLayer = defenseIncrease - defenseDecrease;
        float multiplyLayer = defenseUp - defenseDown;
        int currentDefense = (int)((baseDefense + additionLayer) * (1 + multiplyLayer/100));
        return Math.max(1,currentDefense);
    }
    public int CalculateHunger() {
        int baseHunger = ((Player) character).getHunger();
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseHunger;
        }

        int hungerUp = 0;
        int hungerDown = 0;
        int hungerIncrease = 0;
        int hungerDecrease = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 5) { // Assuming buff ID 11 is for defense up
                hungerUp += buff.getStack();
            }
            if (buff.getId() == 6) { // Assuming buff ID 12 is for defense down
                hungerDown += buff.getStack();
            }
            if (buff.getId() == 25) { // Assuming buff ID 30 is for defense increase
                hungerIncrease += buff.getStack();
            }
            if (buff.getId() == 26) { // Assuming buff ID 31 is for defense decrease
                hungerDecrease += buff.getStack();
            }
        }
        int additionLayer = hungerIncrease - hungerDecrease;
        float multiplyLayer = hungerUp - hungerDown;
        int currentHunger = (int)((baseHunger + additionLayer) * (1 + multiplyLayer/100));
        return Math.max(1, currentHunger);
    }
    public int CalculateHydration() {
        int baseHydration = ((Player) character).getHydration();
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseHydration;
        }

        int hydrationUp = 0;
        int hydrationDown = 0;
        int hydrationIncrease = 0;
        int hydrationDecrease = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 3) { // Assuming buff ID 11 is for defense up
                hydrationUp += buff.getStack();
            }
            if (buff.getId() == 4) { // Assuming buff ID 12 is for defense down
                hydrationDown += buff.getStack();
            }
            if (buff.getId() == 23) { // Assuming buff ID 30 is for defense increase
                hydrationIncrease += buff.getStack();
            }
            if (buff.getId() == 24) { // Assuming buff ID 31 is for defense decrease
                hydrationDecrease += buff.getStack();
            }
        }
        int additionLayer = hydrationIncrease - hydrationDecrease;
        float multiplyLayer = hydrationUp - hydrationDown;
        int currentHydration = (int)((baseHydration + additionLayer) * (1 + multiplyLayer/100));
        return Math.max(1, currentHydration);
    }
    public float CalculateWeight() {
        float baseWeight = ((Player) character).getMaxWeight();
        if (character == null) {
            return 0;
        }
        float equipmentWeight = statFromEquipment("maxWeight");
        baseWeight += equipmentWeight;

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseWeight;
        }

        int weightUp = 0;
        int weightDown = 0;
        int weightIncrease = 0;
        int weightDecrease = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 7) { // Assuming buff ID 11 is for defense up
                weightUp += buff.getStack();
            }
            if (buff.getId() == 8) { // Assuming buff ID 12 is for defense down
                weightDown += buff.getStack();
            }
            if (buff.getId() == 27) { // Assuming buff ID 30 is for defense increase
                weightIncrease += buff.getStack();
            }
            if (buff.getId() == 28) { // Assuming buff ID 31 is for defense decrease
                weightDecrease += buff.getStack();
            }
        }
        int additionLayer = weightIncrease - weightDecrease;
        float multiplyLayer = weightUp - weightDown;
        float currentWeight = (int)((baseWeight + additionLayer) * (1 + multiplyLayer/100));
        return Math.max(0, currentWeight);
    }


    public int CalculateSpeed() {
        int baseSpeed = character.getSpeed();
        if (character instanceof Player) {
            int equipmentSpeed = statFromEquipment("speed");
            baseSpeed += equipmentSpeed;
        }
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseSpeed;
        }

        int speedIncrease = 0;
        int speedDecrease = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 32) { // Assuming buff ID 32 is for speed increase
                speedIncrease += buff.getStack();
            }
            if (buff.getId() == 33) { // Assuming buff ID 33 is for speed decrease
                speedDecrease += buff.getStack();
            }
        }
        int additionLayer = speedIncrease - speedDecrease;
        int currentSpeed = (baseSpeed + additionLayer);
        return Math.max(1,currentSpeed);
    }

    public int CalculateLifeSteal() {
        int baseLifeSteal = character.getLifeSteal();
        if (character instanceof Player) {
            int equipmentLifeSteal = statFromEquipment("lifeSteal");
            baseLifeSteal += equipmentLifeSteal;
        }
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseLifeSteal;
        }

        int lifeStealUp = 0;
        int lifeStealDown = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 13) { // Assuming buff ID 15 is for lifeSteal up
                lifeStealUp += buff.getStack();
            }
            if (buff.getId() == 14) { // Assuming buff ID 16 is for lifeSteal down
                lifeStealDown += buff.getStack();
            }
        }
        int additionLayer = lifeStealUp - lifeStealDown;
        int currentLifeSteal = (baseLifeSteal + additionLayer);
        return Math.max(0,currentLifeSteal);
    }

    public int CalculateDamageReduction() {
        int baseDamageReduction = character.getDamageReduction();
        if (character instanceof Player) {
            int equipmentDamageReduction = statFromEquipment("damageReduction");
            baseDamageReduction += equipmentDamageReduction;
        }
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseDamageReduction;
        }

        int damageReductionUp = 0;
        int damageReductionDown = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 15) { // Assuming buff ID 17 is for damageReduction up
                damageReductionUp += buff.getStack();
            }
            if (buff.getId() == 16) { // Assuming buff ID 18 is for damageReduction down
                damageReductionDown += buff.getStack();
            }
        }
        int additionLayer = damageReductionUp - damageReductionDown;
        int currentDamageReduction = (baseDamageReduction + additionLayer);
        return Math.min(Math.max(0,currentDamageReduction),90);
    }

    public int CalculateMaxHealth() {
        int baseMaxHealth = character.getMaxHealth();
        if (character instanceof Player) {
            int equipmentMaxHealth = statFromEquipment("maxHealth");
            baseMaxHealth += equipmentMaxHealth;
        }
        if (character == null) {
            return 0;
        }

        ArrayList<Buff> characterBuffs = character.getCurrentBuffs();
        if (characterBuffs == null) {
            return baseMaxHealth;
        }

        int maxHealthUp = 0;
        int maxHealthDown = 0;
        int maxHealthIncrease = 0;
        int maxHealthDecrease = 0;
        for (Buff buff : characterBuffs) {
            if (buff.getId() == 1) { // Assuming buff ID 19 is for maxHealth up
                maxHealthUp += buff.getStack();
            }
            if (buff.getId() == 2) { // Assuming buff ID 20 is for maxHealth down
                maxHealthDown += buff.getStack();
            }
            if (buff.getId() == 22) { // Assuming buff ID 38 is for maxHealth increase
                maxHealthIncrease += buff.getStack();
            }
            if (buff.getId() == 23) { // Assuming buff ID 39 is for maxHealth decrease
                maxHealthDecrease += buff.getStack();
            }
        }
        int additionLayer = maxHealthIncrease - maxHealthDecrease;
        float multiplyLayer = maxHealthUp - maxHealthDown;
        int currentMaxHealth =(int)((baseMaxHealth + additionLayer) * (1 + multiplyLayer/100));
        return Math.max(1,currentMaxHealth);
    }

    private int statFromEquipment(String attribute) {
        int stat = 0;
        for (Equipment equipment : equipments) {
            if (equipment == null) {
                continue; // Skip if equipment is null
            }

            switch (attribute) {
                case "attack":
                    stat += equipment.getAttack();
                    break;
                case "defense":
                    stat += equipment.getDefense();
                    break;
                case "speed":
                    stat += equipment.getSpeed();
                    break;
                case "lifeSteal":
                    stat += equipment.getLifeSteal();
                    break;
                case "damageReduction":
                    stat += equipment.getDamageReduction();
                    break;
                case "maxHealth":
                    stat += equipment.getMaxHealth();
                    break;
                case "maxWeight":
                    stat += equipment.getMaxWeight();
                    break;
                default:
                    // Optional: handle the case where attribute is not recognized
                    throw new IllegalArgumentException("Unknown attribute: " + attribute);
            }
        }
        return stat;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
        if (character instanceof Player) {
            equipments = new ArrayList<Equipment>();
            equipments.add(((Player) character).getWeapon());
            equipments.add(((Player) character).getArmor());
            equipments.add(((Player) character).getTool());
            equipments.add(((Player) character).getBag());
            Collections.addAll(equipments, ((Player) character).getAmulet());
        }
    }
}
