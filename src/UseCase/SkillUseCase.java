package UseCase;

import Entity.Skill;
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
    }
}
