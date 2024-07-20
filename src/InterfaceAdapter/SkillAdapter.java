package InterfaceAdapter;

import Enums.Rarity;
import UseCase.PlayerUseCase;

public class SkillAdapter {
    private PlayerUseCase playerUseCase;

    public SkillAdapter(UseCaseManager useCaseManager) {
        this.playerUseCase = useCaseManager.getPlayerUseCase();
    }

    public int getPlayerSkillIdByIndex(int index){
        return playerUseCase.getPlayer().getSkills().get(index).getId();
    }

    public String getPlayerSkillNameByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getName();
    }

    public String getPlayerSkillDescriptionByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getDescription();
    }

    public float getPlayerSkillPowerLevelByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getPowerLevel();
    }

    public int getPlayerSkillCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getCooldown();
    }

    public float getPlayerSkillWorldCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getWorldCooldown();
    }

    public int getPlayerSkillMaxCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getMaxCooldown();
    }

    public int getPlayerSkillMaxWorldCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getMaxWorldCooldown();
    }

    public int getPlayerSkillUsageByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getUsage();
    }

    public boolean isPlayerSkillGeneralByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).isGeneral();
    }

    public boolean isPlayerSkillCombatByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).isCombat();
    }

    public int[] getPlayerSkillRaceRequirementByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getRaceRequirement();
    }

    public Rarity getPlayerSkillRarityByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getRarity();
    }
}
