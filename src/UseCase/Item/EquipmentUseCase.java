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
import java.util.Random;

public class EquipmentUseCase {
    private SkillUseCase skillUseCase;
    private BuffUseCase buffUseCase;
    private String[][] equipmentData;

    private String[] mythicalBuffNames = new String[]{
            "Healing", "Berserk", "Luxury", "Vampire", "Protector", "Sturdy", "Madness", "Immune"
    };

    public EquipmentUseCase(SkillUseCase skillUseCase, BuffUseCase buffUseCase){
        this.skillUseCase = skillUseCase;
        this.buffUseCase = buffUseCase;
        loadEquipmentData();
    }


    private void loadEquipmentData() {
        this.equipmentData = ReadCSV.read("Data/Item/Equipment.csv");
    }

    public Equipment forgeEquipment(int forgeExperience, int equipmentId){
        Equipment equipment = newEquipment(equipmentId);
        setStatsFloat(equipment);
        determineRarity(forgeExperience, equipment);
        addRarityStats(equipment);
        return equipment;
    }

    public void addRarityStats(Equipment equipment){
        Random rand = new Random();
        switch(equipment.getRarity()){
            case UNCOMMON:{
                if(rand.nextBoolean()){
                    equipment.setAttack((int)(equipment.getAttack() * (1.1f + rand.nextFloat() * 0.1f)));
                } else {
                    equipment.setDefense((int)(equipment.getDefense() * (1.1f + rand.nextFloat() * 0.1f)));
                }

            }
            case RARE:{
                if(rand.nextBoolean()){
                    if(rand.nextBoolean()){
                        equipment.setAttack((int)(equipment.getAttack() * (1.2f + rand.nextFloat() * 0.2f)));
                    } else {
                        equipment.setDefense((int)(equipment.getDefense() * (1.2f + rand.nextFloat() * 0.2f)));
                    }
                } else {
                    equipment.setAttack((int)(equipment.getAttack() * (1.1f + rand.nextFloat() * 0.1f)));
                    equipment.setDefense((int)(equipment.getDefense() * (1.1f + rand.nextFloat() * 0.1f)));
                }
            }
            case LEGENDARY:{
                if(rand.nextBoolean()){
                    if(rand.nextBoolean()){
                        equipment.setAttack((int)(equipment.getAttack() * (1.2f + rand.nextFloat() * 0.2f)));
                        equipment.setLifeSteal((int)(equipment.getLifeSteal() * (5f + rand.nextFloat() * 10f)));
                    } else {
                        equipment.setDefense((int)(equipment.getDefense() * (1.2f + rand.nextFloat() * 0.2f)));
                        equipment.setDamageReduction((int)(equipment.getDamageReduction() * (5f + rand.nextFloat() * 5f)));
                    }
                } else {
                    equipment.setAttack((int)(equipment.getAttack() * (1.2f + rand.nextFloat() * 0.2f)));
                    equipment.setDefense((int)(equipment.getDefense() * (1.2f + rand.nextFloat() * 0.2f)));
                }
            }
            case MYTHICAL:{
                if(rand.nextBoolean()){
                    if(rand.nextBoolean()){
                        equipment.setAttack((int)(equipment.getAttack() * (1.2f + rand.nextFloat() * 0.2f)));
                        equipment.setLifeSteal((int)(equipment.getLifeSteal() * (5f + rand.nextFloat() * 10f)));
                    } else {
                        equipment.setDefense((int)(equipment.getDefense() * (1.2f + rand.nextFloat() * 0.2f)));
                        equipment.setDamageReduction((int)(equipment.getDamageReduction() * (5f + rand.nextFloat() * 5f)));
                    }
                } else {
                    equipment.setAttack((int)(equipment.getAttack() * (1.2f + rand.nextFloat() * 0.2f)));
                    equipment.setDefense((int)(equipment.getDefense() * (1.2f + rand.nextFloat() * 0.2f)));
                }
                addMythicalBuffs(equipment);
            }
        }
    }
    public void addMythicalBuffs(Equipment equipment){
        Random rand = new Random();
        int mythicalBuff = rand.nextInt(mythicalBuffNames.length);
        equipment.setName(equipment.getName() + "of" + mythicalBuffNames[mythicalBuff]);
        switch (mythicalBuffNames[mythicalBuff]){
            case "Healing":{
                gainSkill(equipment,3);
                break;
            }
            case "Berserk":{
                gainBuff(equipment,9,100);
                gainBuff(equipment,12,100);
            }
            case "Luxury":{
                equipment.setPrice(equipment.getPrice() * 10);
                equipment.setSinglePrice(equipment.getSinglePrice() * 10);
            }
            case "Vampire":{
                equipment.setLifeSteal(equipment.getLifeSteal() + 20);
            }
            case "Protector":{
                gainBuff(equipment,10,500);
                gainBuff(equipment,14,50);
            }
            case "Sturdy":{
                equipment.setDurability(equipment.getDurability() * 5);
            }
            case "Madness":{
                gainSkill(equipment,8);
                gainBuff(equipment,37,15);
            }
            case "Immune":{
                gainBuff(equipment ,17,100);
                gainBuff(equipment,34,1);
            }
        }

    }
    public void determineRarity(int forgeExperience, Equipment equipment){
        Random rand = new Random();

        float minimalChance = 0.05f;
        float maxMythical = 0.1f;
        float maxLegendary = 0.4f;
        float maxRare = 0.6f;
        float maxUncommon = 0.8f;
        float forgeMultiplier = 1 + (17f * (float)Math.log(forgeExperience) / (float)Math.log(10));

        float mythicalChance =Math.min(maxMythical,forgeMultiplier/1000);
        mythicalChance = Math.max(minimalChance/10,mythicalChance);

        float legendaryChance =Math.min(maxLegendary,forgeMultiplier/190);
        legendaryChance = Math.max(minimalChance/2,legendaryChance);

        float rareChance =Math.min(maxRare,forgeMultiplier/110);
        rareChance = Math.max(minimalChance,rareChance);

        float uncommonChance =Math.min(maxUncommon,forgeMultiplier/75);
        uncommonChance = Math.max(minimalChance,uncommonChance);

        if (rand.nextFloat() < mythicalChance){
            equipment.setRarity(Rarity.MYTHICAL);
        } else if (rand.nextFloat() < legendaryChance){
            equipment.setRarity(Rarity.LEGENDARY);
        } else if (rand.nextFloat() < rareChance){
            equipment.setRarity(Rarity.RARE);
        } else if (rand.nextFloat() < uncommonChance){
            equipment.setRarity(Rarity.UNCOMMON);
        } else{equipment.setRarity(Rarity.COMMON);}
    }
    public void setStatsFloat(Equipment equipment){
        Random rand = new Random();
        float attackFloat = (float) (rand.nextFloat() * 0.4 - 0.2);
        float defenseFloat = (float) (rand.nextFloat() * 0.4 - 0.2);
        float healthFloat = (float) (rand.nextFloat() * 0.4 - 0.2);
        float lifeStealFloat = (float) (rand.nextFloat() * 0.4 - 0.2);
        float damageReduceFloat = (float) (rand.nextFloat() * 0.4 - 0.2);
        equipment.setAttack((int)(equipment.getAttack() * attackFloat));
        equipment.setDefense((int)(equipment.getDefense() * defenseFloat));
        equipment.setMaxHealth((int)(equipment.getMaxHealth() * healthFloat));
        equipment.setLifeSteal((int)(equipment.getLifeSteal() * lifeStealFloat));
        equipment.setDamageReduction((int)(equipment.getDamageReduction() * damageReduceFloat));
    }

    public Equipment newEquipment(int equipmentId) {
        if (equipmentId == 0){return null;}

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
        equipment.setMaxWeight(Integer.parseInt(equipData[12]));
        equipment.setDurability(Integer.parseInt(equipData[13]));
        gainSkills(equipment,ReadCSV.readIntList(equipData[14]));
        gainBuffs(equipment,ReadCSV.readIntList(equipData[15]), ReadCSV.readIntList(equipData[16]));
        equipment.setEquipmentType(determineEquipmentType(Integer.parseInt(equipData[17])));

        equipment.setRarity(Rarity.COMMON);
        equipment.setItemType(ItemType.EQUIPMENT);
        return equipment;

    }

    public void setSkillUseCase(SkillUseCase skillUseCase) {
        this.skillUseCase = skillUseCase;
    }
    public void setBuffUseCase(BuffUseCase buffUseCase) {
        this.buffUseCase = buffUseCase;
    }
    private EquipmentType determineEquipmentType(int equipmentTypeId){
        switch(equipmentTypeId){
            case 1 -> {return EquipmentType.ARMOR;}
            case 2 -> {return EquipmentType.WEAPON;}
            case 3 -> {return EquipmentType.AMULET;}
            case 4 -> {return EquipmentType.BAG;}
            case 5 -> {return EquipmentType.TOOL;}
            default -> System.out.println("Invalid equipment Type Id");
        }
        return null;
    }
    private void gainBuff(Equipment equipment, int buffId, int buffStack){
        buffUseCase.equipmentGainBuff(buffId,buffStack,equipment);
    }
    private void gainBuffs(Equipment equipment, int[] buffId, int[] buffStack){
        for (int i = 0; i < buffStack.length; i++){
            gainBuff(equipment,buffId[i],buffStack[i]);
        }
    }
    private void gainSkill(Equipment equipment, int skillId) {
        ArrayList<Skill> skills = equipment.getSkills();
        if (skills == null) {
            skills = new ArrayList<>();
            equipment.setSkills(skills);
        }

        for (Skill skill : skills) {
            if (skill.getId() == skillId) {
                return; // Skill already exists, no need to add
            }
        }

        // Skill does not exist, add new skill
        equipment.getSkills().add(skillUseCase.newSkill(skillId));
    }
    private void gainSkills(Equipment equipment, int[] skillIds){
        for (int skillId : skillIds) {
            gainSkill(equipment, skillId);
        }
    }
}
