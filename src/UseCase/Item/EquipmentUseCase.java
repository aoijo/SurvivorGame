package UseCase.Item;

import Entity.Character.Player;
import Entity.Item.Equipment;
import Entity.Skills.Skill;
import Enums.Item.EquipmentType;
import Enums.Item.ItemType;
import Enums.Rarity;
import UseCase.BuffUseCase;
import UseCase.SkillUseCase;
import Utils.ReadCSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EquipmentUseCase {
    private SkillUseCase skillUseCase;
    private BuffUseCase buffUseCase;
    private String[][] equipmentData;
    private Random random;

    private static final String[] MYTHICAL_BUFF_NAMES = {
            "Healing", "Berserk", "Luxury", "Vampire", "Protector", "Sturdy", "Madness", "Immune"
    };

    public EquipmentUseCase(SkillUseCase skillUseCase, BuffUseCase buffUseCase) {
        this.skillUseCase = skillUseCase;
        this.buffUseCase = buffUseCase;
        this.random = new Random();
        loadEquipmentData();
    }

    private void loadEquipmentData() {
        this.equipmentData = ReadCSV.read("Data/Item/Equipment.csv");
    }

    public Equipment forgeEquipment(int forgeExperience, int equipmentId) {
        Equipment equipment = newEquipment(equipmentId);
        if (equipment == null) {
            return null;
        }
        setStatsFloat(equipment);
        determineRarity(forgeExperience, equipment);
        addRarityStats(equipment);
        return equipment;
    }

    private void addRarityStats(Equipment equipment) {
        switch (equipment.getRarity()) {
            case UNCOMMON -> {
                if (random.nextBoolean()) {
                    equipment.setAttack((int) (Math.max(equipment.getAttack(),1) * (1.2f + random.nextFloat() * 0.2f)));
                } else {
                    equipment.setDefense((int) (Math.max(equipment.getDefense(),1) * (1.2f + random.nextFloat() * 0.2f)));
                }
            }
            case RARE -> {
                if (random.nextBoolean()) {
                    if (random.nextBoolean()) {
                        equipment.setAttack((int) (Math.max(1,equipment.getAttack()) * (1.4f + random.nextFloat() * 0.2f)));
                    } else {
                        equipment.setDefense((int) (equipment.getDefense() * (1.4f + random.nextFloat() * 0.2f)));
                    }
                } else {
                    equipment.setAttack((int) (Math.max(1,equipment.getAttack()) * (1.2f + random.nextFloat() * 0.2f)));
                    equipment.setDefense((int) (Math.max(1,equipment.getDefense()) * (1.2f + random.nextFloat() * 0.2f)));
                }
            }
            case LEGENDARY -> {
                if (random.nextBoolean()) {
                    if (random.nextBoolean()) {
                        equipment.setAttack((int) (Math.max(1,equipment.getAttack()) * (1.4f + random.nextFloat() * 0.2f)));
                        equipment.setLifeSteal((int) (equipment.getLifeSteal() + (5f + random.nextFloat() * 10f)));
                    } else {
                        equipment.setDefense((int) (Math.max(1,equipment.getDefense()) * (1.4f + random.nextFloat() * 0.2f)));
                        equipment.setDamageReduction((int) (equipment.getDamageReduction() + (5f + random.nextFloat() * 5f)));
                    }
                } else {
                    equipment.setAttack((int) (Math.max(1,equipment.getAttack()) * (1.4f + random.nextFloat() * 0.2f)));
                    equipment.setDefense((int) (Math.max(1,equipment.getDefense()) * (1.4f + random.nextFloat() * 0.2f)));
                }
            }
            case MYTHICAL -> {
                if (random.nextBoolean()) {
                    if (random.nextBoolean()) {
                        equipment.setAttack((int) (Math.max(1,equipment.getAttack()) * (1.4f + random.nextFloat() * 0.2f)));
                        equipment.setLifeSteal((int) (equipment.getLifeSteal() + (5f + random.nextFloat() * 10f)));
                    } else {
                        equipment.setDefense((int) (Math.max(1,equipment.getDefense()) * (1.4f + random.nextFloat() * 0.2f)));
                        equipment.setDamageReduction((int) (equipment.getDamageReduction() + (5f + random.nextFloat() * 5f)));
                    }
                } else {
                    equipment.setAttack((int) (Math.max(1,equipment.getAttack()) * (1.4f + random.nextFloat() * 0.2f)));
                    equipment.setDefense((int) (Math.max(1,equipment.getDefense()) * (1.4f + random.nextFloat() * 0.2f)));
                }
                addMythicalBuffs(equipment);
            }
        }
    }

    private void addMythicalBuffs(Equipment equipment) {
        int mythicalBuffIndex = random.nextInt(MYTHICAL_BUFF_NAMES.length);
        String mythicalBuffName = MYTHICAL_BUFF_NAMES[mythicalBuffIndex];
        equipment.setName(equipment.getName() + " of " + mythicalBuffName);

        switch (mythicalBuffName) {
            case "Healing" -> {
                gainSkill(equipment, 3,-1);
            }
            case "Berserk" -> {
                gainBuff(equipment, 9, 100);
                gainBuff(equipment, 12, 100);
            }
            case "Luxury" -> {
                equipment.setPrice(equipment.getPrice() * 10);
                equipment.setSinglePrice(equipment.getSinglePrice() * 10);
            }
            case "Vampire" -> {
                equipment.setLifeSteal(equipment.getLifeSteal() + 20);
            }
            case "Protector" -> {
                gainBuff(equipment, 10, 500);
                gainBuff(equipment, 15, 50);
            }
            case "Sturdy" -> {
                equipment.setDurability(equipment.getDurability() * 5);
                equipment.setMaxDurability(equipment.getMaxDurability() * 5);
            }
            case "Madness" -> {
                gainSkill(equipment, 8,-1);
                gainBuff(equipment, 40, 15);
            }
            case "Immune" -> {
                gainBuff(equipment, 18, 100);
                gainBuff(equipment, 37, 1);
            }
        }
    }

    private void determineRarity(int forgeExperience, Equipment equipment) {
        float minimalChance = 0.05f;
        float maxMythical = 0.1f;
        float maxLegendary = 0.4f;
        float maxRare = 0.6f;
        float maxUncommon = 0.8f;
        float forgeMultiplier = 1 + (17f * (float) Math.log(forgeExperience) / (float) Math.log(10));

        float mythicalChance = Math.min(maxMythical, forgeMultiplier / 1000);
        mythicalChance = Math.max(minimalChance / 10, mythicalChance);

        float legendaryChance = Math.min(maxLegendary, forgeMultiplier / 190);
        legendaryChance = Math.max(minimalChance / 2, legendaryChance);

        float rareChance = Math.min(maxRare, forgeMultiplier / 110);
        rareChance = Math.max(minimalChance, rareChance);

        float uncommonChance = Math.min(maxUncommon, forgeMultiplier / 75);
        uncommonChance = Math.max(minimalChance, uncommonChance);

        float randomValue = random.nextFloat();
        if (randomValue < mythicalChance) {
            equipment.setRarity(Rarity.MYTHICAL);
        } else if (randomValue < legendaryChance) {
            equipment.setRarity(Rarity.LEGENDARY);
        } else if (randomValue < rareChance) {
            equipment.setRarity(Rarity.RARE);
        } else if (randomValue < uncommonChance) {
            equipment.setRarity(Rarity.UNCOMMON);
        } else {
            equipment.setRarity(Rarity.COMMON);
        }
    }

    private void setStatsFloat(Equipment equipment) {
        float attackFloat = random.nextFloat() * 0.4f - 0.2f;
        float defenseFloat = random.nextFloat() * 0.4f - 0.2f;
        float healthFloat = random.nextFloat() * 0.4f - 0.2f;
        float lifeStealFloat = random.nextFloat() * 0.4f - 0.2f;
        float damageReduceFloat = random.nextFloat() * 0.4f - 0.2f;

        equipment.setAttack((int) (equipment.getAttack() * (1 + attackFloat)));
        equipment.setDefense((int) (equipment.getDefense() * (1 + defenseFloat)));
        equipment.setMaxHealth((int) (equipment.getMaxHealth() * (1 + healthFloat)));
        equipment.setLifeSteal((int) (equipment.getLifeSteal() * (1 + lifeStealFloat)));
        equipment.setDamageReduction((int) (equipment.getDamageReduction() * (1 + damageReduceFloat)));
    }

    public Equipment newEquipment(int equipmentId) {
        if (equipmentId == 0 || equipmentId >= equipmentData.length || equipmentData[equipmentId] == null) {
            return null;
        }

        Equipment equipment = new Equipment(equipmentId);
        String[] equipData = this.equipmentData[equipmentId];

        equipment.setName(equipData[1]);
        equipment.setDescription(equipData[2]);
        equipment.setSinglePrice(Integer.parseInt(equipData[3]));
        equipment.setSingleWeight(Integer.parseInt(equipData[4]));
        equipment.setSingleDust(Integer.parseInt(equipData[5]));
        equipment.setLevelRequirement(Integer.parseInt(equipData[6]));
        equipment.setAttack(Integer.parseInt(equipData[7]));
        equipment.setDefense(Integer.parseInt(equipData[8]));
        equipment.setMaxHealth(Integer.parseInt(equipData[9]));
        equipment.setLifeSteal(Integer.parseInt(equipData[10]));
        equipment.setDamageReduction(Integer.parseInt(equipData[11]));
        equipment.setSpeed(Integer.parseInt(equipData[12]));
        equipment.setMaxWeight(Integer.parseInt(equipData[13]));
        equipment.setMaxDurability(Integer.parseInt(equipData[14]));
        equipment.setEquipmentType(determineEquipmentType(Integer.parseInt(equipData[18])));

        equipment.setPrice(equipment.getSinglePrice());
        equipment.setWeight(equipment.getSingleWeight());
        equipment.setDust(equipment.getSingleDust());
        equipment.setDurability(equipment.getMaxDurability());
        equipment.setRarity(Rarity.COMMON);
        equipment.setItemType(ItemType.EQUIPMENT);

        int[] durability = new int[ReadCSV.readIntList(equipData[15]).length];
        Arrays.fill(durability,Integer.MAX_VALUE);
        gainSkills(equipment, ReadCSV.readIntList(equipData[15]), durability);
        gainBuffs(equipment, ReadCSV.readIntList(equipData[16]), ReadCSV.readIntList(equipData[17]));
        return equipment;
    }

    private EquipmentType determineEquipmentType(int equipmentTypeId) {
        return switch (equipmentTypeId) {
            case 1 -> EquipmentType.ARMOR;
            case 2 -> EquipmentType.WEAPON;
            case 3 -> EquipmentType.AMULET;
            case 4 -> EquipmentType.BAG;
            case 5 -> EquipmentType.TOOL;
            default -> {
                System.out.println("Invalid equipment Type Id: " + equipmentTypeId);
                yield null;
            }
        };
    }

    private void gainBuff(Equipment equipment, int buffId, int buffStack) {
        buffUseCase.equipmentGainBuff(buffId, buffStack, equipment,Float.POSITIVE_INFINITY, Integer.MAX_VALUE);
    }

    private void gainBuffs(Equipment equipment, int[] buffIds, int[] buffStacks) {
        if (buffIds.length == 0) {
            return;
        }
        //System.out.println(equipment.getName());
        for (int i = 0; i < buffIds.length; i++) {
            gainBuff(equipment, buffIds[i], buffStacks[i]);
        }
    }

    private void gainSkill(Equipment equipment, int skillId, int durability) {
        ArrayList<Skill> skills = equipment.getSkills();
        if (skills == null) {
            skills = new ArrayList<>();
            equipment.setSkills(skills);
        }
        if (skillId <= 0){
            return;
        }
        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return; // Skill already exists, no need to add
            }
        }

        // Skill does not exist, add new skill
        skills.add(skillUseCase.newSkill(skillId, durability));
    }

    private void gainSkills(Equipment equipment, int[] skillIds, int[] durability) {
        if (skillIds.length == 0) {
            return;
        }
        for (int i = 0; i < skillIds.length; i++) {
            gainSkill(equipment, skillIds[i],durability[i]);
        }
    }

    public void setSkillUseCase(SkillUseCase skillUseCase) {
        this.skillUseCase = skillUseCase;
    }

    public void setBuffUseCase(BuffUseCase buffUseCase) {
        this.buffUseCase = buffUseCase;
    }
    public String[][] getEquipmentData() {
        return equipmentData;
    }
}
