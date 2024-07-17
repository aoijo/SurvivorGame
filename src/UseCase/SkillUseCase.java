package UseCase;

import Entity.Skills.Skill;
import Enums.Rarity;
import Utils.ReadCSV;

public class SkillUseCase {
    private String[][] SkillData;

    public SkillUseCase() {
        loadSkillData();
    }

    private void loadSkillData() {
        this.SkillData = ReadCSV.read("Data/Skill.csv");
    }

    public Skill newSkill(int skillId){
        Skill skill = new Skill(skillId);
        initialize(skill,skillId);
        return skill;
    }

    private void initialize(Skill skill, int skillId) {
        if(skillId == 0){ return;}
        String[] skillData = SkillData[skillId];

        skill.setName(skillData[1]);
        skill.setDescription(skillData[2]);
        skill.setGeneral(Integer.parseInt(skillData[3]) == 1);
        skill.setCombat(Integer.parseInt(skillData[4]) == 1);
        skill.setPowerLevel(Float.parseFloat(skillData[5]));
        skill.setMaxCooldown(Integer.parseInt(skillData[6]));
        skill.setMaxWorldCooldown(Integer.parseInt(skillData[7]));
        skill.setUsage(Integer.parseInt(skillData[8]));
        skill.setRaceRequirement(ReadCSV.readIntList(skillData[9]));
        skill.setRarity(determineRarity(Integer.parseInt(skillData[10])));
    }
    private Rarity determineRarity(int rarityId){
        switch (rarityId){
            case 1: return Rarity.COMMON;
            case 2: return Rarity.UNCOMMON;
            case 3: return Rarity.RARE;
            case 4: return Rarity.LEGENDARY;
            case 5: return Rarity.MYTHICAL;
            default: System.out.println("Invalid rarityId");
        }
        return null;
    }
}
