package UseCase;

import Entity.Character.Character;
import Entity.Skills.Skill;
import Enums.Rarity;
import Enums.SkillType;
import Utils.ReadCSV;

import java.util.ArrayList;

public class SkillUseCase {
    private String[][] SkillData;
    private SingleSkillUseCase singleSkillUseCase;

    public SkillUseCase(SingleSkillUseCase singleSkillUseCase) {
        this.singleSkillUseCase = singleSkillUseCase;
        loadSkillData();
    }

    private void loadSkillData() {
        this.SkillData = ReadCSV.read("Data/Skill.csv");
    }

    public Skill newSkill(int skillId, int durability){
        Skill skill = new Skill(skillId);
        initialize(skill,skillId,durability);
        return skill;
    }

    private void initialize(Skill skill, int skillId, int durability) {
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
        skill.setActive(Integer.parseInt(skillData[11]) == 1);
        skill.setSkillType(determineSkillType(ReadCSV.readIntList(skillData[12])));

        skill.setDurability(durability);
    }
    private Rarity determineRarity(int rarityId){
        return switch (rarityId) {
            case 1 -> Rarity.COMMON;
            case 2 -> Rarity.UNCOMMON;
            case 3 -> Rarity.RARE;
            case 4 -> Rarity.LEGENDARY;
            case 5 -> Rarity.MYTHICAL;
            default -> throw new IllegalArgumentException("Invalid rarity ID" + rarityId);
        };
    }
    private SkillType[] determineSkillType(int[] skillIds){
        SkillType[] skillTypes = new SkillType[skillIds.length];
        for (int i = 0; i < skillIds.length; i++) {
            switch (skillIds[i]){
                case 1: skillTypes[i] = SkillType.DAMAGE; break;
                case 2: skillTypes[i] = SkillType.RESTORE; break;
                case 3: skillTypes[i] = SkillType.BUFF; break;
                case 4: skillTypes[i] = SkillType.WEAKEN; break;
                case 5: skillTypes[i] = SkillType.BUFF_REMOVE; break;
                case 6: skillTypes[i] = SkillType.WEAKEN_REMOVE; break;
                case 7: skillTypes[i] = SkillType.NEUTRAL; break;
                case 8: skillTypes[i] = SkillType.ESCAPE; break;
                case 9: skillTypes[i] = SkillType.OTHER; break;
                default: throw new IllegalArgumentException("Invalid skillId" + skillIds[i]);
            }
        }
        return skillTypes;
    }
    public void useSkill(Character source, Character target, Skill skill){
        skill.setWorldCooldown(skill.getMaxWorldCooldown());
        skill.setCooldown(skill.getMaxCooldown());
        if (skill.getDurability() > 0){
            if(skill.getDurability() < Integer.MAX_VALUE){
                skill.setDurability(skill.getDurability() - 1);
            }
            singleSkillUseCase.useSingleSkill(source,target, skill.getId(), skill.getName());
        }
        removeSkillWithNoDurability(source);
    }
    private void removeSkillWithNoDurability(Character character){
        ArrayList<Skill> usedSkills = new ArrayList<>();
        for (Skill skill : character.getSkills()) {
            if(skill.getDurability() == 0){
                usedSkills.add(skill);
            }
        }
        for (Skill skill : usedSkills) {
            character.getSkills().remove(skill);
        }
    }
    public void skillPassTurn(Character character){
        for (Skill skill : character.getSkills()) {
            if(skill.getCooldown() > 0){
                skill.setCooldown(skill.getCooldown() - 1);
            }
        }
    }
    public void skillPassTime(Character character, int minute){
        for (Skill skill : character.getSkills()) {
            if(skill.getWorldCooldown() > 0){
                skill.setWorldCooldown(Math.max(skill.getWorldCooldown() - minute,0));
            }
        }
    }
    public SingleSkillUseCase getSingleSkillUseCase(){
        return singleSkillUseCase;
    }
}
